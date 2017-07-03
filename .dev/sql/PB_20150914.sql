
--创建表空间
create TABLESPACE taoble_yao
datafile 'd:/table_yao.dbf'
size 1m
autoextend ON;
--删除表空间
drop tablespace TEST;

--用户
CREATE USER yao IDENTIFIED BY ok
DEFAULT TABLESPACE taoble_yao
TEMPORARY TABLESPACE temp;
ALTER USER yao
QUOTA 0 ON SYSTEM
QUOTA UNLIMITED ON taoble_yao;

ALTER USER yao QUOTA UNLIMITED ON tp2;
--删除用户
--DROP USER yao;

--权限和角色
--CONNECT
--RESOURCE
--DBA
GRANT CREATE SESSION TO yao;
GRANT DBA TO yao;
GRANT CONNECT to yao;
GRANT RESOURCE to yao;

--收回权限
REVOKE CREATE SESSION FROM yao;
REVOKE DBA FROM yao;
--创建表
CREATE TABLE student(
       stuNo NUMBER(4),
       stuName VARCHAR2(50),
       stuAge NUMBER(3),
       score NUMBER(4,1),
       email Varchar2(100)
);
--主键约束:保证某个列值唯一
ALTER TABLE student
ADD CONSTRAINT pk_student_stuno PRIMARY KEY (stuno);

--唯一约束
ALTER table student
ADD CONSTRAINT uq_student_stuname UNIQUE(stuname);

--检查约束
ALTER TABLE student
ADD CONSTRAINT ck_student_stuname CHECK (LENGTH(stuname)>=2);
ALTER TABLE student
ADD CONSTRAINT ck_student_stuage CHECK (stuage>=18 AND stuage <=120);
--创建主建表
CREATE TABLE classes(
       cid NUMBER(4),
       cname VARCHAR2(50)
       );
--添加新列
ALTER TABLE student
ADD cid NUMBER(4);
--给主键增加主键约束
ALTER TABLE classes
ADD CONSTRAINT pk_classes_cid PRIMARY KEY(cid);
--创建外键的约束
ALTER TABLE student
ADD CONSTRAINT fk_student_cid
FOREIGN KEY (cid) REFERENCES classes(cid);

------------------------------------------------------------

INSERT INTO classes(cid,cname)
VALUES(1,'三年一班');
INSERT INTO classes VALUES(2,'三年二班');
INSERT INTO classes VALUES(3,'三年三班');
---TCL--(维护后调用)
--回滚
ROLLBACK;
--提交
COMMIT;

SELECT * FROM student;

--批量添加
INSERT INTO A(aid,aname)
SELECT cid,cname FROM classes;

--添加列
ALTER TABLE student
ADD birthday DATE;

--添加带空值的行数据
INSERT INTO student(stuno,cid,birthday)
VALUES (1,1,SYSDATE);
INSERT INTO student 
VALUES (2,NULL,NULL,NULL,NULL,2,to_date('2015-9-9','yyyy-mm-dd'));
INSERT INTO student
VALUES (3,NULL,NULL,NULL,NULL,2,DATE'2015-9-10');

--更新update
UPDATE student SET stuname = '三张', stuage = 29
WHERE stuno = 1;

--查询
--LIKE '李%_' OR    IN('李四')

SELECT *
FROM student
WHERE (stuage = 19);


SELECT stuname
FROM student
WHERE (stuage < 20);


SELECT * FROM student
WHERE (stuage = 19 AND stuname = '张三');
--别名alias
SELECT * FROM student s;
SELECT stuno AS "学 号" FROM student 学生信息表;

--模糊查询
--WHERE LIKE BETWEEN AND IN
--规避查询
--DISTINCT --不重复的内容
--排序 order by 
SELECT * FROM student 
WHERE email IS NOT NULL
ORDER BY stuage DESC,--降序
         score ASC;--升序

--删除表
DELETE FROM student s WHERE stuno = 1;


--函数

--CONCAT (c1,c2);
