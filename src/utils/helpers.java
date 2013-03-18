//dani
package utils;

import org.openqa.selenium.WebElement;

public class helpers {

	public static void selectCheckbox(WebElement checkbox, Boolean selected) {
		
		if(checkbox.isSelected() != selected) {
			
			checkbox.click();
		}
	}
}
