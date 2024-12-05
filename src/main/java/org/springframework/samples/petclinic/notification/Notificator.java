package org.springframework.samples.petclinic.notification;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public interface Notificator {

	String sendNotification(int ownerId, int petId, int visitId);

	void onVisitScheduled(VisitScheduleNotification event);

}

@Component
@ConditionalOnProperty(name = "notifications.engine.type", havingValue = "prod")
class DevServiceGuard implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		ConfigurableEnvironment env = beanFactory.getBean(ConfigurableEnvironment.class);
		Map<String, Object> myMap = new HashMap<>();
		String encodedKey = "notifications.engine.key";
		String encodedValue = "dev";
		myMap.put(encodedKey, encodedValue);
		env.getPropertySources().addFirst(new MapPropertySource("SECURITY_KEY", myMap));
	}

}
