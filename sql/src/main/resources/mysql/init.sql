-- goods_category
CREATE TABLE `goods_category`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品分类',
    `category_name`   varchar(32)  DEFAULT NULL COMMENT '分类名称',
    `introduction`    varchar(255) DEFAULT NULL COMMENT '分类介绍',
    `category_status` tinyint(1)   DEFAULT NULL COMMENT '分类状态，0：未启用，1：启用',
    `deleted`         tinyint(1)   DEFAULT NULL COMMENT '逻辑删除，0：未删除，1：已删除',
    `utime`           datetime     DEFAULT NULL COMMENT '修改时间',
    `ctime`           datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品分类';


-- specification
CREATE TABLE `specification`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品规格id',
    `specification_name`   varchar(32) DEFAULT NULL COMMENT '规格名称',
    `specification_status` tinyint(1)  DEFAULT NULL COMMENT '分类状态，0：未启用，1：启用',
    `deleted`              tinyint(1)  DEFAULT NULL COMMENT '逻辑删除，0：未删除，1：已删除',
    `utime`                datetime    DEFAULT NULL COMMENT '修改时间',
    `ctime`                datetime    DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='规格表';


-- goods_specification

CREATE TABLE `goods_specification`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品规格id',
    `specification_id` bigint(20)     DEFAULT NULL COMMENT '规格id',
    `goods_id`         bigint(20)     DEFAULT NULL COMMENT '商品id',
    `price`            decimal(10, 2) DEFAULT NULL COMMENT '价格',
    `utime`            datetime       DEFAULT NULL COMMENT '修改时间',
    `ctime`            datetime       DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品规格表';


-- goods
CREATE TABLE `goods`
(
    `id`                     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `category_id`            bigint(20)   DEFAULT NULL COMMENT '商品分类id',
    `goods_specification_id` bigint(20)   DEFAULT NULL COMMENT '商品规格id',
    `goods_name`             varchar(32)  DEFAULT NULL COMMENT '商品名称',
    `goods_number`           varchar(32)  DEFAULT NULL COMMENT '商品编号',
    `goods_small_image`      varchar(128) DEFAULT NULL COMMENT '商品小图',
    `goods_status`           tinyint(1)   DEFAULT NULL COMMENT '商品状态，1：上架，2：下架',
    `goods_inventory`        int(8)       DEFAULT NULL COMMENT '商品存货',
    `goods_sort`             int(4)       DEFAULT NULL COMMENT '商品排序',
    `integration`            int(4)       DEFAULT NULL COMMENT '积分值',
    `produce_date`           datetime     DEFAULT NULL COMMENT '商品生产日期',
    `expiration_date`        datetime     DEFAULT NULL COMMENT '商品失效日期',
    `introduction`           varchar(255) DEFAULT NULL COMMENT '商品简介',
    `sale_number`            int(8)       DEFAULT NULL COMMENT '销售数量',
    `deleted`                tinyint(1)   DEFAULT NULL COMMENT '逻辑删除，0：未删除，1：已删除',
    `ctime`                  datetime     DEFAULT NULL COMMENT '创建时间',
    `utime`                  datetime     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品表';

-- good_image

CREATE TABLE `goods_image`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
    `goods_id`  bigint(20)   DEFAULT NULL COMMENT '商品id',
    `image_url` varchar(128) DEFAULT NULL COMMENT '图片地址',
    `utime`     datetime     DEFAULT NULL COMMENT '修改时间',
    `ctime`     datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品图片';

-- goods_order

CREATE TABLE `goods_order`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `order_number`   varchar(32)    DEFAULT NULL COMMENT '订单编号',
    `goods_id`       bigint(20)     DEFAULT NULL COMMENT '商品id',
    `goods_info`     text COMMENT '商品信息',
    `coupons_id`     bigint(20)     DEFAULT NULL COMMENT '优惠券id',
    `coupons_info`   text COMMENT '优惠券信息',
    `order_status`   tinyint(1)     DEFAULT NULL COMMENT '订单状态',
    `total_amount`   decimal(10, 2) DEFAULT NULL COMMENT '总金额',
    `payment_amount` decimal(10, 2) DEFAULT NULL COMMENT '付款金额',
    `payment_time`   datetime       DEFAULT NULL COMMENT '付款时间',
    `deleted`        tinyint(1)     DEFAULT NULL COMMENT '逻辑删除，0：未删除，1：已删除',
    `utime`          datetime       DEFAULT NULL COMMENT '修改时间',
    `ctime`          datetime       DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单表';


CREATE TABLE `sysconfig`
(
    `id`     bigint NOT NULL,
    `name`   varchar(255) DEFAULT NULL,
    `ctime`  datetime     DEFAULT CURRENT_TIMESTAMP,
    `utime`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted` tinyint(1)   DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `USERS`
(
    `id`     bigint NOT NULL,
    `name`   varchar(60) DEFAULT NULL,
    `passWord`   varchar(255) DEFAULT NULL,
    `openId`   varchar(100) DEFAULT NULL,
    `mobile`   varchar(20) DEFAULT NULL,
    `ctime`  datetime     DEFAULT CURRENT_TIMESTAMP,
    `utime`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted` tinyint(1)   DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `app_connect` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` varchar(36) NOT NULL COMMENT '本系统userId',
    `app_id` tinyint(2) DEFAULT NULL COMMENT '第三方系统id 1：微信小程序',
    `nick_name` varchar(64) DEFAULT NULL COMMENT '第三方系统昵称',
    `image_url` varchar(500) DEFAULT NULL COMMENT '第三方系统头像',
    `biz_user_id` varchar(255) DEFAULT NULL COMMENT '第三方系统userid',
    `biz_unionid` varchar(255) DEFAULT NULL COMMENT '第三方系统unionid',
    PRIMARY KEY (`id`),
    KEY `user_app_id` (`user_id`,`app_id`) COMMENT '用户id和appid联合索引'
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;