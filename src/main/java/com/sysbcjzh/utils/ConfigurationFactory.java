package com.sysbcjzh.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationFactory
{
	public static Properties getConfigDir(String file)
	{
		String path = ConfigurationFactory.class.getClassLoader().getResource(file).getPath();
		Properties prop = new Properties();
		try
		{
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}