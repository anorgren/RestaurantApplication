package restaurant.model;


/**
 * This class represents the Users object.
 *
 */
public class Users {
	
	private int userId;
	private String userName;
	private String password;
	private String userCity;
	private int userZip;
	private int caloricGoal;
	private int fatGoal;
	private int carbGoal;
	private int proteinGoal;
	
	/**
	 * This constructor takes in all attributes in the parameter.
	 * 
	 * @param userId the user's ID
	 * @param userName the user's user name
	 * @param password the user's password
	 * @param city the user's city
	 * @param userZip the user's zip
	 * @param caloricGoal the user's caloric goal
	 * @param fatGoal the user's fat goal
	 * @param carbGoal the user's carb goal
	 * @param proteinGoal the user's protein goal
	 */
	public Users(int userId, String userName, String password, String userCity, int userZip, int caloricGoal, int fatGoal, int carbGoal, int proteinGoal) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userCity = userCity;
		this.userZip = userZip;
		this.caloricGoal = caloricGoal;
		this.fatGoal = fatGoal;
		this.carbGoal = carbGoal;
		this.proteinGoal = proteinGoal;
	}
	
	/**
	 * This constructor does not include the user Id.
	 * 
	 * @param userName the user's user name
	 * @param password the user's password
	 * @param city the user's city
	 * @param caloricGoal the user's caloric goal
	 * @param fatGoal the user's fat goal
	 * @param carbGoal the user's carb goal
	 * @param proteinGoal the user's protein goal
	 */
	public Users(String userName, String password, String userCity, int userZip, int caloricGoal, int fatGoal, int carbGoal, int proteinGoal) {
	
		this.userName = userName;
		this.password = password;
		this.userCity = userCity;
		this.userZip = userZip;
		this.caloricGoal = caloricGoal;
		this.fatGoal = fatGoal;
		this.carbGoal = carbGoal;
		this.proteinGoal = proteinGoal;
	}
	
	public Users(String userName) {
		this.userName = userName;
	}

	
	
	/**
	 * This constructor only takes in the user ID in its parameter.
	 * 
	 * @param userId the user's userId
	 */
	public Users(int userId) {
		this.userId = userId;
	}



	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getCity() {
		return userCity;
	}


	public void setUserCity(String city) {
		this.userCity = city;
	}
	
	public int getUserZip() {
		return userZip;
	}


	public void setUserZip(int userZip) {
		this.userZip = userZip;
	}

	public int getCaloricGoal() {
		return caloricGoal;
	}


	public void setCaloricGoal(int caloricGoal) {
		this.caloricGoal = caloricGoal;
	}
	

	public int getFatGoal() {
		return fatGoal;
	}


	public void setFatGoal(int fatGoal) {
		this.fatGoal = fatGoal;
	}
	

	public int getCarbGoal() {
		return carbGoal;
	}


	public void setCarbGoal(int carbGoal) {
		this.carbGoal = carbGoal;
	}
	

	public int getProteinGoal() {
		return proteinGoal;
	}


	public void setProteinGoal(int proteinGoal) {
		this.proteinGoal = proteinGoal;
	}
	
	

}
