package ch.fhnw.webfr.flashcard.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import ch.fhnw.webfr.flashcard.web.TestUtil.QuestionnaireBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestContext.class})
// WebAppConfiguration triggers WebMvcConfigurationSupport which enables all the default handler-mappings and handler-adapters
// needed for a spring mvc webapp
@WebAppConfiguration  
public class QuestionnaireControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
    private QuestionnaireRepository questionnaireRepositoryMock;

	@Before
    public void setUp() {
		Mockito.reset(questionnaireRepositoryMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
    public void findById_QuestionnaireFound_ShouldReturnFound() throws Exception {
		Questionnaire questionnaire = new QuestionnaireBuilder(1L)
				.description("MyDescription")
				.title("MyTitle")
				.build();
		
		when(questionnaireRepositoryMock.findOne(1L)).thenReturn(questionnaire);
		
	    mockMvc.perform(get("/questionnaires/{id}", 1L)
                .header("Accept", "application/json")
        )
        		//.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.id", is(1)))
        		.andExpect(jsonPath("$.title",is("MyTitle")))
        		.andExpect(jsonPath("$.description", is("MyDescription")));
    }
	
	@Test
    public void findById_QuestionnaireNotExisting_ShouldReturnNotFound() throws Exception {	
	    mockMvc.perform(get("/questionnaires/{id}", 2L)
                .header("Accept", "application/json")
        )
        		.andExpect(status().isNotFound());
    }

	@Test
	public void createQuestionnaire() throws Exception {
		Questionnaire q = new QuestionnaireBuilder(2L)
			.title("title")
			.description("aaa")
			.build();
		when(questionnaireRepositoryMock.save(any(Questionnaire.class))).thenReturn(q);
		mockMvc.perform(
			post("/questionnaires/")
				.header("Accept", "application/json")
				.contentType("application/json")
				.content("{ \"title\": \"title\", \"description\": \"aaa\" }"))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(2)))
			.andExpect(jsonPath("$.title", is("title")))
			.andExpect(jsonPath("$.description", is("aaa")));
		verify(questionnaireRepositoryMock, times(1)).save(
			eq(new QuestionnaireBuilder(0L).title("title").description("aaa").build()));
	}

	@Test
	public void createQuestionnaireError() throws Exception {
		when(questionnaireRepositoryMock.save(any(Questionnaire.class))).thenReturn(null);
		mockMvc.perform(
			post("/questionnaires/")
				.header("Accept", "application/json")
				.contentType("application/json")
				.content("{ \"title\": \"title\", \"description\": \"aa\" }"))
			.andExpect(status().isInternalServerError());
	}

	@Test
	public void readAllQuestionnaires() throws Exception {
		Questionnaire q1 = new QuestionnaireBuilder(1L)
			.title("title")
			.description("aaa")
			.build();
		Questionnaire q2 = new QuestionnaireBuilder(2L)
				.title("title2")
				.description("aaa2")
				.build();
		when(questionnaireRepositoryMock.findAll()).thenReturn(Arrays.asList(q1, q2));
		mockMvc.perform(
			get("/questionnaires/").header("Accept", "application/json"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].title", is("title")))
			.andExpect(jsonPath("$[0].description", is("aaa")))
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].title", is("title2")))
			.andExpect(jsonPath("$[1].description", is("aaa2")));
		verify(questionnaireRepositoryMock, times(1)).findAll();
	}

//	@Test
//	public void updateQuestionnaire() throws Exception {
//		Questionnaire q1 = new QuestionnaireBuilder(1L)
//			.title("title")
//			.description("aaa")
//			.build();
//		Questionnaire q2 = new QuestionnaireBuilder(2L)
//				.title("title2")
//				.description("aaa2")
//				.build();
//		when(questionnaireRepositoryMock.findAll()).thenReturn(Arrays.asList(q1, q2));
//		mockMvc.perform(
//			get("/questionnaires/").header("Accept", "application/json"))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$[0].id", is(1)))
//			.andExpect(jsonPath("$[0].title", is("title")))
//			.andExpect(jsonPath("$[0].description", is("aaa")))
//			.andExpect(jsonPath("$[1].id", is(2)))
//			.andExpect(jsonPath("$[1].title", is("title2")))
//			.andExpect(jsonPath("$[1].description", is("aaa2")));
//	}
}
