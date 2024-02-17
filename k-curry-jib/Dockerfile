FROM mysql:latest

# Настраиваем переменные окружения для MySQL
ENV MYSQL_DATABASE=k-curry-jib
ENV MYSQL_ROOT_PASSWORD=12345

# Копируем файл инициализации в контейнер
COPY k-curry-jib.sql /docker-entrypoint-initdb.d/

# Указываем директиву, чтобы скрипт выполнялся при старте контейнера
# Эта директива встроена в официальный образ MySQL
# Это позволит автоматически выполнить k-curry-jib.sql при первом запуске контейнера
EXPOSE 3306