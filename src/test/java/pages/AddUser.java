package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUser {
	
	@FindBy(name = "username")
	WebElement usernameField;

	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(css = "body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(1) > div > center > table > tbody > tr:nth-child(3) > td:nth-child(2) > p > input[type=button]")
	WebElement saveUser;
	
	
	public void createUser(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		saveUser.click();
	}
	
	

}
