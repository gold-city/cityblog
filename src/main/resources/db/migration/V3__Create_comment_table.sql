create table comment
(
	id int auto_increment primary key not null,
	parent_id int not null,
	type int not null,
	commentator int not null,
	content varchar(1024) not null,
	gmt_create bigint not null,
	like_count bigint default 0
);