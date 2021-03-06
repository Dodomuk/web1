--1. 'SMITH'보다 월급을 많이 받는 사원들의 이름과 월급을 출력하라.

SELECT ENAME, SAL
FROM EMP
WHERE SAL > ALL(SELECT SAL FROM EMP WHERE ENAME = 'SMITH');

--2. 10번 부서의 사원들과 같은 월급을 받는 사원들의 이름, 월급, 부서번호를 출력하라.

SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE SAL IN(SELECT SAL FROM EMP WHERE DEPTNO = '10');

--3. 'BLAKE'와 같은 부서에 있는 사원들의 이름과 고용일을 뽑는데 'BLAKE'는 빼고 출력하라.

SELECT ENAME, HIREDATE
FROM EMP
WHERE DEPTNO =(SELECT DEPTNO FROM EMP WHERE ENAME = 'BLAKE') AND NOT ENAME LIKE 'BLAKE';

--4. 평균급여보다 많은 급여를 받는 사원들의 사원번호, 이름, 월급을 출력하되, 월급이 높은 사람 순으로 출력하라

SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL > (SELECT AVG(SAL) FROM EMP)
ORDER BY SAL DESC; 

--5. 이름에 'T'를 포함하고 있는 사원들과 같은 부서에서 근무하고 있는 사원의 사원번호와 이름을 출력하라.
--다시해보기!!!**
SELECT EMPNO, ENAME
FROM EMP
WHERE DEPTNO IN(
SELECT DEPTNO 
FROM EMP WHERE ENAME LIKE '%T%');
 

--6. 20번 부서에 있는 사원들 중에서 가장 많은 월급을 받는 사원보다 
--많은 월급을 받는 사원들의 이름, 부서번호, 월급을 출력하라.
--(단,ALL 또는 ANY 연산자를 사용할 것)

SELECT ENAME, DEPTNO, SAL
FROM EMP
WHERE SAL > ALL(SELECT SAL FROM EMP WHERE DEPTNO = '20');

--7. 'DALLAS'에서 근무하고 있는 사원과 같은 부서에서 일하는 사원의
--이름, 부서번호, 직업을 출력하라.

SELECT ENAME, DEPTNO, JOB
FROM EMP
WHERE DEPTNO = (SELECT DEPTNO FROM DEPT WHERE LOC IN('DALLAS'));

--8. SALES 부서에서 일하는 사원들의 부서번호, 이름, 직업을 출력하라.

SELECT DEPTNO, ENAME, JOB
FROM EMP
WHERE DEPTNO = (SELECT DEPTNO FROM DEPT WHERE DNAME IN('SALES'));

--9. 'KING'에게 보고하는 모든 사원의 이름과 급여를 출력하라.
--   매니저가 KING인 모든 사원의 이름과 급여를 출력하시오
--***문제가 이해가 잘안됨
SELECT ENAME, SAL
FROM EMP
WHERE MGR = (SELECT EMPNO FROM EMP WHERE ENAME='KING');

--10. 자신의 급여가 평균 급여보다 많고, 이름에 'S'가 들어가는 사원과
-- 동일한 부서에서 근무하는 모든 사원의 사원번호, 이름, 급여를 출력하라.

SELECT EMPNO,ENAME,SAL
FROM EMP
WHERE deptno IN(SELECT DEPTNO FROM EMP WHERE( ENAME LIKE '%S%')
AND
SAL > (SELECT AVG(SAL) FROM EMP));

--11. 커미션을 받는 사원과 부서번호, 월급이 같은 사원의 이름, 월급, 부서번호를 출력하라.

SELECT ENAME, SAL
FROM EMP
WHERE  (DEPTNO, SAL) IN (SELECT DEPTNO, SAL FROM EMP WHERE COMM IS NOT NULL);


--12. 30번 부서 사원들과 월급과 커미션이 같지 않은 사원들의 이름,월급,커미션을 출력하라.
SELECT ENAME, SAL, COMM
FROM EMP
WHERE (SAL, COMM) NOT IN(SELECT SAL, COMM FROM EMP WHERE DEPTNO = 30);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       

----------------------------------------------------------------------------------
--조인 관련 문제












----------------------------------------------------------------------------------
--1.직원명과 주민번호를 조회하세요
-- 단 주민번호 9번째 자리부터는 *로 표시하세요

SELECT EMP_NAME, RPAD(SUBSTR(EMP_NO,1,8),14,'*')
FROM EMPLOYEE;

--2. 직원명, 직급코드,연봉(원)을 조회하세요
--연봉은 보너스가 적용된 1년치 급여입니다.
--연봉은 원하로표시되게끔 작성하세요.
SELECT EMP_NAME, JOB_CODE,('￦' || ((SALARY*12) + NVL((SALARY*BONUS),0))) 연봉
FROM EMPLOYEE;


--3. 부서코드가 D9, D5인 직원들 중에서 2004년도에 입사한 직원을 조회하세요
--사번,사원명,부서코드,입사일을 조회하세요
SELECT EMP_ID,EMP_NAME,DEPT_CODE,HIRE_DATE
FROM EMPLOYEE
WHERE (EXTRACT(YEAR FROM HIRE_DATE) = 2004) AND (DEPT_CODE ='D9' OR DEPT_CODE = 'D5');

--4직원명, 부서코드, 생년월일, 나이를 조회하세요
--생년월일은 00년00월00일과 같은 형태로 출력하세요

SELECT EMP_NAME, DEPT_CODE, (SUBSTR(EMP_NO,1,2)|| '년' ||  SUBSTR(EMP_NO,3,2) || '월'
||  SUBSTR(EMP_NO,5,2)|| '일' ) AS 생년월일, EXTRACT(YEAR FROM SYSDATE) - ('19' || SUBSTR(EMP_NO,1,2)) AS 나이
FROM EMPLOYEE;
--RR : 년도를 두자리로 표시할 경우 두자리 수가 50 미만이면 앞에 20을 붙이고
--     50 이상이면 앞에 19를 붙여준다.


--5.각 년도별 입사인원 수를 구하세요
--  -----------연도 | 인원수 ------------
SELECT EXTRACT(YEAR FROM HIRE_DATE) AS 연도, COUNT(*) AS 인원수
FROM EMPLOYEE
GROUP BY EXTRACT(YEAR FROM HIRE_DATE)
ORDER BY 연도 ASC;

SELECT SUBSTR(HIRE_DATE,4,2)
FROM EMPLOYEE;

--6.부서가 d5,d6,D9인 직원들을 조회하세요.
--부서코드가 D5면 총무부,D6이면 기획부, D9이면 영업부로 표시하세요

SELECT EMP_NAME,CASE WHEN DEPT_CODE ='D5' THEN '총무부'
                     WHEN DEPT_CODE ='D6' THEN '기획부'
                WHEN DEPT_CODE ='D9' THEN '영업부'
                END 부서
 FROM EMPLOYEE
 WHERE DEPT_CODE IN('D5','D6','D9');
 
 ------------------------------------------------------------------
 
 
 --201117 연습문제
 -- SCOTT 함수 연습문제 
-- COMM 의 값이 NULL이 아닌 정보 조회

SELECT *
FROM EMP
WHERE COMM IS NOT NULL;

-- 커미션을 받지 못하는 직원 조회

SELECT *
FROM EMP
WHERE COMM IS NULL;

-- 관리자가 없는 직원 정보 조회

SELECT *
FROM EMP
WHERE MGR IS NULL;

-- 급여를 많이 받는 직원 순으로 조회

SELECT *
FROM EMP
ORDER BY SAL DESC;

-- 급여가 같을 경우 커미션을 내림차순 정렬 조회

SELECT  *
FROM EMP
ORDER BY SAL DESC
, COMM DESC; 

-- EMP 테이블에서 사원번호, 사원명,직급, 입사일 조회
-- 단 입사일을 오름차순 정렬 처리함.
      
      SELECT EMPNO,ENAME,JOB,HIREDATE
      FROM EMP
      ORDER BY HIREDATE ASC;

-- EMP 테이블로 부터 사원번호, 사원명 조회
-- 사원번호 기준 내림차순 정렬

SELECT EMPNO,ENAME
FROM EMP
ORDER BY EMPNO DESC;

-- 사번, 입사일, 사원명, 급여 조회
-- 부서번호가 빠른 순으로, 같은 부서번호일 때는 최근 입사일순으로 처리

SELECT EMPNO,HIREDATE,ENAME,SAL,DEPTNO
FROM EMP
ORDER BY DEPTNO ASC,
HIREDATE ASC;

/***** 함수 *****/

-- 시스템으로 부터 오늘 날짜에 대한 정보를 얻고자 할 때
   SELECT SYSDATE
   FROM DUAL;

-- EMP 테이블로 부터 사번, 사원명, 급여 조회
-- 단, 급여는 100단위 까지의 값만 출력 처리함.
-- 급여 기준 내림차순 정렬함.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE LENGTH(SAL) <= 3
ORDER BY SAL DESC;

-- EMP 테이블로 부터 사원번호가 홀수인 사원들을 조회

SELECT *
FROM EMP
WHERE MOD(EMPNO,2) = 1;


/* 문자 처리 함수*/  

-- EMP 테이블로 부터 사원명, 입사일 조회
-- 단, 입사일은 년도와 월을 분리 추출해서 출력

SELECT ENAME, EXTRACT(YEAR FROM HIREDATE) AS 년도, EXTRACT(MONTH FROM HIREDATE) AS 월
FROM EMP;

-- EMP 테이블로 부터 9월에 입사한 직원의 정보 조회

SELECT *
FROM EMP
WHERE EXTRACT(MONTH FROM HIREDATE) = 9;


-- EMP 테이블로 부터 '81'년도에 입사한 직원 조회

SELECT *
FROM EMP
WHERE SUBSTR(HIREDATE,1,2) = '81';

-- EMP 테이블로 부터 이름이 'E'로 끝나는 직원 조회

SELECT *
FROM EMP
WHERE SUBSTR(ENAME,-1,1) = 'E';

-- emp 테이블로 부터 이름의 세번째 글자가 'R'인 직원의 정보 조회
-- LIKE 연산자를 사용
SELECT *
FROM EMP
WHERE ENAME LIKE '__%R%_';

-- SUBSTR() 함수 사용
SELECT *
FROM EMP
WHERE SUBSTR(ENAME,3,1) = 'R';

/************ 날짜 처리 함수 **************/

-- 입사일로 부터 40년 되는 날짜 조회
SELECT HIREDATE
FROM EMP
WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIREDATE) = 40;

-- 입사일로 부터 33년 이상 근무한 직원의 정보 조회

SELECT  *
FROM EMP
WHERE  MONTHS_BETWEEN(TO_DATE(SYSDATE,'RR/MM/DD') - TO_DATE(HIREDATE,'RR/MM/DD')) >= 33*12;

SELECT  *
FROM EMP
WHERE  MONTHS_BETWEEN(SYSDATE,HIREDATE) >= 33*12;
-- 오늘 날짜에서 년도만 추출

SELECT EXTRACT(YEAR FROM SYSDATE)
FROM DUAL;




   


