package ch.fhnw.webfr.flashcard.test.web;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Configuration
// EnableWebMvc needed to import WebMvcConfigurationSupport which is triggered within the test
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ch.fhnw.webfr.flashcard.web"})
public class TestContext {
	@Bean
    public QuestionnaireRepository questionnaireRepository() {
        return Mockito.mock(QuestionnaireRepository.class);
    }
}
