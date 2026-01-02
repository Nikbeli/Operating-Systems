#!/bin/bash

# Создание systemd unit файла для тестового проекта на Ubuntu 18.04

# 1. Определение переменных

# Замените следующие значения на соответствующие для вашей системы.
SERVICE_NAME="WebSite"                                              # Имя сервиса
PROJECT_DIRECTORY="/home/user/MyWebApp/WebSite"                      # Директория проекта
USER="user"                                                         # Имя пользователя, от которого будет запускаться сервис
NODE_BIN_PATH="/home/user/MyWebApp/build-WebSite-Desktop-Release"   # Путь к исполняемому файлу

# 2. Создание systemd unit файла

# В этой части скрипта мы используем команду cat и оператор перенаправления для создания файла
# systemd unit в директории /etc/systemd/system/ с именем, указанным в переменной SERVICE_NAME.

cat <<EOF | sudo tee /etc/systemd/system/$SERVICE_NAME.service
[Unit]
Description=$SERVICE_NAME

[Service]
Type=simple
ExecStart=$NODE_BIN_PATH/WebSite
WorkingDirectory=$PROJECT_DIRECTORY
Restart=always
User=$USER

[Install]
WantedBy=multi-user.target
EOF

# 3. Перезапуск systemd и включение сервиса

# Мы перезагружаем systemd и запускаем созданный сервис, а также активируем его,
# чтобы он запускался при старте системы.

sudo systemctl daemon-reload
sudo systemctl start $SERVICE_NAME
sudo systemctl enable $SERVICE_NAME

# 4. Вывод информации

# Завершающая часть скрипта выводит сообщение о том, что systemd unit файл был создан
# и активирован. Это сообщение будет отображаться в терминале после выполнения скрипта.

echo "Systemd unit file for $SERVICE_NAME has been created at /etc/systemd/system/$SERVICE_NAME.service"
