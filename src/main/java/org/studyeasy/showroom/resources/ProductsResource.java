package org.studyeasy.showroom.resources;

import java.util.List;

import org.studyeasy.showroom.model.Product;
import org.studyeasy.showroom.services.ProductService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


public class ProductsResource {
	
	ProductService productsService = new ProductService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsByBrands(@PathParam("brandId") int brandId, 
			@QueryParam("category") String category, @QueryParam("start") int start, @QueryParam("end") int end) {
		
		List<Product> ProductList;
		
		if (category != null) {
			ProductList = productsService.getProductsByBrandAndCategory(brandId, category);
			
		} else {
			ProductList = productsService.getProductsByBrand(brandId);
			
		}
		
		if (end > 0) {
			ProductList = ProductList.subList(start, end);
		}
		return ProductList;
	}
}
