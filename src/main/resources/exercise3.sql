DROP TABLE IF EXISTS sometbl;
CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );

INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'),
(3,'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag');
select * from sometbl;

DELIMITER //

DROP PROCEDURE IF EXISTS explode_table //
CREATE PROCEDURE explode_table(bound VARCHAR(255))

  BEGIN

    DECLARE id INT DEFAULT 0;
    DECLARE value TEXT;
    DECLARE occurrence INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE splitted_value TEXT;
    DECLARE done INT DEFAULT 0;
    DECLARE cur CURSOR FOR SELECT sometbl.id, sometbl.name
                                         FROM sometbl
                                         WHERE sometbl.name != '';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    DROP TEMPORARY TABLE IF EXISTS table2;
    CREATE TEMPORARY TABLE table2(
    `id` INT NOT NULL,
    `NAME` VARCHAR(255) NOT NULL
    ) ENGINE=Memory;

    OPEN cur;
      read_loop: LOOP
        FETCH cur INTO id, value;
        IF done THEN
          LEAVE read_loop;
        END IF;

        SET occurrence = (SELECT LENGTH(value)
                                 - LENGTH(REPLACE(value, bound, ''))
                                 +1);
        SET i=1;
        WHILE i <= occurrence DO
          SET splitted_value =
          (SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(value, bound, i),
          LENGTH(SUBSTRING_INDEX(value, bound, i - 1)) + 1), bound, ''));

          INSERT INTO table2 VALUES (id, splitted_value);
          SET i = i + 1;

        END WHILE;
      END LOOP;

      SELECT * FROM table2;
    CLOSE cur;
  END; //

CALL explode_table('|')