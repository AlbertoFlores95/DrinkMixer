package Domotica.DrinkMixer.RESTService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * POJO of the beverage recipe
 *
 * @author Alberto Flores Alberto@Flores.cf
 * @version     1.0
 * @since       2016-04-12
 */
@XmlRootElement(name = "Recipe")
public class RecipeDetails implements Serializable {

    /**
     * Unique identifier of the recipe.
     */
    private int recipeID;
    /**
     * Name of the recipe.
     */
    private String name;
    /**
     * Short description of the recipe that includes history and components.
     */
    private String description;
    /**
     * Cost of 60ml of the beverage.
     */
    private int costSmall;
    /**
     * Cost of 80ml of the beverage.
     */
    private int costMedium;
    /**
     * Cost of 100ml of the beverage.
     */
    private int costBig;
    /**
     * ArrayList of the components that the beverage have.
     */
    private ArrayList<RecipeComponents> components;

    /**
     * Default constructor
     * <p>
     * Constructor that takes no parameters.
     */
    public RecipeDetails() {
    }

    /**
     * Constructor with parameters
     * <p>
     * Constructor that takes all the parameters of the object.
     *
     * @param recipeID Unique identifier of the recipe.
     * @param name Name of the recipe.
     * @param description Short description of the recipe that includes history and components.
     * @param costSmall Cost of 60ml of the beverage.
     * @param costMedium Cost of 80ml of the beverage.
     * @param costBig Cost of 100ml of the beverage.
     * @param components ArrayList of the components that the beverage have.
     */
    public RecipeDetails(int recipeID, String name, String description, int costSmall, int costMedium, int costBig, ArrayList<RecipeComponents> components){
        this.recipeID = recipeID;
        this.name = name;
        this.description = description;
        this.costSmall = costSmall;
        this.costMedium = costMedium;
        this.costBig = costBig;
        this.components = components;
    }

    /**
     * Getter which gets the recipeID.
     * @return Return recipeID.
     */
    public int getRecipeid() {
        return recipeID;
    }

    /**
     * Setter which sets the recipeID of the recipe.
     @param recipeid Unique identifier of the recipe.
     */
    @XmlElement
    public void setRecipeid(int recipeid) {
        recipeID = recipeid;
    }

    /**
     * Getter which gets the name of the recipe.
     * @return Return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter which sets the name of the recipe.
     * @param name Name of the recipe.
     */
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter which gets the description of the recipe.
     * @return Return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter which sets the description of the recipe.
     * @param description Short description of the recipe that includes history and components.
     */
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter which gets the costSmall of the recipe.
     * @return Return costSmall.
     */
    public int getCostSmall() {
        return costSmall;
    }

    /**
     * Setter which sets the costSmall of the recipe.
     * @param costSmall Cost of 60ml of the beverage.
     */
    @XmlElement
    public void setCostSmall(int costSmall) {
        this.costSmall = costSmall;
    }

    /**
     * Getter which gets the costMedium of the recipe.
     * @return Return costMedium.
     */
    public int getCostMedium() {
        return costMedium;
    }

    /**
     * Setter which sets the costMedium of the recipe.
     * @param costMedium Cost of 80ml of the beverage.
     */
    @XmlElement
    public void setCostMedium(int costMedium) {
        this.costMedium = costMedium;
    }

    /**
     * Getter which gets the costBig of the recipe.
     * @return Return costBig.
     */
    public int getCostBig() {
        return costBig;
    }

    /**
     * Setter which sets the costBig of the recipe.
     * @param costBig Cost of 100ml of the beverage.
     */
    @XmlElement
    public void setCostBig(int costBig) {
        this.costBig = costBig;
    }

    /**
     * Getter which gets the components of the recipe.
     * @return Return components.
     */
    public ArrayList<RecipeComponents> getComponentsList() {
        return components;
    }

    /**
     * Setter which sets the components of the recipe.
     * @param components ArrayList of the components that the beverage have.
     */
    @XmlElement
    public void setComponentsList(ArrayList<RecipeComponents> components) {
        this.components = components;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "RecipeDetails{" +
                "recipeID=" + recipeID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", costSmall=" + costSmall +
                ", costMedium=" + costMedium +
                ", costBig=" + costBig +
                ", componentsList=" + components +
                '}';
    }
}