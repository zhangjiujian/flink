CREATE DATABASE `tags_new` DEFAULT CHARACTER SET utf8;

USE `tags_new`;

CREATE TABLE `tbl_basic_tag`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `name`     varchar(50)  DEFAULT NULL COMMENT '标签名称',
    `industry` varchar(30)  DEFAULT NULL COMMENT '行业、子行业、业务类型、标签、属性',
    `rule`     varchar(300) DEFAULT NULL COMMENT '标签规则',
    `business` varchar(100) DEFAULT NULL COMMENT '业务描述',
    `level`    int(11)      DEFAULT NULL COMMENT '标签等级',
    `pid`      bigint(20)   DEFAULT NULL COMMENT '父标签ID',
    `ctime`    datetime     DEFAULT NULL COMMENT '创建时间',
    `utime`    datetime     DEFAULT NULL COMMENT '修改时间',
    `state`    int(11)      DEFAULT NULL COMMENT '状态：1申请中、2开发中、3开发完成、4已上线、5已下线、6已禁用',
    `remark`   varchar(100) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 233
  DEFAULT CHARSET = utf8 COMMENT ='基础标签表';

CREATE TABLE `tbl_model`
(
    `id`         bigint(20)   NOT NULL AUTO_INCREMENT,
    `tag_id`     bigint(20)   DEFAULT NULL,
    `model_name` varchar(200) DEFAULT NULL,
    `model_main` varchar(200) DEFAULT NULL,
    `model_path` varchar(200) DEFAULT NULL,
    `sche_time`  varchar(200) DEFAULT NULL,
    `ctime`      datetime     DEFAULT NULL,
    `utime`      datetime     DEFAULT NULL,
    `state`      int(11)      DEFAULT NULL,
    `args`       varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;