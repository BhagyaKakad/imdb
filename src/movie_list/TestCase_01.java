package movie_list;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCase_01 {
		//initialization
		public TestCase_01(WebDriver driver)
		{
		PageFactory.initElements(driver, this);			
		}
		public String strWebURL = "http://www.imdb.com";
		
		public String strHomePage = "http://www.imdb.com";
		
		@FindBy(xpath = "//*[@id= 'navTitleMenu']/span")
		public WebElement btnlistDropDown;
			
		@FindBy(xpath = "//*[@id='navMenu1']/div[2]/ul[2]/li[4]/a")
		public WebElement top250;

}
