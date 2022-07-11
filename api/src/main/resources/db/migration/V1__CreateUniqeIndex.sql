ALTER TABLE `course_restaurant`.`login_info`
    ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE;
;

ALTER TABLE `course_restaurant`.`order_status`
    ADD UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE;
;

ALTER TABLE `course_restaurant`.`role`
    ADD UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE;
;

ALTER TABLE `course_restaurant`.`category`
    ADD UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE;
;