create table branches
(
    id           int auto_increment
        primary key,
    date_created datetime null,
    description  varchar(255) not null,
    last_updated datetime null,
    name         varchar(255) not null,
    user_id      int          not null,
    version      bigint null,
    constraint branches_name_uindex
        unique (name)
) engine = MyISAM;

create table commits
(
    id                       varchar(255) not null
        primary key,
    branch_id                int          not null,
    date_created             datetime null,
    last_updated             datetime null,
    message                  varchar(255) not null,
    number_of_modified_files int          not null,
    user_id                  int          not null
) engine = MyISAM;

create table pull_requests
(
    id                int auto_increment
        primary key,
    base_branch_id    int          not null,
    compare_branch_id int          not null,
    date_created      datetime null,
    description       varchar(255) null,
    last_updated      datetime null,
    status            varchar(255) not null,
    title             varchar(255) not null,
    user_id           int          not null,
    constraint pull_requests_compare_branch_id_uindex
        unique (compare_branch_id, base_branch_id)
) engine = MyISAM;

create table users
(
    id         int auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    username   varchar(255) null,
    constraint users_username_uindex
        unique (username)
) engine = MyISAM;

