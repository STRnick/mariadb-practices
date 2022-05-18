-- 서브쿼리(혼합) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*) as '평균 연봉보다 많은 월급을 받는 직원 수'
from employees e join salaries s on e.emp_no = s.emp_no
where s.to_date = '9999-01-01'
and s.salary > (select avg(s.salary)
				from employees e join salaries s on e.emp_no = s.emp_no
                where s.to_date = '9999-01-01');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서, 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', d.dept_name as '부서', s.salary as '연봉'
from employees e, salaries s, dept_emp de, departments d
where s.emp_no = e.emp_no
and e.emp_no = de.emp_no
and de.dept_no = d.dept_no
and s.to_date = '9999-01-01'
and de.to_date = '9999-01-01'
and (e.emp_no, s.salary) in (select e.emp_no, s.salary
								from salaries s, employees e, departments d, dept_emp de
								where s.emp_no = e.emp_no
								and e.emp_no = de.emp_no
								and de.dept_no = d.dept_no
                                and s.to_date = '9999-01-01'
                                and de.to_date = '9999-01-01'
                                group by d.dept_name
                                having max(s.salary))
order by s.salary desc;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요
select e.emp_no as '사번', d.dept_name as '부서', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '연봉' 
from employees e, dept_emp de, salaries s, departments d, (select d.dept_no dno, avg(sal.salary) sal_avg
											from dept_emp d join salaries sal on d.emp_no = sal.emp_no
											where d.to_date = '9999-01-01'
                                            and sal.to_date = '9999-01-01'
											group by d.dept_no) gr
where e.emp_no = s.emp_no
and s.emp_no = de.emp_no
and de.dept_no = d.dept_no
and de.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and de.dept_no = gr.dno
and s.salary > gr.sal_avg;
                                
-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select e.emp_no as '사번', concat(e.first_name, e.last_name) as '이름', concat(m.first_name, m.last_name) as '매니저 이름', d.dept_name as '부서 이름'
from employees e, dept_emp de, departments d, dept_manager dm, employees m
where e.emp_no = de.emp_no
and de.dept_no = d.dept_no		
and dm.dept_no = d.dept_no
and dm.emp_no = m.emp_no			
and de.to_date = '9999-01-01'
and dm.to_date = '9999-01-01';
                                                   
-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

-- 문제7.
-- 평균 연봉이 가장 높은 직책?

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.