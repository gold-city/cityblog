create table notification
(
	id integer auto_increment,
	notifier integer,/*通知*/
	receiver integer,/*接收者*/
	outerId integer,/*评论的问题id*/
	type integer,/*评论还是问题*/
	gmt_create integer,
	status integer default 0,/*状态*/
	constraint notification_pk
		primary key (id)
);

