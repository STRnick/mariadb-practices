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

-- 실습문제 1: 현재 회사 상황을 반영한 직원별 근무부서를  사번, 직원 전체이름, 근무부서 형태로 출력해 보세요.
select e.emp_no as 사번, concat(e.first_name, ' ', e.last_name) as 이름, d.dept_name as 근무부서
from employees e, departments d, dept_emp de
where e.emp_no = de.emp_no and de.dept_no = d.dept_no
and de.to_date = '9999-01-01';

-- 실습문제 2: 현재 회사에서 지급되고 있는 급여체계를 반영한 결과를 출력하세요. 사번,  전체이름, 연봉 이런 형태로 출력하세요.
select e.emp_no as 사번, concat(e.first_name, ' ', e.last_name) as 이름, s.salary as 연봉
from employees e, salaries s
where e.emp_no = s.emp_no and s.to_date = '9999-01-01'
order by s.salary desc;

-- 실습문제 3: 현재 직책별로 평균 연봉과 인원수를 구하되 직책별로 인원이 100명 이상인 직책만 출력하세요.
select t.title as 직책, avg(s.salary) as '평균연봉', count(*) as '인원 수'
from titles t, salaries s
where t.emp_no = s.emp_no and t.emp_no = s.emp_no
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title
having count(*) >= '100'
order by avg(s.salary) desc;

-- 실습문제 4: 현재 부서별로 현재 직책이 Engineer인 직원들의 대해서만 평균 급여를 구하세요.
select d.dept_name as 부서, avg(s.salary) as 'Engineer 평균 급여'
from dept_emp de, departments d, titles t, salaries s
where de.emp_no = s.emp_no 
and s.emp_no = t.emp_no 
and d.dept_no = de.dept_no
and de.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and t.title = 'Engineer'
group by d.dept_name;