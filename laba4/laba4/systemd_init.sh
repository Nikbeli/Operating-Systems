#!/bin/bash

# Создание systemd unit файла для тестового проекта

# 1. Определение переменных

# Указываем URL репозитория
repo_url="https://git.athene.tech/is.ulstu.ru/os-test-app.git"

# Указываем папку, в которую необходимо склонировать репозиторий
target_folder="/home/katana/laba4/WebSite"

# Удаляем репозиторий, если уже до этого он был создан
if [ -d $target_folder ]; then
	rm -r $target_folder
fi

# Создаём папку, если она не существует
mkdir -p "$target_folder"

# Переходим в целевую папку
cd "$target_folder" || exit

# Клонируем репозиторий
git clone "$repo_url"

# Выводим сообщение об успешном клонировании
echo "Репозиторий успешно клонирован в $target_folder"


SERVICE_NAME="WebSite"                                              # Имя сервиса
PROJECT_DIRECTORY="/home/katana/laba4/WebSite"                      # Директория проекта
USER="user"                                                         # Имя пользователя, от которого будет запускаться сервис
NODE_BIN_PATH="/home/katana/laba4/WebSite/os-test-app"    # Путь к исполняемому файлу

# 2. Создание systemd unit файла
# В этой части скрипта мы используем команду cat и оператор перенаправления для создания файла
# systemd unit в директории /etc/systemd/system/ с именем, указанным в переменной SERVICE_NAME.

cat <<EOF | sudo tee /etc/systemd/system/$SERVICE_NAME.service
[Unit]
Description=$SERVICE_NAME

[Service]
Type=simple
ExecStart=/bin/bash -c $NODE_BIN_PATH sudo gradle bootRun -Pprod --stacktrace
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

echo "Смотрим статус сервиса"
sudo systemctl status $SERVICE_NAME
sudo systemctl enable $SERVICE_NAME

# 4. Вывод информации

# Завершающая часть скрипта выводит сообщение о том, что systemd unit файл был создан
# и активирован. Это сообщение будет отображаться в терминале после выполнения скрипта.

echo "Systemd unit file for $SERVICE_NAME has been created at /etc/systemd/system/$SERVICE_NAME.service"

