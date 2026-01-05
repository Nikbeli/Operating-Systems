#!/bin/bash

# Путь к первому скрипту
time_logger_script="./logger.sh"

# Функция для старта первого скрипта
start_time_logger() {
    if [ -f "$time_logger_script" ]; then
        nohup bash "$time_logger_script" > /dev/null 2>&1 &
        echo "Скрипт логгера времени запущен."
    else
        echo "Скрипт логгера времени не найден."
    fi
}

# Функция для остановки первого скрипта
stop_time_logger() {
    pids=$(pgrep -f "$time_logger_script")
    if [ -n "$pids" ]; then
        kill "$pids"
        echo "Скрипт логгера времени остановлен."
    else
        echo "Скрипт логгера времени не выполняется."
    fi
}

# Функция для рестарта первого скрипта
restart_time_logger() {
    stop_time_logger
    start_time_logger
}

# Основное меню управления
while true
do
    echo "Выберите действие:"
    echo "1. Старт скрипта логгера времени"
    echo "2. Стоп скрипта логгера времени"
    echo "3. Рестарт скрипта логгера времени"
    echo "4. Выход"
    read choice

    case $choice in
        1) start_time_logger;;
        2) stop_time_logger;;
        3) restart_time_logger;;
        4) exit;;
        *) echo "Некорректный выбор. Попробуйте снова.";;
    esac
done
