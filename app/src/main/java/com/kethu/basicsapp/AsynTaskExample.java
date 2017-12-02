package com.kethu.basicsapp;

import android.os.AsyncTask;
import android.util.Log;

import com.kethu.basicsapp.listners.Listner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by satya on 02-Dec-17.
 */

public class AsynTaskExample extends AsyncTask<Void, Void, JSONObject> {
    private static final String BASE_URL = "http://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    Listner mListner;

    public AsynTaskExample(Listner listner) {
        mListner = listner;

    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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

            JSONObject jsonObject = new JSONObject(buffer.toString());
            Log.e("AsyncTASK", "JSON DATA" + jsonObject);
            return jsonObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        mListner.getJSONDataFromServer(jsonObject);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
