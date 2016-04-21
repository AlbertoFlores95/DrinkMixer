package Domotica.DrinkMixer.RESTService;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Alberto Flores on 4/12/2016.
 */
public class DatabaseConnection {

    Connection con;

    public Connection con() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/DrinkMixer" +
                    ";instance=SQLEXPRESS;user=Alberto.Flores;password=betobeto");

            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return con();
    }

}