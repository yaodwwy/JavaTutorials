--列出至少有一个员工的所有部门
SELECT * FROM (
SELECT count(emp.deptno) eco,dept.dname FROM dept,emp 
WHERE emp.deptno = dept.deptno 
GROUP BY dept.dname
ORDER BY eco
),emp WHERE eco > 1;







SELECT deptno FROM emp GROUP BY deptno HAVING COUNT(*) > 1;
