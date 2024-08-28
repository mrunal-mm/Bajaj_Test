package com.app;


import com.app.utility.HashUtil;
import com.app.utility.RandomStringGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java -jar <jar-file> <PRN Number> <JSON File Location>");
            System.exit(1);
        }

        String prnNumber = args[0];
        String jsonFilePath = args[1];

        // Read and parse JSON file
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonContent);

        // Traverse JSON to get the "destination" value
        String destination = rootNode.path("destination").asText();
        if (destination.isEmpty()) {
            System.err.println("Destination key not found in JSON file.");
            System.exit(1);
        }

        // Generate a random alphanumeric string
        String randomString = RandomStringGenerator.generateRandomString(8);

        // Generate the hash
        String inputString = prnNumber + destination + randomString;
        String hash;
        try {
            hash = HashUtil.generateHash(inputString);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Hashing algorithm not found.");
            System.exit(1);
            return;
        }

        // Format and output the result
        String output = hash + ";" + randomString;
        System.out.println(output);
    }
}
