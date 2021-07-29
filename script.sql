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
    foreign key (`parking_lot_id`) references `ParkingLot`(`id`),
);