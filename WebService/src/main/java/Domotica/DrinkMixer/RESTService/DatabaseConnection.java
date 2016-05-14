package Domotica.DrinkMixer.RESTService;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Create connections to DrinkMixer database
 *
 * @author Alberto Flores Alberto@Flores.cf
 * @version 1.0
 * @since 2016-04-12
 */
public class DatabaseConnection {

    /**
     * creates the connection to DrinkMixer
     *
     * @return con return the active connection to the database.
     */
    public Connection con() {

        /**
         * Stores the connection
         */
        Connection con;

        // try to establish connection to the database
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/DrinkMixer" +
                    ";instance=SQLEXPRESS;user=Alberto.Flores;password=betobeto");
            return con;
        } catch (Exception e) {
            System.out.println(e); // Print error
        }
        return con();
    }

}