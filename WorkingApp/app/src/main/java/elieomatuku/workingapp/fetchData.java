package elieomatuku.workingapp;

/**
 * Created by elieomatuku on 12/14/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import  java.util.*;
import android.content.*;

import elieomatuku.workingapp.MainActivity;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by elieomatuku on 12/14/17.
 */

public class fetchData extends AsyncTask <Void, Void, Void> {

    String data = "";  // this String holds data coming from the api




    public static  String title = ""; // this String will hold the title for a specific film
    String director = "";   // this String will hold the director name for a specific film
    String producer = "";   // this String will hold the producer names for a specific film
    String release_Date = "";  // this String will hold the release date for a specific film
    String plot = "";   // this String will hold the plot  for a specific film
    String character = "";  // this String will hold characters for a specific film


    ArrayList<String> director1 = new ArrayList<String>();  // will hold all the director names
    ArrayList<String> producer1 = new ArrayList<String>();  // will hold all the producer names
    ArrayList<String> release_Date1 = new ArrayList<String>();  // will hold all the release dates
    ArrayList<String> title1 = new ArrayList<String>();   // will hold all the titles
    ArrayList<String> plot1 = new ArrayList<String>();   // will hold all the plots
    ArrayList<String[]>  characters = new ArrayList<>();   // will hold all the characters (their urls)




    ListView listMovie;  // this listView will be assigned the listView from the main activity
    CustomAdapter customAdapter;  // this instance will help us populate the listView with data (this class extends BaseAdapter)
    Context context;

    ArrayList<MovieModel> movieModelData = new ArrayList<>();  // an ArrayList of MovieModel instances. An instance of this class has a title, producers,
                                                                    // release date, plot and characters, etc for variables

    ProgressDialog pd;   // This is a ProgressDialog declaration




    public fetchData(ListView listMovie, Context context)  // The constructor has two parameters: a ListView and a context
    {


        this.listMovie = listMovie;

        this.context = context;


    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        pd = new ProgressDialog(context);  // Creates an instance of the class ProgressDialog with the context has its parameter
        pd.setMessage("loading");   // The progress dialog will show the text "loading"
        pd.show();  // Shows the progress dialog while data is being fetched from the Web API
    }






    @Override
    protected Void doInBackground(Void... params) {   //Data from the web api is being fetched in this function (method)



        try {

            URL url = new URL("https://swapi.co/api/films");   // Creates an URL instance with swapi url as the parameter, this specfies that I'm working the swapi
            //api

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  // All the data from the url are input in the bufferedReader

            String line = "";  // The String line will help me withdraw data from bufferedReader as Strings

            while (line != null)
            {

                line = bufferedReader.readLine();
                data = data + line;  // Concatenate all the String from the variable line in the data variable
                                    // This data variable will help me deal with JSONObjects



            }

            JSONObject jsonResponse =  new JSONObject(data);   // Passes the data variable as a parameter to the JSONObject named jsonResponse
                                                                // This allowed me to deal with the data inside the string data on value/key basis
                                                                // I can retrieve specific values according to a specific key

            JSONArray  jsonArray = (JSONArray) jsonResponse.get("results");   // This statement retrieves all the data got from the use of the key "results"
                                                                                // as a JSONArray called jsonArray.





            for (int i = 0; i < jsonArray.length(); i++) {

                /* Since I'm dealing with a JSONArray that means that it has a particular size. And each index represents a specific film (title, director,
                   plot, producer, release data) */

                title = jsonArray.getJSONObject(i).getString("title") ;  //This String variable takes in the title of film according to the index it deals with.
                title1.add(title);  // Then it is added in the ArrayList title1 (which is an ArrayList of String).

                director = jsonArray.getJSONObject(i).getString("director");
                director1.add("DIRECTOR\n" + director);

                /* The director and director1 apply the same thought process behind the title and title1 variables, takes a specific film director and add it
                  to the director1 ArrayList */


                producer = jsonArray.getJSONObject(i).getString("producer"); //Specific film producer is input in the producer variable
                producer1.add("PRODUCERS\n" + producer);  // And the producer variable is added to the ArrayList producer1

                release_Date = jsonArray.getJSONObject(i).getString("release_date");  //Specific film release date is input in the release_Date variable
                release_Date1.add(release_Date);   // And the release_Date variable is added to the ArrayList release_Date1






                plot = jsonArray.getJSONObject(i).getString("opening_crawl");  //Specific film plot is input in the plot variable
                plot1.add(plot);  // And the plot variable is added to the ArrayList plot1

                JSONArray p = jsonArray.getJSONObject(i).getJSONArray("characters"); // Retrieved all the character URLs and I put them in JSONArray named p (for
                                                                        // a specific movie)
                String[]  characterArray = new String[p.length()];   // Creates a String Array with the same length as the JSONArray p.

                for (int j = 0;  j < p.length(); ++j) {

                    character = (String) p.get(j);  // retrieve every url from the p JSONArray as strings
                    characterArray[j] = character;  // add that string to the characterArray array

                }

                characters.add(characterArray); // Add the characterArray to the arraylist characters. (and arraylist that takes String arrays)
                                                // Basically every component of this arraylist will contain character urls for a specific movie

            }





        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }




        return null;
    }
    @Override
    protected  void onPostExecute(Void aVoid)
    {

        super.onPostExecute(aVoid);
        pd.dismiss();  // Dismisses the progress dialog when all the data are loaded



        for (int pos = 0; pos < title1.size(); ++pos) {

            MovieModel movie = new MovieModel(title1.get(pos), director1.get(pos), release_Date1.get(pos),
                    producer1.get(pos), plot1.get(pos), "");

            movieModelData.add(movie);


            /* I created a class called MovieModel, a MovieModel instance contains a title, director, release data, producers, plot and characters.

             In this for loop, I created MovieModel instances according to the data that I got from the web api. Then I added each instance in
             a MovieModel ArrayList called movieModelData (that I will use for future processing) */
        }





        customAdapter = new CustomAdapter(context, movieModelData);  // This statement creates an instance of the class CustomAdapter which will hold the data
                                                                        // and the different layouts for the listView called listMovie


        listMovie.setAdapter(customAdapter);  // populates the listView

        listMovie.setOnItemClickListener(

                new AdapterView.OnItemClickListener()
                {
            @Override
            public void onItemClick(AdapterView <? > arg0, View arg1, int position,
                                    long id) {






                    ReturnCharacters charNames = new ReturnCharacters(context, characters.get(position), movieModelData,
                            position);
                    charNames.execute();

            }
        });

    }





  }


