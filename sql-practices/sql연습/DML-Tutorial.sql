-- Basic Quries
-- 대소문자 구분이 없다.
select version(), current_date;
SELECT VERSION(), CURRENT_DATE;
-- 수학함수
select sin(pi()/4), (4+1)*5;

-- 테이블 삭제
drop table pet;

-- 테이블 생성
create table pet(
	name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
desc pet;

-- insert
insert into pet values ('Fluffy','Harold','dog','f','2018-02-04',null);

-- 데이터 검색(읽기, Read)
select name, owner, species, gender, birth, death from pet;

-- 데이터 삭제(삭제, Delete)
delete from pet where name = 'Fluffy';

-- load data local infile
load data local infile 'D:\\pet.txt' into table pet;

-- 데이터 수정(수정, Update)
update pet set death=null where name != 'Bowser';

-- 조회(검색) 연습1: where
-- 1990년 이후에 태어난 애들은?
select name, species, birth
from pet
where birth > '1990-12-31';

-- Gwen과 같이 사는 아이들은?
select name, species, owner
from pet
where owner = 'Gwen';

-- null 다루기 1: 살아 있는 애들은?
select name, species, death
from pet
where death is null;

-- null 다루기 2: 죽은 애들은?
select name, species, death
from pet
where death is not null;

-- like 검색(패턴 매칭) : 이름이 b로 시작하는 아이들은?
select name, species, death
from pet
where name like 'b%';

-- like 검색(패턴 매칭) : 이름이 b로 시작하는 아이들중에 이름이 6자인 아이는?
select name, species, death
from pet
where name like 'b_____';

-- 집계(통계) 함수
select count(*) from pet;

select count(death) from pet;
select count(*) from pet where death is not null;

-- 정렬: order by
-- 나이가 제일 어린 아이 순으로 이름, 종, 생일을 출력하세요.
select name, species, birth
from pet
order by birth desc, name asc;