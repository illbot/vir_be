CREATE TABLE IF NOT EXISTS users (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- Role es user tablak osszekotese
CREATE TABLE user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id)
);

-- kulso kulcsok
ALTER TABLE user_roles ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE user_roles ADD CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id) REFERENCES role(id);

-- admin felhasznalo letrehozasa
INSERT INTO users
(email, "password", username)
VALUES('admin@admin.com','$2a$10$UfqkqYnb0.xA8L1twuj/lejtonPV51xN1Uh7PxGmU68YJkSh93mLW','admin');

-- admin felhasznalohoz role-ok rendelese
INSERT INTO public.user_roles
(user_id, role_id)
VALUES(1, 1),
      (1, 2);

