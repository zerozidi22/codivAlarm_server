package com.happyWatter.codivAlarm.service;


import com.happyWatter.codivAlarm.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendService {

    @Autowired
    public TokenService tokenService;

    @Value("${happyWater.fireBaseServerKey}")
    public String key;

    public void sendToFcm(String title, String body) {


        try {

            // fcm에 보내는 로직
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "key=" + key);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            // 1. 메시지 데이터 담을 곳 부터 만들기
            JSONObject container = new JSONObject();
            JSONObject messageData = new JSONObject();
            JSONObject registration_ids = new JSONObject();

            // 1.1 메시지 데이터 만들기
            messageData.put("title", title);
            messageData.put("body", body);

            List<User> user = tokenService.selectTokens();

            List<String> tokens = new ArrayList<>();
//            for(User data : user){
//                tokens.add(data.getToken());
//            }

            JSONArray token = new JSONArray();
            for(User data : user){
                token.put(data.getToken());
            }
//            token.put(tokens);

            container.put("data", messageData);

            container.put("registration_ids", token);



            wr.write(container.toString().getBytes("UTF-8"));

            wr.flush();
            wr.close();

            connection.connect();
            connection.getResponseCode();
            String responseBody = null;
            responseBody = getAndClose(connection.getInputStream());
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getAndClose(InputStream stream) throws IOException {
        try {
            return getString(stream);
        } finally {
            if (stream != null) {
                close(stream);
            }
        }
    }

    private String getString(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder content = new StringBuilder();
        String newLine;
        do {
            newLine = reader.readLine();
            if (newLine != null) {
                content.append(newLine).append('\n');
            }
        } while (newLine != null);
        if (content.length() > 0) {
            content.setLength(content.length() - 1);
        }
        return content.toString();
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }


    public void sendToFcmToOnePerson(String title, String body, String token_from) {

        try {

            // fcm에 보내는 로직
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "key=" + key);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            // 1. 메시지 데이터 담을 곳 부터 만들기
            JSONObject container = new JSONObject();
            JSONObject messageData = new JSONObject();
            JSONObject registration_ids = new JSONObject();

            // 1.1 메시지 데이터 만들기
            messageData.put("title", title);
            messageData.put("body", body);

            List<User> user = tokenService.selectTokens();

            List<String> tokens = new ArrayList<>();

            JSONArray token = new JSONArray();

            token.put(token_from);

            container.put("data", messageData);

            container.put("registration_ids", token);



            wr.write(container.toString().getBytes("UTF-8"));

            wr.flush();
            wr.close();

            connection.connect();
            connection.getResponseCode();
            String responseBody = null;
            responseBody = getAndClose(connection.getInputStream());
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
