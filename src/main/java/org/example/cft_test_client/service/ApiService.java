package org.example.cft_test_client.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ApiService {

    public static void putFile(String path, String uri) {

            RestTemplate restTemplate = new RestTemplate();
            Resource resource = new FileSystemResource(path);
            restTemplate.put(uri, resource);


    }

    public static void getFile(String path, String uri) {
        RestTemplate restTemplate = new RestTemplate();
        Resource resource = restTemplate.getForObject(uri, Resource.class);
        try {
            OutputStream outStream = new FileOutputStream(path);
            outStream.write(resource.getInputStream().readAllBytes());
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getList(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        String list = restTemplate.getForObject(uri, String.class);
        System.out.println(list);
    }

}
