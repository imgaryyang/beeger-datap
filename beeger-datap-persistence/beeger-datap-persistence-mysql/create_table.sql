CREATE TABLE `bg_compute_plugin` (
  `model_name` varchar(200) DEFAULT NULL COMMENT '计算插件模型名，唯一',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_desc` varchar(1000) DEFAULT NULL COMMENT '计算插件描述',
  `model_cls` varchar(150) DEFAULT NULL,
  `model_methods` text COMMENT '计算插件有哪些暴露的计算方法，JSON<List<Methods>>',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `model_status` int(11) DEFAULT NULL COMMENT '状态 1:活跃 上线状态 可用 2:下线状态  3:在线，禁用',
  `alive_machine` varchar(500) DEFAULT NULL COMMENT '当前运行中的活跃的机器列表 JSON<List<String>>',
  `update_date` datetime DEFAULT NULL,
  `model_name_cn` varchar(200) DEFAULT NULL,
  `model_type` varchar(100) DEFAULT NULL COMMENT '计算模型类型:REMOTE,LOCAL',
  `plugin_jar_path` varchar(500) DEFAULT NULL COMMENT '插件jar到位置,如果是远程,可以不管。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8