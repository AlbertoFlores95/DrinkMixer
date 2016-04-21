package Domotica.DrinkMixer.RESTService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Alberto Flores on 4/12/2016.
 */
@XmlRootElement(name = "component")
public class RecipeComponents implements Serializable {

    private String alcoholType;
    private int percentage;

    public RecipeComponents() {
    }

    public RecipeComponents(String alcoholType, int percentage) {
        this.alcoholType = alcoholType;
        this.percentage = percentage;
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    @XmlElement
    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }

    public int getPercentage() {
        return percentage;
    }

    @XmlElement
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "RecipeComponents{" +
                "alcoholType='" + alcoholType + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}