
create table credentials (id uuid not null,password varchar(255),user_name varchar(255) unique,primary key (id));

create table phone (id uuid not null,user_id uuid,city_code varchar(255),country_code varchar(255),number varchar(255),primary key (id));

create table users (credential_id uuid unique,is_active boolean,created timestamp(6),last_login timestamp(6),modified timestamp(6),id uuid not null,email varchar(255) unique,name varchar(255),token varchar(255),primary key (id));

alter table if exists phone add constraint phone_user_fk foreign key (user_id) references users;
alter table if exists users add constraint users_credential_fk foreign key (credential_id) references credentials