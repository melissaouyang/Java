package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import pages.HomePage;
import pages.MailBoxPage;
import pages.SignInPasswordPage;
import pages.SignInUsernamePage;

public class TestClickThisLink {

	private static  WebDriver driver;
	@BeforeClass
	public static void setUp() {
	    final FirefoxProfile firefoxProfile = new FirefoxProfile();
	    firefoxProfile.setPreference("intl.accept_languages", "en");
	    driver = new FirefoxDriver(firefoxProfile);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Before
	public void Signin() {
		HomePage homePage = new HomePage(driver);
		homePage.isPageOpened("Google");
		homePage.clickLink();
		
		SignInUsernamePage loginPage = new SignInUsernamePage(driver);
		loginPage.isPageOpened("Sign in to continue to Gmail");
		loginPage.setEmail("ouyangqinca002@gmail.com");
		loginPage.clickNext();
		
		SignInPasswordPage signInPage = new SignInPasswordPage(driver);
		signInPage.isPageOpened("Sign in to continue to Gmail");
		signInPage.setPassword("Password123#");
		signInPage.clickSignInButton();
	}
	
	@Test
	public void valid_ThisLink() {
			MailBoxPage mailPage = new MailBoxPage(driver);
			mailPage.clickMail("Forgot your Password?");
			ArrayList<String> titleList = mailPage.getTitleList();
			assertEquals(4, titleList.size());
			
			for (String title : titleList) {
				assertEquals("Wysdom | Contextual Customer Support", title);
			}
	}
	
	@AfterClass
	public static void closeBrowser() {
		driver.close();
	}


}
