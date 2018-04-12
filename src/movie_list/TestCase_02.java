package movie_list;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//initializing list of 250 movies
public class TestCase_02 {
	public TestCase_02(WebDriver driver)
	{
	PageFactory.initElements(driver, this);			
	}
	public String str250Title = "IMdb Top 25 - IMdb";
	public String movieName, movieYear, movieRating;
	public int movieCount, movieYearCount;
	
	@FindBy(xpath = "//*[@id='main']/div")
	public WebElement MovieList;
	
	
	/* Child Items
	  @FindBy(tagName = 'a')
	 public WebElement strMovieName;
	 
	  @FindBy(tagName = 'rd')
	 public WebElement strmovieYearCount;
	  
	   @FindBy(tagName = 'nv')
	 public WebElement strMovieRating;
	 */


}
