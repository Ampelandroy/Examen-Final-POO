CREATE TABLE IF NOT EXISTS app_user (
                                        id VARCHAR(255) PRIMARY KEY,
    ref VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS cash_flow (
                                         id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                             amount NUMERIC(15, 2) NOT NULL,
    user_id VARCHAR(255) REFERENCES app_user(id),
    flow_type VARCHAR(20) NOT NULL,
    comment TEXT,
    reason TEXT,
    frequency VARCHAR(20)
    );