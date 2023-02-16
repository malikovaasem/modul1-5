package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.imageio.spi.ServiceRegistry;

public class Util {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "ss";
    private static final String PASSWORD = "";

    private static SessionFactory sessionFactory;

    public static Connection connectDB() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("hibernate.connection.username", USER);
                properties.setProperty("hibernate.connection.password", PASSWORD);
                properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

                Configuration configuration = new Configuration()
                        .addProperties(properties).addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение в создании sessionFactory");
                // e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
