// libaries fra mysql-connector-j-8.0.31.jar
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.io.FileWriter;

public class UpdateLocalFiles {

  // info-en du trenger for å koble til database;
  private String ip = "10.0.0.2";
  private String port = "3306";
  private String username = "Klient";
  private String password = "n]ua2BeXUF!ci[mK";
  private String database = "game4";

  private String[] categories = {"weapon", "armor", "blessing", "enemy"};
  
  public UpdateLocalFiles(){
    try {
      Connection con = getConection();
      for (int i = 0; i < categories.length; i++){// repeterer dette for alle relevante tabellene

        PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + categories[i] + "s`");
        ResultSet result = statement.executeQuery();

        // sjekker hvilke kolloner som finnes i tabbelen for å vite hva den skal lagre
        Boolean tierExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Tier'").executeQuery().next();
        Boolean effectExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Effect'").executeQuery().next();
        Boolean damageExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Damage'").executeQuery().next();
        Boolean healthExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Health'").executeQuery().next();

        ArrayList<String> array = new ArrayList<String>();
        while(result.next()){//går gjennom alle rader i tabbelen
          array.add(result.getString(categories[i] + "Type"));

          // hvis det finnnes legger vi det til i tabbellen
          if(tierExists != false){
            array.add(result.getString(categories[i] + "Tier"));
          }
          if(effectExists != false){
            array.add(result.getString(categories[i] + "Effect"));
          }
          if(damageExists != false){
            array.add(result.getString(categories[i] + "Damage"));
          }
          if(healthExists != false){
            array.add(result.getString(categories[i] + "Health"));
          }
        }

        //lager og skriver i en fil
        FileWriter myWriter = new FileWriter("localFiles/" + categories[i] +".txt");
        for(int g = 0; g < array.size(); g++){
          myWriter.write(array.get(g) + System.lineSeparator());
        }
        myWriter.close();
      }
      System.err.println("\nSuccessfully updated locale files.");
    } catch (Exception e) {
      System.err.println("\nCouldnt update locale files. Continues with old files.");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
    }
  }

  public Connection getConection() throws Exception{// funksjonen for å koble till databasen, denne skjønner jeg ikke helt
    try {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
      Class.forName(driver);

      Connection conn = DriverManager.getConnection(url, username, password);

      return conn;
    } catch (Exception e) {
      System.err.println("\nCouldnt connect to the database. Do you have internet?");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
      return null;
    }
  }
}


