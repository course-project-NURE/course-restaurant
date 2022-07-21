ALTER TABLE `course_restaurant`.`address`
    CHANGE COLUMN `hous_n` `house_n` INT UNSIGNED NOT NULL ;

ALTER TABLE `course_restaurant`.`menu_item`
    CHANGE COLUMN `discription` `description` TEXT NOT NULL ;