package br.com.caelum.ingresso.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Esta classe carrega o arquivo "app.properties" que deverá conter os dados 
 * de acesso à API OMDB.
 * 
 */
public class OmdbApiConfig {
	String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	String appPropertiesPath = rootPath + "app.properties";
	Properties appProperties;

	public OmdbApiConfig() {
		appProperties = new Properties();
		
		try {
			appProperties.load(new FileInputStream(appPropertiesPath));
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
	/**
	 * 
	 * @return ID da API
	 */
	public String getId() {
		return appProperties.getProperty("OMDB_ID", "");
	}
	
	/**
	 * 
	 * @return Key da API
	 */
	public String getKey() {
		return appProperties.getProperty("OMDB_KEY", "");
	}

}
