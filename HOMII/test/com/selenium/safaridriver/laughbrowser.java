package com.selenium.safaridriver;
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.safari.SafariDriver; 
public class laughbrowser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Instantiate a SafariDriver class. 
		WebDriver driver = new SafariDriver(); 
		// Launch Website 
		driver.navigate().to("http://localhost:8081/HOMII/front-end/index.jsp"); 
		driver.manage().window().maximize();



	}

}
