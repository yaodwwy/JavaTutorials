--Oracle 
SELECT * FROM emp ORDER BY sal DESC;
--1、列出至少有一个雇员的所有部门
SELECT dname FROM dept
WHERE deptno IN (SELECT deptno FROM emp);

--2、列出薪金比"SMITH"多的所有雇员 
SELECT ename FROM emp
WHERE sal > (SELECT sal FROM emp WHERE ename = 'SMITH');--snith好可怜!

--3、列出所有雇员的姓名及其直接上级的姓名 
SELECT w.ename 雇员, m.ename 经理
FROM emp w INNER JOIN emp m
ON w.mgr = m.empno
ORDER BY w.ename;

--4、列出入职日期早于其直接上级的所有雇员---
SELECT w.ename 雇员,w.hiredate, m.ename 经理,m.hiredate
FROM emp w INNER JOIN emp m
ON w.mgr = m.empno
WHERE w.hiredate < m.hiredate;

--5、列出部门名称和这些部门的雇员,同时列出那些没有雇员的部门
SELECT dname,ename FROM dept d, emp e left join e.deptno on d.deptno;

--6、列出所有“CLERK”（办事员）的姓名及其部门名称  
SELECT ename,job,dname FROM emp,dept WHERE emp.deptno = dept.deptno AND job = 'CLERK';

--7、列出各种工作类别的最低薪金，显示最低薪金大于1500的记录 
SELECT ename,sal,job FROM 
(SELECT e.*,RANK() OVER (PARTITION BY job ORDER BY sal) r FROM emp e)
WHERE r=1 AND sal > 1500;
--
SELECT MIN(sal),job FROM emp GROUP BY job HAVING MIN(sal)>=1500;

--8、列出从事“SALES”（销售）工作的雇员的姓名，假定不知道销售部的部门编号 
SELECT ename,deptno FROM emp WHERE job = 'SALESMAN';

--9、列出薪金高于公司平均水平的所有雇员 
SELECT ename,sal FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);

--10、列出与“SCOTT”从事相同工作的所有雇员
SELECT ename FROM emp 
WHERE job = (SELECT job FROM emp WHERE ename = 'SCOTT');
--11、列出某些雇员的姓名和薪金，条件是他们的薪金等于部门30中任何一个雇员的薪金 
SELECT ename, sal FROM emp
WHERE sal IN(SELECT sal FROM emp WHERE deptno = 30) AND deptno <> 30;

--12、列出某些雇员的姓名和薪金，条件是他们的薪金高于部门30中所有雇员的薪金 
SELECT ename, sal FROM emp
WHERE sal > ALL(SELECT sal FROM emp WHERE deptno = 30) AND deptno <> 30;

--13、列出每个部门的信息以及该部门中雇员的数量 
SELECT * FROM (
SELECT COUNT(*),e.deptno FROM emp e GROUP BY deptno) e,dept d 
WHERE d.deptno = e.deptno;

--14、列出所有雇员的雇员名称、部门名称和薪金  
SELECT e.ename,d.dname,e.sal FROM emp e,dept d LEFT JOIN d.deptno on e.deptno;

--15、列出从事同一种工作但属于不同部门的雇员的不同组合

--16、列出分配有雇员数量的所有部门的详细信息，即使是分配有0个雇员

--17、列出各种类别工作的最低工资  
SELECT * FROM 
(SELECT e.*,row_number() OVER (PARTITION BY job ORDER BY sal) 编号 FROM emp e)
WHERE  编号 <= 1; 

--18、列出各个部门的MANAGER（经理）的最低薪金 
SELECT min(sal) FROM emp e WHERE e.job='MANAGER' GROUP BY deptno;


--19、列出按年薪排序的所有雇员的年薪
SELECT e.*,sal*12+nvl(comm,0) 年薪 FROM emp e ORDER BY 年薪 DESC;

--20、列出薪金水平处于第四位的雇员
SELECT * FROM (
SELECT e.*,ROWNUM r FROM (
SELECT * FROM emp ORDER BY sal DESC) e) 
WHERE r = 4;
--
SELECT COUNT(*) FROM emp CROSS JOIN dept;

SELECT emp.ename FROM emp;

--隐式内连接
SELECT * FROM emp e,dept d WHERE e.deptno = d.deptno;
--标准的SQL
SELECT * FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;
--外连接
-- SELECT * FROM emp e,dept d WHERE d.deptno = e.deptno(+);
--标准外连接(左)
SELECT * FROM dept d LEFT OUTER JOIN emp e ON e.deptno = d.deptno;
--标准外连接(右)
SELECT * FROM emp e RIGHT OUTER JOIN dept d ON e.deptno = d.deptno;

SELECT dname FROM dept;
--INTERSECT --交集
--UNION     --并集
--UNION ALL --并集总补充
--MINUS     --差集
SELECT ename FROM emp;
--分页查询

SELECT * FROM (
SELECT e.*,ROWNUM r FROM emp e)
WHERE r >3 AND r <7;


