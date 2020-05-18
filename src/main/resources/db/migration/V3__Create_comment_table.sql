create table comment
(
	id int auto_increment primary key not null,/*该评论id*/
	parent_id int not null,/*该评论的父级id*/
	type int not null,/*该评论的类型,如果评论的是问题，则为1，为评论为2，使用了枚举进行封装*/
	commentator int not null,/*评论的user*/
	content varchar(1024) not null,/*评论的内容*/
	gmt_create bigint not null,/*评论时间*/
	comment_count int default 0,/*评论数*/
	like_count bigint default 0/*点赞数*/
);

