# cft_test_client

Java 11.0.10
   Maven 3.6.3

--------------------------------------------------------------------------------------------------------------------------------------------------------
Задание: 

java cli приложение, собирается в jar, работает по параметрам. Рядом с приложением - conf файл, в котором указаны адрес и порт сервера.
Файл с конфигурации создается автоматически и заполняется предварительными данными.

--------------------------------------------------------------------------------------------------------------------------------------------------------
Param:

-java -jar cft_test_client-1.0-SNAPSHOT-jar-with-dependencies.jar {get_file} {Путь для сохранения файла "E:\a\"} {Имя файла на сервере.txt} - Получить файл

-java -jar cft_test_client-1.0-SNAPSHOT-jar-with-dependencies.jar {get_files_list} - Получить список файлов на сервере

-java -jar cft_test_client-1.0-SNAPSHOT-jar-with-dependencies.jar {put_file} {Путь и имя файла "E:\a\имяФайла.txt"} - Положить файл на сервер

--------------------------------------------------------------------------------------------------------------------------------------------------------
Api:

-http://localhost:8080/api/allFiles -получить Список файлов

-http://localhost:8080/api/getFile/{fileName} - Получить файл

-http://localhost:8080/api/putFile/{fileName} - Отдать файл 

--------------------------------------------------------------------------------------------------------------------------------------------------------
Сценрий:

 -Получить список файлов. Обращается к серверу, получает список файлов, выводит на экран список: java -jar / app.jar / get_files_list
 
 -Записать на сервер файл. Обращается к серверу, отдает файл на запись, выводит результат Ок/НеОк: java -jar / app.jar / put_file / local_file_name
 
-Прочитать файл с сервера и записать на клиента. Обращается к серверу, получает файл по имени, записывает по указанному пути: java -jar / app.jar / get_file / server_file_name / local_file_name
   
   --------------------------------------------------------------------------------------------------------------------------------------------------------
   rest api-server
   https://github.com/NaybaVladimir/cft_test_server
   doker
   
   
