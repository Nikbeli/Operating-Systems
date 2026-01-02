#!/bin/bash

# Путь к файлу, в который будет записываться время и дата
log_file="time_log.txt"

# Бесконечный цикл для записи времени и даты каждую секунду
while true
do
    current_time=$(date +"%Y-%m-%d %H:%M:%S")
    echo "$current_time" >> "$log_file"
    sleep 1
done
