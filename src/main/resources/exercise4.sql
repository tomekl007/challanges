DROP TABLE IF EXISTS bugs;
create table bugs (id INT, open_date DATETIME, close_date DATETIME, severity text);

INSERT INTO bugs VALUES (1, '2010-01-01', '2010-01-02', 'minor');
INSERT INTO bugs VALUES (1, '2010-01-02', '2010-01-03', 'minor');

select count(*) from bugs where open_date <= date('2010-01-01') AND close_date > date('2010-01-01');
