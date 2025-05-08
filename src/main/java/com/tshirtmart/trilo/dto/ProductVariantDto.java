package com.tshirtmart.trilo.dto;

public class ProductVariantDto {

		private long id;

		private String colorName;

		private String colorHex;
		
		private double price;

		private int quantity;

		private String size;

		private String imageUrl;

		public ProductVariantDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProductVariantDto(long id, String colorName, String colorHex, double price, int quantity, String size,
				String imageUrl) {
			super();
			this.id = id;
			this.colorName = colorName;
			this.colorHex = colorHex;
			this.price = price;
			this.quantity = quantity;
			this.size = size;
			this.imageUrl = imageUrl;
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

		@Override
		public String toString() {
			return "ProductVariantDto [id=" + id + ", colorName=" + colorName + ", colorHex=" + colorHex + ", price="
					+ price + ", quantity=" + quantity + ", size=" + size + ", imageUrl=" + imageUrl + "]";
		}
		
		


	}

