--p176
CREATE TABLESPACE yaodwwy DATAFILE 'yaodwwy'
SIZE 1000k
AUTOEXTEND ON;
--p178
CREATE USER yaodwwy IDENTIFIED BY good01
DEFAULT TABLESPACE yaodwwy;
--p179
GRANT CONNECT,RESOURCE TO yaodwwy;
--p183
CREATE TABLE student(
       stuno VARCHAR2(4) NOT NULL,
       stuname VARCHAR2(6),
       stuage NUMBER(2),
       cid VARCHAR2(2)
);
--p184
ALTER TABLE student ADD birthday DATE;
--p188
ALTER TABLE student
ADD CONSTRAINT pk_student_stuno PRIMARY KEY (stuno);
ALTER TABLE student
ADD CONSTRAINT uq_student_stuname UNIQUE (stuname);

CREATE TABLE classes (
       cid VARCHAR2(2) NOT NULL,
       cname VARCHAR2(4)
);

ALTER TABLE classes MODIFY (cname VARCHAR2(8));
--p189
ALTER TABLE classes
ADD CONSTRAINT pk_classes_cid PRIMARY KEY (cid);

ALTER TABLE student
ADD CONSTRAINT fk_student_cid FOREIGN KEY (cid) REFERENCES classes (cid);

--p198
INSERT INTO classes
VALUES ( '4','三年八班');
SELECT * FROM classes;

--p199
UPDATE classes
SET cname = '学下三班'
WHERE cid = 3;
--p200
SELECT cid 班号,cname 班级  FROM classes
ORDER BY cid DESC;
--p202
DELETE classes WHERE cid = 4;

--p203
SELECT * FROM emp;
SELECT INITCAP (ename) FROM emp; 
SELECT LOWER (ename) FROM emp;
SELECT LTRIM ('str' , 's') FROM dual; 
SELECT RTRIM ('str' , 'tr') FROM dual;
SELECT TRANSLATE ('abcd','abcdefghij','12345678') FROM dual;
SELECT SUBSTR ('yaodwwy',4,3) FROM dual;
SELECT CONCAT ('yao','dwwy') FROM dual;

--p204
SELECT CEIL (19.2) FROM dual;
--p205
SELECT NVL ( '' , '为空' ) FROM dual;

SELECT deptno 部门编号, max(sal), ROUND (AVG(sal)), MIN(sal) FROM emp
GROUP BY deptno 
HAVING AVG(sal) > 2000;
--p208
SELECT ename, deptno, sal, 
RANK() OVER (PARTITION BY deptno ORDER BY sal DESC) "rank",
dense_rank() OVER (PARTITION BY deptno ORDER BY sal DESC) "dense_rank",
row_number() OVER (PARTITION BY deptno ORDER BY sal DESC) "row_number"
FROM emp;

--p211
SELECT deptno FROM emp WHERE ename = 'SCOTT';
SELECT ename FROM emp WHERE deptno = 20;
SELECT deptno, ename FROM emp 
WHERE deptno IN (
SELECT deptno FROM emp WHERE ename = 'SCOTT');

SELECT empno, ename, deptno FROM emp 
WHERE deptno NOT IN (SELECT deptno FROM dept WHERE dname IN ('SALES','ACCOUNTING'));

SELECT empno, ename, e.deptno, d.dname FROM emp e,dept d 
WHERE e.deptno NOT IN (SELECT deptno FROM dept d WHERE dname IN ('SALES','ACCOUNTING')) 
AND e.deptno = d.deptno;

--p212
SELECT empno, ename, sal, e.deptno, d.dname FROM emp e,dept d
WHERE sal > (SELECT AVG(sal) FROM emp e2 
WHERE e.deptno = e2.deptno) AND e.deptno = d.deptno;

--20、列出薪金水平处于第四位的雇员
SELECT * FROM
(SELECT o.*,ROWNUM r FROM 
       (SELECT * FROM emp e
       ORDER BY sal DESC) o)
WHERE r = 4;
--p213
SELECT AVG(sal)
FROM emp e2
WHERE 10 = e2.deptno;

SELECT empno, ename, sal, deptno
FROM emp e1
WHERE sal > 2916.66;

SELECT empno, ename, sal, deptno FROM emp e1
WHERE sal > (SELECT AVG(sal) FROM emp e2 WHERE e2.deptno = e1.deptno);
