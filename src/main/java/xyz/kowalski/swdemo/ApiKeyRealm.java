package xyz.kowalski.swdemo;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import xyz.kowalski.swdemo.services.FakeInjectedService;

public class ApiKeyRealm extends AuthorizingRealm {

    @Inject
    private FakeInjectedService fakeServiceMaybeForAuth;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {

        System.out.println("From service woohoo! Thanks shiro-wand: " + fakeServiceMaybeForAuth.getMessage());

        final SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(token, this.getName());

        final SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principals, token.getCredentials());

        return info;
    }

    @Override
    public boolean supports(final AuthenticationToken token) {
        return token instanceof ApiToken;
    }

}
