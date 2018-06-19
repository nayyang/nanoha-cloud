/**
 * 
 */
package com.cason.demo.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;

/**
 * 貌似需要初始化配置
 * 
 * @author nanoha
 *
 */
@Component
@ConfigurationProperties
@ConditionalOnClass(Exporter.class)
public class DubboBaseConfig {

	static ApplicationConfig applicationConfig;
	static RegistryConfig registryConfig;
	static ProtocolConfig protocolConfig;

	@Bean
	public static AnnotationBean annotationBean(@Value("${dubbo.annotation.package}") String annotationPackage) {
		// 包扫描
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage(annotationPackage);
		return annotationBean;
	}

	@Bean
	public static ApplicationConfig applicationConfig(@Value("${dubbo.application.name}") String applicationName) {
		// 当前应用配置
		applicationConfig = new ApplicationConfig(applicationName);
		return applicationConfig;
	}

	@Bean
	public static RegistryConfig registryConfig(@Value("${dubbo.registry.address}") String registryAddress) {
		// 连接注册中心配置
		registryConfig = new RegistryConfig(registryAddress);
		return registryConfig;
	}

	@Bean
	public static ProtocolConfig protocolConfig(@Value("${dubbo.protocol.name}") String protocolName,
			@Value("${dubbo.protocol.port}") String protocolPort) {
		// 服务提供者协议配置
		protocolConfig = new ProtocolConfig(protocolName, Integer.valueOf(protocolPort));
		return protocolConfig;
	}

	@Bean(name = "defaultProvider")
	public static ProviderConfig providerConfig(@Value("${dubbo.provider.retries}") String providerRetries,
			@Value("${dubbo.provider.delay}") String providerDelay,
			@Value("${dubbo.provider.timeout}") String providerTimeout) {
		// dubbo提供服务
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setTimeout(Integer.valueOf(providerTimeout));
		providerConfig.setRetries(Integer.valueOf(providerRetries));
		providerConfig.setDelay(Integer.valueOf(providerDelay));
		providerConfig.setApplication(applicationConfig);
		providerConfig.setRegistry(registryConfig);
		providerConfig.setProtocol(protocolConfig);
		return providerConfig;
	}

}
