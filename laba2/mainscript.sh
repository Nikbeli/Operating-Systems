#!/bin/bash

# Функция для скачивания файла
echo "Загрузка файла"
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

# Функция для вывода топ 10 самых длинных слов и подсчёт количество их повторений
top_longest_words() {
    echo "Вывод длинных слов и их повторений..."
    file="war_and_peace.txt"
    tr -sc '[:alpha:]' '\n' < "$file" | awk 'length >= 10' | sort | uniq -c | sort -nr | head -n 10
}

# Функция для вывода топ 10 коротких слов
top_shortest_words() {
    file="war_and_peace.txt"
    echo "Топ 10 самых коротких пробелов в Войне и мир..."
    awk '{for(i=1; i<=NF; i++) print length($i),$i}' "$file" | sort -n | head -n 10 | cut -d' ' -f2-
}

even_length_words() {
    echo "Слова с чётным количеством букв, отсортированное по длине:"
    awk '{for(i=1; i<=NF; i++) if(length($i)%2==0) print length($i), $i}' "$file" | sort -n | cut -d' ' -f2-
}

# Вызываем функции в нужной последовательности
download_file
convert_to_utf8
count_lines
top_longest_words
top_shortest_words
#even_length_words