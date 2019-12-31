
package restaurant.model;

/**
 * This class represents the steps of how to make a recipe.
 */
public class RecipeSteps {

	
	private int stepId;
	private Recipes recipe;
	private int stepNumber;
	private String instruction;
	
	/**
	 * This is a constructor that takes in all attributes in the parameter.
	 * 
	 * @param stepId the unique step ID
	 * @param recipe the recipe
	 * @param stepNumber the step number
	 * @param instruction the instruction of how to make the recipe
	 */
	public RecipeSteps(int stepId, Recipes recipe, int stepNumber, String instruction) {
		this.stepId = stepId;
		this.recipe = recipe;
		this.stepNumber = stepNumber;
		this.instruction = instruction;
	}
	
	/**
	 *  This is a constructor that only takes in the stepId.
	 */
	public RecipeSteps(int stepId) {
		this.stepId = stepId;
	}
	
	/**
	 *  This is a constructor that takes in all attributes, except for the stepId, in its parameter.
	 */
	public RecipeSteps(Recipes recipe, int stepNumber, String instruction) {
		this.recipe = recipe;
		this.stepNumber = stepNumber;
		this.instruction = instruction;
	}
	
	public int getStepId() {
		return stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
	}

	public Recipes getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

}
