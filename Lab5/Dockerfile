FROM openjdk:21

WORKDIR /app

COPY . /app

COPY wait-for-it.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/wait-for-it.sh
RUN microdnf install findutils