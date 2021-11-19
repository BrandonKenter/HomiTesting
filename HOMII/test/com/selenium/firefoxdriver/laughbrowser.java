package com.selenium.firefoxdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class laughbrowser {
	
	public static WebDriver driver = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver" , "/Users/deanh/eclipse-works-cs564/HOMII/driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// open the web app
		driver.navigate().to("http://localhost:8081/HOMII/front-end/mem/MemLogin.jsp");
		driver.manage().window().maximize();
		


	}
//	public static void main(String[] args) {
//        // TODO Auto-generated method stub
//
//        //WebDriver driver =new FirefoxDriver();
//
//        System.setProperty("webdriver.gecko.driver", "/Users/deanh/eclipse-works-cs564/HOMII/driver/geckodriver");
//        WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
//        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.get("https://www.google.com/");
//        driver.findElement(By.name("q")).sendKeys("Browserstack Guide"); //name locator for text box
//        WebElement searchbutton = driver.findElement(By.name("btnK"));//name locator for google search
//        searchbutton.click();
//        driver.quit();
//        
//    }

}
