package org.studyeasy.showroom.services;

import java.util.List;

import org.studyeasy.showroom.dao.ProductsDAO;
import org.studyeasy.showroom.model.Product;

public class ProductService {
	ProductsDAO dao = new ProductsDAO();

	public List<Product> getProductsByBrand(int brandId) {
		List<Product> list = dao.getProductsByBrand(brandId);
		return list;
	}

	public List<Product> getProductsByBrandAndCategory(int brandId, String category) {
		List<Product> productList = dao.getProductsByBrandAndCategory(brandId, category);
		return productList;
	}

}
