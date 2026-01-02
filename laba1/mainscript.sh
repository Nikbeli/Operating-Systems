#!/bin/bash

# Получаем количество каталогов и файлов через аргументы командной строки
echo "Введите количество директорий(папок), которое нужно создать"
num_dirs=$1
echo "Введите количество файлов, которое нужно создать"
num_files=$2

# Создаем заданное количество каталогов
for ((i=1; i<=num_dirs; i++))
do
  mkdir "directory$i"
  echo "Created directory$i"
  
  # Создаем указанное число файлов в каждом каталоге
  for ((j=1; j<=num_files; j++))
  do
    touch "directory$i/file$j"
    echo "Created file$j in directory$i"
  done
done