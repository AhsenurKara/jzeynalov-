package httpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    public static HttpResponse getGetResponse(String schema, String host, String path, String[] queries){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            URIBuilder uri = new URIBuilder();
            uri.setScheme(schema);
            uri.setHost(host);
            uri.setPath(path);

            if (queries != null) {
                for (String query : queries) {
                    uri.setCustomQuery(query);
                }
            }

            HttpGet httpGet = new HttpGet(uri.build());
            httpGet.setHeader("Accept", "application/json");

            return httpClient.execute(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static HttpResponse getGetResponse(String endpoint){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpGet httpGet = new HttpGet(endpoint);
            httpGet.setHeader("Accept", "application/json");

            return httpClient.execute(httpGet);
        } catch (IOException e) {
            return null;
        }
    }

    public static Map<String, Object> getResponseBody(HttpResponse response){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, Object> deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>() {});

            return deserializedObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getResponseBodyAsList(HttpResponse response){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Map<String, Object>> deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                    new TypeReference<List<Map<String, Object>>>() {});

            return deserializedObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse getPostResponse(String schema, String host, String path, String[] queries, String payload){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            URIBuilder uri = new URIBuilder();
            uri.setScheme(schema);
            uri.setHost(host);
            uri.setPath(path);

            if (queries != null) {
                for (String query : queries) {
                    uri.setCustomQuery(query);
                }
            }

            HttpPost post = new HttpPost(uri.build());
            post.setHeader("Content-Type","application/json");
            post.setHeader("Accept","application/json");

            HttpEntity entity = new StringEntity(payload);

            post.setEntity(entity);

            return httpClient.execute(post);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static HttpResponse getPostResponse(String endpoint, String payload){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(endpoint);
            post.setHeader("Content-Type","application/json");
            post.setHeader("Accept","application/json");

            HttpEntity entity = new StringEntity(payload);

            post.setEntity(entity);

            return httpClient.execute(post);
        } catch (IOException e) {
            return null;
        }
    }

    public static HttpResponse getPutResponse(String schema, String host, String path, String[] queries, String payload){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            URIBuilder uri = new URIBuilder();
            uri.setScheme(schema);
            uri.setHost(host);
            uri.setPath(path);

            if (queries != null) {
                for (String query : queries) {
                    uri.setCustomQuery(query);
                }
            }

            HttpPut put = new HttpPut(uri.build());
            put.setHeader("Content-Type","application/json");
            put.setHeader("Accept","application/json");

            HttpEntity entity = new StringEntity(payload);

            put.setEntity(entity);

            return httpClient.execute(put);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static HttpResponse getPutResponse(String endpoint, String payload){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPut put = new HttpPut(endpoint);
            put.setHeader("Content-Type","application/json");
            put.setHeader("Accept","application/json");

            HttpEntity entity = new StringEntity(payload);

            put.setEntity(entity);

            return httpClient.execute(put);
        } catch (IOException e) {
            return null;
        }
    }

    public static HttpResponse getDeleteResponse(String schema, String host, String path, String[] queries){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            URIBuilder uri = new URIBuilder();
            uri.setScheme(schema);
            uri.setHost(host);
            uri.setPath(path);

            if (queries != null) {
                for (String query : queries) {
                    uri.setCustomQuery(query);
                }
            }

            HttpDelete delete = new HttpDelete(uri.build());
            delete.setHeader("Accept", "application/json");

            return httpClient.execute(delete);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static HttpResponse getDeleteResponse(String endpoint){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpDelete delete = new HttpDelete(endpoint);
            delete.setHeader("Accept", "application/json");

            return httpClient.execute(delete);
        } catch (IOException e) {
            return null;
        }
    }
}
