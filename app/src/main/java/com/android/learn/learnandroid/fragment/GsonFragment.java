package com.android.learn.learnandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.learn.learnandroid.R;
import com.android.learn.learnandroid.gson.Car;
import com.android.learn.learnandroid.gson.Person;
import com.google.gson.Gson;

/**
 * Created by sauyee.wong on 18/2/2016.
 */
public class GsonFragment extends Fragment {

  public GsonFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (container == null) {
      return null;
    }
    View view = inflater.inflate(R.layout.content_gson, container, false);
    printGsonInfo();
    return view;
  }

  private void printGsonInfo() {
    Gson gson = new Gson();
    Car audi = new Car("Audi", "A4", 1.8, false);
    Car skoda = new Car("Skoda", "Octavia", 2.0, true);
    Car[] cars = { audi, skoda };
    Person johnDoe = new Person("John", "Doe", 245987453, 35, cars);

    String jsonOutput = gson.toJson(johnDoe);
    _Debug("gson output: (" + jsonOutput);

    Person jsonParse = gson.fromJson(jsonOutput, Person.class);
    String jsonParseStr = jsonParse.toString();
    _Debug("person output: (" + jsonParseStr);
  }

  private static void _Debug(String str) {
    Log.d("widget", str);
  }
}
