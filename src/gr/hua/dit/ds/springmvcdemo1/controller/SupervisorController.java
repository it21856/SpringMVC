package gr.hua.dit.ds.springmvcdemo1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.ds.springmvcdemo1.dao.DayoffsDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Dayoffs;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

	@Autowired
	private DayoffsDAO DayoffsDAO;

	@GetMapping("/requests")
	public String GetRequests(Model model) {

		List<Dayoffs> dayoffs = DayoffsDAO.getDayoffs();

		model.addAttribute("reqlist", dayoffs); // Adds dayoffs to getrequests.jsp

		return "getrequests";
	}

	@PostMapping("/status")
	public String AcceptRequests(HttpServletRequest request, HttpServletResponse response, Model model) {

		int id = Integer.parseInt(request.getParameter("id")); // Gets selected user's id from getrequests.jsp

		System.out.println(id);

		model.addAttribute("Dayoff", DayoffsDAO.getDayoffs());
		// System.out.println(doffs.toString());
		String button = request.getParameter("decline");

		if (button != null) {

			DayoffsDAO.UpdateDayoff(id, "Declined");

		} else {

			DayoffsDAO.UpdateDayoff(id, "Accepted");

		}

		return "redirect:/supervisor/requests";
	}

	@GetMapping("/update")
	public String updateDayoffs() {

		DayoffsDAO.DeleteByDayoff();
		// System.out.println("In progress");
		return "redirect:/supervisor/requests";
	}

}
