package com.android.learn.learnandroid.gson;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.learn.learnandroid.R;
import com.android.learn.learnandroid.listener.OnListItemClickListener;

/**
 * Created by sauyee.wong on 18/2/2016.
 */
public class MainFragment extends ListFragment {

  private OnListItemClickListener mItemClickListener;

  public MainFragment() {
  }

  @TargetApi(23)
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof Activity) {
      mItemClickListener = (OnListItemClickListener) context;
    }
  }

  @SuppressWarnings("depreciation")
  @Override
  public void onAttach(Activity context) {
    super.onAttach(context);
    if (Build.VERSION.SDK_INT < 23) {
      mItemClickListener = (OnListItemClickListener) context;
    }
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    final String[] items = getResources().getStringArray(R.array.main_items);
    final ArrayAdapter<String> adapter = new ArrayAdapter<java.lang.String>(
        getActivity(), android.R.layout.simple_list_item_1, items);
    setListAdapter(adapter);
  }

  @Override
  public void onListItemClick(ListView list, View view, int position, long id) {
    if (mItemClickListener != null) {
      mItemClickListener.onListItemClick(position);
    }
  }
}
