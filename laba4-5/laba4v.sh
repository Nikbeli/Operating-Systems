lab4.sh
#!/bin/bash

mkdir OS
cd OS
git clone https://git.athene.tech/is.ulstu.ru/os-test-app.git

cd os-test-app

echo '{
  "name": "os-test-app",
  "version": "1.0.0",
  "description": "Test App",
  "main": "index.js",
  "scripts": {
    "start": "node index.js"
  },
  "dependencies": {
    "express": "^4.17.1"
  },
  "author": "Nikita",
  "license": "ISC"
}' > package.json

npm install

echo "const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res.send('Hello, World!');
});

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
" > index.js

echo "[Unit]
Description=Test App Service
After=network.target

[Service]
ExecStart=/home/katana/lab4v.sh
Restart=always

Environment=NODE_ENV=production

[Install]
WantedBy=multi-user.target
" > test-app.service

sudo cp test-app.service /etc/systemd/system/

sudo systemctl daemon-reload

sudo systemctl start test-app

sudo systemctl enable test-app

createdb -U postgres sbapp

cd /home/katana/OS/os-test-app

chmod +x gradlew

gradlew bootRun -Pprod
