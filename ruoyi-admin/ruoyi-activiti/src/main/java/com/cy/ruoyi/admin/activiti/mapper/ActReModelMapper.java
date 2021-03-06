package com.cy.ruoyi.admin.activiti.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.ruoyi.admin.activiti.entity.ActReModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程设计模型部署Mapper接口
 */
@Mapper
public interface ActReModelMapper extends BaseMapper<ActReModel>
{
    /**
     * 查询流程设计模型部署
     * 
     * @param id 流程设计模型部署ID
     * @return 流程设计模型部署
     */
    ActReModel selectActReModelById(String id);

    /**
     * 查询流程设计模型部署列表
     * 
     * @param actReModel 流程设计模型部署
     * @return 流程设计模型部署集合
     */
    List<ActReModel> selectActReModelList(@Param("act") ActReModel actReModel);

    /**
     * 新增流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    int insertActReModel(ActReModel actReModel);

    /**
     * 修改流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    int updateActReModel(ActReModel actReModel);

    /**
     * 删除流程设计模型部署
     * 
     * @param id 流程设计模型部署ID
     * @return 结果
     */
    int deleteActReModelById(String id);

    /**
     * 批量删除流程设计模型部署
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteActReModelByIds(String[] ids);

    //************************************************************************
    /**
     * 根据条件分页查询列表
     * @param page
     * @param actReModel
     * @return
     */
    IPage<ActReModel> selectActReModelList(Page page, @Param("act") ActReModel actReModel);
}
