package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

  /**
   * Wait max 10 seconds for an element to become available
   * 
   * @param driver
   * @param element
   * @return WebElement
   */
  public static WebElement waitForElement(WebDriver driver, final String element) {
    WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
      @Override
      public WebElement apply(WebDriver d) {
        return d.findElement(By.id(element));
      }
    });

    return myDynamicElement;
  }

  /**
   * Wait predefined timeout seconds for an element to become available
   * 
   * @param driver
   * @param element
   * @param timeout
   * @return WebElement
   */
  public static WebElement waitForElement(WebDriver driver, final String element, Integer timeout) {
    WebElement myDynamicElement = (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<WebElement>() {
      @Override
      public WebElement apply(WebDriver d) {
        return d.findElement(By.id(element));
      }
    });

    return myDynamicElement;
  }

  /**
   * Pause the running code until the ajax requests are finished - jQuery MUST be loaded in the page
   * 
   * @param driver
   */
  public static void waitForAllAjaxRequests(WebDriver driver) {
    // check first that jQuery exist in the current page

    while (!((JavascriptExecutor) driver).executeScript("return jQuery.active;").toString().equals("0")) {
      waitForAllAjaxRequests(driver);
    }
  }

  /**
   * Read a table from an excel file
   * 
   * @param xlFilePath - file location
   * @param sheetName - sheet name
   * @return an array of arrays, containing all data read from the table
   */
  public static String[][] readXLSXFile(String xlFilePath, String sheetName) {
    String[][] tabArray = null;

    try {
      FileInputStream file = new FileInputStream(new File(xlFilePath));

      // Get the workbook instance for XLS file
      XSSFWorkbook workbook = new XSSFWorkbook(file);

      // Get selected sheet from the workbook
      Sheet sheet = workbook.getSheet(sheetName);

      // calculate sheet's size
      int colNr = sheet.getRow(0).getLastCellNum();
      int rowNr = sheet.getLastRowNum();
      tabArray = new String[rowNr][colNr];

      // Iterate through each row from selected sheet

      // Solution 1
      for (int i = 0; i < rowNr; i++) {
        Row row = sheet.getRow(i + 1);// skip first row because it contains the headers
        for (int j = 0; j < colNr; j++) {
          Cell cell = row.getCell(j); // To read value from each col in each row
          tabArray[i][j] = cellToString(cell);// .getStringCellValue();// it might be needed to add some logic to
                                              // correctly identify the
                                              // type of cells
        }
      }

      // Solution 2
      // Iterator<Row> rowIterator = sheet.iterator();
      // while (rowIterator.hasNext()) {
      // Row row = rowIterator.next();
      // // For each row, iterate through each columns
      // Iterator<Cell> cellIterator = row.cellIterator();
      // while (cellIterator.hasNext()) {
      // Cell cell = cellIterator.next();
      // tabArray[cell.getRowIndex()][cell.getColumnIndex()] = cellToString(cell);
      // }
      // }

      file.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return tabArray;

  }

  public static String cellToString(Cell cell) {
    // This function will convert an object of type excel cell to a string value
    int type = cell.getCellType();
    Object result;
    switch (type) {
    case Cell.CELL_TYPE_NUMERIC: // 0
      result = cell.getNumericCellValue();
      break;
    case Cell.CELL_TYPE_STRING: // 1
      result = cell.getStringCellValue();
      break;
    case Cell.CELL_TYPE_FORMULA: // 2
      throw new RuntimeException("We can't evaluate formulas in Java");
    case Cell.CELL_TYPE_BLANK: // 3
      result = "-";
      break;
    case Cell.CELL_TYPE_BOOLEAN: // 4
      result = cell.getBooleanCellValue();
      break;
    case Cell.CELL_TYPE_ERROR: // 5
      throw new RuntimeException("This cell has an error");
    default:
      throw new RuntimeException("We don't support this cell type: " + type);
    }
    return result.toString();
  }

  /**
   * Verify if an image is loaded within the page
   * 
   * @param driver
   * @param image
   * @return true/false
   */
  public static boolean checkImage(WebDriver driver, WebElement image) {
    Object result = ((JavascriptExecutor) driver).executeScript(
        "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
        image);

    boolean loaded = false;
    if (result instanceof Boolean) {
      loaded = (Boolean) result;
      // System.out.println(loaded);
    }
    return loaded;
  }

  /**
   * Verify if the parameter exist and is not empty
   * 
   * @param val
   * @return
   */
  public static boolean checkDataExists(String val) {

    if (val != null && !val.equals("")) {
      return true;
    }
    else {
      return false;
    }
  }

  public static void takeScreenshot(WebDriver driver) {
    String path;
    try {
      File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      path = "./screenshots/" + screenshotFile.getName();
      FileUtils.copyFile(screenshotFile, new File(path));
      System.out.println("Screenshot taken: " + screenshotFile.getName());
    }
    catch (IOException e) {
      path = "Failed to capture screenshot: " + e.getMessage();
    }
  }

}
