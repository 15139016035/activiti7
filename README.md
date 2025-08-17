activiti7的学习之路：
ZERO：
    一些在实践过程中得到的明悟：
        第一个问题：name 的问题  
            在BPMN中的name，指的是流程定义模板的名字；
            在部署时候的name，那是这一次部署的name；
            在启动实例的name，那是这一个实例的name；
            当前task的name， 那是这个节点的name；当前节点
        第二个问题：在操作RepositoryService进行部署时，明确一个点
            首先会记录两个概念层面的东西： 第一个是部署的信息，此次部署的信息； 第二个是此次部署的流程定义信息； 这两个不要混为一谈；
                                     


一： activiti7的前期准备： 
            mysql和activiti的以来；和数据库连接的配置
            所有的操作都需要先获取到流程引擎；


二：activiti7的部署
            获取RepositoryService（仓库服务），将我们的BPMN流程模型图，部署到activiti中；
            我们的BPMN流程模型图，部署到activiti后就等于是一个模板了；这个模板也叫做流程定义；
            会写表：
                    0. ACT_RE_DEPLOYMENT 主表，记录部署的id(内部的)，部署的时间；
                    1. ACT_GE_BYTEARRAY 将BPMN的XML文件二进制的形式写表； deployment_id_ 与主表的id关联
                    2. ACT_RE_PROCDEF 记录了从BPMN解析出来的重要信息，形成流程定义  deployment_id_ 与主表的id关联
                    3. 会操作activiti的内部的id生成的一张表；（无与无关系）
            这几张表的关系：
                id生成器的表没有业务关系，记录的是全局id的属性管理
                所有的
            ACT_RE_DEPLOYMENT (一次部署) id
            │
            ├──< ACT_GE_BYTEARRAY (部署时存储的 BPMN/PNG/表单文件)  deployment_id_
            │
            └──< ACT_RE_PROCDEF (由 BPMN 解析出的流程定义记录)    deployment_id_

   三： activiti7的依靠流程定义启动实例
           获取RuntimeService（运行时表），依靠我们的BPMN流程定义，来创建实例，并且启动；
           主要是记录运行时表和history表：运行时表记录当前正在执行的流程实例；执行完后就会被删除；
                                     历史表就是记录，方便回溯查看； 
           这些表都会关联流程定义表的id；
           运行时表的主档：是运行实例表：ACT_RU_EXECUTION ，其余运行表根据当前节点确定是否要记录；


   四： businessKey
        这个属性，只能在流程实例启动的时候设置给流程实例；businessKey设置的值就是业务ID；
        这个字段的作用就是将我们的流程实例和业务ID进行关联；
                                        
  流程变量操作：两个层面：全局或者局部；主要的操作就是获取，修改，新增变量；可以在一开始就设置，也可以在在流程实例的过程中获取/修改/新增变量；
   UEL表达式的学习，分为两种 #{}获取变量的 ${}调用方法的可以调用spring容器中的
   监听器的学习，需要配置回调
   候选人，候选人领取任务，成为负责人
   网关操作：就是分支操作，类似代码中的if；