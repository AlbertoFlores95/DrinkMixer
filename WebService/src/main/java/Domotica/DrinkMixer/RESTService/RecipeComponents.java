package Domotica.DrinkMixer.RESTService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * POJO of the components of the recipe
 *
 * @author Alberto Flores A00759273 Alberto@Flores.cf
 * @author Fernando Narvaez A00759294 fernandonarvy69@gmail.com
 * @author Osmar Hernandez OsmarHernadezs@gmailcom
 * @author Jose Enriquez A00759279 joseaenriqueza@gmail.com
 * @version     1.0
 * @since       2016-04-12
 */
@XmlRootElement(name = "component")
public class RecipeComponents implements Serializable {

    /**
     * Liquid that the recipe will contain.
     */
    private String alcoholType;
    /**
     * Percentage of the liquid in the recipe
     */
    private int percentage;

    /**
     * Default constructor
     * <p>
     * Constructor that takes no parameters.
     */
    public RecipeComponents() {
    }

    /**
     * Constructor with parameters
     * <p>
     * Constructor that takes all the parameters of the object.
     * <p>
     * @param alcoholType Liquid that the recipe will contain.
     * @param percentage Percentage of the liquid in the recipe
     */
    public RecipeComponents(String alcoholType, int percentage) {
        this.alcoholType = alcoholType;
        this.percentage = percentage;
    }

    /**
     * Getter which gets the alcoholType.
     */
    public String getAlcoholType() {
        return alcoholType;
    }

    /**
     * Setter which sets the components of the recipe.
     * @param alcoholType Liquid that the recipe will contain.
     */
    @XmlElement
    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }

    /**
     * Getter which gets the percentage.
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Setter which sets the components of the recipe.
     * @param percentage Percentage of the liquid in the recipe
     */
    @XmlElement
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "RecipeComponents{" +
                "alcoholType='" + alcoholType + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}