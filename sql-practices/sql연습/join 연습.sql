-- inner join

-- 예제1: 현재 근무하고있는 직원의 이름과 직책을 출력하세요.
select e.first_name, t.title
from employees e, titles t
where e.emp_no = t.emp_no		-- join 조건(n-1)
and t.to_date = '9999-01-01';	-- row 선택 조건


-- 예제2: 현재 직원의 이름과 직책을 출력하되 여성 엔지니어(engineer)만 출력하세요.
select e.emp_no, e.first_name, e.gender, t.title
from employees e, titles t
where e.emp_no = t.emp_no		-- join 조건(n-1)
and e.gender = 'F'				-- row 선택 조건
and t.title = 'engineer'		-- row 선택 조건
and t.to_date = '9999-01-01';	-- row 선택 조건

--
-- ANSI/ISO SQL1999 JOIN 표준문법
--   
-- 1)	natural join
--		두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건을 명시하지 않아도 암묵적으로 조인이 된다.
select e.emp_no, e.first_name, t.title
from employees e join titles t on e.emp_no = t.emp_no
and t.to_date = '9999-01-01';	-- row 선택 조건

select count(*)
from salaries s natural join titles t
where s.to_date = '9999-01-01'
and t.to_date = '9999-01-01';

-- 2) join ~ using
-- natural join 문제점
select count(*)
from salaries s join titles t using (emp_no)
where s.to_date = '9999-01-01'
and t.to_date = '9999-01-01';

-- 3) join ~ on
select t.title, avg(s.salary)
from salaries s join titles t on s.emp_no = t.emp_no
where s.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
group by t.title
order by avg(s.salary) desc;