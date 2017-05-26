--for postgresql
--drop first
drop TABLE IF EXISTS t_blog;
DROP SEQUENCE IF EXISTS seq_blog;
--then create
CREATE SEQUENCE seq_blog;
CREATE TABLE t_blog
(
  id   INTEGER DEFAULT nextval('seq_blog'),
  name VARCHAR(50),
  PRIMARY KEY (id)
);

