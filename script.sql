create table `ParkingPlace`(
	`id` int auto_increment primary key,
    `name` nvarchar(50) not null
);

create table `ParkingLot`(
	`id` int auto_increment primary key,
    `name` nvarchar(50) not null, 
    `area` float not null,
    `place_id` int,
    `price` numeric not null,
	`status` bit not null default (0),
    foreign key (`place_id`) references `ParkingPlace`(`id`)
);

drop table ParkingLot;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE `ParkingPlace`;
SET FOREIGN_KEY_CHECKS=1;

create table Department(
	`id` int auto_increment primary key,
    `name` nvarchar(50) not null unique
);

create table Employee(
	`id` int auto_increment primary key,
    `name` nvarchar(50) not null,
    `phone` varchar(20) not null,
    `dob` date not null,
    `address` nvarchar(100),
    `sex` bit not null,
    `department_id` int not null,
    `email` varchar(128) unique,
    `account` varchar(50) not null unique,
    `salt` char(16) not null,
    `pwd_hash` char(64) not null,
    foreign key (`department_id`) references `Department`(`id`)
);

create table `Trip`(
	`id` int auto_increment primary key,
    `booked_ticket` int default(0) not null,
    `car_type` varchar(20) not null,
    `departure_time` time not null,
    `departure_date` date not null,
    `destination` varchar(50) not null,
    `driver` nvarchar(50) not null,
    `max_onl_ticket` int not null
);

create table `Office`(
	`id` int auto_increment primary key,
    `name` nvarchar(50) not null,
    `phone` varchar(20) not null,
    `place` nvarchar(50) not null,
    `price` numeric not null,
    `start_contract` date not null,
    `end_contract` date not null,
    `trip_id` int not null,
    foreign key (`trip_id`) references `Trip`(`id`),
    check (`start_contract` < `end_contract`)
);

create table `Car`(
	`license_plate` varchar(20) primary key,
    `type` nvarchar(20),
    `color` varchar(20),
    `office_id` int not null,
    `parking_lot_id` int not null,
    foreign key (`office_id`) references `Office`(`id`),
    foreign key (`parking_lot_id`) references `ParkingLot`(`id`)
);

create table `Ticket`(
	`id` int auto_increment primary key,
    `customer_name` nvarchar(50) not null,
    `booked_time` time not null,
    `trip_id` int not null,
    `license_plate` varchar(20) not null,
    foreign key (`trip_id`) references `Trip`(`id`),
    foreign key (`license_plate`) references `Car`(`license_plate`)
);

create trigger `after_car_insert`
after insert on `Car`
for each row
	update `ParkingLot` set `status` = 1 where new.`parking_lot_id` = `ParkingLot`.`id`;

delimiter //
create trigger `after_car_update`
after update on `Car`
for each row
begin
	update `ParkingLot` set `status` = 0 where old.`parking_lot_id` = `ParkingLot`.`id`;
    update `ParkingLot` set `status` = 1 where new.`parking_lot_id` = `ParkingLot`.`id`;
end; //
delimiter ;

create trigger `after_car_delete`
after delete on `Car`
for each row
	update `ParkingLot` set `status` = 0 where old.`parking_lot_id` = `ParkingLot`.`id`;

create trigger `after_ticket_insert`
after insert on `Ticket`
for each row
	update `Trip` set `booked_ticket` = `booked_ticket` + 1 where new.`trip_id` = `Trip`.`id`;
    
delimiter //
create trigger `after_ticket_update`
after update on `Ticket`
for each row
begin
	update `Trip` set `booked_ticket` = `booked_ticket` + 1 where new.`trip_id` = `Trip`.`id`;
    update `Trip` set `booked_ticket` = `booked_ticket` - 1 where old.`trip_id` = `Trip`.`id`;
end; //
delimiter ;
    
create trigger `after_ticket_delete`
after delete on `Ticket`
for each row
	update `Trip` set `booked_ticket` = `booked_ticket` - 1 where old.`trip_id` = `Trip`.`id`;


SELECT COUNT(*) FROM (SELECT  DENSE_RANK() OVER (ORDER BY `id`) AS `sort` FROM `Trip` WHERE `destination` LIKE CONCAT('%','a','%')) tbl
