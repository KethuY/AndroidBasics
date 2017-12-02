package com.kethu.basicsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.kethu.basicsapp.models.Player;
import com.kethu.basicsapp.models.Tennis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Listner/* implements View.OnClickListener */ {
    private static final String TAG = "MainActivity";//MainActivity.class.getSimpleName();
    private static final String BASE_URL = "http://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";

    @BindView(R.id.hello_world_tv)
    TextView mHelloWorld;
    @BindView(R.id.count_tv)
    TextView mCount;
    @BindView(R.id.click_me_btn)
    TextView mClickMe;
    private int mIntCount = 0;
    @BindView(R.id.toggleButton)
    ToggleButton mToggleButton;
    @BindView(R.id.checkBox)
    CheckBox mCheckBox;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.spinner)
    Spinner mNamesSpin;
    ArrayList<String> mNames;
    Listner mListner;
    Tennis mTennis;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mListner = this;
        mNames = new ArrayList<>();
     /*   mProgressDialog=new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Please wait data is loading....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
*/
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AsynTaskExample asynTaskExample = new AsynTaskExample(mListner);
                asynTaskExample.execute();

                // new  MyAsyncTask().execute(BASE_URL);
            }
        });


        mNamesSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedName = (String) parent.getSelectedItem();
                ToastHelper.showToast(MainActivity.this, "Selected Item " + selectedName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       /* mNamesSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdanpterView<?> parent, View view, int position, long id) {
              //  parent.getId()


            }
        });*/

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ToastHelper.showToast(MainActivity.this, "Checkbox isChecked " + isChecked);
               /* ToastHelper toastHelper=new ToastHelper();
                toastHelper.showToastH();*/
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
                ToastHelper.showToast(MainActivity.this, "Gender " + radioButton.getText());

            }
        });

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mHelloWorld.setText("Hello Wolrd ToggleButton Status " + isChecked);

            }
        });

        //  mClickMe.setOnClickListener(this);
        //Inline click method
      /*  mClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mIntCount++;
                mCount.setText(String.valueOf(mIntCount));
            }
        });*/


    }


    public void clickOnMe(View view) {
        mIntCount++;
        mCount.setText(String.valueOf(mIntCount));
    }

    @Override
    public void getJSONDataFromServer(JSONObject jsonObject) {
        try {
            if (null != jsonObject) {
                mTennis = new Tennis();

                if (jsonObject.has("status") && jsonObject.getString("status") != null) {
                    String statusStr = jsonObject.getString("status");
                    boolean status = Boolean.parseBoolean(statusStr);
                    mTennis.setStatus(status);
                }

                if (jsonObject.has("message") && jsonObject.getString("message") != null) {
                    String message = jsonObject.getString("message");
                    mTennis.setMessage(message);
                }

                ArrayList<Player> players = new ArrayList<>();
                if (jsonObject.has("data") && jsonObject.getJSONArray("data") != null) {

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (jsonArray.length() > 0) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Player player = new Player();
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            if (jsonObj.has("id") && jsonObj.getString("id") != null) {
                                String id = jsonObj.getString("id");
                                player.setId(id);
                            }

                            if (jsonObj.has("name") && jsonObj.getString("name") != null) {
                                String id = jsonObj.getString("name");
                                player.setName(id);
                                mNames.add(id);
                            }

                            if (jsonObj.has("country") && jsonObj.getString("country") != null) {
                                String id = jsonObj.getString("country");
                                player.setCountry(id);
                            }

                            if (jsonObj.has("city") && jsonObj.getString("city") != null) {
                                String id = jsonObj.getString("city");
                                player.setCity(id);
                            }

                            players.add(player);
                        }

                        mTennis.setPlayers(players);
                    }
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mNames.size() > 0) {
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_text, mNames);
            mNamesSpin.setAdapter(spinnerAdapter);
            mNamesSpin.setSelection(1);
        }

        //    mProgressDialog.dismiss();
    }

   /* @Override
    public void onClick(View v) {
      //  switch ()

        mIntCount++;
        mCount.setText(String.valueOf(mIntCount));
    }*/
}
