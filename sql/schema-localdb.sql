create database exchangerates;
use exchangerates;
create table currency (id bigint not null, currency_code varchar(255), currency_name varchar(255), primary key (id)) engine=InnoDB;
create table exchange_rate_tab (id bigint not null, exchange_rate double precision, last_record_time datetime, version_no bigint not null, from_id bigint, to_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
alter table exchange_rate_tab add constraint FK1 foreign key (from_id) references currency (id);
alter table exchange_rate_tab add constraint FK2 foreign key (to_id) references currency (id);
