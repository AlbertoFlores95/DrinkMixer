package Domotica.DrinkMixer.RESTService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alberto Flores on 4/12/2016.
 */
@XmlRootElement(name = "Recipe")
public class RecipeDetails implements Serializable {

    private int recipeID;
    private String name;
    private String description;
    private int costSmall;
    private int costMedium;
    private int costBig;
    private ArrayList<RecipeComponents> components;

    public RecipeDetails() {
    }

    public RecipeDetails(int costBig, ArrayList<RecipeComponents> components, int costMedium, int costSmall, String description, String name, int recipeID) {
        this.costBig = costBig;
        this.components = components;
        this.costMedium = costMedium;
        this.costSmall = costSmall;
        this.description = description;
        this.name = name;
        this.recipeID = recipeID;
    }

    public int getRecipeid() {
        return recipeID;
    }

    @XmlElement
    public void setRecipeid(int recipeid) {
        recipeID = recipeid;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public int getCostSmall() {
        return costSmall;
    }

    @XmlElement
    public void setCostSmall(int costSmall) {
        this.costSmall = costSmall;
    }

    public int getCostMedium() {
        return costMedium;
    }

    @XmlElement
    public void setCostMedium(int costMedium) {
        this.costMedium = costMedium;
    }

    public int getCostBig() {
        return costBig;
    }

    @XmlElement
    public void setCostBig(int costBig) {
        this.costBig = costBig;
    }

    public ArrayList<RecipeComponents> getComponentsList() {
        return components;
    }

    @XmlElement
    public void setComponentsList(ArrayList<RecipeComponents> components) {
        this.components = components;
    }

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