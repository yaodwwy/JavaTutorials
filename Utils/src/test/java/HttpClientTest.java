import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HttpClientTest {

    @Test
    public void testTimeout() throws IOException, InterruptedException {
        //1.set connect timeout
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(5000))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        //2.set read timeout
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .timeout(Duration.ofMillis(5009))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

    }

    @Test
    public void testBasicAuth() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(5000))
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("user", "passwd".toCharArray());
                    }
                })
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/basic-auth/user/passwd"))
                .timeout(Duration.ofMillis(5009))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    @Test
    public void testCookies() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(5000))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/headers"))
                .header("Cookie", "JSESSIONID=4f994730-32d7-4e22-a18b-25667ddeb636; userId=java11")
                .timeout(Duration.ofMillis(5009))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    @Test
    public void testSyncGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/ip"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    @Test
    public void testAsyncGet() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get?show_env=1"))
                .build();

        CompletableFuture<String> result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        System.out.println(result.get());
    }

    @Test
    public void testPostForm() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("id=999&value=content"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @Test
    public void testPostJsonGetJson() throws ExecutionException, InterruptedException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        StockDto dto = new StockDto();
        dto.setName("Stock1");
        dto.setSymbol("s1");
        dto.setType(StockDto.StockType.SH);
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(dto);

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        CompletableFuture<String> result = HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        System.out.println(result.get());
    }

    /**
     * 文件上传
     * 官方的HttpClient并没有提供类似WebClient那种现成的BodyInserters.fromMultipartData方法，因此这里需要自己转换
     * 这里使用org.apache.httpcomponents(httpclient及httpmime)的MultipartEntityBuilder构建multipartEntity，最后通过HttpRequest.BodyPublishers.ofInputStream来传递内容
     * 这里header要指定Content-Type值为multipart/form-data以及boundary的值，否则服务端可能无法解析
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testUploadFile() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        Path path = Path.of(getClass().getClassLoader().getResource("msconfig.properties").toURI());
        File file = path.toFile();

        String multipartFormDataBoundary = "Java11HttpClientFormBoundary";
        HttpEntity multipartEntity = MultipartEntityBuilder.create()
                .addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY))
                .setBoundary(multipartFormDataBoundary) //要设置，否则阻塞
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "multipart/form-data; boundary=" + multipartFormDataBoundary)
                .POST(HttpRequest.BodyPublishers.ofInputStream(() -> {
                    try {
                        return multipartEntity.getContent();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    /**
     * 文件下载
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testAsyncDownload() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get?file&download"))
                .build();

        String s2 = UUID.randomUUID().toString();
        s2 = s2.substring(0, 3);
        CompletableFuture<Path> result = client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Paths.get("msconfig-" + s2 + ".properties")))
                .thenApply(HttpResponse::body);
        System.out.println(result.get());
    }

    /**
     * 并发请求
     */
    @Test
    public void testConcurrentRequests() {
        HttpClient client = HttpClient.newHttpClient();
        List<String> urls = List.of("https://httpbin.org/get", "https://httpbin.org/get", "https://httpbin.org/get");
        List<HttpRequest> requests = urls.stream()
                .map(url -> HttpRequest.newBuilder(URI.create(url)))
                .map(reqBuilder -> reqBuilder.build())
                .collect(Collectors.toList());

        List<CompletableFuture<HttpResponse<String>>> futures = requests.stream()
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());
        futures.stream()
                .forEach(e -> e.whenComplete((resp, err) -> {
                    if (err != null) {
                        err.printStackTrace();
                    } else {
                        System.out.println(resp.body());
                        System.out.println(resp.statusCode());
                    }
                }));
        CompletableFuture.allOf(futures
                .toArray(CompletableFuture<?>[]::new))
                .join();
        /*
        sendAsync方法返回的是CompletableFuture，可以方便地进行转换、组合等操作
        这里使用CompletableFuture.allOf组合在一起，最后调用join等待所有future完成
         */
    }

    @Test
    public void testHandleException() throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(5000))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://twitter.com"))
                .build();

        CompletableFuture<String> result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenComplete((resp, err) -> {
                    if (err != null) {
                        err.printStackTrace();
                    } else {
                        System.out.println(resp.body());
                        System.out.println(resp.statusCode());
                    }
                })
                .thenApply(HttpResponse::body)
                .exceptionally(err -> {
                    err.printStackTrace();
                    return "fallback";
                });
        System.out.println(result.get());
    }

    @Test
    public void testHttp2() throws URISyntaxException {
        HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NEVER)
                .version(HttpClient.Version.HTTP_2)
                .build()
                .sendAsync(HttpRequest.newBuilder()
                                .uri(new URI("https://http2.akamai.com/demo"))
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .whenComplete((resp, t) -> {
                    if (t != null) {
                        t.printStackTrace();
                    } else {
                        System.out.println(resp.version());
                        System.out.println(resp.statusCode());
                    }
                }).join();
    }

    @Test
    public void testWebSocket() throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        WebSocket webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create("ws://localhost:8080/echo"), new WebSocket.Listener() {

                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        // request one more
                        webSocket.request(1);

                        // Print the message when it's available
                        return CompletableFuture.completedFuture(data)
                                .thenAccept(System.out::println);
                    }
                }).join();
        webSocket.sendText("hello ", false);
        webSocket.sendText("world ",true);

        TimeUnit.SECONDS.sleep(10);
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").join();
    }


    private static class StockDto {
        private String name;
        private String symbol;
        private StockType type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public StockType getType() {
            return type;
        }

        public void setType(StockType type) {
            this.type = type;
        }

        static enum StockType {
            SH
        }

        @Override
        public String toString() {
            return "StockDto{" +
                    "name='" + name + '\'' +
                    ", symbol='" + symbol + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}