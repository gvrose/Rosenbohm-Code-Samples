import java.sql.*;
import java.util.ArrayList;


public class CompanyQuery 
{
   // DBMS and Database-specific connection parameters
   private static final String JDBC_DRIVER = "org.postgresql.Driver";
   // You may need to change these values
   private static final String URL = "jdbc:postgresql://dhansen.cs."
         + "georgefox.edu:5432/company";
   
   // The username and password for connecting to the database
   private static final String USERNAME = "grosenbohm13";
   private static final String PASSWORD = "GVRdatabase";
   
   /**
    * Program connects to the database, issues a simple query and
    * displays the results
    */
   public static void main(String[] args) throws Exception 
   {
   	   
      Connection dbConn = null;
      Statement query = null;
      ResultSet results = null;
 
      // Known employee names
      ArrayList<String> seenName = new ArrayList<String>();
      String firstName = null;
      String lastName = null;

      // Explicitly load the JDBC driver
      Class.forName(JDBC_DRIVER);
   
      // Create a connection to the database
      try 
      {
         dbConn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      }
      catch (SQLException e) 
      {
         System.err.println("Unable to connect to database\n"+e.getMessage());
         System.exit(1);
      }

      try 
      {
         // Create a query to find an employee and each project they work on
         query = dbConn.createStatement();
         results = query.executeQuery("select fname, lname, pname from "
          + "((works_on join project on pno = pnumber) join employee on "
          + "ssn = essn) order by lname asc");

         // Loop over the result set until false is returned by next()
         // and print out the data we retrieved
         while (results.next()) 
         {
            // Get the first name and last name
            firstName = results.getString("fname");
            lastName = results.getString("lname");
 	
            // If you haven't already seen the person's name, print it
            if (!seenName.contains(firstName + " " + lastName))
            {
               System.out.println("\n" + firstName + " " + lastName);
               seenName.add(firstName + " " + lastName);
            }
 	
            // Print the project name that the employee works on
            System.out.print("\t");
            System.out.println(results.getString("pname"));
 	
         }
   
         // Close the connection to the database 
         dbConn.close();
      }
      // If there's an exception log the error, close the 
      // database connection and die
      catch (SQLException e) 
      {
         System.err.println("Fatal database error\n"+e.getMessage());
         try 
         {
            dbConn.close();
         }
         // Closing the connection could raise another exception
         catch (SQLException x) {} // Nothing more to do, we're dying
         System.exit(1);
      }
   
   } // main
	
}
