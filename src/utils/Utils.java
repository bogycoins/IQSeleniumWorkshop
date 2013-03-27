package utils;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;
import java.sql.*;

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



import au.com.bytecode.opencsv.CSVReader;

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
  
  /**
	 * Parses a CSV file and returns the data in a String[][] object
	 * 
	 * @param filePath
	 *            : string with full *.csv file path
	 * @param fileWithHeader
	 *            : boolean: true if the file has a header line, false
	 *            otherwise. In case of true, the first line will NOT be
	 *            returned
	 * @return a String[][] array with data from file
	 * @throws IOException
	 */
	public static String[][] readCSVFile(String filePath, Boolean fileWithHeader)
			throws IOException {
		String[][] tabArray = null;
		CSVReader reader = new CSVReader(new FileReader(filePath));
		List<String[]> rowsList = reader.readAll();

		// Remove the header (first line)
		if (fileWithHeader) {
			rowsList.remove(0);
		}

		tabArray = new String[rowsList.size()][rowsList.get(0).length];

		for (int i = 0; i < rowsList.size(); i++) {
			tabArray[i] = rowsList.get(i);
		}
		
		reader.close();
		return (tabArray);
	}

	/**
	 * Parses a XML file and returns the data in a String[][] object<br>The method
	 * expects an XML file with the following schema:
	 * <p>
	 * {@code
	 * <dataset>
	 * }<br>
	 * {@code <datarow>}<br>
	 * {@code <someelement1></someelement1>}<br>
	 * {@code <someelement2></someelement2> }<br>
	 * {@code </datarow>}<br>
	 * {@code </dataset>}<br>
	 * </p>
	 * 
	 * @param filePath
	 *            : string with full *.xls file path
	 * 
	 */
	public static String[][] readXMLFile(String filePath) {
		String[][] tabArray = null;

		try {

			List<String> content = new ArrayList<String>();
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(filePath);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document

			int datarows = 0;
			int cols = 0;
			Boolean isFirstDataRow = true;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					// if the element is dataset it should be ignored
					if (!event.asStartElement().getName().getLocalPart()
							.equals("dataset")) {
						// if it's a "datarow" element we should count it to
						// find how many rows we'll need
						if (event.asStartElement().getName().getLocalPart()
								.equals("datarow")) {
							datarows++;
						}
						// a child of the first datarow (we'll count it to find
						// how many columns we need)
						else if (isFirstDataRow == true) {
							cols++;
							// move to next element (expected to be the needed
							// text)
							event = eventReader.nextEvent();

							// System.out.println(getEventData(event));
							// add the content to the temp list
							content.add(getEventData(event));
						}
						// a datarow child element, but not of the first datarow
						else {
							event = eventReader.nextEvent();
							// System.out.println(getEventData(event));
							content.add(getEventData(event));
						}
					}
				}

				// the closing of the first datarow element
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == "datarow") {
						isFirstDataRow = false;
					}
				}
			}

			 //System.out.println(datarows);
			 //System.out.println(cols);

			tabArray = new String[datarows][cols];

			for (int rn = 0; rn < datarows; rn++) {
				for (int cn = 0; cn < cols; cn++) {
					// always add the first list element and then we remove it
					// from the list
					tabArray[rn][cn] = content.get(0);
					content.remove(0);
				}
			}

			/*
			 * print final array for(int rn = 0; rn < datarows; rn ++) { for
			 * (int cn = 0; cn < cols; cn++) { System.out.println(rn + "." + cn
			 * + ": " + tabArray[rn][cn]); } }
			 */
		} catch (FileNotFoundException e) {
			System.out.println("Error finding file " + filePath);
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return tabArray;
	}

	private static String getEventData(XMLEvent event) {
		if (event.isCharacters()) {
			return event.asCharacters().getData();
		} else {
			return "";
		}
	}
	
	/**
	 * Connects to a SQLite DB and returns data in a String[][] object
	 * 
	 * @param filePath
	 *            : string with full *.db file path
	 * @param selector
	 *            : Sql command to select data
	 * @return a String[][] array with data from file
	 * @throws IOException
	 */
	public static String[][] readSQLLite(String filePath, String selector) {
		String[][] tabArray = null;
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selector);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			ArrayList<String[]> list = new ArrayList<String[]>();

			while (resultSet.next()) {
				String[] row = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					row[i] = resultSet.getString(i + 1);
				}
				list.add(row);
			}

			int rowCount = list.size();
			tabArray = new String[rowCount][columnCount];

			for (int row = 0; row < list.size(); row++) {
				tabArray[row] = list.get(row);
				//System.out.println(tabArray[row][0] + "/" + tabArray[row][1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return (tabArray);
	}
// set value in the ClipboardData
public static void setClipboardData(String string) {
   StringSelection stringSelection = new StringSelection(string);
   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
}//end set value in the ClipboardData

//upload file using robot
public static void uploadFile(String file_path) throws AWTException{
	  setClipboardData(file_path);	
	  
	  //native key strokes for CTRL, V and ENTER keys
	Robot robot = new Robot();
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
}//end upload file using robot

}
