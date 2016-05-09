package domotica.androidapp.drinkmixer;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecipeCardMainActivityAdapter amAdapter;
    ArrayList<RecipeDetails> recipesList = new ArrayList<>();
    List<RecipeDetails> reccc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String[] ForecastArray = new String[]{" Alberto - Clear - 00/16", "Tuesday - Rain - 05/13",
                " Wednesday - sunny - 01/13", "Thursday - Shinny - 00/00", "Friday - Snow - 01/23",
                " Monday - Clear - 00/16", "Tuesday - Rain - 05/13", " Wednesday - sunny - 01/13",
                "Thursday - Shinny - 00/00", "Friday - Snow - 01/23", " Monday - Clear - 00/16",
                "Tuesday - Rain - 05/13", " Wednesday - sunny - 01/13", "Thursday - Shinny - 00/00",
                "Friday - Snow - 01/23"};

        RecipesFetchTask fetchRecipesTask = new RecipesFetchTask();
        fetchRecipesTask.execute();



        RecyclerView rv = (RecyclerView)findViewById(R.id.listView_recipeCards);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(llm);
        //RecipeCardMainActivityAdapter adapter = new RecipeCardMainActivityAdapter (MainActivity.this,reccc);
        amAdapter = new RecipeCardMainActivityAdapter(MainActivity.this,reccc);
        rv.setAdapter(amAdapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class RecipesFetchTask extends AsyncTask<Void, Void, List<RecipeDetails>> {

        private final String LOG_TAG = RecipesFetchTask.class.getSimpleName();
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
                        .authority("192.168.0.7")
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

                reccc = ReadXMLFile(xml);

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
                        Log.v(LOG_TAG, recipesList.toString());

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return recipesList;
        }


    }


}
