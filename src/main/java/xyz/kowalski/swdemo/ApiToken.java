package xyz.kowalski.swdemo;

import org.apache.shiro.authc.AuthenticationToken;

public class ApiToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private final String key;

    public ApiToken(final String key) {
        this.key = key;
    }

    public Object getCredentials() {
        return key;
    }

    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return null;
    }

}
