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
    `date_born` date not null,
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
   