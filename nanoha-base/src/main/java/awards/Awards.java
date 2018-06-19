package awards;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

public class Awards {
	public static void main(String[] args) throws Exception {
		List<String> lines = FileUtils.readLines(new File("D:/docs_dkc/nanoha/src/main/resources/ad.txt"));
		for (String line : lines) {
			System.out.println(StringUtils.substringAfter(line, " "));
		}
	}
}
