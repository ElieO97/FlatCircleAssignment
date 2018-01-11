import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import elieomatuku.workingapp.MainActivity;


/**
 * Created by elieomatuku on 12/14/17.
 */

public class fetchData extends AsyncTask <Void, Void, Void> {

    String data = "";
    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL("https://swapi.co/");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while (line != null)
            {

                line = bufferedReader.readLine();
                data = data + line;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected  void onPostExecute(Void aVoid)
    {


        super.onPostExecute(aVoid);

        MainActivity.data.setText((CharSequence) this.data);
    }
}

