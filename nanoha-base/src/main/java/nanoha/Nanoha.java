package nanoha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Nanoha {
    public static void main(String[] args) {
        List<Content> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new Content("机器人" + i));
        }
        System.out.println(JSON.toJSONString(list));
    }
}

class Content {
    String timePrize;
    // timePrize的时间撮
    String timePrizeNum;
    String username;

    public Content() {
    }

    /**
     * 时间撮如：10:10:10.101-->101010101
     * 
     * @param timePrize
     *            2016-10-18 15:41:00.001
     * @param username
     */
    public Content(String username) {
        this.timePrize = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        this.username = username;
        this.timePrizeNum = new SimpleDateFormat("HHmmssSSS").format(new Date());
    }

    public String getTimePrize() {
        return timePrize;
    }

    public void setTimePrize(String timePrize) {
        this.timePrize = timePrize;
    }

    public String getTimePrizeNum() {
        return timePrizeNum;
    }

    public void setTimePrizeNum(String timePrizeNum) {
        this.timePrizeNum = timePrizeNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}