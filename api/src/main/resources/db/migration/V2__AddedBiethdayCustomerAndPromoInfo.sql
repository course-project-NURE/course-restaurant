ALTER TABLE `course_restaurant`.`customer`
    ADD COLUMN `birthdate` DATE NOT NULL AFTER `phone`,
    ADD COLUMN `promoReceived` TINYINT NOT NULL AFTER `birthdate`,
    ADD COLUMN `promoAvailable` TINYINT NOT NULL AFTER `promoReceived`;