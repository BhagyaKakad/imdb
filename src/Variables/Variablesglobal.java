package Variables;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;



//variables that are declared globally in the project

public class Variablesglobal {
		public Statement stmt;
		public Connection conn;
		public ResultSet rs;
		public String strSQLite = "org.sqlite.JDBC";
		public String strDBPath =  "jdbc:sqlite:d:\\IMDB\\IMDB_1.0\\IMDB.sqlite";
		public String strQuery;
		public String strFilePath = "D:\\QA\\Space\\IMDB_Top_250_Movie.csv";

}
