INSERT INTO tb_roles (authority) VALUES ('ADMIN');
INSERT INTO tb_roles (authority) VALUES ('USER');

INSERT INTO tb_users (username, handle, email, password ) VALUES ('John Doe', 'john.doe', 'example@test.br', '$2a$10$0oDspz3eSve/PLRU674YOu5iBh9CgmPVB0nkG5M04Q9AI56l9JIwe');

INSERT INTO tb_users_roles (user_id, role_id) VALUES (1, 2);