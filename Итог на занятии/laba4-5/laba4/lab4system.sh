#!/bin/bash

# Задаем переменные
PROJECT_NAME="os-test-app"
PROJECT_URL="https://github.com/timourka/demo.git"
PROJECT_DIR="/opt/$PROJECT_NAME"
SYSTEMD_UNIT="/etc/systemd/system/$PROJECT_NAME.service"

# Создаем каталог для проекта
if [ -d $PROJECT_DIR ]; then
	sudo rm -r $PROJECT_DIR
fi
sudo mkdir -p "$PROJECT_DIR"

# Скачиваем проект из Git
sudo git clone "$PROJECT_URL" "$PROJECT_DIR"

sudo chmod +x "$PROJECT_DIR/gradlew"

sudo apt install openjdk-17-jdk-headless
javac --version

sudo snap install gradle --classic
gradle -v
if [ -d /usr/bin/gradle ]; then
    echo "Каталог /usr/bin/gradle существует"
elif [ -e /usr/bin/gradle ]; then
    echo "Файл /usr/bin/gradle уже существует и не является каталогом"
else
    sudo ln -s /snap/bin/gradle /usr/bin/gradle
fi

# Генерируем systemd unit файл
sudo cat > "$SYSTEMD_UNIT" <<EOF
[Unit]
Description=My Project Service
After=network.target

[Service]
ExecStart=sudo /bin/bash -c 'cd /opt/os-test-app && ./gradlew bootRun -Prod'
Restart=always

[Install]
WantedBy=multi-user.target
EOF

echo "create unit"

# Перезагружаем systemd и запускаем сервис
sudo systemctl daemon-reload
echo "обновили daemon"
sudo systemctl enable "$PROJECT_NAME"
echo "включили daemon"
sudo systemctl start "$PROJECT_NAME"
echo "запустили daemon"
