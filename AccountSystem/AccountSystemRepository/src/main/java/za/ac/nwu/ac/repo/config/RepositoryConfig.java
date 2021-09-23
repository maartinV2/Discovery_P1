package za.ac.nwu.ac.repo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("za.ac.nwu.ac.repo.persistence")
@EntityScan("za.ac.nwu.ac.domain.persistence")
@PropertySource(value = "classpath:application-db.properties")
public class RepositoryConfig {

    /*private static  final  String[] ENTITY_PACKAGES_TO_SCAN={"za.ac.nwu.ac.domain.persistence"};
    private static  final  String   PERSISTENCE_UNIT_NAME="account.system.persistence";

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private  String password;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(buildJpaProperties());
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return  transactionManager;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return  new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        try{
            OracleDataSource dataSource = new OracleDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setURL(datasourceUrl);
            dataSource.setImplicitCachingEnabled(true);
            dataSource.setFastConnectionFailoverEnabled(true);

            return  dataSource;
        }
        catch(SQLException e) {
            throw new RuntimeException("unable to connect to database",e);
        }
    }

    private Properties buildJpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.transactionType","jta");
        return null;
    }*/
}

