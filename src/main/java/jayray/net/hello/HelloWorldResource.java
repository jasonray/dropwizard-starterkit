package jayray.net.hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

@Path("/hello-world")
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Saying sayHelloXml(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(), String.format(template, name.or(defaultName)));
    }

    @GET
    @Timed
    @Path("simple")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello2(@QueryParam("name") Optional<String> name) {
        return String.format(template, name.or(defaultName));
    }
}