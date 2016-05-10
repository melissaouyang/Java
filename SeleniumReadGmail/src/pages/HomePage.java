package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	private static String url = "https://www.google.com/"; 
	
	@FindBy (how = How.LINK_TEXT, using="Gmail")
	private WebElement gmailLink;
	
	@FindBy (how = How.TAG_NAME, using="title")
	private WebElement heading;
	
	//constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.get(url);
		PageFactory.initElements(driver, this);
	}
	
	public void clickLink() {
		gmailLink.click();
	}
	
	public boolean isPageOpened(String str_heading) {
		return heading.getText().toString().contains(str_heading);
	}
}
