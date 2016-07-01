package xyz.kowalski.swdemo.services;

import javax.inject.Inject;

public class FakeInjectedService {

    private final String injectedHooplah;

    @Inject
    public FakeInjectedService(final String injectedHooplah) {
        this.injectedHooplah = injectedHooplah;
    }

    public String getMessage() {
        return "Hello ".concat(injectedHooplah).concat("!!!");
    }

}
