//iulia
package test;

import org.openqa.selenium.chrome.ChromeDriver;

import pageobjects.*;

public class TestPageObjects {

	
	public static void main(String[] args) throws Exception{
		
		//File file = new File("D:/selenium automation files/library/chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		ChromeDriver driver = new ChromeDriver();
		driver.get("http://iqsles-prod");

		LoginPage loginPage = new LoginPage(driver);
		
		//test login is successful
		DashboardPage homePage = loginPage.loginSuccess("bogdan.olaru@iquestgroup.com", "f9LIIs5EyR2n0wT?x");
		
		System.out.println("Success");
		
		driver.quit();
	}
}
		
