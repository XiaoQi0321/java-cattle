# 开发中的sql放这里

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
    deleted        int(1) comment '删除状态：0->未删除；1->已删除',
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
    create_time          datetime comment '创建时间',
    primary key (id)
);

alter table pms_product comment '商品信息';

CREATE TABLE `UZS_USERS` (
    `id` BIGINT ( 20 ) NOT NULL COMMENT 'id',
    `name` VARCHAR ( 60 ) DEFAULT NULL COMMENT '用户姓名',
    `nick_name` VARCHAR ( 100 ) DEFAULT NULL,
    `password` VARCHAR ( 255 ) DEFAULT NULL,
    `mobile` VARCHAR ( 20 ) DEFAULT NULL,
    `pic` VARCHAR ( 20 ) DEFAULT NULL COMMENT '头像链接',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `deleted` TINYINT ( 1 ) DEFAULT '0',
    PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `UZS_APP_CONNECT` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
      `user_id` bigint(20) NOT NULL COMMENT '本系统userId',
      `app_id` tinyint(2) DEFAULT NULL COMMENT '第三方系统id 1：微信小程序',
      `nick_name` varchar(64) DEFAULT NULL COMMENT '第三方系统昵称',
      `image_url` varchar(500) DEFAULT NULL COMMENT '第三方系统头像',
      `biz_user_id` varchar(255) DEFAULT NULL COMMENT '第三方系统userid',
      `biz_unionid` varchar(255) DEFAULT NULL COMMENT '第三方系统unionid',
      `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
      `deleted` TINYINT ( 1 ) DEFAULT '0',
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