--查询部门员工工资都大于平均工资的部门名称
SELECT dname FROM dept d WHERE 
(SELECT AVG(sal) FROM emp)<ALL
(SELECT sal FROM emp WHERE deptno = d.deptno) AND
EXISTS (SELECT * FROM emp WHERE deptno = d.deptno);

--查询每个部门中工资最高的员工姓名
SELECT ename FROM emp WHERE sal ||'-'||deptno IN 
(SELECT MAX(sal)||'-'||deptno FROM emp GROUP BY deptno);

--列出至少有一个雇员的所有部门
SELECT dname FROM dept d  WHERE EXISTS 
(SELECT ename FROM emp WHERE deptno = d.deptno);

--列出薪金比"smith"多的所有雇员
SELECT * FROM emp WHERE sal > (SELECT sal FROM emp WHERE ename = 'SMITH');
--列出所有雇员的姓名及其直接上级的姓名
SELECT ename AS 员工姓名,(SELECT ename FROM emp WHERE empno = e.mgr) AS 上级姓名 FROM emp e;

--SALES部门内所有员工姓名
SELECT ename FROM emp WHERE deptno IN 
(SELECT deptno FROM dept WHERE dname = 'SALES');

--部门位置在NEWYORK的所有员工工资

SELECT sal FROM emp WHERE deptno IN
(SELECT deptno FROM dept WHERE loc = 'NEW YORK');

--查询大于员工平均工资的员工ID、姓名、工资；
SELECT empno 员工ID,ename 姓名,sal 工资 FROM emp
WHERE sal > (SELECT AVG(sal) FROM emp);

--列出入职日期早于其直接上司的所有雇员

--列出部门名称和这些部门的雇员,同时列出没有雇员的部门
SELECT ename,dept.deptno,dept.dname FROM emp,dept;

SELECT deptno,(SELECT ename FROM emp WHERE deptno = emp.deptno)
 FROM emp e;

SELECT deptno,dname,ROWNUM FROM dept WHERE ROWNUM = 2;
SELECT ename,hiredate FROM (SELECT ename,hiredate FROM emp ORDER BY hiredate DESC) 
WHERE ROWNUM <= 5 ORDER BY hiredate DESC;

--rownum 排序 按页查询
SELECT * FROM(
SELECT * FROM(
       SELECT e.*,ROWNUM r FROM emp e
       )WHERE r <= 10 )
WHERE r >= 5;

--查询总页数
SELECT ceil(COUNT(empno)/5) FROM emp;
--内连接
SELECT ename,dname FROM emp e,dept d WHERE e.deptno = d.deptno;
--外连接
SELECT ename,dname FROM emp e,dept d WHERE e.deptno(+) = d.deptno;
