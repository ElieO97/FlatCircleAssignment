package elieomatuku.workingapp;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.*;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity   {



    ArrayList<MovieModel> movieModelData = new ArrayList<>();  //Create an ArrayList of MovieModel instances
    ListView listView = null;      // Create a ListView instance



    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   // Use activity_main as the layout for this activiy

        ListView listView = (ListView)findViewById(R.id.listView);  // assigns listView to the ListView in the activity_main

        fetchData process = new fetchData(listView, this);   // creates an instance from the fetchData class (which extends AsyncTask)
                                                              // fetchData is used to retrieve data from the web api (swapi)
        process.execute();

    }

}







