import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Delfi {
    @Test
    public void getTemperatureAndCurrency() {
		//Initializing Web driver and opening site URL
    	System.setProperty("webdriver.chrome.driver", "/Users/audriusp/Documents/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.delfi.lt");
        
        //Selecting top bar cities drop-down
        WebElement headerWeather = driver.findElement(By.className("header-weather"));
        
        //Clicking on "Klaipeda" city in drop-down
        Actions action = new Actions(driver);
        action.moveToElement(headerWeather).build().perform();
        driver.findElement(By.partialLinkText("Klaip")).click();
        
        //Get temperature and print it out in console
        WebElement temp = driver.findElement(By.className("dweather2-temp")).findElements(By.tagName("span")).get(0);
        System.out.println("Temperature in Klaipeda: " + temp.getText());
        
        //Get and click on "Valiutu kursai" link in top bar
        ArrayList<WebElement> resultsDiv = (ArrayList<WebElement>) driver.findElement(By.id("header-links1")).findElements(By.tagName("a"));
        resultsDiv.get(0).click();
        
        //Explicitly wait for currency input to exist in page
        WebElement curInput = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.className("cur-input")));
        
        //Get currency to convert from selector and select Turkish lira
        Select curFrom = new Select(driver.findElement(By.className("cur-cal-from")).findElement(By.tagName("select")));
        curFrom.selectByVisibleText("Turkijos lira");
        
        //Get currency converter to selector and change for conversion to happen to Euro
        Select curTo = new Select(driver.findElement(By.className("cur-cal-to")).findElement(By.tagName("select")));
        curTo.selectByValue("2");
        
        //Enter value to be exchanged - 100
        curInput.findElement(By.tagName("input")).sendKeys("100");
        
        //Get Euro value to be exchanged and print it to console
        WebElement change = driver.findElement(By.id("eur-txt"));
        System.out.println("Your will get: " + change.getText() + " Eur");
        
        //Exit Driver
        driver.quit();
	}
}
