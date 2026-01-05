#!/bin/bash

# Функция для скачивания файла
download_file() {
    file_url="https://cloud.athene.tech/f/d691a2c8e8ab49dca788/?dl=1"
    wget -O "war_and_peace.txt" "$file_url"
}

# Функция для изменения кодировки на UTF-8 (если необходимо)
convert_to_utf8() {
    file="war_and_peace.txt"
    encoding=$(file -bi "$file" | awk -F "=" '{print $2}')
    
    if [ "$encoding" != "utf-8" ]; then
        iconv -f "$encoding" -t utf-8 "$file" -o "$file.utf8"
        mv "$file.utf8" "$file"
    fi
}

# Функция для вывода количества строк в файле
count_lines() {
    file="war_and_peace.txt"
    line_count=$(wc -l < "$file")
    echo "Количество строк в файле: $line_count"
}

# Функция для вывода топ 10 самых длинных слов
top_longest_words() {
    file="war_and_peace.txt"
    tr -sc '[:alpha:]' '\n' < "$file" | awk 'length >= 10' | sort | uniq -c | sort -nr | head -n 10
}

# Вызываем функции в нужной последовательности
download_file
convert_to_utf8
count_lines
top_longest_words
