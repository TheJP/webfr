package ch.fhnw.webfr.hello;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/counter")
public class CounterController {
//	private Integer counter = 0;
//	
//	/*
//	 *  AB7 Aufgabe 1
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public String count(Model model) {
//		model.addAttribute("counter", ++counter);
//		return "count";
//	}	

	/*
	 * AB7 Aufgabe 2
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String count(Model model, HttpSession session) {
		Integer counter = (Integer) session.getAttribute("counter");
		counter = (counter == null) ? 1 : ++counter;
		model.addAttribute("counter", counter);
		session.setAttribute("counter", counter);
		return "count";
	}	
}
