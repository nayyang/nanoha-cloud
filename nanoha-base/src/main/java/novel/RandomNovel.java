package novel;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RandomNovel {
	public static void main(String[] args) throws Exception {
		read();
	}

	static void read() throws Exception {
		List<String> readLines = FileUtils.readLines(new File("D:\\dkc\\nanoha\\src\\main\\resources\\novel\\novel"), Charset.defaultCharset());
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
