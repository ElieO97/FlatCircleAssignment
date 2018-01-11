package elieomatuku.workingapp;

/**
 * Created by elieomatuku on 12/19/17.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import android.app.Activity;
import  android.content.Intent;

public class ReturnCharacters   extends AsyncTask<Void, Void, Void>  {


    private String[] character;  // This variable will hold the character urls from a specific film
    String character1;
    private String data = "";  // This variable will be used in the retrieval of data from the wapi
    private String names;
    ArrayList<String> characters  = new ArrayList<String>();
    ArrayList<String> names1 = new ArrayList<String>();
    ArrayList<MovieModel> movieModelData = new ArrayList<>();  // Will be used to append character names to a specific film
    ProgressDialog pd;   // Progress Dialog declaration
    int position;   // referes to the listView item's position


    Context m;

    public ReturnCharacters(Context m, String[] character, ArrayList<MovieModel> movieModelData, int position)
    {

        /*The constructor takes as parameters, a context, an array of strings (that will holds the character urls), an arraylist of MovieModel
        that will append the character names to a particular film and the position of the item clicked
         */
        this.m = m;
        this.character = character;
        this.movieModelData = movieModelData;


       this.position = position;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(m);
        pd.setMessage("loading");  // progress dialog being shown while data is being retrieved
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... params) {



       StringBuilder name = new StringBuilder() ;  // I used a StringBuilder because I could append Strings to it.


       for (int i = 0; i < character.length; i++)  // This loop counts till the number of characters for a film is reached.
       {




           if (i == (character.length - 1))
             name.append( new ReturnCharacters(m, character, movieModelData, position).returnValue(character[i]) +
                     "."); // the method returnValue to return the name of a particular character, using the url of that character. (this if statement targets
                            // the last character. So that a period can be put next to it.
            else
                name.append(new ReturnCharacters(m, character, movieModelData, position).returnValue(character[i]) +
                        ", ");  // the method returnValue to return the name of a particular character, using the url of that character.



       }

        String names = name.toString();

        movieModelData.get(position).setCharacter(names);




        return null;
    }

    @Override
    protected  void onPostExecute(Void aVoid)
    {


        super.onPostExecute(aVoid);



        Intent myIntent = new Intent(m, SecondSreen.class);


        myIntent.putExtra("title", movieModelData.get(position).getTitle() );
        myIntent.putExtra("Release", movieModelData.get(position).getReleaseDate());
        myIntent.putExtra("plot", movieModelData.get(position).getPlot());
        myIntent.putExtra("character", movieModelData.get(position).getCharacter());


        pd.dismiss();
        m.startActivity(myIntent);





    }


    public String returnValue (String urlString)   // This method returns the name of a character using its url
    {
        InputStream inputStream;

        try {




            URL url = new URL(urlString);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

             inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while (line != null) {

                line = bufferedReader.readLine();
                data = data + line;


            }

            JSONObject jsonResponse = new JSONObject(data);


            character1 =  jsonResponse.getString("name");
            names1.add(character1);
         //   movieModelData.get(position).setCharacter(character1);



            return  character1;




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





        return  "";

    }







}