--1、动态sql创建表
BEGIN 
    EXECUTE IMMEDIATE 'create table bonus(id number,amt number)';
END;
SELECT * FROM bonus;
--2.动态sql查询
DECLARE 
       sql_stmt VARCHAR2(200);
       emp_id NUMBER(10):='&emp_id';
       emp_rec emp%ROWTYPE;
       
BEGIN
  sql_stmt:='select * from emp where empno =: id';
  EXECUTE IMMEDIATE sql_stmt INTO emp_rec USING emp_id;
  dbms_output.put_line(emp_rec.sal);
END;


----------------
