package de.stl.saar.internetentw1.model;

public class Dish {
	private int dishId;
	private String dishName;
	private double price;
	private Category category;
	private String imageName;
	
	public Dish(String dishName, double price, Category category, String imageName) {
		super();
		this.dishName = dishName;
		this.price = price;
		this.category = category;
		this.imageName = imageName;
	}
	
	public String getDishName() {
		return dishName;
	}
	
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public int getDishId() {
		return dishId;
	}
	
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
}
