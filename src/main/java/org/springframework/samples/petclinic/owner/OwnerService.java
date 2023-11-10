package org.springframework.samples.petclinic.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Lazy
@Scope("prototype")
@Transactional
public class OwnerService implements ApplicationContextAware, InitializingBean {

	private final OwnerRepository ownerRepository;

	public Page<Owner> findAll(Pageable pageable) {
		return ownerRepository.findAll(pageable);
	}

	public Owner findById(Integer id) {
		return ownerRepository.findById(id);
	}

	public Page<Owner> findByLastName(String lastName, Pageable pageable) {
		return ownerRepository.findByLastName(lastName, pageable);
	}

	public void save(Owner owner) {
		ownerRepository.save(owner);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO: implements setApplicationContext logic
	}

	@Override
	public void afterPropertiesSet() {
		// TODO: implements postConstruct logic
	}
}
