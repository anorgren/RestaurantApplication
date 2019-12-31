package restaurant.model;

/**
 * This class represents the Recipe object.
 */
public class Recipes {

	private int recipeId;
	private String recipeName;
	private String description;
	private int timeToMake;
	private int totalIngredients;
	
	/**
	 * This constructor takes in all attributes as its parameters.
	 * 
	 * @param recipeId the recipe ID
	 * @param recipeName the recipe name
	 * @param description the description of the recipe
	 * @param timeToMake the time it takes to make the recipe in minutes
	 * @param totalIngredients the number of ingredients required to make the recipe
	 */
	public Recipes(int recipeId, String recipeName, String description, int timeToMake, int totalIngredients) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.description = description;
		this.timeToMake = timeToMake;
		this.totalIngredients = totalIngredients;
	}


	public int getRecipeId() {
		return recipeId;
	}


	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getTimeToMake() {
		return timeToMake;
	}


	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}


	public int getTotalIngredients() {
		return totalIngredients;
	}


	public void setTotalIngredients(int totalIngredients) {
		this.totalIngredients = totalIngredients;
	}
	
	
	
}
