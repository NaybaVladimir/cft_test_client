package org.example.cft_test_client;


import org.example.cft_test_client.service.ApiService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/*
 *Получить список файлов. Обращается к серверу, получает список файлов, выводит на экран список: java -jar / app.jar / get_files_list
 *Записать на сервер файл. Обращается к серверу, отдает файл на запись, выводит результат Ок/НеОк: java -jar / app.jar / put_file / local_file_name
 *Прочитать файл с сервера и записать на клиента. Обращается к серверу, получает файл по имени, записывает по указанному пути: java -jar / app.jar / get_file / server_file_name / local_file_name
 */
//http://localhost:8080/api/allFiles
//http://localhost:8080/api/getFile/{fileName}
//http://localhost:8080/api/putFile/{fileName}


//java -jar cft_test_client-1.0-SNAPSHOT-jar-with-dependencies.jar get_file E:\a\ 12.txt
//java -jar cft_test_client-1.0-SNAPSHOT-jar-with-dependencies.jar get_files_list
//java -jar cft_test_client-1.0-SNAPSHOT-jar-with-dependencies.jar put_file E:\a\2.txt

public class Main {
    private static String host;
    private static String configPath;

    public static void main(String[] args) {
        config();
        hostInit();
        if (args.length > 0)
            switch (args[0]) {
                case "get_files_list":
                    String uriGetList = host + "/api/allFiles";
                    System.out.println("Адрес: " + uriGetList);
                    new ApiService().getList(uriGetList);
                    System.out.println("Статус: Ок");
                    break;
                case "put_file":
                    String uriPutFile = host + "/api/putFile/" + args[1].substring(args[1].lastIndexOf("\\") + 1);
                    System.out.println("Адрес: " + uriPutFile);
                    new ApiService().putFile(args[1], uriPutFile);
                    System.out.println("Статус: Ок");
                    break;
                case "get_file":
                    String uriGetFile = host + "/api/getFile/" + args[2];
                    System.out.println("Адрес: " + uriGetFile);
                    new ApiService().getFile(args[1] + args[2], uriGetFile);
                    System.out.println("Статус: Ок");
                    break;
                default:
                    System.out.println("Статус: указан не валидный параметр");
                    break;
            }
    }


    // Инициализируем файл конфигураций
    public static void config() {
        //Получаем путь для расположения config файла
        String srcPath = null;
        try {
            srcPath = (new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI())
                    .getPath());
            srcPath = srcPath.substring(0, srcPath.lastIndexOf("\\")) + "\\config.properties";

            //Задаем значение пути к config-файлу переменной класса
            configPath = srcPath;
            System.out.println("Расположение файла конфигруации: " + configPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //Создаем конфигурационный файл(если его нет) рядом .jar файлом(заполняем первоначальными значениями)
        File fileProperties = new File(srcPath);
        if (!fileProperties.isFile()) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(fileProperties);
                fileOutputStream.write(("host=http://localhost:\n" +
                        "port=8080").getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Инициализируем host
    public static void hostInit() {
        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(configPath);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        host = properties.getProperty("host") + properties.getProperty("port");
        System.out.println("Обращаемся к серверу: " + host);
    }

}
