/*
-- Address
*/
INSERT INTO `` (`address_id`,`city`,`state`,`street`,`zip_code`) VALUES (2,'Fairfield','IA','1702 Rukm','52556');
INSERT INTO `` (`address_id`,`city`,`state`,`street`,`zip_code`) VALUES (4,'Otummwa','IA','1000 Nth','53445');

/*
-- Customer
*/
INSERT INTO `` (`customer_id`,`birth_date`,`email`,`first_name`,`last_name`,`phone_number`,`address_id`) VALUES (2,'1989-05-31','anna@email.com','Anna','Smith','741-998-9807',2);

/*
-- Pizza
*/
INSERT INTO `` (`pizza_id`,`additional_details`,`name`,`price`,`size`,`type`) VALUES (1,NULL,'Supreme3',13.98,'SMALL','CHICAGO-STYLE');
INSERT INTO `` (`pizza_id`,`additional_details`,`name`,`price`,`size`,`type`) VALUES (2,NULL,'Veggie',30.99,'EXTRA-LARGE','NORMAL');
INSERT INTO `` (`pizza_id`,`additional_details`,`name`,`price`,`size`,`type`) VALUES (9,NULL,'Chicken BBQ',35.99,'SMALL','CHICAGO-STYLE');

/*
-- Order
*/
INSERT INTO `` (`order_number`,`order_date`,`status`,`total_price`,`customer_id`) VALUES (17,'2024-04-24 12:59:00.000000','REQUESTED',35.98,2);
INSERT INTO `` (`order_number`,`order_date`,`status`,`total_price`,`customer_id`) VALUES (18,'2024-04-24 12:59:00.000000','DELIVERY',40.98,2);
INSERT INTO `` (`order_number`,`order_date`,`status`,`total_price`,`customer_id`) VALUES (19,'2024-04-24 12:59:00.000000','IN-PROCESS',60.98,2);

/*
-- OrderLine
*/
INSERT INTO `` (`order_line_id`,`delivery_date`,`price`,`quantity`,`order_id`,`pizza_id`,`order_idsss`) VALUES (15,'2024-04-24 13:59:00.000000',27.98,2,17,1,NULL);
INSERT INTO `` (`order_line_id`,`delivery_date`,`price`,`quantity`,`order_id`,`pizza_id`,`order_idsss`) VALUES (16,'2024-04-24 13:59:00.000000',27.98,2,18,1,NULL);
INSERT INTO `` (`order_line_id`,`delivery_date`,`price`,`quantity`,`order_id`,`pizza_id`,`order_idsss`) VALUES (17,'2024-04-24 13:59:00.000000',15.98,1,19,1,NULL);
INSERT INTO `` (`order_line_id`,`delivery_date`,`price`,`quantity`,`order_id`,`pizza_id`,`order_idsss`) VALUES (18,'2024-04-24 13:59:00.000000',35.98,3,19,2,NULL);

/*
-- User
*/
INSERT INTO `` (`user_id`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`email`,`enabled`,`first_name`,`last_name`,`password`,`username`) VALUES (7,'1','1','1','mayra@email.com','1','Mayra','Pullupaxi','$2a$10$3UpZ8u7Z6pg5xRpfC9GDDuO9gcfhrjXXuKqMXmefdFO878Oik.7ee','mayrita');
INSERT INTO `` (`user_id`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`email`,`enabled`,`first_name`,`last_name`,`password`,`username`) VALUES (8,'1','1','1','jim@email.com','1','Jim','Doe','$2a$10$loolczfcFR34ycnN39zb9OJkPiq9ABk8bs6imjwlZphg5OP2YtMra','jim');

/*
-- Role
*/
INSERT INTO `` (`role_id`,`role_name`) VALUES (1,'ROLE_MANAGER');
INSERT INTO `` (`role_id`,`role_name`) VALUES (2,'ROLE_RECEPCIONIST');
INSERT INTO `` (`role_id`,`role_name`) VALUES (3,'ROLE_CUSTOMER');

/*
-- User-role
*/
INSERT INTO `` (`user_id`,`role_id`) VALUES (7,1);
INSERT INTO `` (`user_id`,`role_id`) VALUES (8,3);
INSERT INTO `` (`user_id`,`role_id`) VALUES (9,3);
INSERT INTO `` (`user_id`,`role_id`) VALUES (10,1);

