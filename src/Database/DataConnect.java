package Database;

import java.sql.DriverManager;
import java.sql.SQLException;

import Variables.Variablesglobal;

//connection to database
public class DataConnect {
		Variablesglobal objGlobal = new Variablesglobal();
		public void connect() {
			
			try {
				Class.forName(objGlobal.strSQLite);
				objGlobal.conn = DriverManager.getConnection(objGlobal.strDBPath);
				objGlobal.conn.setAutoCommit(false);
				System.out.println("Database is opened now");
			}
			catch(Exception e) {
				System.err.println(e.getClass().getName() + ":" + e.getMessage());
				System.exit(0);
			}
		}
		
		// fetching the data
		
		public void exeQuery(String query) throws SQLException {
			
			objGlobal.stmt = null;
			objGlobal.stmt = objGlobal.conn.createStatement();
			objGlobal.stmt.executeUpdate(query);
			objGlobal.stmt.close();
			objGlobal.conn.commit();
		}
		
}
