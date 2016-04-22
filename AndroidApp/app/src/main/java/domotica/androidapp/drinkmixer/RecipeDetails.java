package domotica.androidapp.drinkmixer;

import java.io.Serializable;

/**
 * Created by Alberto Flores on 4/22/2016.
 */
public class RecipeDetails {

    private int recipeID;
    private String name;
    private String description;
    private int costSmall;
    private int costMedium;
    private int costBig;
    //private ArrayList<RecipeComponents> components;

    public RecipeDetails() {
    }

    public RecipeDetails(int costBig, int costMedium, int costSmall, String description, String name, int recipeID) {
        this.costBig = costBig;
        //this.components = components;
        this.costMedium = costMedium;
        this.costSmall = costSmall;
        this.description = description;
        this.name = name;
        this.recipeID = recipeID;
    }

    public int getRecipeid() {
        return recipeID;
    }

    public void setRecipeid(int recipeid) {
        recipeID = recipeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCostSmall() {
        return costSmall;
    }

    public void setCostSmall(int costSmall) {
        this.costSmall = costSmall;
    }

    public int getCostMedium() {
        return costMedium;
    }

    public void setCostMedium(int costMedium) {
        this.costMedium = costMedium;
    }

    public int getCostBig() {
        return costBig;
    }

    public void setCostBig(int costBig) {
        this.costBig = costBig;
    }

//    public ArrayList<RecipeComponents> getComponentsList() {
//        return components;
//    }
//
//    public void setComponentsList(ArrayList<RecipeComponents> components) {
//        this.components = components;
//    }

    @Override
    public String toString() {
        return "RecipeDetails{" +
                "recipeID=" + recipeID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", costSmall=" + costSmall +
                ", costMedium=" + costMedium +
                ", costBig=" + costBig +
//                ", componentsList=" + components +
                '}';
    }
}