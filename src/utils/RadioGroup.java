package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Daniel Candrea
 * 
 */
public class RadioGroup {

	private List<WebElement> radioGroup;

	/**
	 * Identify radio group using the name (the radio buttons from the same group have the same name)
	 * @param groupName the name of the radio group
	 * @param driver current WebDriver
	 */
	public RadioGroup(String groupName, WebDriver driver) {

		// find all elements having the provided name and radio type
		String radioGroupXpath = String.format("//input[@name='%s' and @type='radio']", groupName);
		List<WebElement> radioWebElements = driver.findElements(By.xpath(radioGroupXpath));

		this.radioGroup = radioWebElements;

		// throw exception if no element found
		if (radioWebElements.size() == 0) {
			
			throw new NoSuchElementException(String.format("Could not find radiogroup with name <%s>", groupName));
		}
	}

	/**
	 * @return the value of the current selected radio button or "NO_SELECTION"
	 */
	public String getSelectedValue() {

		String result = "NO_SELECTION";

		// iterate the available radio button options searching for the selected one 
		for (WebElement radioOption : radioGroup) {

			if (radioOption.isSelected()) {

				result = radioOption.getAttribute("value");
				break;
			}
		}

		return result;
	}

	/**
	 * @return List<String> of available values for selection
	 */
	public List<String> getAvailableValues() {

		List<String> results = new ArrayList<String>();

		// iterate the available radio button options and get the value attribute
		for(WebElement radioOption : radioGroup) {
						
			results.add(radioOption.getAttribute("value"));
		}

		return results;
	}

	/**
	 * @param value the radioButton value to be selected
	 * @return true | false / IllegalArgumentException if value not available for selection
	 */
	public Boolean selectOptionByValue(String value) {

		Boolean result = false;
		
		// search the value in the available radio buttons
		for (WebElement radioOption : radioGroup) {

			if (radioOption.getAttribute("value").equalsIgnoreCase(value)) {

				radioOption.click();
				result = true;
				break;
			}
		}
		
		// throw exception if value not found
		if (!result) {
			
			throw new IllegalArgumentException(String.format("Supplied value <%s> could not selected. Available values: <%s>", value, this.getAvailableValues()));
		}
		
		return result;
	}
}
