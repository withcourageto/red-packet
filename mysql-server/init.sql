/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/4 13:35:41                            */
/*==============================================================*/


drop table if exists r_activity;

drop table if exists r_pool_red_packets;

drop table if exists r_red_packet;

drop table if exists r_red_packet_pool;

drop table if exists r_user;

drop table if exists 用户红包记录;

/*==============================================================*/
/* Table: r_activity                                            */
/*==============================================================*/
create table r_activity
(
   id                   integer not null,
   name                 varchar(20) comment '活动名称',
   start_time           datetime,
   end_time             datetime,
   red_packet_nums      integer comment '红包数量',
   red_packet_remain_nums integer comment '剩余数量,剩余数量等于0时，表示红包被抢完',
   max_amount           decimal(8,2) comment '最大红包金额',
   total_amount         decimal(8,2) comment '总金额',
   min_amount           decimal(8,2) comment '最小金额',
   user_max_num         integer comment '用户最大获得数量',
   primary key (id)
);

/*==============================================================*/
/* Table: r_pool_red_packets                                    */
/*==============================================================*/
create table r_pool_red_packets
(
   pool_id              integer,
   red_packet_id        integer
);

/*==============================================================*/
/* Table: r_red_packet                                          */
/*==============================================================*/
create table r_red_packet
(
   id                   int not null auto_increment,
   money                decimal(8,2) not null,
   type                 varchar(10) comment 'ex: wechat, aplipay',
   expire_time          datetime,
   best_wishes          varchar(20) comment '祝福语',
   scenarios            varchar(10) comment '新年，新婚，等等',
   activity_id          integer,
   primary key (id)
);

/*==============================================================*/
/* Table: r_red_packet_pool                                     */
/*==============================================================*/
create table r_red_packet_pool
(
   id                   integer not null,
   activity_id          integer,
   red_packet_nums      integer,
   red_packet_remain_nums integer,
   primary key (id)
);

/*==============================================================*/
/* Table: r_user                                                */
/*==============================================================*/
create table r_user
(
   id                   int not null auto_increment,
   username             varchar(20),
   primary key (id)
);

/*==============================================================*/
/* Table: 用户红包记录                                                */
/*==============================================================*/
create table 用户红包记录
(
   id                   bigint not null,
   user_id              integer,
   activity_id          integer,
   red_packet_id        integer,
   winning_time         datetime,
   primary key (id)
);

