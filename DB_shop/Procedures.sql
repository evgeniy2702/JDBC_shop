CREATE DEFINER=`root`@`localhost` PROCEDURE `updateQuantityProd`(IN prodID INT, OUT valueProd INT)
BEGIN
UPDATE `product` 
SET `quantity` = `quantity` + valueProd
WHERE `id_prod` = prodID;
END