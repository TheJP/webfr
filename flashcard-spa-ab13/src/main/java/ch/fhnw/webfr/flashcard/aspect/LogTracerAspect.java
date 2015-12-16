package ch.fhnw.webfr.flashcard.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTracerAspect {

	private static final Log logger = LogFactory.getLog(LogTracerAspect.class);

	@Before("execution(* ch.fhnw.webfr.flashcard.web.*.*(..))")
	public void beforeMethod(JoinPoint joinPoint){
		logger.debug(String.format("Called: %s", joinPoint.getSignature()));
	}
}
