package com.tshirtmart.trilo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product_variant")
public class ProductVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String colorName;

	private String colorHex;
	
	private double price;

	private int quantity;

	private String size;

	private String imageUrl;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private Product product;


	public ProductVariant() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProductVariant(long id, String colorName, String colorHex, double price, int quantity, String size,
			String imageUrl, Product product) {
		super();
		this.id = id;
		this.colorName = colorName;
		this.colorHex = colorHex;
		this.price = price;
		this.quantity = quantity;
		this.size = size;
		this.imageUrl = imageUrl;
		this.product = product;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getColorName() {
		return colorName;
	}


	public void setColorName(String colorName) {
		this.colorName = colorName;
	}


	public String getColorHex() {
		return colorHex;
	}


	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "ProductVariant [id=" + id + ", colorName=" + colorName + ", colorHex=" + colorHex + ", price=" + price
				+ ", quantity=" + quantity + ", size=" + size + ", imageUrl=" + imageUrl + ", product=" + product + "]";
	}
	
	

}
