package com.kgisl.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgisl.dao.ProductDAO;
import com.kgisl.exception.GlobalException;
import com.kgisl.exception.ProductNotFound;
import com.kgisl.model.Product;



/* @author  Praveena.D Ramya.B
 * @since  
 * 
 */
@Controller
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);
	@Autowired
	ProductDAO productDAO;


	
	/*
	 * To Select all Products 
	 * 
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public @ResponseBody List<Product> getAllProducts() {
		List<Product> list = productDAO.getAllProduct();
		return list;
	}
	
	
	
	/*
	 * To Select one product using id..
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProduct(@PathVariable("id") int id) throws Exception {
		String message = "Hello World!";
		int prdId = id;
		List<Product> list = productDAO.getAllProduct();
		for (int i = list.size() - 1; i >= 0; i--) {
			Product f = list.get(i);
			if (f.getProductId() == ((id))) {
				List<Product> listprd = productDAO.getProduct(id);
				return listprd;
			}
		}
		throw new ProductNotFound(id);
	}
	
	
	
	/*
	 * To add a product..
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(ModelMap m, @RequestBody Product Product) throws Exception {
		List<Product> listprd = productDAO.getAllProduct();
		for (Product f : listprd) {
			if (f.getProductName().equals(Product.getProductName()) || (f.getProductId() == Product.getProductId())) {
				m.addAttribute("msg10", "Same Name and Same Prdid");
				return "index";
			}
		}
		productDAO.create(Product);
		return "redirect:/";// will redirect to viewemp request mapping
	}
	
	
	
	
	/*
	 * To Update a product..
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public String update(ModelMap mp, @RequestBody Product product) throws Exception {
		List<Product> listprd = productDAO.getAllProduct();
		for (Product f : listprd) {
			if (f.getProductName().equals(product.getProductName())) {
				mp.addAttribute("msg100", "Same Name");
				return "index";
			}
		}
		productDAO.update(product);
		return "redirect:/";// will redirect to viewemp request mapping
	}

	
	
	
	/*
	 * To Delete a product..
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		productDAO.delete(id);
	}
	
	
	
	/*
	 * To Select a product from category
	 * 
	 */
	@RequestMapping(value = "/selectProductCakes/{catname}",method = RequestMethod.GET)
	public @ResponseBody List<Product> selectProductCake(@PathVariable("catname") String catname) {
		List<Product> listprd = productDAO.selectProduct(catname);
		return listprd;
	}
}
