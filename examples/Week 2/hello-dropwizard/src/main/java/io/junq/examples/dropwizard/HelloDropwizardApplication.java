package io.junq.examples.dropwizard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.junq.examples.dropwizard.resources.HelloDropwizardResource;

/**
 * A Hello World sample Dropwizard Application
 *
 */
public class HelloDropwizardApplication extends Application<HelloDropwizardConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloDropwizardApplication.class);

	private HelloDropwizardConfiguration configuration;
	
	public static void main(String[] args) throws Exception {
		new HelloDropwizardApplication().run(args);
	}

	@Override
	public String getName() {
		if (configuration == null || configuration.getServiceName() == null) {
			return super.getName();
		}

		return configuration.getServiceName();
	}

	@Override
	public void run(HelloDropwizardConfiguration configuration, Environment environment) throws Exception {
		this.configuration = configuration;
		
		LOGGER.info("Start run " + getName() + " application");
		
		HelloDropwizardResource resource = new HelloDropwizardResource();
		
		environment.jersey().register(resource);
	}

}
