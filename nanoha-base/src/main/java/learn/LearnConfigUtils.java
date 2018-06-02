package learn;

import utils.AbsConfigUtils;

public class LearnConfigUtils extends AbsConfigUtils {

	private static final LearnConfigUtils propConfig = new LearnConfigUtils();

	private LearnConfigUtils() {
	}

	public static LearnConfigUtils getInstance() {
		if (propConfig == null) {
			return new LearnConfigUtils();
		}
		return propConfig;
	}

	@Override
	public String getFileNameStart() {
		return "learn";
	}

	@Override
	public String getFilePath() {
		return null;
	}

	@Override
	public String[] getJarFiles() {
		return new String[] { "/learn/learn.properties" };
	}
}
