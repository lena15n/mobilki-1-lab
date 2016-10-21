package com.lena.googletranslateexample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GoogleTranslateExampleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String[] languages;
    boolean[] downloadedLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gt_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.gt_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        languages = getResources().getStringArray(R.array.languages);
        downloadedLanguages = new boolean[]{false, true, false};

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, R.id.item_layout, languages);
        Spinner fromSpinner = (Spinner) findViewById(R.id.from_spinner);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                //debug
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Translate from " + languages[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        fromSpinner.setAdapter(customArrayAdapter);

        Spinner toSpinner = (Spinner) findViewById(R.id.to_spinner);
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                //debug
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Translate to " + languages[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        toSpinner.setAdapter(customArrayAdapter);
    }


    public class CustomArrayAdapter extends ArrayAdapter<String> {
        CustomArrayAdapter(Context context, int textViewResourceId,
                           String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomText(position, convertView, parent);
        }

        private View getCustomText(int position, View convertView,
                                   ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.gt_spinner_item, parent, false);
            TextView label = (TextView) row.findViewById(R.id.gt_item_textview);
            label.setText(languages[position]);
            label.setTextColor(getColor(R.color.gt_colorPrimary));


            return label;
        }

        private View getCustomView(int position, View convertView,
                                   ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.gt_spinner_item, parent, false);
            TextView label = (TextView) row.findViewById(R.id.gt_item_textview);
            label.setText(languages[position]);

            ImageView icon = (ImageView) row.findViewById(R.id.gt_item_icon);

            if (!downloadedLanguages[position]) {
                icon.setImageResource(R.drawable.ic_file_download_black_24px);
            } else {
                icon.setImageResource(R.color.gt_colorItems);
            }
            return row;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_phrasebook) {
            // Handle the camera action
        } else if (id == R.id.nav_sms) {

        } else if (id == R.id.nav_offline) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
