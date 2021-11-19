package com.selenium.safaridriver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

class seleniumSigninTesting {

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
	void test_Login() throws InterruptedException{
		driver.findElement(By.id("user")).sendKeys("mem1@wisc.edu");
//		Thread.sleep(2000);
		driver.findElement(By.id("pass")).sendKeys("1111");
//		Thread.sleep(2000);
		
		Actions action = new Actions(driver);
		
		//driver.findElement(By.className("group")).click();
		// it should be return to the main page once it signs in successfully
		String at = driver.getTitle();
		String et = "MemLogin";
		
		assertEquals(et, at);
			
	}

}
