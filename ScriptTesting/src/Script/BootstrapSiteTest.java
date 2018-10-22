package Script;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Utils.Util;

public class BootstrapSiteTest {

	WebDriver driver;
	
	
	public void startTestExecution()  {
		
		driver = initializeWebDriver();
		
		launceSite(Util.BOOTSTRAP_URL);
		
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_GETTING_STARTED_XPATH));
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_CONTENT_XPATH));
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_LAYOUT_XPATH));
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_THEME_XPATH));
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_BLOG_XPATH));
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_DOWNLOAD_XPATH));
		MouseClick(getWebElementByXPath(Util.BOOTSTRAP_DOWNLOAD_ACTION_XPATH));
		stopSite();
	}

	private void MouseClick(WebElement webElement) {
		webElement.click();
	}

	private void stopSite() {
		driver.quit();
	}


	public void waitForPageLoaded() {
	/*	 
		ExpectedCondition<Boolean> expectation = new
		        ExpectedCondition<Boolean>() {
		            public Boolean apply(WebDriver driver) {
		                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	            }
	        };*/
		try {
		    Thread.sleep(3000);
		    //WebDriverWait wait = new WebDriverWait(driver, 60);
		   // wait.until(expectation);
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
