package Domotica.DrinkMixer.RESTService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alberto Flores on 4/12/2016.
 */
@Path("/recipes")
public class RecipeService {

    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public List<RecipeDetails> getAllRecipes() {
        ArrayList<RecipeDetails> RecipesList = new ArrayList<RecipeDetails>();
        RecipeDetails recipe = new RecipeDetails();

        RecipeComponents component = new RecipeComponents();
        ArrayList<RecipeComponents> componentsList = new ArrayList<RecipeComponents>();
        Connection mycon = new DatabaseConnection().con();
        try {
            Statement stmt = mycon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Recipe_Details");
            while (rs.next()) {

                recipe = new RecipeDetails();
                recipe.setRecipeid(rs.getInt("Recipe_ID"));
                recipe.setName(rs.getString("Name"));
                recipe.setDescription(rs.getString("Description"));
                recipe.setCostSmall(rs.getInt("Cost_Small"));
                recipe.setCostMedium(rs.getInt("Cost_Medium"));
                recipe.setCostBig(rs.getInt("Cost_Big"));

                Statement stmt1 = mycon.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT * FROM Recipe_Components WHERE Recipe_ID = " + recipe.getRecipeid());

                componentsList = new ArrayList<RecipeComponents>();
                while (rs1.next()) {

                    component = new RecipeComponents();
                    component.setPercentage(rs1.getInt("Percentage"));
                    component.setAlcoholType(rs1.getString("Alcohol_Type"));
                    componentsList.add(component);

                }
                recipe.setComponentsList(componentsList);
                RecipesList.add(recipe);

            }
            mycon.close();
        } catch (SQLException SQLE) {
            System.out.println(SQLE);
        }
        return RecipesList;
    }


}