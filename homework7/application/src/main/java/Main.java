import commons.FlywayInitializer;
import core.DefaultServer;
import handlers.SecurityHandlerBuilder;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import servlets.ProductServlet;

import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        FlywayInitializer.initDb();

        final Server server = new DefaultServer().build();
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        final URL resource = DefaultServlet.class.getResource("/static");
        context.setBaseResource(Resource.newResource(resource.toExternalForm()));
        context.addServlet(DefaultServlet.class, "/*");
        context.addServlet(new ServletHolder("products", new ProductServlet()), "/products");

        final String jdbcConfig = Main.class.getResource("/db/jdbc_config").toExternalForm();
        final JDBCLoginService jdbcLoginService = new JDBCLoginService("login", jdbcConfig);
        final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(jdbcLoginService);
        server.addBean(jdbcLoginService);
        securityHandler.setHandler(context);
        server.setHandler(securityHandler);

        server.start();
    }
}
