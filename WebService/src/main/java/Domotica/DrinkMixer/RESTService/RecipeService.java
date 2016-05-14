package Domotica.DrinkMixer.RESTService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * RESTful web service to manage recipes
 *
 * @author Alberto Flores Alberto@Flores.cf
 * @version 1.0
 * @since 2016-04-12
 */
@Path("/recipes")
public class RecipeService {

    /**
     * String to show a successful result
     */
    private static final String successResult = "<result>success</result>";
    /**
     * String to show a failure result
     */
    private static final String failureResult = "<result>failure</result>";

    /**
     * Fetch all recipes from database and generate XML
     * <p>
     * Creates a connection to the database then fetch every recipe and its components to inflate the java objects
     * (RecipeDetails and RecipeComponents)
     * and show the objects in XML format in the path: (Authority)/recipes/all
     *
     * @return Return an ArrayList of the recipes objects that will be shown in XML
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public List<RecipeDetails> getAllRecipes() {

        /**
         * ArrayList to store all recipes
         */
        ArrayList<RecipeDetails> RecipesList = new ArrayList<RecipeDetails>();
        /**
         * RecipeDetails object to store a recipe
         */
        RecipeDetails recipe = new RecipeDetails();
        /**
         * RecipeComponents object to store a the components of a recipe
         */
        RecipeComponents component = new RecipeComponents();
        /**
         * ArrayList of recipeComponents to store RecipeComponents objects
         */
        ArrayList<RecipeComponents> componentsList = new ArrayList<RecipeComponents>();
        /**
         * create and store connection to the database
         */
        Connection mycon = new DatabaseConnection().con();
        try {

            Statement stmt = mycon.createStatement(); //prepare SQL query to fetch recipes
            ResultSet rs = stmt.executeQuery("SELECT * FROM Recipe_Details"); //Fetch all recipes from database

            // For every recipe in the database create a RecipeObject
            while (rs.next()) {

                // Initiate recipe object.
                recipe = new RecipeDetails();
                // Set the recipe object attributes from the database
                recipe.setRecipeid(rs.getInt("Recipe_ID"));
                recipe.setName(rs.getString("Name"));
                recipe.setDescription(rs.getString("Description"));
                recipe.setCostSmall(rs.getInt("Cost_Small"));
                recipe.setCostMedium(rs.getInt("Cost_Medium"));
                recipe.setCostBig(rs.getInt("Cost_Big"));

                // Prepare second query to fetch all RecipeComponents of the recipe
                Statement stmt1 = mycon.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT * FROM Recipe_Components WHERE Recipe_ID = " + recipe.getRecipeid());

                // Initialize components ArrayList
                componentsList = new ArrayList<RecipeComponents>();
                // For every component of the recipe create RecipeComponentObject
                while (rs1.next()) {

                    // Set the components object attributes from the database
                    component = new RecipeComponents();
                    component.setPercentage(rs1.getInt("Percentage"));
                    component.setAlcoholType(rs1.getString("Alcohol_Type"));
                    componentsList.add(component); // Add the components to the components ArrayList

                }
                recipe.setComponentsList(componentsList); // Add the components ArrayList to the recipe object.
                RecipesList.add(recipe);// add the recipe object to the recipes list.
            }
            mycon.close(); // Close database connection
        } catch (SQLException SQLE) {
            System.out.println(SQLE);
            // Error in reaching database
        }

        return RecipesList;
    }

    /**
     * Create new recipe and store it in the database
     * <p>
     * Receives the necessary params to create the recipe
     * then creates a connection to the database,
     * insert recipe in database, close connection
     * and last return a message of success or failure
     *
     * @param name        Name of the recipe.
     * @param description Short description of the recipe that includes history and components.
     * @param costSmall   Cost of 60ml of the beverage.
     * @param costMedium  Cost of 80ml of the beverage.
     * @param costBig     Cost of 100ml of the beverage.
     * @return Return a message of success if the recipe was inserted or failure ir not
     */
    @PUT
    @Path("/new/{Name}/{Description}/{CostSmall}/{CostMedium}/{CostBig}")
    @Produces(MediaType.TEXT_PLAIN)
    public String createRecipe(@PathParam("Name") String name, @PathParam("Description") String description,
                               @PathParam("CostSmall") int costSmall, @PathParam("CostMedium") int costMedium,
                               @PathParam("CostBig") int costBig) {
        //TODO: Change from pathparam to formparam
        //TODO: Insert array of components

        Connection mycon = new DatabaseConnection().con(); // Creates connection to DrinkMixer database

        // Try to insert recipe into database
        try {

            Statement stmt = mycon.createStatement(); //Prepare SQL query to insert recipe
            String insertQuery = "INSERT INTO Recipe_Details (Name,Description,Cost_Small," +
                    "Cost_Medium,Cost_Big) VALUES ('" + name + "','" + description + "'," +
                    costSmall + "," + costMedium + "," + costBig + ");";
            ResultSet rs = stmt.executeQuery(insertQuery); //Insert recipe to database

        } catch (SQLException e) {
            return failureResult; // Error in reaching Database
        }
        return successResult; // successfully inserted recipe into database
    }

    /**
     * Delete the selected recipe from the database
     * <p>
     * Receives the recipeID that will be deleted
     * then creates a connection to the database,
     * delete the recipe in the database, close connection
     * and last return a message of success or failure
     *
     * @param recipeID Unique identifier of the recipe.
     * @return Return a message of success if the recipe was inserted or failure ir not
     */
    @DELETE
    @Path("Delete/{RecipeID}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteRecipe(@PathParam("RecipeID") String recipeID) {

        Connection mycon = new DatabaseConnection().con(); // Creates connection to DrinkMixer database

        // Try to delete recipe from database
        try {

            Statement stmt = mycon.createStatement(); //Prepare SQL query to delete recipe
            String insertQuery = "DELETE FROM Recipe_Details WHERE Recipe_ID = " + recipeID + ";";
            stmt.executeQuery(insertQuery); // Delete recipe from database

        } catch (SQLException e) {
            return failureResult; // Error in reaching Database
        }
        return successResult; // successfully deleted recipe from database
    }

    /**
     * Update the selected recipe from the database
     * <p>
     * Receives the parameters of the recipe that will be updated
     * then creates a connection to the database,
     * update the recipe in the database, close connection
     * and last return a message of success or failure
     *
     * @param recipeID Unique identifier of the recipe.
     * @param name Name of the recipe.
     * @param description Short description of the recipe that includes history and components.
     * @param costSmall Cost of 60ml of the beverage.
     * @param costMedium Cost of 80ml of the beverage.
     * @param costBig Cost of 100ml of the beverage.
     *
     * @return Return a message of success if the recipe was inserted or failure ir not
     */
    @POST
    @Path("/update/{RecipeID}/{Name}/{Description}/{CostSmall}/{CostMedium}/{CostBig}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateRecipe(@PathParam("RecipeID") int recipeID,
                               @PathParam("Name") String name,
                               @PathParam("Description") String description,
                               @PathParam("CostSmall") int costSmall,
                               @PathParam("CostMedium") int costMedium,
                               @PathParam("CostBig") int costBig) {
        //TODO: Change from pathparam to formparam
        //TODO: Insert array of components

        Connection mycon = new DatabaseConnection().con(); // Creates connection to DrinkMixer database

        // Try to update recipe from database
        try {

            Statement stmt = mycon.createStatement(); //Prepare SQL query to update recipe
            String updateQuery = "UPDATE Recipe_Details SET Name = '" + name + "' , Description = '" + description +
                    "' , Cost_Small =" + costSmall + ", Cost_Medium =" + costMedium + ", Cost_Big =" + costBig +
                    "WHERE Recipe_ID =" + recipeID + ";";
            stmt.executeQuery(updateQuery); // Update recipe to database
            mycon.close();

        } catch (SQLException e) {
            return failureResult; // Error in reaching Database
        }
        return successResult; // successfully updated recipe into database
    }
}