CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,        -- unique user id

    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,  -- BCrypt hashed password
    role VARCHAR(30) NOT NULL,        -- ROLE_USER, ROLE_ADMIN

    enabled BOOLEAN DEFAULT TRUE,     -- active / inactive user

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
