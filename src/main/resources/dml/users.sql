-- usersテーブルテストデータ
INSERT INTO users (user_id, name, email, password, tel, remarks, role) VALUES
('00001', 'John Doe', 'johndoe@example.com', 'password1', '123-456-7890', 'Test user 1', 'USER'),
('00002', 'Jane Smith', 'janesmith@example.com', 'password2', '987-654-3210', 'Test user 2', 'ADMIN'),
('00003', 'Alice Johnson', 'alicejohnson@example.com', 'password3', '555-555-5555', 'Test user 3', 'USER'),
('00004', 'Bob Brown', 'bobbrown@example.com', 'password4', '777-888-9999', 'Test user 4', 'ADMIN'),
('90001', 'テスト', null, '$2a$10$ybSuHt1eIhWSt2BC48ODNeQvW6/.3w1t9x2XWP7Tq5H7RqrR9mxmO', null, null, 'USER'),
('90002', 'テスト', null, '$2a$10$pM.qOfdfnZ9A55NIpnPsgenHrj962eLd/QahuRYdmWtF1/LMGbVs2', null, null, 'ADMIN');