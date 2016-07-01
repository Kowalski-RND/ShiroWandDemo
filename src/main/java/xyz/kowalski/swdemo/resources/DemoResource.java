package xyz.kowalski.swdemo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import xyz.kowalski.swdemo.ApiToken;
import xyz.kowalski.swdemo.filters.ApiKey;

@Path("/demo")
@Produces(MediaType.TEXT_PLAIN)
public class DemoResource {

    @GET
    public String smokeTest() {
        return "Potato";
    }

    @GET
    @Path("test")
    @ApiKey
    public String requiresApiAuth() {
        return "You are authed!";
    }

    @POST
    @Path("login")
    public String fakeLogin() {
        final Subject currentSubject = SecurityUtils.getSubject();
        currentSubject.login(new ApiToken("potato-salad"));
        return "this just logs you in cause it is fake";
    }

    @GET
    @Path("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "logged out!";
    }

    @GET
    @Path("shiro")
    @RequiresAuthentication
    public String requiresShiroAuth() {
        return "you are logged in with shiro";
    }

}
