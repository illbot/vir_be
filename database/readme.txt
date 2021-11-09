docker build -t postgres .
docker run --rm --publish 127.0.0.1:5432:5432 --name pg_test postgres // delete container after close
docker run --publish 127.0.0.1:5432:5432 --name pg_test postgres 