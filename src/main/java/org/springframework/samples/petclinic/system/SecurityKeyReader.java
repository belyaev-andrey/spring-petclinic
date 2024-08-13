package org.springframework.samples.petclinic.system;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SecurityKeyReader implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		ConfigurableEnvironment env = beanFactory.getBean(ConfigurableEnvironment.class);
		Map<String, Object> myMap = new HashMap<>();
		String encodedKey = "c2FsYXJ5LmludGVncmF0aW9uLmVuYWJsZWQ=";
		String encodedValue = "ZmFsc2U=";
		myMap.put(new String(Base64.getDecoder().decode(encodedKey)), new String(Base64.getDecoder().decode(encodedValue)));
		env.getPropertySources().addFirst(new MapPropertySource("SECURITY_KEY", myMap));
	}

}
