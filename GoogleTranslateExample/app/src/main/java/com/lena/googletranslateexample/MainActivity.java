package com.lena.googletranslateexample;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            drawer.setScrimColor(Color.argb(30, 0, 0, 0));

            //drawer.setDrawerShadow(R.drawable.ic_toolbar_question, GravityCompat.START);
        }

        //toolbar.setNavigationIcon(R.drawable.ic_toolbar_navigation);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        toolbar.setNavigationIcon(R.drawable.ic_toolbar_navigation);

        ImageButton shareButton = (ImageButton) findViewById(R.id.imageview_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.adv));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.adv_inner));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
            }
        });
        /* without time line on top:   getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_about: {
                FragmentManager fragmentManager = getFragmentManager();
                AboutFragment  aboutFragment = new AboutFragment();
                aboutFragment.show(fragmentManager, getString(R.string.about_dialog_title));

                return true;
            }
            case R.id.action_buy: {
                Toast.makeText(this, R.string.buy_product_text, Toast.LENGTH_SHORT).show();

                return true;
            }
            case  R.id.action_exit: {
                FragmentManager fragmentManager = getFragmentManager();
                ExitFragment  exitFragment = new ExitFragment();
                exitFragment.show(fragmentManager, getString(R.string.exit_dialog_title));

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bio) {

        } else if (id == R.id.tags) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.settings) {
            /*getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();*/
            Intent intent = new Intent(this, ForSettingsFragmentActivity.class);
            startActivity(intent);
        } else if (id == R.id.translate) {
            Intent intent = new Intent(this, GoogleTranslateExampleActivity.class);
            startActivity(intent);
        }
        ///

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
