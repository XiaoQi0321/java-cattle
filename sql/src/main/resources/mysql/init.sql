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

CREATE TABLE `FILE_META` (
    `id` bigint(20) unsigned NOT NULL COMMENT 'id',
    `filekey` varchar(64) DEFAULT NULL COMMENT '文件key',
    `sizes` bigint DEFAULT NULL COMMENT '文件大小',
    `mimeType` varchar(64) DEFAULT NULL COMMENT '文件路径',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists pms_product;

/*==============================================================*/
/* Table: pms_product                                           */
/*==============================================================*/
create table pms_product
(
    id                   bigint not null auto_increment,
    brand_id             bigint,
    product_category_id  bigint,
    feight_template_id   bigint,
    product_attribute_category_id bigint,
    name                 varchar(64) not null,
    pic                  varchar(255),
    product_sn           varchar(64) not null comment '货号',
    delete_status        int(1) comment '删除状态：0->未删除；1->已删除',
    publish_status       int(1) comment '上架状态：0->下架；1->上架',
    new_status           int(1) comment '新品状态:0->不是新品；1->新品',
    recommand_status     int(1) comment '推荐状态；0->不推荐；1->推荐',
    verify_status        int(1) comment '审核状态：0->未审核；1->审核通过',
    sort                 int comment '排序',
    sale                 int comment '销量',
    price                decimal(10,2),
    promotion_price      decimal(10,2) comment '促销价格',
    gift_growth          int default 0 comment '赠送的成长值',
    gift_point           int default 0 comment '赠送的积分',
    use_point_limit      int comment '限制使用的积分数',
    sub_title            varchar(255) comment '副标题',
    description          text comment '商品描述',
    original_price       decimal(10,2) comment '市场价',
    stock                int comment '库存',
    low_stock            int comment '库存预警值',
    unit                 varchar(16) comment '单位',
    weight               decimal(10,2) comment '商品重量，默认为克',
    preview_status       int(1) comment '是否为预告商品：0->不是；1->是',
    service_ids          varchar(64) comment '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
    keywords             varchar(255),
    note                 varchar(255),
    album_pics           varchar(255) comment '画册图片，连产品图片限制为5张，以逗号分割',
    detail_title         varchar(255),
    detail_desc          text,
    detail_html          text comment '产品详情网页内容',
    detail_mobile_html   text comment '移动端网页详情',
    promotion_start_time datetime comment '促销开始时间',
    promotion_end_time   datetime comment '促销结束时间',
    promotion_per_limit  int comment '活动限购数量',
    promotion_type       int(1) comment '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购',
    product_category_name varchar(255) comment '产品分类名称',
    brand_name           varchar(255) comment '品牌名称',
    primary key (id)
);

alter table pms_product comment '商品信息';


drop table if exists oms_order;

/*==============================================================*/
/* Table: oms_order                                             */
/*==============================================================*/
create table oms_order
(
    id                   bigint not null auto_increment comment '订单id',
    member_id            bigint not null,
    coupon_id            bigint,
    order_sn             varchar(64) comment '订单编号',
    member_username      varchar(64) comment '用户帐号',
    total_amount         decimal(10,2) comment '订单总金额',
    pay_amount           decimal(10,2) comment '应付金额（实际支付金额）',
    freight_amount       decimal(10,2) comment '运费金额',
    promotion_amount     decimal(10,2) comment '促销优化金额（促销价、满减、阶梯价）',
    integration_amount   decimal(10,2) comment '积分抵扣金额',
    coupon_amount        decimal(10,2) comment '优惠券抵扣金额',
    discount_amount      decimal(10,2) comment '管理员后台调整订单使用的折扣金额',
    pay_type             int(1) comment '支付方式：0->未支付；1->支付宝；2->微信',
    source_type          int(1) comment '订单来源：0->PC订单；1->app订单',
    status               int(1) comment '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    order_type           int(1) comment '订单类型：0->正常订单；1->秒杀订单',
    delivery_company     varchar(64) comment '物流公司(配送方式)',
    delivery_sn          varchar(64) comment '物流单号',
    auto_confirm_day     int comment '自动确认时间（天）',
    promotion_info       varchar(100) comment '活动信息',
    receiver_name        varchar(100) not null comment '收货人姓名',
    receiver_phone       varchar(32) not null comment '收货人电话',
    receiver_post_code   varchar(32) comment '收货人邮编',
    receiver_province    varchar(32) comment '省份/直辖市',
    receiver_city        varchar(32) comment '城市',
    receiver_region      varchar(32) comment '区',
    receiver_detail_address varchar(200) comment '详细地址',
    note                 varchar(500) comment '订单备注',
    confirm_status       int(1) comment '确认收货状态：0->未确认；1->已确认',
    deleted        int(1) not null default 0 comment '删除状态：0->未删除；1->已删除',
    payment_time         datetime comment '支付时间',
    delivery_time        datetime comment '发货时间',
    receive_time         datetime comment '确认收货时间',
    comment_time         datetime comment '评价时间',
    modify_time          datetime comment '修改时间',
    create_time          datetime comment '提交时间',
    primary key (id)
);

alter table oms_order comment '订单表';


drop table if exists oms_order_item;

/*==============================================================*/
/* Table: oms_order_item                                        */
/*==============================================================*/
create table oms_order_item
(
    id                   bigint not null auto_increment,
    order_id             bigint comment '订单id',
    order_sn             varchar(64) comment '订单编号',
    product_id           bigint,
    product_name         varchar(200) comment '商品名称',
    product_brand        varchar(200) comment '商品名称',
    product_sn           varchar(64)  comment '商品编号',
    product_price        decimal(10,2) comment '销售价格',
    product_quantity     int comment '购买数量',
    product_sku_id       bigint comment '商品sku编号',
    product_sku_code     varchar(50) comment '商品sku条码',
    product_category_id  bigint comment '商品分类id',
    coupon_amount        decimal(10,2) comment '优惠券优惠分解金额',
    product_attr         varchar(500) comment '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
    primary key (id)
);

alter table oms_order_item comment '订单中所包含的商品';