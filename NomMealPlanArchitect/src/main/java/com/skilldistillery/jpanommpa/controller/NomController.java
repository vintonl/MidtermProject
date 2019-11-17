package com.skilldistillery.jpanommpa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpanommpa.dao.AuthenticationDAO;
import com.skilldistillery.jpanommpa.dao.GroceryListDAO;
import com.skilldistillery.jpanommpa.dao.IngredientDAO;
import com.skilldistillery.jpanommpa.dao.MealPlanDAO;
import com.skilldistillery.jpanommpa.dao.RecipeDAO;
import com.skilldistillery.jpanommpa.entities.Recipe;
import com.skilldistillery.jpanommpa.entities.User;

@Controller
public class NomController {

	@Autowired
	private AuthenticationDAO userDao;
	@Autowired
	private GroceryListDAO groceryDao;
	@Autowired
	private IngredientDAO ingredientDao;
	@Autowired
	private MealPlanDAO mealPlanDao;
	@Autowired
	private RecipeDAO recipeDao;

	@RequestMapping(path = { "/", "index.do" })
	public String index(Model model) {

		return "index";
	}

	@RequestMapping(path = "login.do")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		User u = new User();
		mv.addObject("user", u);
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(path = "loginAction.do", method = RequestMethod.GET)
	public ModelAndView loginDo(@RequestParam("email") String email, @RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView();

		User u = userDao.lookUp(email, password);
		System.out.println("in controller: " + u);
//		if (userDao.isValidUser(u)) {
//			mv.addObject("user", u);
//			mv.setViewName("userProfile");
//			System.out.println("Valid user: " + u);
//			return mv;
//		}
		if (u.getFirstName().equalsIgnoreCase("InvalidUser")) {
			mv.setViewName("login");
			return mv;
		} else {
			mv.addObject("user", u);
			mv.setViewName("userProfile");
			System.out.println("Valid user: " + u);
			return mv;

		}

	}

	@RequestMapping(path = "register.do", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		User u = new User();
		mv.addObject("user", u);
		mv.setViewName("register");
		return mv;
	}

	@RequestMapping(path = "register.do", method = RequestMethod.POST)
	public String createUser(@Valid User user, Errors errors) {
		userDao.addUserToMap();

		if (errors.hasErrors()) {
			return "userProfile";
		}

		if (!userDao.isEmailUnique(user.getEmail())) {
			errors.rejectValue("email", "error.email", "Email already in use");
			return "register";
		}
		if (!userDao.isUserNameUnique(user.getUsername())) {
			errors.rejectValue("username", "error.username", "Username already in use");
			return "register";

		}
		System.out.println(user);

		userDao.create(user);

		return "userProfile";

	}

	@RequestMapping(path = "recipeSearch.do", method = RequestMethod.GET)
	public ModelAndView searchRecipe() {
		ModelAndView mv = new ModelAndView();
		User u = new User();
		mv.addObject("user", u);

		mv.setViewName("recipeSearch");
		return mv;
	}

	@RequestMapping(path = "searchRecipe.do", method = RequestMethod.GET)
	public ModelAndView searchRecipeResults(String key) {
		ModelAndView mv = new ModelAndView();
		User u = new User();
//		List<Recipe> recipeList = recipeDao.selectRecipeByKeyword(key);
//		mv.addObject("recipe", recipeList);
//		mv.addObject("deleteStatus", false);
//		mv.addObject("updateStatus", false);
//		mv.addObject("user", u);
		mv.setViewName("recipeSearchResult");
		return mv;
	}

	@RequestMapping(path = "createRecipe.do", method = RequestMethod.GET)
	public ModelAndView createRecipe() {
		ModelAndView mv = new ModelAndView();
		User u = new User();
		mv.addObject("user", u);

		mv.setViewName("createRecipe");
		return mv;
	}

	@RequestMapping(path = "groceryList.do")
	public ModelAndView viewGroceryList(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User u = new User();
		mv.addObject("user", u);
//		TODO needs grocery list to function

		mv.setViewName("groceryList");
		return mv;
	}
}
