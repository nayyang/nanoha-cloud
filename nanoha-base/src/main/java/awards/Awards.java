package awards;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Awards {
    public static void main(String[] args) throws Exception {
        List<String> lines = IOUtils.readLines(Awards.class.getResourceAsStream("/ad.txt"));
        for (String line : lines) {
            System.out.println(StringUtils.substringAfter(line, " "));
        }
    }
}
