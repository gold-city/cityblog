create table blog_user
(
    id int auto_increment primary key not null,
    name varchar(50),
    password varchar(50),
    token varchar(36),
    gmt_create bigint,
    gmt_modified bigint,
    avatar_url varchar(100)
);