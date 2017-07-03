/*
-- noinspection SqlNoDataSourceInspectionForFile

*/
/*
  以下内容有:动态sql(4种),序列,函数,游标,动态游标.
*//*


DECLARE
     v NUMBER:=1;
BEGIN
     LOOP
      dbms_output.put_line('数字:' || v);
       v := v+1;
       EXIT WHEN v>10;
       --IF v>10 THEN EXIT;
       --END IF
     END LOOP;
     
     v:=1;
     WHILE v<10 LOOP
       dbms_output.put_line('NUMBER:' || v);
       v:=v+1;
     END LOOP;
     
     FOR i IN 1..10 LOOP
         dbms_output.put_line('number:' || i);
     END LOOP;
END;


DECLARE 
  v_ename Varchar2(20);
  v_rate NUMBER(7,2);
  c_rate_incr CONSTANT NUMBER(7,2) :=1.10;
 BEGIN
   --方法一:通过select into给变量赋值
   SELECT ename, sal* c_rate_incr INTO v_ename, v_rate
   FROM emp
   WHERE empno = '7788';
   --方法二:通过赋值操作符"="给变量幅值
   v_ename:='SCOTT';
  END;
  
  SELECT emp.*,dept.* FROM emp LEFT OUTER JOIN dept ON emp.deptno = dept.deptno;
  SELECT emp.*,dept.* FROM dept NATURAL JOIN emp;
  
  
  SELECT * FROM emp;
  
  
DECLARE
   v_counter NUMBER := 1;
BEGIN
   LOOP
             dbms_output.put_line('v_counter 的当前值为:' || v_counter);
             v_counter := v_counter + 1;
             IF v_counter > 10 THEN
                          GOTO labelOffLOOP ;
             ELSE
                          NULL; --为了使语法变得有意义,去掉Null会报语法错误
                          END IF;
    END LOOP;
    << labelOffLOOP>>
            dbms_output.put_line('v_counter 的当前值为:' || v_counter);
END;


DECLARE
            sal emp.sal%TYPE;
            sal2 sal%TYPE;
            deptno1 emp.deptno%TYPE;
            
BEGIN
            SELECT sal INTO sal FROM emp WHERE ename = 'SMITH';
            SELECT deptno INTO deptno1 FROM emp  WHERE ename = 'SMITH';
            SELECT AVG(sal) INTO sal2 FROM emp WHERE deptno = deptno1;
            
            IF  sal < sal2   THEN
                UPDATE emp SET sal = sal + 100;
                dbms_output.put_line('涨100工资了!');
            ELSE
                dbms_output.put_line('什么也没发生');
            END IF;
            COMMIT;
END;

DECLARE
            sal emp.sal%TYPE;
            sal2 sal%TYPE;
            deptno1 emp.deptno%TYPE;
            s1 sal%TYPE;
            
BEGIN
            SELECT sal INTO sal FROM emp WHERE ename = 'SMITH';
            SELECT deptno INTO deptno1 FROM emp  WHERE ename = 'SMITH';
            SELECT AVG(sal) INTO sal2 FROM emp WHERE deptno = deptno1;
            
            CASE 
              WHEN sal < sal2  THEN
                UPDATE emp SET sal = sal + 100;
                dbms_output.put_line('涨100工资了!');
              WHEN sal = sal2  THEN
                UPDATE emp SET sal = sal + 50;
                dbms_output.put_line('涨50工资了!');
              ELSE
                dbms_output.put_line('什么也没发生');
            END CASE;
            COMMIT;
            
            --等值case
            SELECT sal INTO s1 FROM emp WHERE ename = 'SMITH';
            CASE s1 
              WHEN 1300 THEN
                dbms_output.put_line('工资正确');
              ELSE
                dbms_output.put_line('工资异常');
            END CASE;
END;


SELECT ename,sal,
CASE
       WHEN sal <1500 THEN '低收入'
       WHEN sal >=1500 AND sal <4500 THEN '中等收入'
       ELSE '高收入'
END
"收入等级"
FROM emp;





DECLARE
     ex EXCEPTION;
     v NUMBER;
     msg_no_data_found CONSTANT VARCHAR2(20):='数据检查失败!';
     msg_ex CONSTANT VARCHAR2(20):='编号错误!';
     msg_others CONSTANT VARCHAR2(20):='系统繁忙!';
BEGIN
     SELECT empno INTO v FROM emp WHERE ename='KING';
     IF v>1 THEN
        RAISE ex;
     END IF;
     
     COMMIT;
     EXCEPTION
       WHEN no_data_found THEN
            dbms_output.put_line(msg_no_data_found);
       WHEN ex THEN
            dbms_output.put_line(msg_ex);
       WHEN OTHERS THEN
            dbms_output.put_line(msg_others);
END;     
     
--for循环游标
CREATE OR REPLACE PROCEDURE p1
AS
       CURSOR cur IS SELECT * FROM emp;
       avgsal NUMBER(7,2);
BEGIN
       SELECT AVG(sal) INTO avgsal FROM emp;
       FOR r IN cur LOOP
         dbms_output.put_line(r.ename||':('||r.sal||'-'||avgsal||')'||
           CASE
             WHEN r.sal<avgsal THEN '低收入'
             WHEN r.sal<avgsal THEN '还行'
             ELSE '高收入'
           END);
       END LOOP;
END;
----------------执行
BEGIN
  p1;
END;

--loop循环游标
CREATE OR REPLACE PROCEDURE p2
AS
       CURSOR cur IS SELECT * FROM emp;
       row_cur emp%ROWTYPE;
       avgsal NUMBER(7,2);
BEGIN
  OPEN cur;
       SELECT AVG(sal) INTO avgsal FROM emp;
        LOOP
          FETCH cur INTO row_cur;
          EXIT WHEN cur%NOTFOUND;
               dbms_output.put_line(row_cur.ename||':('||row_cur.sal||'-'||avgsal||')'||
               CASE
                 WHEN row_cur.sal<avgsal THEN '低收入2'
                 WHEN row_cur.sal<avgsal THEN '还行'
                 ELSE '高收入2'
               END);
        END LOOP;
  CLOSE cur;
END;
----------------执行
BEGIN
  p2;
END;

--while循环游标
CREATE OR REPLACE PROCEDURE p3
AS
       CURSOR cur IS SELECT * FROM emp;
       row_cur emp%ROWTYPE;
       avgsal NUMBER(7,2);
BEGIN
  OPEN cur;
       SELECT AVG(sal) INTO avgsal FROM emp;
          FETCH cur INTO row_cur;
        WHILE cur%FOUND LOOP
          
          dbms_output.put_line(row_cur.ename||':('||row_cur.sal||'-'||avgsal||')'||
               CASE
                 WHEN row_cur.sal<avgsal THEN '低收入3'
                 WHEN row_cur.sal<avgsal THEN '还行'
                 ELSE '高收入3'
               END);
         FETCH cur INTO row_cur;
        END LOOP;
  CLOSE cur;
END;
----------------执行
BEGIN
  p3;
END;




--传入部门编号 , 返回该部门下高于平均工资的人数；
CREATE OR REPLACE PROCEDURE p5
(d NUMBER,c OUT NUMBER)
AS
     dno NUMBER := d;
     counts NUMBER := c;
     dname VARCHAR2(30);
BEGIN
  SELECT COUNT(empno),dname 
  INTO counts,dname 
  FROM emp e,dept d 
  WHERE e.deptno = dno 
  AND e.deptno = d.deptno 
  AND sal>  (SELECT AVG(sal) FROM emp WHERE deptno = dno);
END;


DECLARE
     c NUMBER;
     dno NUMBER := &dno;
BEGIN
   p5(dno,c);
   dbms_output.put_line('部门编号'||dno||'大于平均工资的人数为:'||c||'人');
   EXCEPTION
      WHEN no_data_found THEN
        dbms_output.put_line('未找到该部门');
      WHEN OTHERS THEN
        dbms_output.put_line('未知异常');
END;






------------
CREATE OR REPLACE PROCEDURE emp_Demo1 AS
empnum NUMBER; --number类型不用写长度?表示多少?   默认表示四位
avgsal NUMBER;
empname VARCHAR2(50);
sale NUMBER;
CURSOR empavg IS
       SELECT (SELECT NAME FROM employees e 
       WHERE e.id = t.id),SUM(t.totalmoney) 
       FROM salerecord t
       GROUP BY t.employeeid;
BEGIN
  SELECT COUNT(ID) INTO empnum FROM employees;--获取员工人数
  SELECT SUM(t.totalmoney)/empnum INTO avgsal
  FROM salerecord t
  WHERE to_date('2009-1-1','yyyy-mm-dd hh:mi:ss') <t.contacttime
  AND t.contacttime < to_date('2010-1-1','yyyy-mm-dd hh:mi:ss');
  --获取2009年员工平均销售额；
  dbms_output.put_line('09年员工平均销售额是:'||avgsal);
  OPEN empavg;
       LOOP
          FETCH empavg INTO empname,sale ;
          IF sale < avgsal THEN 
            dbms_output.put_line(empname||'需要努力');
          END IF;
          IF sale = avgsal THEN 
            dbms_output.put_line(empname||'良好');
          END IF;
          IF sale > avgsal THEN 
            dbms_output.put_line(empname||'优秀');
          END IF;
          EXIT WHEN empavg %NOTFOUND;            
       END LOOP;
  CLOSE empavg;
END emp_Demo1;



--动态游标
DECLARE
    --自定义声明
    TYPE mycursor IS REF CURSOR;
    cur mycursor;
    
    cur1 SYS_REFCURSOR;--系统声明
    
    
    deptrow dept%ROWTYPE;
    emprow emp%ROWTYPE;
BEGIN
  
  OPEN cur FOR SELECT * FROM dept;
       LOOP
         FETCH cur INTO deptrow;
         EXIT WHEN cur%NOTFOUND;
         
         dbms_output.put_line(deptrow.dname);
       END LOOP;
         dbms_output.put_line('--------------------------');
  CLOSE;
  
  OPEN cur1 FOR SELECT * FROM dept;
    FETCH cur1 INTO emprow;
       WHILE cur1%FOUND LOOP
         dbms_output.put_line(emprow.ename);
         FETCH cur1 INTO emprow;
       END LOOP;
  CLOSE;
END;


--序列
create sequence SEQ
minvalue 1 --最小值
maxvalue 9999999999999999999999999999
start with 1 --开始数字
increment by 1  --步进
cache 20  --单次缓冲
NOCYCLE;  --是否循环
SELECT seq.nextval FROM dual;  --查询下一个
SELECT seq.currval FROM dual;  --查询当前值

-------------------------
-------动态sql(1)--------
-------------------------
--传入一个员工名或员工编号,返回员工所有信息;
CREATE OR REPLACE PROCEDURE p6
(eno NUMBER, ena VARCHAR2, cur OUT sys_refcursor)
IS
sqls VARCHAR2(200);
BEGIN
       sqls := 'select * from emp where 1=1';
       
       IF eno > 0 THEN
          sqls := sqls ||'and empno = '||eno;
       END IF;
       dbms_output.put_line(sqls);
       OPEN cur FOR sqls;
END;
---执行测试
DECLARE
  eno NUMBER := &eno;
  ena VARCHAR2(20);
  cur SYS_REFCURSOR;
  r emp%ROWTYPE;
BEGIN
  p6(eno,ena,cur);
  OPEN cur;
  LOOP 
   
    FETCH cur INTO r;
     WHEN cur%NOTFOUND EXIT;
    dbms_output.put_line(r.ename||r.eno);
  END LOOP;
  CLOSE cur;
  END;
--------------
DECLARE
cur SYS_REFCURSOR;
r emp%ROWTYPE;
BEGIN 
  p6(&编号,NULL,cur);
  LOOP
    FETCH cur INTO r;
    IF cur%NOTFOUND THEN
      EXIT;
      END IF;
      dbms_output.put_line('姓名:'||r.ename);
      END LOOP;
      CLOSE cur;
END;

-------------------------
-------动态sql(2)--------
-------------------------
DECLARE
       sqls VARCHAR2(200) := 'insert into dept values(:dno,:dna,:dloc)';
       dno NUMBER := &dno;
       dna VARCHAR2(20) := '&dna';
       dloc VARCHAR2(20) := '&dloc';
BEGIN
  EXECUTE IMMEDIATE sqls USING dno,dna,dloc;
  COMMIT;
  
  dbms_output.put_line('部门'||dno||'新建成功!');
EXCEPTION WHEN OTHERS THEN
          ROLLBACK;
END;

-------------------------
-------动态sql(3)--------
-------------------------
DECLARE
       sqls VARCHAR2(200) := 'insert into dept values(seq.nextval,:dna,:dloc) returning deptno into :dno';
       dno NUMBER;
       dna VARCHAR2(20) := '&dna';
       dloc varchar2(20) := '&dloc';
BEGIN
  EXECUTE IMMEDIATE sqls USING dna,dloc RETURNING INTO dno;
  COMMIT;
  
  dbms_output.put_line('部门'||dno||'新建成功!');
EXCEPTION WHEN OTHERS THEN
          ROLLBACK;
END;


-------------------------
-------动态sql(4)-------- 
-------------------------
--:name 占位符,表示空位需要用into using \ returning into给值
DECLARE
       sqls VARCHAR2(200) := 'selcet deptno into :dno from dept where dname =:dna';
       dno NUMBER;
       dna VARCHAR2(20) := '&dna';
BEGIN
  EXECUTE IMMEDIATE sqls INTO dno USING dna;
  COMMIT;
  
  dbms_output.put_line('部门'||dna||'的编号是:'||dno);
EXCEPTION WHEN OTHERS THEN
          ROLLBACK;
END;

--函数--必须嵌套使用
--简单的计算,小业务使用函数;
CREATE OR REPLACE FUNCTION f1
(n1 NUMBER, n2 NUMBER)
RETURN NUMBER --返回类型
IS
  res NUMBER(4);
BEGIN
  res := n1 + n2;
  RETURN res; --返回值;
END;
--测试 
SELECT f1(1,2) FROM dual;
*/
