package org.studyeasy.showroom.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.studyeasy.showroom.model.Brand;


public class BrandsDAO {
	
	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Brand.class)
			.buildSessionFactory();
	public BrandsDAO() {
			
	}
	
	public List<Brand> getBrands() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Brand> list = session.createQuery("from brands").getResultList();
		return list;
	}

	public void addBrand(Brand brand) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(brand);
		session.getTransaction().commit();
	}

	public void updateBrand(Brand updatedBrand) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		int id = updatedBrand.getBrandId();
		Brand brand = session.get(Brand.class, id);
		brand.setBrandName(updatedBrand.getBrandName());;
		session.getTransaction().commit();
	}

	public void deleteBrand(int brandId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Brand brand = session.get(Brand.class, brandId);
		session.delete(brand);
		session.getTransaction().commit();
		
	}

	public Brand getBrandbyId(int brandId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Brand obj = session.get(Brand.class, brandId);
		return obj;
	}

}
