DROP USER IF EXISTS 'restaurant_admin'@'localhost';
CREATE USER 'restaurant_admin'@'localhost' IDENTIFIED BY 'restaurantpassword';
GRANT INSERT, UPDATE, DELETE, SELECT, ALTER on *.* TO 'restaurant_admin'@'localhost' WITH GRANT OPTION;