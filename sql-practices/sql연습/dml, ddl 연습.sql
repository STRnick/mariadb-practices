drop table member;
CREATE TABLE member (
    no INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(64) NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    PRIMARY KEY (no)
);
desc member;

alter table member add juminbunho char(13) not null;
desc member;

alter table member drop juminbunho;
desc member;

alter table member add juminbunho char(13) not null after email;
desc member;

alter table member change department department char(13) not null;
desc member;

alter table member add self_intro text;
desc member;

alter table member drop juminbunho;
desc member;


-- insert
insert into member values (null, 'nicksumin97@gmail.com', password('1234'), '이수민', '개발팀', null);
select * from member;

insert into member(no, email, name, password, department)
values(null, 'nicksumin97@naver.com', '수민이', '1234', '개발팀');
select * from member;

-- update member
update member
set email = 'nick3', password=password('1234')
where no = 2;
select * from member;

-- delete
delete from member 
where no = 2;
select * from member;

-- transaction
select @@autocommit;
set autocommit = 0;

insert into member(no, email, name, password, department)
values(null, 'nicksumin97@naver.com', '이수민3', '1234', '개발팀');
commit;
select * from member;

