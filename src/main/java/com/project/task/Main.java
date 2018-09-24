package com.project.task;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

class Main {

    static String getStatus(String text, String kpp, String data) throws IOException {
        URL url = new URL("http://npchk.nalog.ru/ajax.html");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("inn", text);
        params.put("kpp", kpp);
        params.put("dt", data);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
        }catch (IOException e){
            boolean isError = conn.getResponseCode() >= 400;
            InputStream is = isError ? conn.getErrorStream() : conn.getInputStream();
            String contentEncoding = conn.getContentEncoding() != null ? conn.getContentEncoding() : "UTF-8";
            return IOUtils.toString(is, contentEncoding).replace("{\"ERRORS\":{", "")
                    .replace("},\"STATUS\":\"ERROR\"}", "")
                    .replace("\"inn\":[\"\\\"ИНН\\\" - ", "")
                    .replace("\"kpp\":[\"", "")
                    .replace("\"dt\":[\"", "")
                    .replace("\\\"КПП\\\"", "КПП")
                    .replace("\\\"Дата сделки\\\"", "Дата сделки")
                    .replace("\"]", "");
        }

        StringBuilder result = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            result.append((char) c);
        switch (result.toString()){
            case "\"0\"": result = new StringBuilder("Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату");
                break;
            case "\"1\"": result = new StringBuilder("Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату");
                break;
            case "\"2\"": result = new StringBuilder("Налогоплательщик зарегистрирован в ЕГРН");
                break;
            case "\"3\"": result = new StringBuilder("Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН, КПП не соответствует ИНН или не указан");
                break;
            case "\"3A\"": result = new StringBuilder("Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН");
                break;
            case "\"4\"": result = new StringBuilder("Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН");
                break;
        }
        return result.toString();

    }
}
