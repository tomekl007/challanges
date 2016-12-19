DROP TABLE IF EXISTS votes;

CREATE TABLE votes ( name CHAR(10), votes INT );

INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);

select * from votes order by votes DESC;