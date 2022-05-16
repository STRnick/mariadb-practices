-- subquery

-- 1) select 절의 서브쿼리
select (select max(salary) from salaries);

-- 2) from 절의 서브쿼리
select s.a, s.b, s.c
from (select now() as a, sysdate() as b, 3+1 as c) s;

-- 3) where 절의 서브쿼리
-- 예제1: 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요.
select a.dept_no as '사번', concat(b.first_name, ' ', b.last_name) as '전체 이름'
from dept_emp a, employees b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and dept_no = 'd004';

-- sol)
select a.dept_no as '사번', concat(b.first_name, ' ', b.last_name) as '전체 이름'
from dept_emp a, employees b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and dept_no = (select dept_no
			   from dept_emp a, employees b
			   where a.emp_no = b.emp_no
			   and a.to_date = '9999-01-01'
			   and concat(b.first_name, ' ', b.last_name) = 'Fai Bale');
        
-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제 1: 현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 나타내세요.
select concat(e.first_name, ' ', e.last_name) as 이름, s.salary as 급여
from employees e, salaries s
where e.emp_no = s.emp_no
and s.to_date = '9999-01-01'
and s.salary < (select avg(salary)
				from employees e, salaries s
                where s.to_date = '9999-01-01'
                and e.emp_no = s.emp_no)
order by s.salary desc;

-- 실습문제 1: (평균급여 검증)
select avg(salary)
from employees e, salaries s
where e.emp_no = s.emp_no
and s.to_date = '9999-01-01';

-- 실습문제 2: 현재 가장적은 평균 급여를 받고 있는 직책에서 평균 급여를 구하세요
-- 1) 현재 가장 적은 평균급여
select min(salary)
from salaries s
where s.to_date = '9999-01-01';

-- 1-1) 직책별 평균급여
select t.title as 직책, avg(s.salary) as '평균급여'
from titles t, salaries s
where t.emp_no = s.emp_no
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title;

-- 1-2) 가장 적은 평균급여
select min(c.avg_salary)
from (select t.title as 직책, avg(s.salary) as avg_salary
from titles t, salaries s
where t.emp_no = s.emp_no
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01' 
group by t.title) c;

-- 2-1) sol : subquery
select t.title as 직책, avg(s.salary) as '평균급여'
from titles t, salaries s
where t.emp_no = s.emp_no
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title
having avg(s.salary) = (select min(c.avg_salary)
						from (select t.title as 직책, avg(s.salary) as avg_salary
						from titles t, salaries s
						where t.emp_no = s.emp_no
						and t.to_date = '9999-01-01'
						and s.to_date = '9999-01-01' 
						group by t.title) c);

-- 2-2) sol2 : top-k
select t.title as 직책, avg(s.salary) as '평균급여'
from titles t, salaries s
where t.emp_no = s.emp_no
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
group by t.title
order by avg(salary) asc limit 0, 1;

-- 3-2) 복수행 연산자:
-- 실습문제3: 현재 급여가 50000 이상인 직원의 이름을 급여가 큰 순서대로 출력하세요.