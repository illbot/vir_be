docker build -t postgres .
docker run --rm --publish 127.0.0.1:5432:5432 --name pg_test postgres // delete container after close
docker run --publish 127.0.0.1:5432:5432 --name pg_test postgres 

After docker container running:
	1. IntelliJ -> Maven fül -> flyway:migrate
	2. IntelliJ -> Maven fül -> spring-boot:run