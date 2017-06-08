package app.studentacademy.springConfig;

import app.studentacademy.model.Student;
import app.studentacademy.repository.Impl.StudentRepositoryImpl;
import app.studentacademy.repository.StudentRepository;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({
        "app.studentacademy.springConfig", "app.studentacademy.model", "app.studentacademy.repository",
        "app.studentacademy.service", "app.studentacademy.controller" })
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContextConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        /*Local MySQL DATA CONNECTION */
        /*BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sastudent");
        dataSource.setUsername("root");
        dataSource.setPassword("root");*/

        /*Local MySQL DATA CONNECTION */
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:8080/sastudent");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        /*POSTGRES REMOTE DATA CONNECTION */
        /*BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-50-17-236-15.compute-1.amazonaws.com:5432/de82mg27hgsfij");
        dataSource.setUsername("wkzruuxneupehn");
        dataSource.setPassword("4fa5e2f3ad5265b63e7abe5f7a03920241ec82366b5e1d1cda04a952051b563e");*/


        /*POSTGRES REMOTE DATA CONNECTION */
        /*BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-197-232-155.compute-1.amazonaws.com:5432/d2h2o5lfrdg5dk");
        dataSource.setUsername("aamyipvtgerbyq");
        dataSource.setPassword("13beb37f5e3d49de480519a1548e9e18e0f8e6ff15b8e72e650ce77882942547");*/

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        sessionBuilder.scanPackages("app.studentacademy.springConfig", "app.studentacademy.model",
                "app.studentacademy.repository", "app.studentacademy.service", "app.studentacademy.controller");
        sessionBuilder.addAnnotatedClasses(Student.class); // @Entity
        sessionBuilder.addProperties(getHibernateProperties());

        return sessionBuilder.buildSessionFactory();
    }


    private Properties getHibernateProperties(){

        Properties properties = new Properties();
        //properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // POSTGRES
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.c3p0.min_size", "5");
        properties.put("hibernate.c3p0.max_size", "20");
        properties.put("hibernate.c3p0.timeout", "300");
        properties.put("hibernate.c3p0.max_statements", "50");
        properties.put("hibernate.c3p0.idle_test_period", "3000");

        return properties;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }

    @Bean(name = "studentRepository")
    public StudentRepository studentRepository(){
        return new StudentRepositoryImpl();
    }

}
