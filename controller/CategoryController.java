package com.kgisl.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgisl.dao.CategoryDAO;
import com.kgisl.exception.CategoryNotFound;
import com.kgisl.model.Category;






/* @author  Praveena.D Ramya.B
 * @since  
 * 
 */

@Controller
@RequestMapping("/category")


@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryNotFound.class);

	@Autowired
	CategoryDAO categoryDAO;


	/*
	 * Method : getallcategory
	 * Description: Returns all category
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategories(){
		return categoryDAO.getAllCategory();
	}

	
	
	
	
	/*
	 * get one category using id.
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Category> get(@PathVariable("id") int id) throws Exception {
		List<Category> listC = categoryDAO.getAllCategory();
		for (Category f : listC) {
			if (f.getCatId() == ((id))) {
				List<Category> list = categoryDAO.getCategory(id);
				return list;
			}
		}
		throw new CategoryNotFound(id);
	}


	
	
	
	/*
	 * CategoryNotFound
	 */
	@ExceptionHandler(CategoryNotFound.class)
	public ModelAndView handleCategoryNotFoundException(HttpServletRequest request, Exception ex) {
		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName("error");
		return modelAndView;
	}

	
	
	
	/*
	 * delete category by using id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		categoryDAO.delete(id);
	}

	/*
	 * save new category..
	 */
	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public String save(ModelMap m, @RequestBody Category category) throws Exception {
		List<Category> listC = categoryDAO.getAllCategory();
		for (Category category2 : listC) {
			if (category2.getCatName().equals(category.getCatName())) {
				m.addAttribute("msg10", "Same Name");
				return "index";
			}
		}
		categoryDAO.save(category);
		return "redirect:/";// will redirect to viewemp request mapping
	}

	/*
	 * updating category.
	 */

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public String update(ModelMap mp, @RequestBody Category category) throws Exception {
		List<Category> listContact = categoryDAO.getAllCategory();
		for (Category category2 : listContact) {
			if (category2.getCatName().equals(category.getCatName())) {

				mp.addAttribute("msg100", "Same Name");
				return "index";
			}
		}

		categoryDAO.update(category);
		return "redirect:/";// will redirect to viewemp request mapping
	}

}
