INSERT INTO tb_roles (authority) VALUES ('ADMIN');
INSERT INTO tb_roles (authority) VALUES ('USER');

INSERT INTO tb_avatars (ascii, foreground_hex_color, background_hex_color) VALUES ('>x<', '#FF0000', '#0000FF');
INSERT INTO tb_avatars (ascii, foreground_hex_color, background_hex_color) VALUES (':o', '#000000', '#03FC03');
INSERT INTO tb_avatars (ascii, foreground_hex_color, background_hex_color) VALUES ('8u', '#000000', '#DBFC03');
INSERT INTO tb_avatars (ascii, foreground_hex_color, background_hex_color) VALUES ('l0l', '#000000', '#FC03C2');

INSERT INTO tb_users (nickname, handle, email, password, avatar_id ) VALUES ('John Doe', 'john.doe294', 'john.doe@test.br', '$2a$10$0oDspz3eSve/PLRU674YOu5iBh9CgmPVB0nkG5M04Q9AI56l9JIwe', 2);
INSERT INTO tb_users (nickname, handle, email, password, avatar_id ) VALUES ('Tyrell Jones', 'tyrell_jones023', 'tyrell.jones@test.br', '$2a$10$0oDspz3eSve/PLRU674YOu5iBh9CgmPVB0nkG5M04Q9AI56l9JIwe', 3);

INSERT INTO tb_users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (1, 3);

INSERT INTO tb_rooms (id, name, description, avatar_id, owner_id) VALUES ('18cb549e-cf0c-4669-845b-38f68f91c9a9', 'Room 1', 'Room 1 description', 1, 1);
INSERT INTO tb_rooms (id, name, description, avatar_id, owner_id) VALUES ('2709ef37-6d17-4af0-ba72-ccbd5e83c81b', 'Room 2', 'Room 2 description', 4, 2);

INSERT INTO tb_rooms_users (room_id, user_id) VALUES ('18cb549e-cf0c-4669-845b-38f68f91c9a9', 1);
INSERT INTO tb_rooms_users (room_id, user_id) VALUES ('18cb549e-cf0c-4669-845b-38f68f91c9a9', 2);
INSERT INTO tb_rooms_users (room_id, user_id) VALUES ('2709ef37-6d17-4af0-ba72-ccbd5e83c81b', 1);
INSERT INTO tb_rooms_users (room_id, user_id) VALUES ('2709ef37-6d17-4af0-ba72-ccbd5e83c81b', 2);