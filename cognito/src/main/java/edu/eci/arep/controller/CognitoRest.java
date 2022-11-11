package edu.eci.arep.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CognitoRest {

    @Value("${edu.eci.arep.post}")
    private String poststream;

    @Value("${edu.eci.arep.get}")
    private String getstream;

    @GetMapping("/stream")
    public ResponseEntity<?> getStream(@RequestHeader Map<String, String> headers) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type",
                    "application/json");
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(callBack(getstream, headers, null));
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error en el get", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stream")
    public ResponseEntity<?> postStream(@RequestHeader Map<String, String> headers, @RequestBody String json) {
        try {
            return new ResponseEntity<>(callBack(poststream, headers, json), HttpStatus.ACCEPTED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error en el post", HttpStatus.BAD_REQUEST);
        }
    }

    private String callBack(String destino, Map<String, String> headers, String json) throws IOException {
        URL url = new URL(destino);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        for (String headerKey : headers.keySet()) {
            connection.setRequestProperty(headerKey, headers.get(headerKey));
        }
        if (json != null) {
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
        }
        connection.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        connection.disconnect();
        return sb.toString();
    }

}
