package bt;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class BTW16 {
    static String baseUrl = "http://www.go543.com/bt/";
    static String pageUrl = baseUrl + "thread.php?fid=16&page=";
    static String[] searchs = { "水菜", "水菜丽", "みづなれい", "Mizuna-rei" };
    static HttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) {
        execute();
    }

    private static void execute() {
        int page = 1;
        while (true) {
            try {
                Thread.sleep(500);
                System.out.println("第" + page + "页");
                getResponse(page);
                page++;
            } catch (Exception e) {
            }
        }
    }

    static void getResponse(int page) throws Exception {
        HttpGet get = new HttpGet(pageUrl + page);
        HttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 解析response
            List<String> lines = IOUtils.readLines(response.getEntity().getContent(), "GBK");
            // <h3><a href="htm_data/4/1703/894328.html" id="a_ajax_894328"
            // target=_blank>MLW-2170 セックスカウンセラー 片瀬仁美の性感クリニック [中文字幕]</a></h3>
            for (String line : lines) {
                if (searchMethod(line)) {
                    String text = StringUtils.substringBetween(line, "target=_blank>", "</a></h3>");
                    System.out.println(text);
                    String url = baseUrl + StringUtils.substringBetween(line, "<h3><a href=\"", "\"");
                    System.out.println(url);
                    FileUtils.writeStringToFile(new File("D:/docs_dkc/nanoha/src/main/resources/bt/BTW16.txt"),
                            text + "\n" + url, true);
                }
            }
            get.releaseConnection();
        } else {
            get.releaseConnection();
            getResponse(page);
        }
    }

    static boolean searchMethod(String line) {
        for (String search : searchs) {
            if (StringUtils.contains(line, search)) {
                return true;
            }
        }
        return false;
    }

}
