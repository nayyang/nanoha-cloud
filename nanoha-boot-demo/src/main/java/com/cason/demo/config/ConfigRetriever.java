/**
 * 
 */
package com.cason.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author nanoha
 *
 */
@Configuration
@EnableAutoConfiguration
@PropertySources(value = { @PropertySource("classpath:config/config.properties") })
public class ConfigRetriever {
	@Value("${config.nihaoya}")
	public String nihaoya;
}
