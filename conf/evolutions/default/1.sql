# --- !Ups
CREATE TABLE IF NOT EXISTS PUBLIC.USER (
  id  SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  phone VARCHAR(100) NOT NULL
);

INSERT INTO PUBLIC.USER (first_name, last_name, phone) VALUES ('neith','mikkeli','79998882211');

# --- !Downs
DROP TABLE USER;