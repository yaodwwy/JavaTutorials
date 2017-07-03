--安装数据库
--开启scott用户
--安装PL/SQL
--配置关键字大写、中文
--使用system登录并开始默单词!

--part1.

--创建表空间-------------------------------------------------为什么1k的表空间无法创建?
CREATE TABLESPACE tablespace_admin
DATAFILE 'D:\Java\Oracle\oradata\orcl\tablespace_by_admin'
SIZE 2m
AUTOEXTEND ON;
--修改表空间大小为64K
ALTER DATABASE DATAFILE 'D:\Java\Oracle\oradata\orcl\tablespace_by_admin'
RESIZE 1048K;

--创建用户指定表空间
CREATE USER admini IDENTIFIED BY ok
TEMPORARY TABLESPACE tablespace_admin
DEFAULT TABLESPACE tablespace_admin;

--修改口令
ALTER USER admini IDENTIFIED BY admini;

--删除用户 cascade 删除包含模式对象 --------------------------重要!
DROP USER admini CASCADE;

--授权登录
GRANT CONNECT , RESOURCE TO admini;
GRANT DBA TO admini;
--收回权限
REVOKE DBA FROM admini;


--启用用户登录创建表
CREATE TABLE student(
       stuid NUMBER(4),
       stucid NUMBER(2),
       stuname VARCHAR2(4),
       stuage NUMBER(2),
       score NUMBER(3,1)
);

--添加字段
ALTER TABLE student ADD(birthday DATE);
ALTER TABLE student ADD(className DATE);

ALTER TABLE student ADD(
      stuPhone VARCHAR2(11),
      stuAdd VARCHAR2(35)
);

--修改字段
ALTER TABLE student MODIFY(classid NUMBER(4));

--删除字段
ALTER TABLE student DROP COLUMN testcolumn;
ALTER TABLE student DROP (testcolumn);

--清空表记录-----------------------------------------------注意安全!
TRUNCATE TABLE student;

--删除表及全部数据-----------------------------------------注意安全!
DROP TABLE student;

--显示所有字段(结构)
--DESC student;

--添加主键约束
ALTER TABLE student ADD CONSTRAINT pk_stuid PRIMARY KEY (stuid);

--添加唯一约束
ALTER TABLE student ADD CONSTRAINT uq_stucid UNIQUE (stucid);

--添加检查约束
ALTER TABLE student
ADD CONSTRAINT ck_stuage CHECK( stuage BETWEEN 16 AND 50);

--新建表2
CREATE TABLE classes(
       classid NUMBER(4),
       classname VARCHAR2(4)
);
--表2添加主键
ALTER TABLE classes
ADD CONSTRAINT Pk_classid
PRIMARY KEY (classid);

--添加外键约束
ALTER TABLE classes 
ADD CONSTRAINT fk_classid 
FOREIGN KEY (classid) REFERENCES student(stuid);

--查看约束
SELECT * FROM user_constraints WHERE table_name = 'student';

--ddl---pqrt-----
SELECT * FROM student;

INSERT INTO student(stuid,stuname,stuage)
VALUES('1','张三',26);

--字符函数
--CONCAT --字符串的连接
--CONCAT(a,CONCAT(b,c))||d;
--NVL(a,'暂无详细描述');

--数字函数
--TRUNC --截取函数
--TRUNC(nubmer,n);--n可以为负数
--ROUND --四舍五入截取
SELECT CEIL(45.12) FROM dual; --向上进位
SELECT FLOOR(45.12) FROM dual; --向下忽略

--日期函数
--SYSDATE--系统日期
SELECT EXTRACT(DAY FROM SYSDATE)||'-'||EXTRACT(MONTH FROM SYSDATE) FROM dual;--提取日期函数
SELECT ROUND(SYSDATE,'year') FROM dual;
SELECT EXTRACT(DAY FROM SYSDATE)||'/'||EXTRACT(MONTH FROM SYSDATE)|| '/'||EXTRACT(YEAR FROM SYSDATE) FROM dual;

--转换函数
--to_date();

SELECT to_date('2012年12月12','yyyy"年"mm"月"dd') FROM dual; 
--to_char();
SELECT to_char(SYSDATE,'yyyy-mm-dd hh24:mi:ss') FROM dual; 


--测试/将生日在1995年1月15日前的所有学生成绩+5分；满分100分；
SELECT MOD(7,2) FROM dual;
UPDATE student SET birthday  = to_date('1995-1-'||stuno,'yyyy-mm-dd') WHERE stuno<=31;
UPDATE student SET birthday  = to_date('1991-3-'||mod(stuno,31),'yyyy-mm-dd') 
WHERE  stuno>31 and stuno<=61;
UPDATE student SET birthday  = to_date('1990-5-'||mod(stuno,31),'yyyy-mm-dd')
WHERE stuno>62 AND mod(stuno,31)<>0;

--内容:
UPDATE student SET score = score + 5
WHERE birthday < to_date('1995-1-15','yyyy-mm-dd') 
AND EXTRACT(YEAR FROM birthday)=1995
AND stuname IS NOT NULL
AND score <=95;

UPDATE student SET score = 100
WHERE birthday < to_date('1995-1-15','yyyy-mm-dd') 
AND EXTRACT(YEAR FROM birthday)=1995

AND score >=95;

SELECT stuname "姓名",score "分数",birthday "生日" FROM student;

--聚合函数

SELECT cid,COUNT(stuno) FROM student GROUP BY cid;

SELECT EXTRACT (YEAR FROM birthday) "年份",COUNT(stuno) "人数" 
FROM student GROUP BY EXTRACT (YEAR FROM birthday);
--测试取出9月13日至10月13日的人数
SELECT COUNT(stuno) "人数" FROM student---------------有问题!!!
WHERE birthday >= to_date('01-13','mm-dd') AND birthday <= to_date('02-13','mm-dd');

SELECT COUNT(stuno) "人数" FROM student
WHERE (extract(MONTH FROM birthday )=1 AND EXTRACT (DAY FROM birthday)>=13)
OR (extract(MONTH FROM birthday )=2 AND EXTRACT (DAY FROM birthday)<=13);

--测试 查询每个部门总工资--最高工资--平均工资
SELECT deptno 部门,
SUM(sal) 总工资, 
MAX(sal) 最高工资,
round(AVG(sal),2) 平均工资
FROM emp GROUP BY deptno;

SELECT * FROM emp;






