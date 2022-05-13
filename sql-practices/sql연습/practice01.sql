-- 기본 SQL 문제입니다.

-- 문제1.
-- 사번이 10944인 사원의 이름은(전체 이름)
select concat(first_name, ' ', last_name) as '전체 이름'
from employees
where emp_no = '10944';

-- 문제2. 
-- 전체직원의 다음 정보를 조회하세요. 가장 선임부터 출력이 되도록 하세요. 출력은 이름, 성별,  입사일 순서이고 “이름”, “성별”, “입사일로 컬럼 이름을 대체해 보세요.
select first_name as '이름', gender as '성별', hire_date as '입사일'
from employees
order by hire_date asc;

-- 문제3.
-- 여직원과 남직원은 각 각 몇 명이나 있나요?
select count(case when gender='M' then 1 end) as '남직원 수',
count(case when gender='F' then 1 end) as '여직원 수'
from employees;

-- 문제4.
-- 현재 근무하고 있는 직원 수는 몇 명입니까? (salaries 테이블을 사용합니다.) 
select count(emp_no) as '재직자'
from salaries
where to_date like '9999-01-01';

-- 문제5.
-- 부서는 총 몇 개가 있나요?
select count(dept_no) as '부서 개수'
from departments;

-- 문제6.
-- 현재 부서 매니저는 몇 명이나 있나요?(역임 매너저는 제외)
select count(emp_no) as '현직 매니저수'
from dept_manager
where to_date = '9999-01-01';

-- 문제7.
-- 전체 부서를 출력하려고 합니다. 순서는 부서이름이 긴 순서대로 출력해 보세요.
select dept_name as '부서이름'
from departments
group by char_length(dept_name) desc;

-- 문제8.
-- 현재 급여가 120,000이상 받는 사원은 몇 명이나 있습니까?
select count(emp_no) as '부자들'
from salaries
where salary >= '120000' and to_date = '9999-01-01';

-- 문제9.
-- 어떤 직책들이 있나요? 중복 없이 이름이 긴 순서대로 출력해 보세요.
select distinct title as '직책명'
from titles
order by char_length(title) desc;

-- 문제10
-- 현재 Enginner 직책의 사원은 총 몇 명입니까?
select count(emp_no) as '현직 엔지니어 수'
from titles
where title = 'Engineer' and to_date = '9999-01-01';

-- 문제11
-- 사번이 13250(Zeydy)인 지원이 직책 변경 상황을 시간순으로 출력해보세요.
select emp_no as '사번', title as '직책', from_date, to_date
from titles
where emp_no = '13250'
order by from_date;