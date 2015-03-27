package com.sh.bite;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bite.R;
import com.sh.entities.RestaurantByDistance;
import com.sh.helpers.App;

import java.util.ArrayList;
import java.util.List;

public class ChangeRestaurant extends Activity {
    static App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_restaurant);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivityForResult(i, 1);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_change_restaurant, container, false);
            List<RestaurantByDistance> listData = new ArrayList<RestaurantByDistance>();
            listData = getRestaurantList();
            CustomAdapter adapter = new CustomAdapter(this.getActivity(), listData);
            ListView listView = (ListView) rootView.findViewById(R.id.listView);
            listView.setAdapter(adapter);
            Context ctx = getActivity().getApplicationContext();
            app = (App)ctx;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view,
                                        int pos, long id) {
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    app.selectedRestaurantName = (String) textView.getText();
//                    toast((String) textView.getText());

                }
            });
            return rootView;
        }
        private void toast(String text) {
            Toast.makeText(getActivity(),
                    String.format("Item clicked: %s", text), Toast.LENGTH_SHORT)
                    .show();
        }

        private List<RestaurantByDistance> getRestaurantList() {
            List<RestaurantByDistance> listData = new ArrayList<RestaurantByDistance>();
            RestaurantByDistance listItem = new RestaurantByDistance();
            listItem.setId(1);
            listItem.setDistanceFromCurrentLocation(0.01);
            listItem.setName("Xanh");
            listData.add(listItem); listItem = new RestaurantByDistance();
            listItem.setId(2);
            listItem.setDistanceFromCurrentLocation(0.01);
            listItem.setName("Oren's Hummus Shop");
            listData.add(listItem); listItem = new RestaurantByDistance();
            listItem.setId(3);
            listItem.setDistanceFromCurrentLocation(0.6);
            listItem.setName("Sakkoon");
            listData.add(listItem); listItem = new RestaurantByDistance();
            listItem.setId(4);
            listItem.setDistanceFromCurrentLocation(0.7);
            listItem.setName("Pho");
            listData.add(listItem);
            listItem = new RestaurantByDistance();
            listItem.setId(5);
            listItem.setDistanceFromCurrentLocation(0.5);
            listItem.setName("AsianBox");
            listData.add(listItem);
            listItem = new RestaurantByDistance();
            listItem.setId(6);
            listItem.setDistanceFromCurrentLocation(0.8);
            listItem.setName("Trapioca Express");
            listData.add(listItem);
            listItem = new RestaurantByDistance();
            listItem.setId(7);
            listItem.setDistanceFromCurrentLocation(0.8);
            listItem.setName("Verde Tea");
            listData.add(listItem);
            return listData;
        }
    }
}
