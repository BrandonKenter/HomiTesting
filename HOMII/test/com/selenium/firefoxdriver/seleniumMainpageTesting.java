package com.selenium.firefoxdriver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

class seleniumMainpageTesting {
	public static WebDriver driver = null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver" , "/Users/deanh/eclipse-works-cs564/HOMII/driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// open the web app
		driver.navigate().to("http://localhost:8081/HOMII/front-end/index.jsp");
		driver.manage().window().maximize();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test
	void test_mainpage_title() {
		String title = driver.getTitle();
		assertEquals("HOMI", title);
	}
	
	@Test
	void test_mainpage_MemCenter() {
		String MemCenter = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li:nth-child(2) > a")).getText();
		assertEquals("Member Center", MemCenter);
		
	}
	
	@Test
	void test_mainpage_MyRent() {
		String rent = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li:nth-child(4)")).getText();
		assertEquals("My rent", rent);
		
	}
	
	@Test
	void test_mainpage_MyCom() {
		String commu = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li:nth-child(6)")).getText();
		assertEquals("My community", commu);
		
	}
	
	@Test
	void test_mainpage_OurSer() {
		String ourSer = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li.nav-item.dropdown")).getText();
		assertEquals("Our Services", ourSer);
		
	}
	@Test 
	void test_mainpage_ser1(){
		WebElement service = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li.nav-item.dropdown"));
		Actions action = new Actions(driver);
		action.moveToElement(service);
		action.click().perform();
		
		String ser1 = driver.findElement(By.linkText("Service1")).getText();
		assertEquals("Service1", ser1);
	}

//	@Test 
//	void test_mainpage_ser2(){
//		WebElement service = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li.nav-item.dropdown"));
//		Actions action = new Actions(driver);
//		action.moveToElement(service);
//		action.click().perform();
//		
//		String ser2 = driver.findElement(By.linkText("Service2")).getText();
//		assertEquals("Service3", ser2);
//	}
//	
//	@Test 
//	void test_mainpage_ser3(){
//		WebElement service = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li.nav-item.dropdown"));
//		Actions action = new Actions(driver);
//		action.moveToElement(service);
//		action.click().perform();
//		
//		String ser3 = driver.findElement(By.linkText("Service3")).getText();
//		assertEquals("Service3", ser3);
//	}
	
	
	@Test
	void test_mainpage_CreateCase() {
		String cases = driver.findElement(By.cssSelector("#mob-navbar > a:nth-child(2)")).getText();
		assertEquals("Create case", cases);
	}
	
	@Test
	void test_mainpage_Contac() {
		String contact = driver.findElement(By.cssSelector("#mob-navbar > ul.navbar-nav.mb-2.mb-lg-0.mx-auto > li:nth-child(9)")).getText();
		assertEquals("Contact Us", contact);
	}

}
