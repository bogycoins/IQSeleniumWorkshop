//dani
package test;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import pageobjects.NegociationRequestDetailsOnePage;
import pageobjects.NegociationRequestDetailsOnePage.genderOptions;
import pageobjects.NegociationRequestDetailsOnePage.marketTypeOptions;

public class TestPage {

	public static void main(String[] args) {

//		try {

			TestFillInStepOne();
//		}
//
//		catch (Exception e) {
//
//			System.out.println("Error: " + e);
//		}

	}

	private static void TestFillInStepOne() {

		// open browser and navigate to URL
		WebDriver driver = ex1_1_openSpecificBrowser(browserType.FF,
				"http://iqsles-prod");
		SetupForPuma(driver);
		
		NegociationRequestDetailsOnePage pageNegociationRequestDetailsOne = new NegociationRequestDetailsOnePage(driver);
		
//		pageNegociationRequestDetailsOne.DoFillNegociationRequestDetailsOneValid(
//				"2015", "d://mesi.jpg", "Adi Moldovan", "Int Football", "Daniel Test1",
//				"1981/03/12", true, false, "Romania", "Position1", "Club1",
//				"Power", true, true, true, true, false, false, driver);

		pageNegociationRequestDetailsOne.DoFillNegociationRequestDetailsOneValid(
				"2015", "d://mesi.jpg", "Adi Moldovan", "Int Football", "",
				"1981/03/12", genderOptions.FEMALE, "Romania", "Position1", "",
				"Power", true, true, true, marketTypeOptions.COMPLEMENTARY);
		
		System.out.println("XXXX" + pageNegociationRequestDetailsOne.GetValidationMessages());

//		// logout
//		driver.navigate().to("http://iqsles-prod/protected/unit/user/logout");
//		WebElement confirmBtn = driver.findElement(By
//				.xpath("//button[@name='confirm']"));
//		confirmBtn.click();

		
		//driver.quit();
	}

	public static WebDriver ex1_1_openSpecificBrowser(browserType browserType,
			String url) {

		WebDriver driver;

		// initialize the driver according to given browser type
		switch (browserType) {

		case IE:
			driver = new InternetExplorerDriver();
			break;

		case FF:
			driver = new FirefoxDriver();
			break;

		case CH:
			driver = new ChromeDriver();
			break;

		// start FF by default
		default:
			driver = new FirefoxDriver();
			break;
		}

		// maximize the window
		driver.manage().window().maximize();

		// set wait time out
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// clear cookies
		// driver.manage().deleteAllCookies();

		// navigate to URL
		driver.navigate().to(url);

		return driver;
	}

	public enum browserType {

		IE, FF, CH
	}

	private static void SetupForPuma(WebDriver driver) {

		//
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		// button class="button button-black" name="login"
		WebElement loginBtn = driver.findElement(By
				.xpath("//button[@name='login']"));

		email.clear();
		email.sendKeys("daniel.candrea@iquestgroup.com");
		password.clear();
		password.sendKeys("Daniel12!");
		loginBtn.click();

		driver.navigate().to(
				"http://iqsles-prod/protected/negotiationRequests/overview");

		WebElement newBtn = driver.findElement(By.id("add-new-sponsorship"));
		newBtn.click();
		WebElement createBtn = driver.findElement(By
				.xpath("//button[@name='createNew']"));
		createBtn.click();
		
	}

	/*
	 * EXAMPLE public static void main_example(String[] args) {
	 * 
	 * //initialize the driver WebDriver driver = new ChromeDriver();
	 * 
	 * //navigate to specified URL driver.get("http://www.imdb.com");
	 * 
	 * //maximize the window driver.manage().window().maximize();
	 * 
	 * //find searchbox WebElement searchBox =
	 * driver.findElement(By.id("navbar-query"));
	 * 
	 * //enter text in searchbox searchBox.sendKeys("Kill Bill");
	 * 
	 * //try to find and click the search button - display error if failed try{
	 * WebElement searchBtn =
	 * driver.findElement(By.xpath("//*[@id='navbar-submit-button']"));
	 * searchBtn.click(); } catch(Exception e){ System.out.println("Error: " +
	 * e); }
	 * 
	 * //quits the driver (closes the browser) driver.quit(); }
	 */

}
