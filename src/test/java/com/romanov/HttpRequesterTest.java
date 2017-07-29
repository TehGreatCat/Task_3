package com.romanov;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpRequesterTest {

    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static final HttpRequester rqstr = new HttpRequester(httpClient,
            "trnsl.1.1.20170728T113613Z.85ed6ab48bd75e8f.5aa49e8cdc0685852d0e19df81717444b845b83e");

    @Test
    public void SimpleTest() throws Exception {
        assertEquals("Привет", rqstr.TranslateRequester("Hello"));
        assertEquals("Задача", rqstr.TranslateRequester("Task"));
    }

    @Test
    public void MediumTest() throws Exception {
        assertEquals("тестовое задание", rqstr.TranslateRequester("test task"));
        assertEquals("как вы", rqstr.TranslateRequester("how are you"));
    }

    @Test
    public void DifficultTest() throws Exception {
        assertEquals("Лондон-столица Великобритании", rqstr.
                TranslateRequester("London is a capital of Great Britain"));
        assertEquals("Снятся ли андроидам электрические овцы?", rqstr.
                TranslateRequester("Do androids dream of electric sheep?"));
    }

    @Test
    public void RussianToRussianTest() throws Exception {
        assertEquals("Привет", rqstr.TranslateRequester("Привет"));
        assertEquals("Фраш Нельзя Провернуть Назад", rqstr.
                TranslateRequester("Фраш Нельзя Провернуть Назад"));
    }

}