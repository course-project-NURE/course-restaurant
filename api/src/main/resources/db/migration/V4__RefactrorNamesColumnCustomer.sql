ALTER TABLE `course_restaurant`.`customer`
    CHANGE COLUMN `promoReceived` `promo_received` TINYINT NOT NULL ,
    CHANGE COLUMN `promoAvailable` `promo_available` TINYINT NOT NULL ;