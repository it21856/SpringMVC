#create database test;
use test;

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `authorities` (
   `id` int not null auto_increment,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_usernametest` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `userDetails` (
 `username` varchar(50) NOT NULL,
 `salary` int NOT NULL,
 CONSTRAINT `userDetails_username` FOREIGN KEY (`username`) REFERENCES user (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `dayoffs` (
  `id` int not null auto_increment,
 `username` varchar(50) NOT NULL,
 `startdate` date not null,
 `enddate` date not null,
 `days` int(11) NOT NULL,
 `type` varchar(100) NOT NULL,
 `state` varchar(10) NOT NULL,
 primary key(`id`),
 CONSTRAINT `dayoffs_username` FOREIGN KEY (`username`) REFERENCES user (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user` (`username`, `password`, `enabled`) VALUES #foreach user : username=password except root u:root p:pass123
    ('root', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1),
    ('manager','$2a$10$.3ciSsMi7C595wu1XH5sIufKoEikV3UxL0T4dVOXHXBuT7Q6/V9va',1),
    ('pay','$2a$10$eaVI0i8jWKgDbHPM1Ul.mOFVSK5QlSQoxphaZKQSRMIIqFPulwbj.',1),
    ('super','$2a$10$H6n4LWLdZMFjpbmd9ZYq.u9GtRgwuuKOvwNMS26KuR1oThcA7MCNC',1),
    ('user','$2a$10$W3oNgGiHWBiO0JX8BNx45.Adrp5bvoC/6GVlbG1razBPl0/JEy7lO',1 );

INSERT INTO `authorities` (`username`, `authority`) VALUES
    ('root', 'ROLE_ADMIN'),
    ('manager','ROLE_MANAGER'),
    ('super','ROLE_SUPERVISOR'),
    ('pay','ROLE_PAYROLL'),
    ('user','ROLE_USER');
    
INSERT INTO `userDetails`(`username`,`salary`) VALUES
    ('manager',800),
    ('super',750),
    ('pay',740),
    ('user',700);
    
    