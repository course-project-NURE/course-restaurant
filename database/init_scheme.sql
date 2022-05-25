SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema course_restaurant
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `course_restaurant` ;

-- -----------------------------------------------------
-- Schema course_restaurant
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `course_restaurant` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `course_restaurant` ;

-- -----------------------------------------------------
-- Table `course_restaurant`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`address` (
  `id_address` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `street` TEXT NOT NULL,
  `hous_n` INT UNSIGNED NOT NULL,
  `flat` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`id_address`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`category` (
  `id_category` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_category`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`menu_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`menu_item` (
  `id_menu_item` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `price` FLOAT(6,2) UNSIGNED NOT NULL,
  `discription` TEXT NOT NULL,
  `fk_category` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_menu_item`),
  INDEX `fk_Dish_Catalog1_idx` (`fk_category` ASC) VISIBLE,
  CONSTRAINT `fk_Menu_item_Category1`
    FOREIGN KEY (`fk_category`)
    REFERENCES `course_restaurant`.`category` (`id_category`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`order_status` (
  `id_order_status` TINYINT UNSIGNED NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_order_status`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`role` (
  `id_role` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`login_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`login_info` (
  `id_login_info` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` CHAR(20) NOT NULL,
  `fk_role` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_login_info`),
  INDEX `fk_Login_info_Role1_idx` (`fk_role` ASC) VISIBLE,
  CONSTRAINT `fk_Login_info_Role1`
    FOREIGN KEY (`fk_role`)
    REFERENCES `course_restaurant`.`role` (`id_role`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`customer` (
  `id_customer` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fk_login_info` INT UNSIGNED NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` TEXT NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_customer`),
  INDEX `fk_Customer_Login_info1_idx` (`fk_login_info` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_Login_info1`
    FOREIGN KEY (`fk_login_info`)
    REFERENCES `course_restaurant`.`login_info` (`id_login_info`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`order` (
  `id_order` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `comment` TEXT NOT NULL,
  `fk_status` TINYINT UNSIGNED NOT NULL,
  `fk_user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_order`),
  INDEX `fk_Order_Order_status1_idx` (`fk_status` ASC) VISIBLE,
  INDEX `fk_Order_User1_idx` (`fk_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Order_status1`
    FOREIGN KEY (`fk_status`)
    REFERENCES `course_restaurant`.`order_status` (`id_order_status`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Order_User1`
    FOREIGN KEY (`fk_user_id`)
    REFERENCES `course_restaurant`.`customer` (`id_customer`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`basket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`basket` (
  `Order_id` INT UNSIGNED NOT NULL,
  `Menu_item_id` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Order_id`, `Menu_item_id`),
  INDEX `fk_Basket_Menu_item1_idx` (`Menu_item_id` ASC) VISIBLE,
  INDEX `fk_Basket_Order1_idx` (`Order_id` ASC) VISIBLE,
  CONSTRAINT `fk_Basket_Menu_item1`
    FOREIGN KEY (`Menu_item_id`)
    REFERENCES `course_restaurant`.`menu_item` (`id_menu_item`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Basket_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `course_restaurant`.`order` (`id_order`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`customer_has_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`customer_has_address` (
  `Customer_id` INT UNSIGNED NOT NULL,
  `Address_id` INT UNSIGNED NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Customer_id`, `Address_id`),
  INDEX `fk_Customer_has_Address_Address1_idx` (`Address_id` ASC) VISIBLE,
  INDEX `fk_Customer_has_Address_Customer1_idx` (`Customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_has_Address_Address1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `course_restaurant`.`address` (`id_address`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Customer_has_Address_Customer1`
    FOREIGN KEY (`Customer_id`)
    REFERENCES `course_restaurant`.`customer` (`id_customer`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`delivery` (
  `id_delivery` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `price` FLOAT(6,2) NOT NULL,
  `comment` TEXT NOT NULL,
  `fk_order` INT UNSIGNED NOT NULL,
  `fk_address` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_delivery`),
  INDEX `fk_Delivery_Order1_idx` (`fk_order` ASC) VISIBLE,
  INDEX `fk_Delivery_Adress1_idx` (`fk_address` ASC) VISIBLE,
  CONSTRAINT `fk_Delivery_Adress1`
    FOREIGN KEY (`fk_address`)
    REFERENCES `course_restaurant`.`address` (`id_address`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Delivery_Order1`
    FOREIGN KEY (`fk_order`)
    REFERENCES `course_restaurant`.`order` (`id_order`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`staff` (
  `id_staff` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fk_login_info` INT UNSIGNED NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` TEXT NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `salary` INT NOT NULL,
  PRIMARY KEY (`id_staff`),
  INDEX `fk_Staff_Login_info1_idx` (`fk_login_info` ASC) VISIBLE,
  CONSTRAINT `fk_Staff_Login_info1`
    FOREIGN KEY (`fk_login_info`)
    REFERENCES `course_restaurant`.`login_info` (`id_login_info`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `course_restaurant`.`staff_has_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course_restaurant`.`staff_has_order` (
  `Staff_id` INT UNSIGNED NOT NULL,
  `Order_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Staff_id`, `Order_id`),
  INDEX `fk_Staff_has_Order_Order1_idx` (`Order_id` ASC) VISIBLE,
  INDEX `fk_Staff_has_Order_Staff1_idx` (`Staff_id` ASC) VISIBLE,
  CONSTRAINT `fk_Staff_has_Order_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `course_restaurant`.`order` (`id_order`),
  CONSTRAINT `fk_Staff_has_Order_Staff1`
    FOREIGN KEY (`Staff_id`)
    REFERENCES `course_restaurant`.`staff` (`id_staff`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
