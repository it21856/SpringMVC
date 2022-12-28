package gr.hua.dit.ds.springmvcdemo1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.ds.springmvcdemo1.dao.DayoffsDAO;
import gr.hua.dit.ds.springmvcdemo1.dao.UserDetailsDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.Dayoffs;
import gr.hua.dit.ds.springmvcdemo1.entity.UserDetails;

@Controller
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private DayoffsDAO DayoffsDAO;

	@Autowired
	private UserDetailsDAO userDetailsDAO;

	@GetMapping("/update")
	public String GetAcceptedRequests(Model model) {

		List<Dayoffs> det = DayoffsDAO.getDayoffs();

		model.addAttribute("lists", det); // Adds Dayoffs to updatepayroll.jsp

		return "updatepayroll";
	}

	@PostMapping("/updatesal")
	public String UpdatePayrolls(HttpServletRequest request, HttpServletResponse response, Model model) {

		String username = request.getParameter("username"); // Gets updated user's username from updatepayroll.jsp
		System.out.println(username);
		String sal1 = request.getParameter("sal"); // Gets updated salary entry from updatepayroll.jsp

		if (sal1.isEmpty() || checkNumber(sal1)) {

			return "redirect:/payroll/update";

		}

		int sal = Integer.parseInt(sal1); // Converts salary (sal1) from String to int

		System.out.println(sal);

		userDetailsDAO.InsertSalary(username, sal);

		return "redirect:/payroll/update";
	}

	private boolean checkNumber(String sal) {

		int temp = -1;

		try {
			
			temp = Integer.parseInt(sal);
			if(temp<0) {
				temp=-1;
			}
		} catch (Exception e) {

			System.out.println("not an int");

		}

		return temp == -1;
	}

	@GetMapping("/check")
	public String GetNewUsers(Model model) {

		List<UserDetails> det = userDetailsDAO.getnewUserDetails();

		model.addAttribute("lists", det); // Adds new users to checknewusers.jsp

		return "checknewusers";
	}

	@PostMapping("/checksal")
	public String addSal(HttpServletRequest request, HttpServletResponse response, Model model) {

		String username = request.getParameter("username"); // Gets selected user's username from checknewusers.jsp
															// prompt

		// System.out.println(request.getParameter("entry"));
		String sal1 = request.getParameter("entry"); // Gets salary value from checknewusers.jsp prompt

		if (sal1.isEmpty() || checkNumber(sal1)) {

			return "redirect:/payroll/check";

		}

		int sal = Integer.parseInt(sal1); // Converts salary (sal1) from String to int

		System.out.println(sal);

		userDetailsDAO.InsertSalary(username, sal);

		return "redirect:/payroll/check";
	}
}
