package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class SignInPasswordPage {
	private WebDriver driver;
	
	@FindBy(id="Passwd")
	WebElement password;
	
	@FindBy(id="signIn")
	WebElement signInButton;
	
	@FindBy(css=".banner h2")
	WebElement heading;
	
	public SignInPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setPassword(String str_password) {
		waitUntil(password);
		password.clear();
		password.sendKeys(str_password);
	}
	
	public void clickSignInButton() {
		waitUntil(signInButton);
		signInButton.click();
	}
	
	public boolean isPageOpened(String str_heading) {
		return heading.getText().toString().contains(str_heading);
	}
	
	public WebElement waitUntil(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return element.isDisplayed();
			}
		});
		return element;		
	}

}
