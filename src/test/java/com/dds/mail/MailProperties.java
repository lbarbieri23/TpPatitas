package com.dds.mail;

import java.io.IOException;
import java.util.Properties;

public class MailProperties {

	public static Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(MailProperties.class.getResourceAsStream("/mail.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}
}