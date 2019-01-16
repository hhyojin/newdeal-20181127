create table board(
	num       int          not null auto_increment,
	writer    varchar(12)  not null,
	title     varchar(50)  not null,
	content   text         not null,
	pwd       varchar(12)  not null,
	hit       int          not null,
	regdate   datetime     not null,
	primary key(num)
) default character set utf8 collate utf8_general_ci;