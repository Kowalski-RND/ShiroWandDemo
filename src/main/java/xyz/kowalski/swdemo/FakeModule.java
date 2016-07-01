package xyz.kowalski.swdemo;

import com.google.inject.AbstractModule;

public class FakeModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(String.class).toInstance("Won Wockwell");

    }

}
