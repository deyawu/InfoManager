#
# use myapp;
# create table user (
#   stringId VARCHAR(100) not null ,
#   user_name VARCHAR(100) not null ,
#   password VARCHAR(100) not null ,
#   primary key (stringId)
# );

use myapp;
# create table if not exists Appinfo (
#   appId varchar(100) not null ,
#   checkTime varchar(100) not null ,
#   appName varchar(100) not null ,
#   rangeApplication varchar(100) not null ,
#   businessLink varchar(100) not null ,
#   knowledgeType varchar(100) not null ,
#   testFile varchar(100) not null ,
#   primary key (appId)
# );
alter table appinfo drop column checkTime;