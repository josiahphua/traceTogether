CREATE DATABASE IF NOT EXISTS dasodo (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  todo_start_date DATE NOT NULL,
  todo_end_date DATE NOT NULL,
  description VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  priority INTEGER NOT NULL
)