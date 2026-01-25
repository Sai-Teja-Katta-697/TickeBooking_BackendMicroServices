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

CREATE TABLE seat (
    id BIGSERIAL PRIMARY KEY,
    bus_id BIGINT NOT NULL,
    seat_number INT NOT NULL,
    UNIQUE (bus_id, seat_number),
    CONSTRAINT fk_seat_bus
        FOREIGN KEY (bus_id) REFERENCES bus_info(id)
);

CREATE TABLE booking (
    id BIGSERIAL PRIMARY KEY,
    bus_id BIGINT NOT NULL,
    seat_number INT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,d
    UNIQUE (bus_id, seat_number),
    CONSTRAINT fk_booking_bus
        FOREIGN KEY (bus_id) REFERENCES bus_info(id)
);
	ALTER TABLE booking
	ADD COLUMN booking_id VARCHAR(50) NOT NULL default 0;

	ALTER TABLE booking
	ADD CONSTRAINT uq_booking_booking_id UNIQUE (booking_id);
