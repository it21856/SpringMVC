package gr.hua.dit.ds.springmvcdemo1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.ds.springmvcdemo1.dao.AdminDAO;
import gr.hua.dit.ds.springmvcdemo1.entity.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminDAO adminDAO;

	@RequestMapping(value = { "", "/" })
	public String adminpage(Model model) {

		List<User> users = adminDAO.getUsers();
		model.addAttribute("lists", users);

		return "getuser";
	}

	@GetMapping("/add")
	public String AddUser() {

		return "adduser";
	}

	@PostMapping("/add")
	public String AddNewUser(HttpServletRequest request, Model model) {

		String username = request.getParameter("username"); // Gets username entry from adduser.jsp
		String password = request.getParameter("password"); // Gets password entry from adduser.jsp
		String[] roles = request.getParameterValues("roles"); // Gets roles entries from adduser.jsp

		int enabled = Integer.parseInt(request.getParameter("enabled")); // Gets enabled value from adduser.jsp and
																			// converts it from String to int

		User user = new User(username, password, enabled);
		if (adminDAO.getUserByUsername(username) != null) {

			model.addAttribute("error", "User Exists");

		} else {

			adminDAO.InsertUser(user, roles);

		}

		return "redirect:/admin";
	}

	@GetMapping("/get/{username}")
	public String GetUserByUsername(Model model, @PathVariable("username") String username) {

		User user = adminDAO.getUserByUsername(username);

		if (user != null) {

			model.addAttribute("user", user);
		}

		return "getuser";
	}

	@GetMapping("/edit/{username}")
	public String EditUser(Model model, @PathVariable("username") String username) {

		User user = adminDAO.getUserByUsername(username);

		if (user != null) {// if user exists get editform else redirect to add a new user

			model.addAttribute("user", user); // Adds user to editform.jsp
			model.addAttribute("role", "checked"); // Checks the boxes of user's roles in editform.jsp

			return "editform";
		}

		return "redirect:/admin/add/";
	}

	@PostMapping("/edit/{username}")
	public String UpdateUser(HttpServletRequest request, @PathVariable("username") String username) {

		User user = adminDAO.getUserByUsername(username);

		if (user == null) {

			return "redirect:/admin";

		}

		String password = request.getParameter("password"); // Gets password entry from editform.jsp
		String[] roles = request.getParameterValues("roles"); // Gets checked roles from editform.jsp

		int enabled = Integer.parseInt(request.getParameter("enabled")); // Gets enabled value from editform.jsp

		adminDAO.UpdateUser(username, password, roles, enabled);

		return "redirect:/admin";
	}

	@GetMapping("/delete/{username}")
	public String DeleteUser(@PathVariable("username") String username) {

		adminDAO.DeleteUser(username);

		return "redirect:/admin";
	}

	@GetMapping("/test")
	public String Test() {

		adminDAO.Test();

		return "redirect:/admin";
	}

}
