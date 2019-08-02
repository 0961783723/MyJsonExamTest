package com.example.myjsonexamtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvWeather;
    TextView tvMain;
    List<weather> weatherList;
    List<main> mainList;

    String json ="{  \n" +
            "   \"coord\":{  \n" +
            "      \"lon\":139,\n" +
            "      \"lat\":35\n" +
            "   },\n" +
            "   \"weather\":[  \n" +
            "      {  \n" +
            "         \"id\":800,\n" +
            "         \"main\":\"Clear\",\n" +
            "         \"description\":\"clear sky\",\n" +
            "         \"icon\":\"01n\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"base\":\"stations\",\n" +
            "   \"main\":{  \n" +
            "      \"temp\":289.92,\n" +
            "      \"pressure\":1009,\n" +
            "      \"humidity\":92,\n" +
            "      \"temp_min\":288.71,\n" +
            "      \"temp_max\":290.93\n" +
            "   },\n" +
            "   \"wind\":{  \n" +
            "      \"speed\":0.47,\n" +
            "      \"deg\":107.538\n" +
            "   },\n" +
            "   \"clouds\":{  \n" +
            "      \"all\":2\n" +
            "   },\n" +
            "   \"dt\":1560350192,\n" +
            "   \"sys\":{  \n" +
            "      \"type\":3,\n" +
            "      \"id\":2019346,\n" +
            "      \"message\":0.0065,\n" +
            "      \"country\":\"JP\",\n" +
            "      \"sunrise\":1560281377,\n" +
            "      \"sunset\":1560333478\n" +
            "   },\n" +
            "   \"timezone\":32400,\n" +
            "   \"id\":1851632,\n" +
            "   \"name\":\"Shuzenji\",\n" +
            "   \"cod\":200\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWeather = findViewById(R.id.tvWeather);
        tvMain = findViewById(R.id.tvMain);


        weatherList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray jsonArray = object.getJSONArray("weather");

            for (int i = 0 ; i<jsonArray.length() ; i++){
                JSONObject objectWeather = jsonArray.getJSONObject(i);

                String id = objectWeather.getString("id");
                String main = objectWeather.getString("main");
                String description = objectWeather.getString("description");
                String icon = objectWeather.getString("icon");

                weatherList.add(new weather(id,main,description,icon));
            }
            tvWeather.setText( weatherList.size() +"\n"+ "ID: " +
                     weatherList.get(0).getId() +"\n"+ "Main: " +
                    weatherList.get(0).getMain() +"\n"+ "Des: " +
                    weatherList.get(0).getDescription() +"\n"+ "Icon: " +
                    weatherList.get(0).getIcon() );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject objectMain = jsonObject.getJSONObject("main");

            String temp = objectMain.getString("temp");
            String pressure = objectMain.getString("pressure");
            String humidity = objectMain.getString("humidity");
            String temp_min = objectMain.getString("temp_min");
            String temp_max = objectMain.getString("temp_max");

            mainList.add(new main(temp,pressure,humidity,temp_min,temp_max));
            tvMain.setText(mainList.size() +"\n"+
                    "Temp: " + mainList.get(0).getTemp() +"\n"+
                    "Pressusre: " + mainList.get(0).getPressure() +"\n"+
                    "Humidity: "+ mainList.get(0).getHumidity() +"\n"+
                    "Temp_min: "+ mainList.get(0).getTemp_min() +"\n"+
                    "Temp_max: " + mainList.get(0).getTemp_max());

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
