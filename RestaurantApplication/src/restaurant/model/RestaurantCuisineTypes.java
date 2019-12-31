package restaurant.model;

public class RestaurantCuisineTypes {
	protected int restaurantTypeId;
	protected Restaurants restaurant;
	protected CuisineType cuisineType;
	
	public RestaurantCuisineTypes(int restaurantTypeId, Restaurants restaurant, CuisineType cuisineType) {
		super();
		this.restaurantTypeId = restaurantTypeId;
		this.restaurant = restaurant;
		this.cuisineType = cuisineType;
	}

	public RestaurantCuisineTypes(Restaurants restaurant, CuisineType cuisineType) {
		super();
		this.restaurant = restaurant;
		this.cuisineType = cuisineType;
	}

	public int getRestaurantTypeId() {
		return restaurantTypeId;
	}

	public void setRestaurantTypeId(int restaurantTypeId) {
		this.restaurantTypeId = restaurantTypeId;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public CuisineType getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(CuisineType cuisineType) {
		this.cuisineType = cuisineType;
	}
}
