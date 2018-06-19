package nanoha;

import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtils {

    private static final Logger log = Logger.getLogger(HttpUtils.class);

    public static String sendGet(String url) {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                get.releaseConnection();
                // 休息3秒重试
                Thread.sleep(3000);
                sendGet(url);
            }
            return EntityUtils.toString(res.getEntity());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            get.releaseConnection();
        }
    }

    public static void main(String[] args) {

        // 由于短信模板是固定的，所以不要把content封装进去
        // 由于短信模板是估计的，所以不要把content封装进去
        // int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        // String content = new String("您的验证码是：" + mobile_code +
        // "。请不要把验证码泄露给其他人。");
        // HttpPost.sendsms("18520594287", content);
        File file = new File("D:/Banner.png");
        // HttpUtils.uploadFile("http://192.168.0.44:8080/ygouy-user-consumer/file/uploadShopImage?idShop=28",file);
    }

}