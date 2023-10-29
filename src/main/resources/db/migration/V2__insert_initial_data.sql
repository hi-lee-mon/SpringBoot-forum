-- 初期データの挿入
INSERT INTO spring_k_channel.user_info (login_id, password, account_locked_time, is_disabled, login_failure_count, authority)
VALUES ('auser1234', '$2a$10$StsnQjxzHH82GWL2kmvO5.POl7F.VpEfbNwk7ggO.JIBIASr5s4eW', NULL, 0, 0, '3');

INSERT INTO spring_k_channel.user_info (login_id, password, account_locked_time, is_disabled, login_failure_count, authority)
VALUES ('user1234', '$2a$10$JLS1AYlH5Ga2bvVV3UqIXOW6KpOxI.as9ADs/QE/hxUMwxIgUUTEW', NULL, 0, 0, '1');

INSERT INTO spring_k_channel.roles (role_id, name)
VALUES (1, "GENERAL");

INSERT INTO spring_k_channel.roles (role_id, name)
VALUES (2, "ADMIN");
