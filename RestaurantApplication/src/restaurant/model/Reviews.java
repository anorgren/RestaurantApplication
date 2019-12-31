package restaurant.model;

/**
 * This class represents the Reviews of recipes from users.
 */
public class Reviews {
	
	private int reviewId;
	private Recipes recipe;
	private Users user;
	private String review;
	private int rating;
	
	/**
	 * This is a constructor that takes in all attributes as parameters.
	 * @param reviewID the review ID
	 * @param recipe the recipe
	 * @param user the user who writes the review
	 * @param review the review of recipe in text format
	 * @param rating the rating of the recipe between 1 to 5
	 */
	public Reviews(int reviewId, Recipes recipe, Users user, String review, int rating) {
		this.reviewId = reviewId;
		this.recipe = recipe;
		this.user = user;
		this.review = review;
		this.rating = rating;
	}
	
	/**
	 * This is a constructor that takes in all attributes as parameters.
	 * @param reviewID the review ID
	 */
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}
	
	/**
	 * This is a constructor that takes in all attributes as parameters.
	 * @param reviewID the review ID
	 * @param recipe the recipe
	 * @param user the user who writes the review
	 * @param review the review of recipe in text format
	 * @param rating the rating of the recipe between 1 to 5
	 */
	public Reviews(Recipes recipe, Users user, String review, int rating) {
		this.recipe = recipe;
		this.user = user;
		this.review = review;
		this.rating = rating;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewID) {
		this.reviewId = reviewID;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
