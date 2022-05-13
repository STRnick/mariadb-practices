-- 함수: 문자열

-- upper
select upper('buSan'), upper('busan'), upper('DouZone');
select upper(first_name) from employees;

-- lower
select lower('buSan'), lower('busan'), lower('DouZone');
select lower(first_name) from employees;

-- substring(문자열, index, lenth)
select substring('Hello World', 3 , 2);

select concat(first_name, ' ', last_name) as '이름', hire_date as '입사일'
from employees
where substring(hire_date, 1, 4) = '1989'
order by hire_date desc;

-- lpad(오른쪽 정렬), rpad(왼쪽 정렬)
select lpad('1234', 10, ' ');
select rpad('1234', 10, ' ');

-- 예제: 직원들의 월급을 오른쪽 정렬
select lpad(salary, 10, '*'), emp_no from salaries order by salary desc;

-- trim, ltrim, rtrim
select concat('---', ltrim('    hello    '), '---') as 'ltrim',
		concat('---', rtrim('    hello    '), '---') as 'rtrim',
        concat('---', trim('    hello    '), '---') as 'trim',
         concat('---', trim(both 'x' from 'xxxxxhelloxxxxx'), '---') as '"x"trim';