-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, active, password, provider)
    values(1, 'admin@gmail.com', 'Admin', 'Admin', null, null, null, null, true, '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', 'LOCAL');

-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, active, password, provider)
    values(2, 'test123@test.com', 'John', 'Doe', 'New York', 'Wall Street1', '1234567890', '1234567890', true, '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', 'LOCAL');

-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, active, password, provider)
    values(3, 'eli@test.com', 'Eli', 'Timova', 'Strumica', 'Leninova 89', '38978555333', '1234567890', true, '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', 'LOCAL');

insert into user_role (user_id, roles)
    values (1, 'ADMIN');

insert into user_role (user_id, roles)
    values (2, 'USER');

insert into user_role (user_id, roles)
    values (3, 'USER');
