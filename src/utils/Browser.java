package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Browser {

  /**
   * Start a browser and navigate to given URL
   * 
   * @param browser: firefox, chrome, ie
   * @param location
   * @return
   * @throws Exception 
   */
  public WebDriver startBrowser(String browser, String url) {

    WebDriver drv;
    
    switch (browser) {
    case "firefox":
      drv = new FirefoxDriver();
      break;
    case "chrome":
      //ChromeOptions options = new ChromeOptions();
      // options.addArguments("start-maximized");
      drv = new ChromeDriver();
      break;
    case "ie":
      drv = new InternetExplorerDriver();
      break;
    default:
      drv = new FirefoxDriver();
      break;
    }

    drv.get(url);
    drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    return drv;
  }
}
