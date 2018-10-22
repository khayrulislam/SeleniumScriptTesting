package Script;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.Util;

public class KotlinSiteTest {

	WebDriver driver;
	
	
	public void startTestExecution()  {
		
		driver = initializeWebDriver();
		
		launceSite(Util.KOTLIN_URL);
		MouseClick(getWebElementByXPath(Util.KOTLIN_COMMUNITY_XPATH));
		MouseClick(getWebElementByXPath(Util.KOTLIN_LEARN_XPATH));
		waitForPageLoaded();
		MouseClick(getWebElementByXPath(Util.KOTLIN_TUTORIAL_XPATH));
		waitForPageLoaded();
		MouseClick(getWebElementByXPath(Util.KOTLIN_BOOK_XPATH));
		waitForPageLoaded();
		MouseClick(getWebElementByXPath(Util.KOTLIN_MORE_RESOURCE_XPATH));
		stopSite();
	}
		
	private void MouseClick(WebElement webElement) {
		//webElement.click();

		Actions action = new Actions(driver);
		action.moveToElement(webElement).click().build().perform();
	}

	private void stopSite() {
		driver.quit();
	}


	public void waitForPageLoaded() {
	 
		/*ExpectedCondition<Boolean> expectation = new
		        ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver driver) {
		                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	            }
	        };*/
		try {
		    Thread.sleep(3000);
		   // WebDriverWait wait = new WebDriverWait(driver, 60);
		    //wait.until(expectation);
		} catch (Throwable error) {
		    System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	 }

	private WebElement getWebElementByXPath(String path) {
		return driver.findElement(By.xpath(path));
	}

	private void launceSite(String siteURL) {
		driver.get(siteURL);
	}

	private WebDriver initializeWebDriver() {

		System.setProperty(Util.WEB_DRIVER_PATH, Util.CHROME_DRIVER_EXE_PATH);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		WebDriver driver = new ChromeDriver(option);
		
		//WebDriver driver = new ChromeDriver();  //maximize after start
		//driver.manage().window().maximize();
		return driver;
	}
	
}
