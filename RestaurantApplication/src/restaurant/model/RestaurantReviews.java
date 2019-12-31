package restaurant.model;

public class RestaurantReviews {
	protected int restaurantReviewId;
	protected Restaurants restaurant;
	protected double averageReview;
	
	public RestaurantReviews(int restaurantReviewId, Restaurants restaurant, double averageReview) {

		this.restaurantReviewId = restaurantReviewId;
		this.restaurant = restaurant;
		this.averageReview = averageReview;
	}

	public RestaurantReviews(Restaurants restaurant, double averageReview) {
		this.restaurant = restaurant;
		this.averageReview = averageReview;
	}

	public int getRestaurantReviewId() {
		return restaurantReviewId;
	}

	public void setRestaurantCategoryTypeId(int restaurantReviewId) {
		this.restaurantReviewId = restaurantReviewId;
	}


	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public double getAverageReview() {
		return averageReview;
	}

	public void setAverageReview(double averageReview) {
		this.averageReview = averageReview;
	}
}
