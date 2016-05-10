package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class SignInUsernamePage {
	private WebDriver driver;
	
	@FindBy(id="Email")
	WebElement email;
	
	@FindBy(id="next")
	WebElement nextButton;
	
	@FindBy(css=".banner h2.hidden-small")
	WebElement heading;
	
	public SignInUsernamePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setEmail(String str_email) {
		waitUntil(email);
		email.clear();
		email.sendKeys(str_email);
	}
	
	public void clickNext() {
		waitUntil(nextButton);
		nextButton.click();
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
