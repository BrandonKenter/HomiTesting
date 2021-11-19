package com.selenium.chromedriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class laughbrowser {
	
	public static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver" , "/Users/deanh/eclipse-works-cs564/HOMII/driver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// open the web app
		driver.navigate().to("http://localhost:8081/HOMII/front-end/mem/MemLogin.jsp");
		driver.manage().window().maximize();
		
//		// click my service
//		WebElement service = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li.nav-item.dropdown"));
//		Actions action = new Actions(driver);
//		action.moveToElement(service);
//		action.click().perform();
//		Thread.sleep(1000);
//		// click my service 1
//		WebElement service1 = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li.nav-item.dropdown > ul > li:nth-child(1) > a"));
//		action.moveToElement(service1);
//		action.click();
//		action.perform();
//		Thread.sleep(1000);
//		// click somewhere,and forward to log in page
//		action.click().perform();
				
//	
//		Actions action = new Actions(driver);
//		WebElement signUp = driver.findElement(By.id("tab-2"));
//		action.moveToElement(signUp).click().perform();  
//		Thread.sleep(1000);
//		// mem name
//		driver.findElement(By.id("name")).sendKeys("Dean");
//		Thread.sleep(1000);
//		// email
//		driver.findElement(By.id("email")).sendKeys("bhuang67@gmail.com");
//		Thread.sleep(1000);
//		// password
//		driver.findElement(By.id("pass2")).sendKeys("1111");
//		Thread.sleep(1000);
//		// phone
//		driver.findElement(By.id("phone")).sendKeys("6083206666");
//		Thread.sleep(1000);
//		// address
//		driver.findElement(By.id("address")).sendKeys("530 W Main St");
//		Thread.sleep(1000);
//		
//		// pick tenant
//		//WebElement tenant = driver.findElement(By.cssSelector("body > div.login-wrap > div > div > form:nth-child(2) > div > div:nth-child(6) > label"));
//		WebElement tenant = driver.findElement(By.id("membership"));
//		action.moveToElement(tenant).click();
//		// pick sign up
//		driver.findElement(By.className("group")).click();
		
		
		driver.close();

	}

}
