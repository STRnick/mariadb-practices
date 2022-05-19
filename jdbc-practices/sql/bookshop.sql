-- bookshop
-- schema 확인
desc book;
desc author;

-- AuthorDao:insert
insert into author values(null, '원수연');

select a.no, a.title, b.name, a.state_code
from book a, author b
where a.author_no = b.no
order by no asc;
