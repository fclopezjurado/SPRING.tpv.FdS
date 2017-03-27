package config;

import controllers.MailServiceMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.MailService;

@Configuration
@Import({MailConfig.class})
public class TestsMailConfig {

    @Bean
    public MailService mailService() {
        return new MailServiceMock();
    }
}
