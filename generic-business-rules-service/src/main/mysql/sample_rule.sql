use bu_rule;

CREATE TABLE `business_rule`
(
	`rule_id`          int          NOT NULL AUTO_INCREMENT,
	`name`        varchar(32)  NOT NULL COMMENT '规则名称',
	`description` varchar(64)  NOT NULL COMMENT '描述',
	`condition`   varchar(256) NOT NULL COMMENT '应条件',
	`functions`   varchar(256) NOT NULL COMMENT '条件中使用的函数',
	`priority`    tinyint      NOT NULL COMMENT '规则的优先级',
	`warning`     varchar(128) NOT NULL COMMENT '不满足规则时的警告信息',
	PRIMARY KEY (`rule_id`),
	UNIQUE KEY (`name`)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4 COMMENT '业务规则表';
