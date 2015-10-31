package ch.fhnw.webfr.flashcard.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@ManagedBean
/*
 * Use RequestScoped if no state must be maintained between requests, what is
 * true if using simple forms
 */
@RequestScoped
/*
 * This is a spring annotation to expose this bean for dependency injection.
 */
@Component
public class QuestionnaireBean {
	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	/*
	 * Represents the backing bean for the form.
	 */
	private Questionnaire actualQuestionnaire;

	public int getSize() {
		return questionnaireRepository.findAll().size();
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaireRepository.findAll();
	}

	/**
	 * Returns the actual questionnaire (as backing bean)
	 * 
	 * @return the actual questionnaire
	 */
	public Questionnaire getActualQuestionnaire() {
		return actualQuestionnaire;
	}

	/**
	 * Returns the form view
	 * 
	 * @return view name containing the form
	 */
	public String createForm() {
		/*
		 * we want to have a new questionnaire as backing bean for each form request
		 */
		actualQuestionnaire = new Questionnaire();
		return "/pages/create";
	}

	/**
	 * Called from the create form to cancel the request
	 * 
	 * @return view name to switch to
	 */
	public String cancel() {
		return "/pages/questionnaires?faces-redirect=true";
	}

	/**
	 * Called from the create form to save a new questionnaire
	 *  
	 * @return view name to switch to
	 */
	public String create() {
		questionnaireRepository.save(actualQuestionnaire);
		return "/pages/questionnaires?faces-redirect=true";
	}
}
