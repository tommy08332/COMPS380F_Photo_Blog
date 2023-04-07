insert into photoblog_users (username, user_password) values ('testuser', '{noop}testuserpw');

insert into photoblog_user_role (user_role_id, username, user_role) values (default, 'testuser', 'ROLE_ADMIN');

insert into photoblog_user_role (user_role_id, username, user_role) values (default, 'testuser', 'ROLE_USER');
