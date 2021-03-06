package com.cy.ruoyi.admin.sys.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cy.ruoyi.admin.sys.base.entity.SysUser;
import com.cy.ruoyi.admin.sys.base.service.ISysMenuService;
import com.cy.ruoyi.admin.sys.base.service.ISysUserService;
import com.cy.ruoyi.common.auth.annotation.HasPermissions;
import com.cy.ruoyi.common.auth.constants.UserConstants;
import com.cy.ruoyi.common.core.basic.controller.BaseController;
import com.cy.ruoyi.common.log.annotation.OperLog;
import com.cy.ruoyi.common.log.enums.BusinessType;
import com.cy.ruoyi.common.sql.page.PageDomain;
import com.cy.ruoyi.common.sql.page.PageUtils;
import com.cy.ruoyi.common.utils.annotation.LoginUser;
import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import com.cy.ruoyi.common.utils.util.PasswordUtil;
import com.cy.ruoyi.common.utils.util.R;
import com.cy.ruoyi.common.utils.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户提供者
 */
@RestController
@RequestMapping("user")
@Api(value = "SysUserController",description = "用户")
public class SysUserController extends BaseController
{
    private static final Log log = LogFactory.get();

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询用户
     */
    @GetMapping("get/{userId}")
    @ApiOperation(value = "查询用户")
    @SentinelResource("get/{userId}")
    public SysUser get(@PathVariable("userId") Long userId)
    {
        return sysUserService.selectUserById(userId);
    }

    @GetMapping("info")
    @ApiOperation(value = "查询信息")
    @SentinelResource("info")
    public SysUser info(@LoginUser SysUser user)
    {
        user.setButtons(sysMenuService.selectPermsByUserId(user.getUserId()));
        return user;
    }

    /**
     * 查询用户
     */
    @GetMapping("find/{username}")
    @ApiOperation(value = "查询用户")
    @SentinelResource("find/{username}")
    public SysUser findByUsername(@PathVariable("username") String username)
    {
        return sysUserService.selectUserByLoginName(username);
    }

    /**
     * 查询用户列表
     */
    @GetMapping("list")
    @ApiOperation(value = "分页用户列表")
    @SentinelResource("list")
    public R list(SysUser sysUser)
    {
        PageDomain pageDomain = getPageInfo();
        log.info("开始查询第[{}]页[{}]条的数据!",pageDomain.getPageNum(), pageDomain.getPageSize());
        PageUtils page = sysUserService.selectUserList(pageDomain, sysUser);
        return R.ok(page);
    }

    /**
     * 新增保存用户
     */
    @HasPermissions("system:user:add")
    @PostMapping("save")
    @OperLog(title = "用户管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增保存用户")
    @SentinelResource("save")
    public R addSave(@RequestBody SysUser sysUser)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(sysUser.getLoginName())))
        {
            return R.error(sysUser.getLoginName() + TradeErrorEnum.USER_ADD_USERNAME_REPEAT_ERROR.msg);
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser)))
        {
            return R.error(sysUser.getLoginName() + TradeErrorEnum.USER_ADD_PHONE_REPEAT_ERROR.msg);
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser)))
        {
            return R.error(sysUser.getLoginName() + TradeErrorEnum.USER_ADD_MAIL_REPEAT_ERROR.msg);
        }
        sysUser.setSalt(RandomUtil.randomStr(6));
        sysUser.setPassword(PasswordUtil.encryptPassword(sysUser.getLoginName(), sysUser.getPassword(), sysUser.getSalt()));
        sysUser.setCreateBy(getLoginName());
        return toAjax(sysUserService.insertUser(sysUser));
    }

    /**
     * 修改保存用户
     */
    @HasPermissions("system:user:edit")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    @ApiOperation(value = "修改保存用户")
    @SentinelResource("update")
    public R editSave(@RequestBody SysUser sysUser)
    {
        if (null != sysUser.getUserId() && SysUser.isAdmin(sysUser.getUserId()))
        {
            return R.error(TradeErrorEnum.USER_IS_ADMIN);
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser)))
        {
            return R.error(sysUser.getLoginName() + TradeErrorEnum.USER_UPDATE_PHONE_REPEAT_ERROR.msg);
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser)))
        {
            return R.error(sysUser.getLoginName() + TradeErrorEnum.USER_UPDATE_MAIL_REPEAT_ERROR.msg);
        }
        return toAjax(sysUserService.updateUser(sysUser));
    }

    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */
    @HasPermissions("system:user:edit")
    @PostMapping("update/info")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改用户信息")
    @SentinelResource("update/info")
    public R updateInfo(@RequestBody SysUser sysUser)
    {
        return toAjax(sysUserService.updateUserInfo(sysUser));
    }

    /**
     * 记录登陆信息
     * @param sysUser
     * @return
     * @author zmr
     */
    @PostMapping("update/login")
    @ApiOperation(value = "记录登陆信息")
    @SentinelResource("update/login")
    public R updateLoginRecord(@RequestBody SysUser sysUser)
    {
        return toAjax(sysUserService.updateUser(sysUser));
    }

    @HasPermissions("system:user:resetPwd")
    @OperLog(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ApiOperation(value = "重置密码")
    @SentinelResource("resetPwd")
    public R resetPwdSave(@RequestBody SysUser user)
    {
        user.setSalt(RandomUtil.randomStr(6));
        user.setPassword(PasswordUtil.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(sysUserService.resetUserPwd(user));
    }

    /**
     * 修改状态
     * @param sysUser
     * @return
     * @author zmr
     */
    @HasPermissions("system:user:edit")
    @PostMapping("status")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改状态")
    @SentinelResource("status")
    public R status(@RequestBody SysUser sysUser)
    {
        return toAjax(sysUserService.changeStatus(sysUser));
    }

    /**
     * 删除用户
     * @throws Exception
     */
    @HasPermissions("system:user:remove")
    @OperLog(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("remove")
    @ApiOperation(value = "删除用户")
    @SentinelResource("remove")
    public R remove(String ids) throws Exception
    {
        return toAjax(sysUserService.deleteUserByIds(ids));
    }
}
