package elieomatuku.workingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elieomatuku on 12/18/17.
 */

public class CustomAdapter extends BaseAdapter {  //This class will populate the different layouts from the customlayout xml file

    /* Declare four TextViews, an ImageView that will be used to populate the customLayout file */

    TextView textView_title  = null;
    TextView textView_director  = null;
    TextView textView_release  = null;
    TextView textView_producer  = null;
    ImageView image = null;
    Context context = null;


    ArrayList<MovieModel> movieModelData = new ArrayList<>();  // creates an ArrayList of MovieModel instances
    //fetchData process = new fetchData();

    public CustomAdapter(Context m, ArrayList<MovieModel> model)
    {
        context = m;
         movieModelData = model;



    }

    @Override
    public int getCount() {


        return movieModelData.size();  // get the size of the movieModelData ArrayList, and make a listView with that size of item
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {


        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        /* This function will populate the different layouts of customLayout with data in the ArrayList movieModelData*/

        view = LayoutInflater.from(context).inflate(R.layout.customlayout,null);
        MovieModel movie = movieModelData.get(position);

        //  ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        textView_title  = (TextView)view.findViewById(R.id.textView);
        textView_director  = (TextView)view.findViewById(R.id.textView3);
        textView_release = (TextView)view.findViewById(R.id.textView2);
        textView_producer = (TextView)view.findViewById(R.id.textView4);
        image = (ImageView)view.findViewById(R.id.imageView);

        //   imageView.setImageResource(IMAGES[position]);

        textView_title.setText(movie.getTitle());
        textView_director.setText(movie.getDirector());
        textView_release.setText(movie.getReleaseDate());
        textView_producer.setText(movie.getProducer());

        switch (position) {  /* This switch statement helps uploading pictures to each iem*/

            case 0:

             image.setImageResource(R.drawable.anewhope);
                break;


            case 1:
                image.setImageResource(R.drawable.attackoftheclones);
                break;

           case 2:
                image.setImageResource(R.drawable.thephantommenace);
                break;

            case 3:
                image.setImageResource(R.drawable.revengeofthesith);
                break;

            case 4:
                image.setImageResource(R.drawable.returnofthejedi);
                break;

            case 5:
                image.setImageResource(R.drawable.theempirestrack);
                break;

            case 6:
                image.setImageResource(R.drawable.theforceawakens);
                break;




             default:
                 image.setImageResource(R.drawable.starwars);


        }

        return view;
    }
}

