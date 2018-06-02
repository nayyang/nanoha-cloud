package novel;

import org.apache.commons.io.FileUtils;
import utils.ResourceUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

public class RandomNovel {
    public static void main(String[] args) throws Exception {
        read();
    }

    static void read() throws Exception {
        File file = ResourceUtil.getResourcePath("novel/novel");
        if (file != null && file.isFile()) {
            List<String> readLines = FileUtils.readLines(file, Charset.defaultCharset());
            for (String line : readLines) {
                char[] charArray = line.toCharArray();
                for (char c : charArray) {
                    Thread.sleep(200);
                    System.out.print(c);
                }
                System.out.println();
            }
        }


    }
}
