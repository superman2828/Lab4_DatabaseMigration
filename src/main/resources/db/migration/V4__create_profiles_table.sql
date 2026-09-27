create table profiles
(
    id             bigint auto_increment
        primary key,
    bio            TEXT                   null,
    phone_number   VARCHAR(15)            null,
    date_of_birth  DATE                   null,
    loyalty_points INT UNSIGNED DEFAULT 0 null,
    constraint profiles_users_id_fk
        foreign key (id) references users (id)
);