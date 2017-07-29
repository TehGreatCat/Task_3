package com.romanov;

import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class App {

    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static final Scanner sc = new Scanner(System.in);
    private static final HttpRequester rqstr = new HttpRequester(httpClient,
            "trnsl.1.1.20170728T113613Z.85ed6ab48bd75e8f.5aa49e8cdc0685852d0e19df81717444b845b83e");

    public static void main(String[] args) throws JSONException, IOException, HttpException {
        System.out.println("Translator is online: ");
        while(true){
            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                if (input.equals("0")) {
                    break;
                } else {
                    String output = rqstr.TranslateRequester(input);
                    System.out.println(output);
                }
            }
        }
    }
}
