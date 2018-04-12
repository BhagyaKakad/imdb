package TestList;

//Executing the tests

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Variables.Variablesglobal;
import Database.DataConnect;
import movie_list.TestCase_01;
import movie_list.TestCase_02;

@Test
public class Get250List {
	
	@BeforeTest
	public void droptable() throws SQLException {
		objGlobal.strQuery = "DELETE FROM IMDB_TOP250";
		objDConnect.connect();
		objDConnect.exeQuery(objGlobal.strQuery);
		System.out.println("Records are now deleted");
	}
	
	public WebDriver driver = new ChromeDriver();
	TestCase_01 objFirstPage = new TestCase_01(driver);
	TestCase_02 objTop250 = new TestCase_02(driver);
	DataConnect objDConnect = new DataConnect();
	Variablesglobal objGlobal = new Variablesglobal();
	
	//opens IMDB site & checks title
	
	@Test
	public void GetURL()
	{
		driver.manage().window().maximize();
		driver.get(objFirstPage.strWebURL);
		Assert.assertEquals(driver.getTitle() , objFirstPage.strHomePage);
	}
	
	//Goto top 250 movie list
	
	@Test(dependsOnMethods = {"Get URL"})
	public void NavigateIMDB() throws InterruptedException
	{
		objFirstPage.btnlistDropDown.click();
		Thread.sleep(3000L);
		objFirstPage.top250.click();
		Assert.assertEquals(driver.getTitle() , objTop250.str250Title);
	}
	
	//Fetching data from database
	
	@Test(dependsOnMethods = {"Get URL","NavigateIMDB"})
	public void FetchData() throws SQLException
	{
		objDConnect.connect();
		objTop250.movieCount = 2;
		System.out.println("Database creation is in progress");
		
	//Search child objects
	for (objTop250.movieYearCount = 0; objTop250.movieYearCount< 250; objTop250.movieYearCount ++)
	{
		objTop250.movieName = objTop250.MovieList.findElements(By.tagName("a")).get(objTop250.movieCount).getText();
		if(objTop250.movieName.contains("'"));
		{
			objTop250.movieName = objTop250.movieName.replaceAll("'", "'");
		}
		
		//Finding movie year
		objTop250.movieYear = objTop250.MovieList.findElements(By.name("rd")).get(objTop250.movieYearCount).getText();
		objTop250.movieYear = objTop250.movieYear.substring(1, 5);
		
		//Find Movie ratings
		objTop250.movieRating = objTop250.MovieList.findElements(By.name("nv")).get(objTop250.movieYearCount).getText();
	
		//Updating results in database
		objGlobal.strQuery = "INSERT INTO imdb (Sr_No,Movie_Nmae,Movie_Year,Movie_Ratings)" + "Values ("+(objTop250.movieYearCount+1)+",'"+objTop250.movieName+"','"+objTop250.movieYear+"','"+objTop250.movieRating+"')";
		objDConnect.exeQuery(objGlobal.strQuery);
		
		objTop250.movieCount = objTop250.movieCount+2;
	}
	}
	/**
	 * @param args
	 */
//	private void if(boolean contains){
		
	//creating file containg movies list
		@Test(dependsOnMethods = {"Get URL","NavigateIMDB","FetchData"})
		public void CreateFile() throws SQLException
		{
		try
		{
			System.out.println("Database is created");
			FileWriter objWriter = new FileWriter(objGlobal.strFilePath);
			BufferedWriter objbw = new BufferedWriter(objWriter);
			objDConnect.connect();
			objGlobal.conn = DriverManager.getConnection(objGlobal.strDBPath);
			objGlobal.stmt = objGlobal.conn.createStatement();
			objGlobal.strQuery = "selecy * from imdb";
			objGlobal.rs = objGlobal.stmt.executeQuery(objGlobal.strQuery);
			ResultSetMetaData col = objGlobal.rs.getMetaData();
			objbw.write(col.getColumnName(1)+","+col.getColumnName(2)+","+col.getColumnName(3)+","+col.getColumnName(4));
			objbw.newLine();
			while (objGlobal.rs.next())
			{
				objbw.write(objGlobal.rs.getInt("Sr_No")+","+objGlobal.rs.getString("Movie_Name")+","+objGlobal.rs.getString("Movie_Year")+","+objGlobal.rs.getString("Movie_Rating"));
				objbw.newLine();
			}
			objbw.close();
			objGlobal.rs.close();
			objGlobal.stmt.close();
			objGlobal.conn.close();
			System.out.println("File is created");
		}catch (IOException e)
		{
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
			}
		}
	}

