package com.cts.presentation;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.cts.bo.RegistrationVO;

@Configuration
@ComponentScan("com.cts")
@PropertySource("com\\cts\\resource\\constant.properties")
public class AppConfig
{

	@Bean
	public DataSource dataSource(@Value("${jdbc.driverClassName}") String driver,@Value("${jdbc.url}") String url) {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName( driver );
		driverManagerDataSource.setUrl( url );
		return driverManagerDataSource;
	}
	
	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertyPlaceholderConfigurer();
	}
	
	@Bean(name="us")
	public Properties getUsProp() throws IOException {
		Properties pr = new Properties();
		ClassPathResource cs = new ClassPathResource( "cts\\resource\\locale\\messages_en_US.properties" );
		pr.load( cs.getInputStream() );
		return pr;
	}
	
	@Bean
	public Properties getFrProp() throws IOException {
		Properties pr = new Properties();
		ClassPathResource cs = new ClassPathResource( "cts\\resource\\locale\\messages_fr.properties" );
		pr.load( cs.getInputStream() );
		return pr;
	}
	
	@Bean
	public RegistrationVO getRegistrationVO(@Value("${csr.empName}") String empName, @Value("${csr.empEmail}") String empEmail,@Value("${csr.phoneNo}") String phoneNo,@Value("${csr.unitCode}") Integer unitCode,@Value("${csr.amount}") Integer amount) {
		RegistrationVO registrationVO = new RegistrationVO();
		registrationVO.setAmount( amount );
		registrationVO.setEmpEmail( empEmail );
		registrationVO.setEmpName( empName );
		registrationVO.setPhoneNo( phoneNo );
		registrationVO.setUnitCode( unitCode );
		return registrationVO;
	}
}
