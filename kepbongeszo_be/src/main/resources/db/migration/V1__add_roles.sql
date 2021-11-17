CREATE TABLE IF NOT EXISTS role(
    id serial NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

INSERT INTO role(name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');