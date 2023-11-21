-- usersテーブルテストデータ
INSERT INTO users (user_id, name, email, password, tel, remarks, role) VALUES
('00001', 'John Doe', 'johndoe@example.com', 'password1', '123-456-7890', 'Test user 1', 'ROLE_USER'),
('00002', 'Jane Smith', 'janesmith@example.com', 'password2', '987-654-3210', 'Test user 2', 'ROLE_ADMIN'),
('00003', 'Alice Johnson', 'alicejohnson@example.com', 'password3', '555-555-5555', 'Test user 3', 'ROLE_USER'),
('00004', 'Bob Brown', 'bobbrown@example.com', 'password4', '777-888-9999', 'Test user 4', 'ROLE_ADMIN');