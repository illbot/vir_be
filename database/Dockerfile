FROM postgres:12

ENV POSTGRES_USER docker
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB docker

COPY init.sql /docker-entrypoint-initdb.d/

