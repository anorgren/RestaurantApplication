package restaurant.model;

public class RestaurantCategoryTypes {
	protected Integer restaurantCategoryTypeId;
	protected Restaurants restaurant;
	protected Categories category;
	
	public RestaurantCategoryTypes(Integer restaurantCategoryTypeId, Restaurants restaurant, Categories category) {

		this.restaurantCategoryTypeId = restaurantCategoryTypeId;
		this.restaurant = restaurant;
		this.category = category;
	}

	public RestaurantCategoryTypes(Restaurants restaurant, Categories category) {
		this.restaurant = restaurant;
		this.category = category;
	}

	public Integer getRestaurantCategoryTypeId() {
		return restaurantCategoryTypeId;
	}

	public void setRestaurantCategoryTypeId(Integer restaurantCategoryTypeId) {
		this.restaurantCategoryTypeId = restaurantCategoryTypeId;
	}


	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}
}
