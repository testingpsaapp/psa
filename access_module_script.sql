create table access_module
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`main_module` varchar(300) NOT NULL,
	`sub_module` varchar(300) NOT NULL,
	`role` varchar(300) NOT NULL
);
--Communication Module
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Briefing Paper Publisher','Team Member');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Briefing Paper Publisher','LOB Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Briefing Paper Publisher','PSM');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Briefing Paper Publisher','PSSM');

insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Change Communication','Team Member');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Change Communication','LOB Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Change Communication','PSM');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Change Communication','PSSM');

insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Incident - MIM Communication','Triage Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Communication','Incident - MIM Communication','Reviewer');

--Triage Lead Activity
insert into access_module (`main_module`,`sub_module`,`role`) values ('Triage Lead Activity','Critical / Daily Call Incident Tracker','Triage Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Triage Lead Activity','Critical / Daily Call Incident Tracker','L1 Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Triage Lead Activity','Critical / Daily Call Incident Tracker','PSSM');


insert into access_module (`main_module`,`sub_module`,`role`) values ('Triage Lead Activity','Triage Lead Incident Dashboard','Triage Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Triage Lead Activity','Triage Lead Incident Dashboard','L1 Lead');
insert into access_module (`main_module`,`sub_module`,`role`) values ('Triage Lead Activity','Triage Lead Incident Dashboard','PSSM');

--Admin Console

insert into access_module (`main_module`,`sub_module`,`role`) values ('Admin Console','All Admin Console Modules','Admin');



