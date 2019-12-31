package restaurant.model;

public class Ingredients {
	protected int ingredientId;
	protected String foodName;
	protected Recipes recipe;
	protected int quantity;
	public Ingredients(int ingredientId, String foodName, Recipes recipe, int quantity) {
		
		this.ingredientId = ingredientId;
		this.foodName = foodName;
		this.recipe = recipe;
		this.quantity = quantity;
	}

	//constructor without ingredientId
	public Ingredients(String foodName, Recipes recipe, int quantity) {
		this.foodName = foodName;
		this.recipe = recipe;
		this.quantity = quantity;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
