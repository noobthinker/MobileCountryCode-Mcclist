DELIMITER $$

DROP PROCEDURE IF EXISTS `en2ch` $$
CREATE DEFINER=`root`@`%` PROCEDURE `en2ch`()
BEGIN
DECLARE done INT DEFAULT FALSE;
declare en,zn varchar(50);
declare cur cursor for select e_n,z_n from sim.country;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
OPEN cur;
read_loop: LOOP
FETCH cur INTO en, zn;
IF done THEN
LEAVE read_loop;
END IF;
update sim.habit set zh_country=en where en_country=zn;
END LOOP;
END $$

DELIMITER ;