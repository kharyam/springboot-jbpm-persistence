package com.company.service;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                //naming is not enabled by default
                tomcat.enableNaming();
                return new TomcatWebServer(tomcat, this.getPort() >= 0, this.getShutdown());
            }
        };
        factory.addContextCustomizers(new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/ds");
				resource.setType(DataSource.class.getName());
                resource.setProperty("driverClassName", driverClassName);
                resource.setProperty("url", url);
                resource.setProperty("username", username);
                resource.setProperty("password", password);
				context.getNamingResources().addResource(resource);
            }
            });
            return factory;
        } 

}
