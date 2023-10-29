-- ユーザーテーブルを作成
CREATE TABLE spring_k_channel.user_info (
  `login_id` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `account_locked_time` datetime DEFAULT NULL,
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0',
  `login_failure_count` int NOT NULL DEFAULT '0',
  `authority` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE spring_k_channel.roles (
  `role_id` int NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
