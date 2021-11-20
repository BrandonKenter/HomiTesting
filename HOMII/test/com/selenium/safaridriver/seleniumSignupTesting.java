package com.selenium.safaridriver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

class seleniumSignupTesting {

	public static WebDriver driver = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.navigate().to("http://localhost:8081/HOMII/front-end/mem/MemLogin.jsp");
		driver.manage().window().maximize();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test
	void test_SignUp() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement signUp = driver.findElement(By.cssSelector("body > div.login-wrap > div > label:nth-child(4)"));
		action.moveToElement(signUp).click().perform();  
//		Thread.sleep(1000);
		// mem name
		driver.findElement(By.id("name")).sendKeys("Dean");
//		Thread.sleep(1000);
		// email
		driver.findElement(By.id("email")).sendKeys("bhuang67@gmail.com");
//		Thread.sleep(1000);
		// password
		driver.findElement(By.id("pass2")).sendKeys("1111");
//		Thread.sleep(1000);
		// phone
		driver.findElement(By.id("phone")).sendKeys("6083206666");
//		Thread.sleep(1000);
		// address
		driver.findElement(By.id("address")).sendKeys("530 W Main St");
//		Thread.sleep(1000);
		
		// pick tenant
		WebElement tenant = driver.findElement(By.id("membership"));
		action.moveToElement(tenant).click().perform();
		// pick sign up
		//driver.findElement(By.className("group")).click();
		
		String at = driver.getTitle();
		String et = "MemLogin";
		
		assertEquals(et, at);
		
	}

}
