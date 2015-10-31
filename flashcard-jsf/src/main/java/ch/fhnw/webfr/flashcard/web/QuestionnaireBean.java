package ch.fhnw.webfr.flashcard.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@ManagedBean
@RequestScoped
@Component
public class QuestionnaireBean {

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@ManagedProperty(value = "#{questionaire}", name="questionnaire")
	private Questionnaire actualQuestionnaire;

	public List<Questionnaire> getQuestionnaires() {
		return questionnaireRepository.findAll();
	}

	public void add() {
//		questionnaireRepository.save(q);
	}
}
