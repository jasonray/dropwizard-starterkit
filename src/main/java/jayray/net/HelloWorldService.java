package jayray.net;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jayray.net.hello.CustomerResource;
import jayray.net.hello.EchoResource;
import jayray.net.hello.HelloWorldResource;

public class HelloWorldService extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldService().run(args);
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) {
		final String template = configuration.getTemplate();
		final String defaultName = configuration.getDefaultName();

		environment.jersey().register(new HelloWorldResource(template, defaultName));
		environment.jersey().register(new EchoResource());
		environment.jersey().register(new CustomerResource());

		environment.healthChecks().register("sample-healthcheck", new TemplateHealthCheck(template));
	}

}