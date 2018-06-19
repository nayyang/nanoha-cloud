package nanoha;

import org.apache.commons.lang.StringUtils;

public class RandomT {
    public static void main(String[] args) {
        System.err.println("03493b53ff2353c67f8adae2c4088d83".length());
        String keywords = "\\";
        String[] seachs = { "%", "_", "[", "]", "^", ".", "$", "{", "}", "'", ",", "`", "\\" };
        String[] replas = { "\\%", "\\_", "\\[", "\\]", "\\^", "\\.", "\\$", "\\{", "\\}", "\\'", "\\,", "\\`",
                "\\\\" };
        keywords = StringUtils.replaceEach(keywords, seachs, replas);
        System.out.println(keywords);

        String t = "holy shit";
        System.out.println(StringUtils.reverse(t));
    }
}
