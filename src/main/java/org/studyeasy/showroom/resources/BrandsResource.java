package org.studyeasy.showroom.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.studyeasy.showroom.model.Brand;
import org.studyeasy.showroom.model.Link;
import org.studyeasy.showroom.services.BrandsService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/showroom/brands")
public class BrandsResource {
	
	BrandsService service = new BrandsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Brand> getBrands() {
		List<Brand> list = service.getBrands();
		return list;
	}
	
	@GET
	@Path("/{brandId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Brand getBrand(@PathParam("brandId") int brandId, @Context UriInfo uri) {
		
		Link self = new Link(uri.getAbsolutePath().toString(), "self");
//		Link products = new Link(uri.getAbsolutePathBuilder().path("products").build().toString(), "products");
		String productsUri = uri.getBaseUriBuilder()
				.path(ProductsResource.class)
				.path(ProductsResource.class, "getProductsByBrands")
				.resolveTemplate("brandId", brandId).toString();
		
		Link products = new Link(productsUri, "products");
		List<Link> links = new ArrayList<>();
		links.add(self);
		links.add(products);
		Brand brand = service.getBrandById(brandId);
		brand.setLinks(links);
		return brand;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setBrand(Brand brand, @Context UriInfo uri) {
		service.addBrand(brand);
		URI location = uri.getAbsolutePathBuilder().path(Integer.toString(brand.getBrandId())).build();
		return Response.created(location).entity(brand).build();
				//.status(Status.CREATED).entity(brand).build();
	}
	
	@PUT
	@Path("/{brandId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void putBrand(@PathParam("brandId") int brandId, Brand updatedBrand) {
		updatedBrand.setBrandId(brandId);
		service.updateBrand(updatedBrand);
	}
	
	@DELETE
	@Path("/{brandId}")
	public void deleteBrand(@PathParam("brandId") int brandId) {
		service.deleteBrand(brandId); 
	}
	
	@Path("/{brandId}/products")
	public ProductsResource productDelegation() {
		return new ProductsResource();
	}
	
}
