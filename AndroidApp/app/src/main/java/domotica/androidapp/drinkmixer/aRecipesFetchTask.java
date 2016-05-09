package domotica.androidapp.drinkmixer;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Created by Alberto Flores on 4/17/2016.
 */
public class aRecipesFetchTask extends AsyncTask<Void, Void, List<RecipeDetails>> {

    private final String LOG_TAG = aRecipesFetchTask.class.getSimpleName();
    private XmlPullParserFactory xmlFactoryObject;

    @Override
    protected List<RecipeDetails> doInBackground(Void... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            //TODO:
            Uri.Builder buildURL = new Uri.Builder();
            buildURL.scheme("http")
                    .authority("10.33.24.56")
                    .appendPath("recipes")
                    .appendPath("all")
                    .build();

            // Create the request to OpenWeatherMap, and open the connection
            URL URL = new URL(buildURL.toString());
            urlConnection = (HttpURLConnection) URL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.v(LOG_TAG, "RECIPES: " + forecastJsonStr);
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><recipeDetailss><Recipe><componentsList><alcoholType>Rum</alcoholType><percentage>1</percentage></componentsList><componentsList><alcoholType>coconut milk</alcoholType><percentage>1</percentage></componentsList><componentsList><alcoholType>Pineapple juice</alcoholType><percentage>3</percentage></componentsList><costBig>30</costBig><costMedium>15</costMedium><costSmall>10</costSmall><description>sweet cocktail made with rum, coconut cream or coconut milk, and pineapple juice</description><name>PiÃ±a colada</name><recipeid>2</recipeid></Recipe><Recipe><componentsList><alcoholType>Rum</alcoholType><percentage>4</percentage></componentsList><componentsList><alcoholType>lime juice</alcoholType><percentage>3</percentage></componentsList><costBig>12</costBig><costMedium>8</costMedium><costSmall>3</costSmall><description>Tradit</description><name>Mojito</name><recipeid>8</recipeid></Recipe><Recipe><componentsList><alcoholType>Rum</alcoholType><percentage>5</percentage></componentsList><componentsList><alcoholType>Cola</alcoholType><percentage>12</percentage></componentsList><componentsList><alcoholType>lime juice</alcoholType><percentage>1</percentage></componentsList><costBig>13</costBig><costMedium>10</costMedium><costSmall>5</costSmall><description>cocktail made of cola, lime, and dark or light rum.</description><name>Cuba Libre</name><recipeid>11</recipeid></Recipe><Recipe><componentsList><alcoholType>Vodka</alcoholType><percentage>3</percentage></componentsList><componentsList><alcoholType>Tomato Juice</alcoholType><percentage>6</percentage></componentsList><componentsList><alcoholType>Lime Juice</alcoholType><percentage>1</percentage></componentsList><costBig>10</costBig><costMedium>7</costMedium><costSmall>3</costSmall><description>The name \"Bloody Mary\" is associated with a number of historical figures â€” particularly Qu</description><name>Bloody Mary</name><recipeid>15</recipeid></Recipe></recipeDetailss>";

            ReadXMLFile(xml);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return null;
    }

    public List<RecipeDetails> ReadXMLFile(String xml) {

        ArrayList<RecipeDetails> recipesList = new ArrayList<>();

        try {

            ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();

            RecipeDetails recipes;

            Log.v(LOG_TAG, "Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Recipe");

            Log.v(LOG_TAG, "------------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                Log.v(LOG_TAG, "\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    recipes = new RecipeDetails();

                    recipes.setRecipeid(Integer.parseInt(eElement.getElementsByTagName("recipeid").item(0).getTextContent()));
                    recipes.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    recipes.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    recipes.setCostSmall(Integer.parseInt(eElement.getElementsByTagName("costSmall").item(0).getTextContent()));
                    recipes.setCostMedium(Integer.parseInt(eElement.getElementsByTagName("costMedium").item(0).getTextContent()));
                    recipes.setCostBig(Integer.parseInt(eElement.getElementsByTagName("costBig").item(0).getTextContent()));
//                    Log.v(LOG_TAG,recipes.toString());
//                    Log.v(LOG_TAG, "    ");

                    recipesList.add(recipes);
                    Log.v(LOG_TAG,recipesList.toString());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipesList;
    }


}

