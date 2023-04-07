drop table if exists comments;
drop table if exists photo;
drop table if exists photoblog_user_role;
drop table if exists photoblog_users;

create table if not exists photoblog_users (
     username varchar(255) not null,
     user_password varchar(255) not null,
     primary key (username)
);

create table if not exists photoblog_user_role (
     user_role_id integer generated by default as identity,
     user_role varchar(255) not null,
     username varchar(255) not null,
     primary key (user_role_id),
     foreign key (username) references photoblog_users
);

create table if not exists photo (
    photo_id uuid default random_uuid() not null,
    photo_data blob,
    photo_description varchar(255),
    photo_file_type varchar(255),
    photo_title varchar(255),
    photo_uploaded_datetime timestamp(6),
    username varchar(255),
    primary key (photo_id),
    foreign key (username) references photoblog_users
);

create table if not exists comments (
    comment_id bigint not null,
    comment_datetime timestamp(6),
    comment_text varchar(255),
    photo_id uuid,
    username varchar(255),
    primary key (comment_id),
    foreign key (username) references photoblog_users,
    foreign key (photo_id) references photo
)
