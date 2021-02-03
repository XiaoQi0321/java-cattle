/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/2/3 22:01:36                            */
/*==============================================================*/


drop table if exists ozs_cart_item;

drop table if exists ozs_order;

drop table if exists ozs_order_item;

drop table if exists ozs_order_setting;

drop table if exists pzs_product;

drop table if exists pzs_product_category;

drop table if exists pzs_product_recommod;

drop table if exists pzs_sku_stock;

drop table if exists uzs_app_contact;

drop table if exists uzs_sys_user;

drop table if exists uzs_user;

/*==============================================================*/
/* Table: ozs_cart_item                                         */
/*==============================================================*/
create table ozs_cart_item
(
    id                   bigint not null auto_increment,
    product_id           bigint,
    product_sku_id       bigint,
    user_id              bigint,
    quantity             int comment '购买数量',
    price                decimal(15,2) comment '添加到购物车的价格',
    product_pic          varchar(1000) comment '商品主图',
    product_name         varchar(500) comment '商品名称',
    product_brand        varchar(200),
    product_sn           varchar(200),
    product_sub_title    varchar(500) comment '商品副标题（卖点）',
    product_sku_code     varchar(200) comment '商品sku条码',
    member_nickname      varchar(500) comment '会员昵称',
    create_date          datetime comment '创建时间',
    modify_date          datetime comment '修改时间',
    delete_status        int(1) default 0 comment '是否删除',
    product_category_id  bigint comment '商品的分类',
    product_attr         varchar(500) comment '商品销售属性:[{"key":"颜色","value":"银色"},{"key":"容量","value":"4G"}]',
    primary key (id)
);

alter table ozs_cart_item comment '购物车表';

/*==============================================================*/
/* Table: ozs_order                                             */
/*==============================================================*/
create table ozs_order
(
    id                   bigint not null,
    shop_id              int,
    user_id              bigint,
    create_time          datetime,
    order_sn             varchar(20) comment '订单流水号',
    pay_type             smallint,
    total                decimal(15,2) comment '总值',
    remarks              varchar(1024),
    status               smallint,
    dvy_type             varchar(20) comment '物流类型',
    dvy_flow_sn          varchar(50),
    freight_amount       decimal(15,2) comment '运费金额',
    receiver_name        varchar(100),
    receiver_phone       varchar(32),
    receiver_province    varchar(32),
    receiver_city        varchar(32),
    receiver_region      varchar(32),
    receiver_detail_address varchar(200),
    payment_time         datetime,
    delivery_time        datetime,
    receive_time         datetime,
    cancel_time          datetime,
    close_type           smallint,
    primary key (id)
);

alter table ozs_order comment '订单表';

/*==============================================================*/
/* Table: ozs_order_item                                        */
/*==============================================================*/
create table ozs_order_item
(
    id                   bigint not null auto_increment,
    order_id             bigint comment '订单id',
    order_sn             varchar(64) comment '订单编号',
    product_id           bigint,
    product_pic          varchar(500),
    product_name         varchar(200),
    product_brand        varchar(200),
    product_sn           varchar(64),
    product_price        decimal(10,2) comment '销售价格',
    product_quantity     int comment '购买数量',
    product_sku_id       bigint comment '商品sku编号',
    product_sku_code     varchar(50) comment '商品sku条码',
    product_category_id  bigint comment '商品分类id',
    integration_amount   decimal(10,2) comment '积分优惠分解金额',
    real_amount          decimal(10,2) comment '该商品经过优惠后的分解金额',
    product_attr         varchar(500) comment '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
    primary key (id)
);

alter table ozs_order_item comment '订单中所包含的商品';

/*==============================================================*/
/* Table: ozs_order_setting                                     */
/*==============================================================*/
create table ozs_order_setting
(
    id                   bigint not null auto_increment,
    flash_order_overtime int comment '秒杀订单超时关闭时间(分)',
    normal_order_overtime int comment '正常订单超时时间(分)',
    confirm_overtime     int comment '发货后自动确认收货时间（天）',
    finish_overtime      int comment '自动完成交易时间，不能申请售后（天）',
    comment_overtime     int comment '订单完成后自动好评时间（天）',
    primary key (id)
);

alter table ozs_order_setting comment '订单设置表';

/*==============================================================*/
/* Table: pzs_product                                           */
/*==============================================================*/
create table pzs_product
(
    id                   bigint not null,
    prodoct_category_id  bigint comment '商品分类id',
    name                 varchar(64) comment '商品名称',
    publish_status       smallint comment '上架状态',
    new_status           smallint comment '是否新品',
    sort                 int,
    sale                 int,
    price                decimal(10,2),
    sub_title            varchar(255),
    original_price       decimal(10,2),
    stock                int,
    weight               decimal(10,2),
    detail_html          text,
    detail_mobile_html   text,
    create_time          datetime,
    primary key (id)
);

alter table pzs_product comment '商品表';

/*==============================================================*/
/* Table: pzs_product_category                                  */
/*==============================================================*/
create table pzs_product_category
(
    id                   bigint not null,
    parent_id            bigint,
    name                 varchar(64),
    level                int(1) comment '分类级别：0->1级；1->2级',
    show_status          int(1) comment '显示状态：0->不显示；1->显示',
    keywords             varchar(255),
    icon                 varchar(255) comment '图标',
    description          text comment '描述',
    sort                 int,
    primary key (id)
);

alter table pzs_product_category comment '产品分类';

/*==============================================================*/
/* Table: pzs_product_recommod                                  */
/*==============================================================*/
create table pzs_product_recommod
(
    shop_id              int,
    product_id           bigint comment '商品id',
    sort                 smallint
);

alter table pzs_product_recommod comment '商品推荐关系表';

/*==============================================================*/
/* Table: pzs_sku_stock                                         */
/*==============================================================*/
create table pzs_sku_stock
(
    id                   bigint not null auto_increment,
    product_id           bigint,
    sku_code             varchar(64) not null comment 'sku编码',
    price                decimal(10,2),
    stock                int default 0 comment '库存',
    low_stock            int comment '预警库存',
    pic                  varchar(255) comment '展示图片',
    sale                 int comment '销量',
    promotion_price      decimal(10,2) comment '单品促销价格',
    lock_stock           int default 0 comment '锁定库存',
    sp_data              varchar(500) comment '商品销售属性，json格式',
    primary key (id)
);

alter table pzs_sku_stock comment 'sku的库存';

/*==============================================================*/
/* Table: uzs_app_contact                                       */
/*==============================================================*/
create table uzs_app_contact
(
    id                   bigint not null auto_increment,
    user_id              bigint,
    app_id               int(1),
    nick_name            varchar(200),
    image_url            varchar(500),
    biz_user_id          varchar(255),
    biz_unionid          varchar(255),
    primary key (id)
);

alter table uzs_app_contact comment '用户各平台绑定关系';

/*==============================================================*/
/* Table: uzs_sys_user                                          */
/*==============================================================*/
create table uzs_sys_user
(
    id                   bigint not null auto_increment,
    username             varchar(64) comment '用户名',
    password             varchar(64) comment '密码',
    icon                 varchar(500) comment '头像',
    email                varchar(100) comment '邮箱',
    nick_name            varchar(200) comment '昵称',
    note                 varchar(500) comment '备注信息',
    create_time          datetime comment '创建时间',
    login_time           datetime comment '最后登录时间',
    status               int(1) default 1 comment '帐号启用状态：0->禁用；1->启用',
    primary key (id)
);

alter table uzs_sys_user comment '后台用户表';

/*==============================================================*/
/* Table: uzs_user                                              */
/*==============================================================*/
create table uzs_user
(
    id                   bigint not null auto_increment,
    username             varchar(64) comment '用户名',
    password             varchar(64) comment '密码',
    nickname             varchar(64) comment '昵称',
    phone                varchar(64) comment '手机号码',
    status               int(1) comment '帐号启用状态:0->禁用；1->启用',
    create_time          datetime comment '注册时间',
    icon                 varchar(500) comment '头像',
    gender               int(1) comment '性别：0->未知；1->男；2->女',
    birthday             date comment '生日',
    city                 varchar(64) comment '所做城市',
    job                  varchar(100) comment '职业',
    personalized_signature varchar(200) comment '个性签名',
    source_type          int(1) comment '用户来源',
    integration          int comment '积分',
    primary key (id)
);

alter table uzs_user comment '会员表';

