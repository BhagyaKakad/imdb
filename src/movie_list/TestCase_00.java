package movie_list;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestCase_00 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/chromedriver.exe");
		System.out.println(System.getProperty("webdriver.chrome.driver"));
		WebDriver driver  = new ChromeDriver();
		
		driver.get("http://www.imdb.com");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.className("rec_default_image_text")).click();
		
	}

}
