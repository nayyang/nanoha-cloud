package netty.simple;

import java.util.Scanner;

public class TestNetty {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		while (true) {
			String line = s.nextLine();
			if (line.equals("bye"))
				break;
			System.out.println(">>>" + line);
		}
	}
}
