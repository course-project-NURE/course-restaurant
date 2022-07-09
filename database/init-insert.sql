USE course_restaurant;

INSERT IGNORE INTO `course_restaurant`.`role` (`title`) VALUES ('COOK');
INSERT IGNORE INTO `course_restaurant`.`role` (`title`) VALUES ('CUSTOMER');
INSERT IGNORE INTO `course_restaurant`.`role` (`title`) VALUES ('MANAGER');
INSERT IGNORE INTO `course_restaurant`.`role` (`title`) VALUES ('ADMIN');
INSERT IGNORE INTO `course_restaurant`.`role` (`title`) VALUES ('COURIER');
INSERT IGNORE INTO `course_restaurant`.`role` (`title`) VALUES ('DELIVERY_MANAGER');

INSERT IGNORE INTO `course_restaurant`.`order_status` (`id_order_status`,`title`) VALUES (1,'PROCESSING');
INSERT IGNORE INTO `course_restaurant`.`order_status` (`id_order_status`,`title`) VALUES (2,'CONFIRMED');
INSERT IGNORE INTO `course_restaurant`.`order_status` (`id_order_status`,`title`) VALUES (3,'REJECTED');
INSERT IGNORE INTO `course_restaurant`.`order_status` (`id_order_status`,`title`) VALUES (4,'COOKING');
INSERT IGNORE INTO `course_restaurant`.`order_status` (`id_order_status`,`title`) VALUES (5,'DELIVERING');
INSERT IGNORE INTO `course_restaurant`.`order_status` (`id_order_status`,`title`) VALUES (6,'PERFORMED');

INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('PIZZA');
INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('SUSHI');
INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('NONALCOHOLIC');
INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('ALCOHOLIC');
INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('WOK');
INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('DESSERT');
INSERT IGNORE INTO `course_restaurant`.`category` (`title`) VALUES ('SOUP');