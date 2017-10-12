package com.example.brandonderbidge.myapplication.buy;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.brandonderbidge.myapplication.Contract;
import com.example.brandonderbidge.myapplication.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;
import java.util.Map;

public class BuyActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Contract> listOfContracts;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Iconify.with(new FontAwesomeModule());


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        loadFakeData(null);

        adapter = new CustomAdapter(listOfContracts);
        recyclerView.setAdapter(adapter);

        changeNavItemSelected("buy");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.navigation_buy:
                                createToast("Buy Item Selected", Toast.LENGTH_SHORT);
                                changeNavItemSelected("buy");
                                break;
                            case R.id.navigation_sell:
                                createToast("Sell Item Selected", Toast.LENGTH_SHORT);
                                changeNavItemSelected("sell");
                                break;
                            case R.id.navigation_messages:
                                createToast("Messages Item Selected", Toast.LENGTH_SHORT);
                                changeNavItemSelected("messages");
                                break;
                            case R.id.navigation_more:
                                createToast("More Item Selected", Toast.LENGTH_SHORT);
                                changeNavItemSelected("more");
                        }
                        return true;
                    }
                });

    }


    private void loadFakeData(Map<String, Object> filters) {
        listOfContracts = new ArrayList<>();
        boolean add = true;

        for (int i = 0; i < MyData.nameArray.length; i++) {
            if (filters != null) {
                if (filters.containsKey("sex") && !MyData.sexStatusArray[i].equals(filters.get("sex"))) {
                    add = false;
                } else if (filters.containsKey("low price") && false == true) {
                    add = false;
                } else if (filters.containsKey("high price") && false==true) {
                    add = false;
                } else if (filters.containsKey("martial status") && false == true) {
                    add = false;
                }
            }

            if (add) {
                listOfContracts.add(new Contract(
                    MyData.nameArray[i],
                    MyData.priceArray[i],
                    MyData.cityArray[i],
                    MyData.stateArray[i],
                    MyData.maritalStatusArray[i],
                    MyData.sexStatusArray[i]
                ));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        return true;
    }


    public void createToast(String message, int toastLength) {
        Toast.makeText(getBaseContext(), message, toastLength).show();
    }

    public void showProgressWheel(boolean show) {
        /*if (findViewById(R.id.progressBar) != null) {
            if (show) {
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        }*/
    }

    public void changeNavItemSelected(String nav) {
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        if (bnv == null) {
            return;
        }

        Menu menu = bnv.getMenu();

        menu.findItem(R.id.navigation_buy).setIcon(
                new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_home)
                        .colorRes(nav.equals("buy") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        menu.findItem(R.id.navigation_sell).setIcon(
                new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_usd)
                        .colorRes(nav.equals("sell") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        menu.findItem(R.id.navigation_messages).setIcon(
                new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_comment)
                        .colorRes(nav.equals("messages") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        menu.findItem(R.id.navigation_more).setIcon(
                new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_bars)
                        .colorRes(nav.equals("more") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));
    }




}
