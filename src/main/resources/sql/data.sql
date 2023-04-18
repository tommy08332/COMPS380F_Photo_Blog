insert into photoblog_users (USERNAME, EMAIL, USER_PASSWORD, PHONE_NUMBER, USER_DESCRIPTION) values ('testuser01', 'example1@mail', '{noop}P@ssw0rd', 12345678, 'This is an example description');
insert into photoblog_user_role (USER_ROLE, USERNAME) values ('ROLE_USER', 'testuser01');
insert into photoblog_user_role (USER_ROLE, USERNAME) values ('ROLE_ADMIN', 'testuser01');


insert into photoblog_users (USERNAME, EMAIL, USER_PASSWORD, PHONE_NUMBER, USER_DESCRIPTION) values ('testuser02', 'example2@mail', '{noop}P@ssw0rd', 22345678, 'This is an example description');
insert into photoblog_user_role (USER_ROLE, USERNAME) values ('ROLE_USER', 'testuser02');


insert into photoblog_users (USERNAME, EMAIL, USER_PASSWORD, PHONE_NUMBER, USER_DESCRIPTION) values ('testuser03', 'example3@mail', '{noop}P@ssw0rd', 32345678, 'This is an example description');
insert into photoblog_user_role (USER_ROLE, USERNAME) values ('ROLE_ADMIN', 'testuser03');
