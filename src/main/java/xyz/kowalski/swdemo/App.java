package xyz.kowalski.swdemo;

import org.eclipse.jetty.server.session.SessionHandler;
import org.secnod.shiro.jaxrs.ShiroExceptionMapper;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.kowalski.shirowand.ShiroWandBundle;

public class App extends Application<DemoConfig> {

    private final FakeModule guiceModule;
    private final GuiceBundle<DemoConfig> guiceBundle;

    public App() {
        guiceModule = new FakeModule();
        guiceBundle = GuiceBundle.<DemoConfig> newBuilder()
                .addModule(guiceModule)
                .setConfigClass(DemoConfig.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
    }

    public static void main(final String[] args) {
        try {
            new App().run(args);
        } catch (final Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public final void initialize(final Bootstrap<DemoConfig> bootstrap) {
        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(ShiroWandBundle.<DemoConfig> newBuilder().bindInjector(guiceBundle.getInjector())
                .addRealm(ApiKeyRealm.class).setSessionTimeout(30000).setCookieName("fakeDemo").build());
    }

    @Override
    public final void run(final DemoConfig configuration, final Environment environment) throws Exception, RuntimeException {
        environment.jersey().register(new ShiroExceptionMapper());
        environment.getApplicationContext().setSessionHandler(new SessionHandler());
    }

}
