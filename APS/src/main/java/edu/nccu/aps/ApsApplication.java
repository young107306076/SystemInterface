package edu.nccu.aps;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.Logger;

//這邊使用 Java Class 作為設定，而非XML
@Configuration
//啟用 Spring Boot 自動配置，將自動判斷專案使用到的套件，建立相關的設定。
@EnableAutoConfiguration
//自動掃描 Spring Bean 元件
@ComponentScan(basePackages = { "edu.nccu.aps.controller", "edu.nccu.aps.service" })

@SpringBootApplication
public class ApsApplication {
	private static final Logger log = (Logger) LoggerFactory.getLogger(ApsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApsApplication.class, args);
		log.info("\n----------------------------------------------------------------------\n\t" + "Application '{}' is running!\n" + "----------------------------------------------------------------------", ApsApplication.class.getSimpleName());
	}
}
