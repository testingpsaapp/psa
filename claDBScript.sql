CREATE DATABASE cla;

use cla;

CREATE TABLE brief_pap_pub (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
mim_num varchar(15) NOT NULL,
mim_date date,
impact_start_date datetime,
impact_end_time datetime,
impacted_lob varchar(40),
title varchar(200),
description varchar(500),
impacted_country varchar(100),
impacted_department varchar(100),
impact LONGTEXT,
fix varchar(500),
preventive_act varchar(500),
chronology LONGTEXT,
prepared_by varchar(10),
first_reviewer varchar(10),
second_reviewer varchar(10),
status varchar(10),
impacted_region varchar(10),
impacted_sector varchar(10)
);


CREATE TABLE  change_comm (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
change_num varchar not null,
change_date date null,
change_start_time datetime null,
change_end_time datetime null,
impacted_pp clob null,
impacted_country clob null,
sanity_scope clob null,
impacted_region varchar null
);


CREATE TABLE incident_comm (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
incident_num varchar(10) NOT NULL,
incident_severity varchar(10),
comm_typ varchar(20),
incident_date date,
impact_start_date datetime,
impact_end_time datetime,
impacted_lob varchar(10),
title varchar(200),
description varchar(500),
impacted_country varchar(10),
impacted_department varchar(20),
impact LONGTEXT,
fix varchar(500),
prepared_by varchar(10),
reviewed_by varchar(10),
status varchar(10),
impacted_region varchar(10),
impacted_sector varchar(10)
);


CREATE TABLE daily_call_incident_tracker (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
inc_num varchar(10) NOT NULL,
impacting_reg varchar(10),
impacting_ctry varchar(10),
highlighted_by varchar(10),
highlighted_date date,
closed_date date,
impacting_dept varchar(20),
reason_of_criticality varchar(200),
action_item LONGTEXT,
upd_comm varchar(400),
inc_state varchar(20),
pp_involved LONGTEXT,
curr_owner varchar(10),
curr_owner_app varchar(10),
impact LONGTEXT,
incident_priority varchar(20)
);


CREATE TABLE daily_inc_act (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
inc_num varchar(20) NOT NULL,
date datetime,
old_priority varchar(10),
new_priority varchar(10),
impact varchar(500),
act_taken varchar(50),
act_category varchar(50),
ctry varchar(10),
tl_soeid varchar(10)
);


CREATE TABLE comm_dlist_config (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
comm_type varchar(100),
inc_type varchar(100),
inc_priority varchar(100),
impacted_ctry varchar(100),
impacted_lob varchar(100),
to_ids LONGTEXT,
cc LONGTEXT,
bcc LONGTEXT
);


CREATE TABLE cla_access_config (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
access_type varchar(50),
access_role varchar(50),
soe_id varchar(10),
access_data LONGTEXT
);


CREATE TABLE  lob_config(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
soe_id varchar(10),
lob varchar(20),
app varchar(50),
role varchar(50),
config_data LONGTEXT
);


CREATE TABLE app_config (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
app_name varchar(50),
lob varchar(20),
d_list LONGTEXT,
snow_dlist LONGTEXT,
lob_lead varchar(10),
psm varchar(10),
pssm varchar(10)
);


CREATE TABLE worklist (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
task_id varchar(10),
task_name varchar(50),
link varchar(200),
task_owner varchar(10)
);


CREATE TABLE email_template (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
template_id varchar(40),
template LONGTEXT
);

CREATE TABLE triage_lead_config (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
tl_soe_id varchar(40),
region varchar(40),
country varchar(40)
);

CREATE TABLE countries (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
country_code varchar(50),
country_name varchar(100),
region varchar(40)
);
