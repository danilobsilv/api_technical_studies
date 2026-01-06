CREATE TABLE user (
    user_id CHAR(36) NOT NULL,
    user_name VARCHAR(150) NOT NULL,
    user_email VARCHAR(150) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    user_is_active BOOLEAN NOT NULL DEFAULT TRUE,
    user_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id),
    INDEX idx_user_name (user_name)
);