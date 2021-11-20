package com.selenium.firefoxdriver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

class seleniumSigninTesting {
	public static WebDriver driver = null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver" , "/Users/deanh/eclipse-works-cs564/HOMII/driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// open the web app
		driver.navigate().to("http://localhost:8081/HOMII/front-end/mem/MemLogin.jsp");
		driver.manage().window().maximize();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test
	void test_Login(){
		driver.findElement(By.id("user")).sendKeys("mem1@wisc.edu");
		driver.findElement(By.id("pass")).sendKeys("1111");
		
		Actions action = new Actions(driver);
		
		driver.findElement(By.className("group")).click();
		// it should be return to the main page once it signs in successfully
		String at = driver.getTitle();
		String et = "MemLogin";
		
		assertEquals(et, at);
			
	}

}
