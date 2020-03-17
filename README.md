# cy-ruoyi
SpringCloud Alibaba + Dubbo + MybatisPlus + Mysql + Redis

---
## 中间件
* 注册中心：Nacos
* 配置中心：Nacos
* 限流熔断：Sentinel
* 消息队列：RocketMQ
* 分布式事务：Seata
* 分布式调用链：SkyWalking
* 分库分表：Mycat
* 分布式任务调度：XXL-Job   
* 日志收集：ELK
* 工作流：activiti
* 容器：Docker

---

## 目录结构

 ```
cy-ruoyi
 ├──docs //文件
 |  ├──poms //相关依赖
 |  ├──server //服务包
 ├──ruoyi-common //通用依赖
 |  ├──ruoyi-common-core //核心框架包
 |  ├──ruoyi-common-redis //redis工具包
 |  ├──ruoyi-common-log //通用日志工具包
 |  ├──ruoyi-common-pay //支付工具包（尚未）
 |  ├──ruoyi-common-utils // 通用工具包
 |  ├──ruoyi-common-auth // 授权工具包
 |  ├──ruoyi-common-sms // 信息推送（短信，邮件）工具包（尚未）
├──ruoyi-user //用户 8081
 |  ├──ruoyi-user-application //用户启动
 |  ├──ruoyi-user-api //用户API
 |  ├──ruoyi-user-impl //用户实现
 |  ├──sql //用户sql
├──ruoyi-order //订单 8071
 |  ├──ruoyi-order-application //订单启动
 |  ├──ruoyi-order-api //订单API
 |  ├──ruoyi-order-impl //订单实现
 |  ├──sql //订单sql
 ├──ruoyi-product // 商品 8061
 |  ├──ruoyi-product-application //商品启动
 |  ├──ruoyi-product-api //商品API
 |  ├──ruoyi-product-impl //商品实现
 |  ├──sql //商品sql
 ├──ruoyi-pay //收银台 8021（尚未）
 |  ├──ruoyi-pay-application //收银台启动
 |  ├──ruoyi-pay-api //收银台API
 |  ├──ruoyi-pay-impl //收银台实现
 |  ├──sql //收银台sql
 ├──ruoyi-stock //库存 8051
 |  ├──ruoyi-stock-application //库存启动
 |  ├──ruoyi-stock-api //库存API
 |  ├──ruoyi-stock-impl //库存实现
 |  ├──sql //库存sql
 ├──ruoyi-logistics //物流 8041（尚未）
 |  ├──ruoyi-logistics-application //物流启动
 |  ├──ruoyi-logistics-api //物流API
 |  ├──ruoyi-logistics-impl //物流实现
 |  ├──sql //物流sql
 ├──ruoyi-demo //Demo 7070
 |  ├──ruoyi-demo-application //Demo启动
 |  ├──ruoyi-demo-api //Demo API
 |  ├──ruoyi-demo-impl //Demo 实现
 |  ├──sql //Demo sql
 ├──ruoyi-tool //工具
 |  ├──ruoyi-gen // 代码生成 7065
 |  ├──ruoyi-auth // 授权鉴权  8090
 |  ├──ruoyi-io //文件系统 7050（尚未）
 |  ├──ruoyi-mock // 数据收集 7021（尚未）
 |  ├──ruoyi-monitor // 系统监控 7060
 |  ├──ruoyi-activity //工作流 8031
 |  ├──ruoyi-quartz //定时任务 8011（尚未）
 |  ├──ruoyi-gateway //网关 9527
 ├──ruoyi-ant --前端 使用ant design框架 8000
 ```

## 前端菜单
 ```
  ├──仪表盘
  |  ├──欢迎页
  |  ├──工作台
  ├──个人页
  |  ├──个人中心
  |  ├──个人设置
  ├──业务管理（尚未）
  |  ├──商品管理（尚未）
  |  ├──库存管理（尚未）
  |  ├──交易管理（尚未）
  |  ├──物流管理（尚未）
  |  ├──账务管理（尚未）
  |  ├──报表统计（尚未）
  ├──权限管理
  |  ├──用户管理
  |  ├──角色管理
  |  ├──菜单管理
  |  ├──部门管理
  ├──系统参数
  |  ├──通知公告（尚未）
  |  ├──参数管理
  |  ├──字典管理
  |  ├──文件管理
  |  ├──地区管理
  ├──系统监控
  |  ├──在线用户（尚未）
  |  ├──操作日志
  |  ├──登陆日志
  |  ├──服务监控
  |  ├──Nacos服务
  ├──系统工具
  |  ├──代码生成
  |  ├──定时任务（尚未）
  ├──流程管理
  |  ├──我的申请
  |  ├──我的待办
  |  ├──我的已办
  |  ├──模型管理
  |  ├──流程定义
  |  ├──运行中流程
  |  ├──结束的流程 

 ```

