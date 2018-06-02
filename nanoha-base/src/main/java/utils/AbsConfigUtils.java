package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsConfigUtils {
	private static final Logger logger = LoggerFactory.getLogger(AbsConfigUtils.class);

	public abstract String getFileNameStart();

	public abstract String getFilePath();

	public abstract String[] getJarFiles();

	private Map<String, String> pros = new HashMap<String, String>();

	public AbsConfigUtils() {
		try {
			loadConfigs();
		} catch (Exception e) {
			logger.error("加载配置失败", e);
		}
	}

	public void loadJarConfig() {
		try {
			String[] files = getJarFiles();
			Properties properties = new Properties();
			String name = "";
			for (int i = 0; i < files.length; i++) {
				properties.load(this.getClass().getResourceAsStream(files[i]));
				Enumeration<?> propertyNames = properties.propertyNames();
				while (propertyNames.hasMoreElements()) {
					name = (String) propertyNames.nextElement();
					pros.put(name, properties.getProperty(name));
				}
			}
		} catch (Exception ex) {
			logger.error("加载配置失败", ex);
		}
	}

	public void loadFileConfig() {
		try {
			String classPath = getFilePath();
			File file = new File(classPath);
			File[] files = null;

			files = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.startsWith(getFileNameStart())) {
						return true;
					}
					return false;
				}
			});

			Properties properties = new Properties();
			String name = "";
			for (int i = 0; i < files.length; i++) {
				properties.load(new FileReader(files[i]));
				Enumeration<?> propertyNames = properties.propertyNames();
				while (propertyNames.hasMoreElements()) {
					name = (String) propertyNames.nextElement();
					pros.put(name, properties.getProperty(name));
				}
			}
		} catch (Exception e) {
			logger.error("加载配置失败", e);
		}
	}

	public void loadConfigs() throws Exception {
		// 加载jar包中配置
		if (getJarFiles() != null && getJarFiles().length > 0) {
			loadJarConfig();
		}

		// 加载文件中配置
		if (getFileNameStart() != null && getFilePath() != null) {
			loadFileConfig();
		}
	}

	public Map<String, String> getPros() {
		return pros;
	}

	public String getString(String name) {
		Object obj = getStringContainKey(name);
		if (obj == null) {
			return null;
		}
		return (String) obj;
	}

	public String[] getStringArr(String name, String regex) {
		Object obj = get(name);
		if (obj == null) {
			return null;
		}
		return ((String) obj).split(regex);
	}

	public String[] getStringArr(String name) {
		return getStringArr(name, ",");
	}

	public String getString(String name, String... params) {
		String value = getStringContainKey(name);
		for (int i = 0; i < params.length; i++) {
			value = value.replace("{" + i + "}", params[i]);
		}
		return value;
	}

	public int getInt(String name) {
		return Integer.parseInt(getPros().get(name));
	}

	public long getLong(String name) {
		return Long.parseLong(getPros().get(name));
	}

	public boolean getBoolean(String name) {
		return Boolean.parseBoolean(getPros().get(name));
	}

	public double getDouble(String name) {
		return Double.parseDouble(getPros().get(name));
	}

	public Float getFloat(String name) {
		String obj = get(name);
		if (obj == null) {
			return null;
		}
		return Float.parseFloat(obj);
	}

	public String get(String name) {
		return getPros().get(name);
	}

	/**
	 * 获取字符串(包含key)
	 * 
	 * @param name
	 * @return
	 */
	private String getStringContainKey(String name) {
		String value = get(name);
		if (value == null) {
			return value;
		}

		String result = new String(value.getBytes());
		String temp;

		int start = value.indexOf("$pl{");
		int end;
		if (start == -1) {
			return value;
		}
		while (start != -1) {
			end = value.indexOf("}", start);
			temp = value.substring(start + 4, end);
			result = result.replace("$pl{" + temp + "}", getString(temp));
			start = value.indexOf("$pl{", end);
		}

		return result;
	}
}
