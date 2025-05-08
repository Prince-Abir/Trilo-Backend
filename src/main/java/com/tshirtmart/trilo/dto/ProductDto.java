package com.tshirtmart.trilo.dto;

import java.util.List;

public class ProductDto {

	private long productId;

	private String productName;

	private String productDescription;

	private String productCategory;

	private double productRating;

	private boolean isOnSale;

	private List<ProductVariantDto> productVariants;
	
	

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ProductDto(long productId, String productName, String productDescription, String productCategory,
			double productRating, boolean isOnSale, List<ProductVariantDto> productVariants) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productCategory = productCategory;
		this.productRating = productRating;
		this.isOnSale = isOnSale;
		this.productVariants = productVariants;
	}



	public long getProductId() {
		return productId;
	}



	public void setProductId(long productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getProductDescription() {
		return productDescription;
	}



	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}



	public String getProductCategory() {
		return productCategory;
	}



	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}



	public double getProductRating() {
		return productRating;
	}



	public void setProductRating(double productRating) {
		this.productRating = productRating;
	}



	public boolean getOnSale() {
		return isOnSale;
	}



	public void setOnSale(boolean isOnSale) {
		this.isOnSale = isOnSale;
	}



	public List<ProductVariantDto> getProductVariants() {
		return productVariants;
	}



	public void setProductVariants(List<ProductVariantDto> productVariants) {
		this.productVariants = productVariants;
	}



	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productCategory=" + productCategory + ", productRating=" + productRating
				+ ", isOnSale=" + isOnSale + ", productVariants=" + productVariants + "]";
	}
	
	
	
	

}
