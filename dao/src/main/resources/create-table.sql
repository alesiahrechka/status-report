CREATE TABLE IF NOT EXISTS TASK (
task_key  VARCHAR(10) NOT NULL,
description VARCHAR(255) NOT NULL,
duration DECIMAL(4,2) NOT NULL,
status VARCHAR(15) NOT NULL,
PRIMARY KEY (task_key),
CHECK (status in ('NOT_STARTED','IN_PROGRESS', 'COMPLETED'))
);