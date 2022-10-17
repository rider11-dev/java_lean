package rider11.hellospringboot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.controller.dto.HttpRequestType;
import rider11.hellospringboot.entity.User;

@RestController
@Slf4j
@RequestMapping("http")
public class HttpController {
    private User _user;
    private static final String charset = "UTF-8";
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public HttpController(User user, ObjectMapper objectMapper, RestTemplate restTemplate) {
        this._user = user;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @PostMapping("getUser")
    public User getUser(@RequestBody User user) {
        // public User getUser(User user) {
        log.debug("getUser入参:{}", user.toString());
        return this._user;
    }

    @GetMapping("test")
    public String test(User user, HttpRequestType type) throws IOException {
        String result = "";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("type:%s\r\n", type));
        switch (type) {
            case http_url_connection:
                result = test_http_url_connection(user);
                break;
            case url_connection:
                result = test_url_connection(user);
                break;
            case http_client:
                result = test_http_client(user);
            case rest_template:
                result = test_rest_template(user);
            default:
                break;
        }
        sb.append(String.format("result:%s", result));

        return sb.toString();
    }

    private String test_rest_template(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:8081/http/getUser";
        // String resp = restTemplate.postForObject(url, user, String.class);
        // return resp;
        HttpEntity<User> request = new HttpEntity<User>(user, headers);
        ResponseEntity<String> resp = restTemplate.postForEntity(url, request, String.class);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("response_code:%d\r\n", resp.getStatusCodeValue()));
        sb.append(String.format("response_body:%s", resp.getBody()));
        return sb.toString();
    }

    private String test_http_client(User user) throws HttpException, IOException {
        HttpClient httpClient = new HttpClient();
        HttpMethod m = null;
        try {
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
            PostMethod method = new PostMethod("http://localhost:8081/http/getUser");
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
            method.addRequestHeader("Content-Type", "application/json;charset=" + charset);
            StringRequestEntity requestEntity = new StringRequestEntity(this.objectMapper.writeValueAsString(user),
                    "application/json", charset);
            method.setRequestEntity(requestEntity);
            m = method;

            httpClient.executeMethod(method);
            byte[] body = method.getResponseBody();
            String result = new String(body, charset);
            method.releaseConnection();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("response_code:%d\r\n", method.getStatusCode()));
            sb.append(String.format("response_body:%s", result));
            return sb.toString();

        } catch (Exception e) {
            log.error("test_http_client", e);
            return "test_http_client出错";
        } finally {
            if (m != null) {
                m.releaseConnection();
            }
        }
    }

    private String test_url_connection(User user) throws IOException {
        URLConnection connection = null;
        BufferedReader reader = null;
        OutputStream out = null;
        try {
            URL url = new URL("http", "localhost", 8081, "/http/getUser");
            connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            // 请求参数
            connection.connect();
            out = connection.getOutputStream();
            out.write(this.objectMapper.writeValueAsString(user).getBytes(charset));
            out.flush();

            InputStream inputStream = connection.getInputStream();
            StringBuffer resultBuilder = new StringBuffer();
            String line;
            reader = new BufferedReader(new InputStreamReader(inputStream, charset));
            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line);
            }
            return resultBuilder.toString();
        } catch (Exception e) {
            log.error("test_url_connection", e);
            return "test_url_connection出错";
        } finally {
            if (reader != null) {
                reader.close();
            }
            // if (connection != null) {

            // }
            if (out != null) {
                out.close();
            }
        }
    }

    private String test_http_url_connection(User user) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        OutputStream out = null;
        try {
            URL url = new URL("http", "localhost", 8081, "/http/getUser");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);
            // 允许写出
            connection.setDoOutput(true);
            // 允许读入
            connection.setDoInput(true);
            // 不用缓存
            connection.setUseCaches(false);
            // 传递参数
            connection.connect();
            out = connection.getOutputStream();
            out.write(objectMapper.writeValueAsString(user).getBytes(charset));
            out.flush();

            int respCode = connection.getResponseCode();
            // 得到响应流
            InputStream inputStream = connection.getInputStream();
            // 响应流转换成字符串
            StringBuffer resultBuilder = new StringBuffer();
            String line;
            resultBuilder.append(String.format("response_code:%d\r\n", respCode));
            resultBuilder.append("response_body:\r\n");
            reader = new BufferedReader(new InputStreamReader(inputStream, charset));
            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line);
            }
            return resultBuilder.toString();

        } catch (Exception e) {
            log.error("test_http_url_connection", e);
            return "test_http_url_connection出错";
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}
