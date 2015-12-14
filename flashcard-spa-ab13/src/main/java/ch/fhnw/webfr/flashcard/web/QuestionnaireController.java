package ch.fhnw.webfr.flashcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@RestController
@RequestMapping("/questionnaires")
public class QuestionnaireController {

	@Autowired
	private QuestionnaireRepository repository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Questionnaire> get(@PathVariable("id") Long id){
		Questionnaire q = repository.findOne(id);
		if(q == null){ return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
		return new ResponseEntity<>(q, HttpStatus.OK); //Why not HttpStatus.FOUND?
	}
}
