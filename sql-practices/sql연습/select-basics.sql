-- select 연습
select * 
from departments 
order by dept_no 
limit 0, 3;

select dept_no, dept_name
from departments;

-- alias(as 생략가능)
-- 예제1: employees 테이블에서 직원의 이름, 성별, 입사일을 출력
select first_name as 'firstName', gender as '성별', hire_date '입사일'
from employees;

-- 예제2: employees 테이블에서 직원의 이름(성+이름), 성별, 입사일을 출력
select concat(first_name, last_name) as '이름', gender as '성별', hire_date '입사일'
from employees;

-- distict
-- 예제: titles 테이블에서 모든 직급의 이름 출력
select distinct(title)
from titles;

-- where 절 #1
-- 예제: 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력 하라
select concat(first_name, last_name) as '이름', gender as '성별', hire_date '입사일'
from employees
where hire_date < '1991-01-01'
order by hire_date desc;

-- where 절 #2: 논리 연산자
-- 예제: employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름', hire_date '입사일'
from employees
where hire_date < '1989-01-01' and gender = 'F'
order by hire_date desc;

-- where 절 #3: in 연산자
-- 예제: dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의 사번, 부서번호 출력
select emp_no as '사번', dept_no '부서번호'
from dept_emp
where dept_no in ('d005', 'd009');

-- where 절 #4: Like 검색
-- 예제: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름', hire_date as '입사일'
from employees
where hire_date <= '1989-12-31' and '1989-01-01' <= hire_date
order by hire_date desc;

select concat(first_name, ' ', last_name) as '이름', hire_date as '입사일'
from employees
where hire_date like '1989%'
order by hire_date desc;

select concat(first_name, ' ', last_name) as '이름', hire_date as '입사일'
from employees
where hire_date between '1989-01-01' and '1989-12-31'
order by hire_date desc;

-- order by 절
-- 예제1: employees 테이블에서 남자 직원의 전체이름, 성별, 입사일을 입사일 순(선임 순)으로 출력
select concat(first_name, ' ', last_name) as '이름', gender as '성별', hire_date as '입사일'
from employees
where gender = 'M'
order by hire_date asc;

-- 예제2: 직원들의 사번, 월급을 사번과 월급순으로 출력
select emp_no as '사번', salary as '월급', from_date, to_date
from salaries
order by emp_no asc, salary asc;