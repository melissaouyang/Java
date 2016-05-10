package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class MailBoxPage {
	private WebDriver driver;
	private ArrayList<String> titleList;
	
	@FindBy(css=".UI table tr")
	List<WebElement> table;
	
	@FindBy(css="div.gs")
	//List<WebElement> mailTable;
	WebElement mailBody;
	
	@FindBy(css=".TN.GLujEb")
	WebElement inBoxButton;
	
	public MailBoxPage(WebDriver driver) {
		this.driver = driver;
		setTitleList(new ArrayList<String>());
		PageFactory.initElements(driver, this);
	}
	
	
	public List<WebElement> findMails(String subject, List<WebElement> table) {

		//int index = 0;
		List<WebElement> elem_list = new ArrayList<WebElement>();
		for (WebElement ele : table) {
			//index++;
			if (ele.getText().contains(subject)) {
				String targetId = ele.getAttribute("id");  //
				waitUntil(ele);
				WebElement targetElem = ele.findElement(By.id(targetId));
				waitUntil(targetElem);
				elem_list.add(targetElem);
			}
		}
		return elem_list;	
	}
	

	public void switchWindow() {
		String base = driver.getWindowHandle();
		Set <String> set = driver.getWindowHandles();
		set.remove(base);
		assert set.size()==1;
		
		driver.switchTo().window(set.toArray(new String[0])[0]);
		titleList.add(driver.getTitle());     // Collect titles for assertion
		
		driver.close();	  //Close ThisLink window
		driver.switchTo().window(base);  //Switch back to Gmail window
	}
	

	public void readMail(int index, String keyWord) {
		waitUntil(mailBody);
		
		System.out.println("Click this link ... " + "[ " + index + " ]");
		WebElement thisLinkElem = mailBody.findElement(By.tagName("a"));

		waitUntil(thisLinkElem);
		thisLinkElem.click();
		
		switchWindow();     // Switch to ThisLink new window, get title, close and switch back to Gmail window

		waitUntil(inBoxButton);
		inBoxButton.click();   //Go back to mailbox
		
		System.out.println("Continue to find the next Email... " + "[ " + index + " ]");
		
	}
	
	public String waitUntilSwitch(String handle) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !handle.isEmpty();
			}
		});
		return handle;
	}
	
	public void clickMail(String subject) {
		List<WebElement> elem_list = findMails(subject, table);
		
		int index = 0;
		for (WebElement elem : elem_list) {
			index++;
			waitUntil(elem);
			elem.click();		
			readMail(index, "this link");
		}
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


	public ArrayList<String> getTitleList() {
		return titleList;
	}


	private void setTitleList(ArrayList<String> titleList) {
		this.titleList = titleList;
	}
}
