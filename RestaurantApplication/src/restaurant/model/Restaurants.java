package restaurant.model;

public class Restaurants {
 protected int restaurantID;
 protected String restaurantName;
 protected float latitude; 
 protected float longitude;
 protected String address;
 protected String city;
 protected String state;
 protected int zip;
 protected String price;
 
 
public Restaurants(int restaurantID, String restaurantName, float latitude, float longitude, String address,
		String city, String state, int zip, String price) {
	
	this.restaurantID = restaurantID;
	this.restaurantName = restaurantName;
	this.latitude = latitude;
	this.longitude = longitude;
	this.address = address;
	this.city = city;
	this.state = state;
	this.zip = zip;
	this.price = price;
}
 
//constructor without restaurantId
public Restaurants(String restaurantName, float latitude, float longitude, String address,
		String city, String state, int zip, String price) {
	
	
	this.restaurantName = restaurantName;
	this.latitude = latitude;
	this.longitude = longitude;
	this.address = address;
	this.city = city;
	this.state = state;
	this.zip = zip;
	this.price = price;
}

public int getRestaurantID() {
	return restaurantID;
}

public void setRestaurantID(int restaurantID) {
	this.restaurantID = restaurantID;
}

public String getRestaurantName() {
	return restaurantName;
}

public void setRestaurantName(String restaurantName) {
	this.restaurantName = restaurantName;
}

public float getLatitude() {
	return latitude;
}

public void setLatitude(float latitude) {
	this.latitude = latitude;
}

public float getLongitude() {
	return longitude;
}

public void setLongitude(float longitude) {
	this.longitude = longitude;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public int getZip() {
	return zip;
}

public void setState(int zip) {
	this.zip = zip;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
} 




 
}