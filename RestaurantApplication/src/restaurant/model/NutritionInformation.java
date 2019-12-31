package restaurant.model;

public class NutritionInformation {
	protected Recipes recipe;
	protected float calories;
	protected float totalFat;
	protected float sugar;
	protected float sodium;
	protected float protein;
	protected float saturatedFat;
	protected float carbohydrates;
	public NutritionInformation(Recipes recipe, float calories, float totalFat, float sugar, float sodium, float protein,
			float saturatedFat, float carbohydrates) {
	
		this.recipe = recipe;
		this.calories = calories;
		this.totalFat = totalFat;
		this.sugar = sugar;
		this.sodium = sodium;
		this.protein = protein;
		this.saturatedFat = saturatedFat;
		this.carbohydrates = carbohydrates;
	}
	
	//constructor without recipeId;
	public NutritionInformation(float calories, float totalFat, float sugar, float sodium, float protein,
			float saturatedFat, float carbohydrates) {
	
	
		this.calories = calories;
		this.totalFat = totalFat;
		this.sugar = sugar;
		this.sodium = sodium;
		this.protein = protein;
		this.saturatedFat = saturatedFat;
		this.carbohydrates = carbohydrates;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(float totalFat) {
		this.totalFat = totalFat;
	}

	public float getSugar() {
		return sugar;
	}

	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	public float getSodium() {
		return sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(float saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public float getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	
	
	
	

}
