package ch.fhnw.webfr.flashcard.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import ch.fhnw.webfr.flashcard.util.QuestionnaireInitializer;

@WebListener
public class BasicListener implements ServletContextListener {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String mode = sce.getServletContext().getInitParameter("mode");
		if("test".equals(mode)){
			QuestionnaireInitializer.createQuestionnaires();
		}
		logger.debug("Initialized repository");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) { }

}
