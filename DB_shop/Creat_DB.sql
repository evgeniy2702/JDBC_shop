set global time_zone = '-3:00';

/*Создание ДБ магазин*/
CREATE SCHEMA `shop` ;

/*Переход в ДБ магазин*/
use shop;

/*Создание таблицы ОТДЕЛ в магазине*/
create table `Department`
(
	`id_dep` int not null auto_increment primary key,
    `name_dep` varchar(25) not null,
    `type_department` varchar(25) not null
);

/*Создание таблицы ПРОДАВЕЦ в магазине*/
create table `saler`
(
	`id_saler` int not null auto_increment primary key,
    `FIO` varchar(100) not null,
    `date_born` varchar(10) not null,
    `salary` double not null,
    `dep_id_s` int not null
);

/*Создание таблицы ТОВАР в магазине*/
create table `product`
(
	`id_prod` int not null auto_increment primary key,
    `name_product` varchar(100) not null,
    `quantity` int not null,
    `dep_id_p` int not null
);

/*Создание внеш ключей связывающих товар и продавца с отделом магазина*/
ALTER TABLE `shop`.`product` 
ADD INDEX `fk_id_dep_p_id_dep_idx` (`dep_id_p` ASC);
ALTER TABLE `shop`.`product` 
ADD CONSTRAINT `fk_id_dep_p_id_dep`
  FOREIGN KEY (`dep_id_p`)
  REFERENCES `shop`.`department` (`id_dep`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  ALTER TABLE `shop`.`saler`
  ADD INDEX `fk_id_dep_s_id_dep_idx` (`dep_id_s` ASC);
  ALTER TABLE `shop`.`saler`
  ADD CONSTRAINT `fk_id_dep_s_id_dep`
   FOREIGN KEY (`dep_id_s`)
   REFERENCES `shop`.`department` (`id_dep`)
   ON DELETE CASCADE
   ON UPDATE CASCADE;
   
   USE `shop`;
DROP procedure IF EXISTS `getIdProd`;

DELIMITER $$
USE `shop`$$
CREATE PROCEDURE `getIdProd` (OUT nameProd varchar(100))
BEGIN
	SELECT `id_prod`
    FROM `product`
    WHERE `name_product` = nameProd;
END$$

DELIMITER ;

USE `shop`$$
DROP procedure IF EXISTS `updateQuantityProd`;

DELIMITER $$
USE `shop`$$
CREATE PROCEDURE `updateQuantityProd`(IN prodID INT, OUT valueProd INT)
BEGIN
UPDATE `product` 
SET `quantity` = `quantity` + valueProd
WHERE `id_prod` = prodID;
END$$

DELIMITER ;




SET @valueProd = 5;
CALL `updateQuantityProd`(1,@valueProd);



set @nameProd = 'мыло';
CALL getIdProd(@nameProd);
select @nameProd;

