package restaurant.model;

public class CuisineType {
	protected Integer cuisineTypeId;
	protected String typeDescription;
	
	public CuisineType(Integer cuisineTypeId, String typeDescription) {
		this.cuisineTypeId = cuisineTypeId;
		this.typeDescription = typeDescription;
	}

	public CuisineType(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public Integer getCuisineTypeId() {
		return cuisineTypeId;
	}

	public void setCuisineTypeId(Integer cuisineTypeId) {
		this.cuisineTypeId = cuisineTypeId;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	
}
