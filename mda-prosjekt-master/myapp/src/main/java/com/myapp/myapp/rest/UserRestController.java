package com.myapp.myapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.dao.CustomerDAO;
import com.myapp.myapp.dao.UserDAO;
import com.myapp.myapp.entity.Customer;
import com.myapp.myapp.entity.MyUser;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api")
public class UserRestController {

	private UserDAO uDAO;

	@Autowired
	private CustomerDAO cDAO;

	@Autowired
	public UserRestController(UserDAO uDAO) {
		this.uDAO = uDAO;
	}

	@GetMapping("/users")
	public List<MyUser> findAll() {
		return uDAO.findAll();
	}

	@PostMapping("/createUser")
	public void createUser(@RequestBody JSONObject jsonObject) {

		Customer customer = new Customer(jsonObject.getAsString("firstName"), jsonObject.getAsString("lastName"),
				jsonObject.getAsString("email"), jsonObject.getAsString("phoneNumber"), null, null, null);

		MyUser myUser = new MyUser(jsonObject.getAsString("username"), jsonObject.getAsString("password"),
				jsonObject.getAsString("role"));

		Customer createdCustomer = cDAO.save(customer);

		myUser.setCustomer(createdCustomer);

		uDAO.save(myUser);

	}

	@PutMapping("/updateUser")
	public MyUser updateUser(@RequestBody MyUser theUser) {
		uDAO.save(theUser);
		return theUser;
	}

	@DeleteMapping("/deleteUser/{theUser}")
	public String deleteUser(@PathVariable String theUser) {
		MyUser tempUser = uDAO.getUserById(theUser);

		if (tempUser == null) {
			throw new RuntimeException("User is not found: " + tempUser);
		}

		uDAO.deleteById(theUser);
		return "Deleted user id: " + theUser;
	}

}
