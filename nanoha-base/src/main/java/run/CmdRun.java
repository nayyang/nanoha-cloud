package run;

public class CmdRun {
	public static void runbat() throws Exception {
		String cmd = "cmd /c start D:\\soft\\zookeeper-3.5.1-alpha\\bin\\zkServer.cmd";

		Runtime.getRuntime().exec(cmd);
	}

	public static void main(String[] args) throws Exception {
		runbat();
	}
}