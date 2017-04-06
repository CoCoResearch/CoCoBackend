# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table feature_model (
  id                            bigserial not null,
  owner                         varchar(255) not null,
  name                          varchar(255) not null,
  extension                     varchar(255) not null,
  file                          varchar(255),
  constraint pk_feature_model primary key (id)
);


# --- !Downs

drop table if exists feature_model cascade;

