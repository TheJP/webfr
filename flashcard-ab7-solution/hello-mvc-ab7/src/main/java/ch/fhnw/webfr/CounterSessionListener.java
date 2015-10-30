package ch.fhnw.webfr;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebListener
public class CounterSessionListener implements HttpSessionListener {
	private static final Log logger = LogFactory.getLog(CounterSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.debug("new session created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.debug("session destroyed");
	}

}
