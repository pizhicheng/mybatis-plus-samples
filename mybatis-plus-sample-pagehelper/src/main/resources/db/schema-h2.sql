DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id   bigint(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    PRIMARY KEY (id)
);