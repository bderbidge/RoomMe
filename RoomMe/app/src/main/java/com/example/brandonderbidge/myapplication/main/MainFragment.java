package com.example.brandonderbidge.myapplication.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.brandonderbidge.myapplication.R;
import com.example.brandonderbidge.myapplication.buy.BuyFragment;
import com.example.brandonderbidge.myapplication.sell.SellFragment;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    private static final String TAG = "SellFragment";
    private MainController mainController;
    private BottomNavigationView bnv;

    public MainFragment() {}

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        bnv = view.findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bnv);

        bnv = view.findViewById(R.id.bottom_navigation);

        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.navigation_buy:
                                changeNavItemSelected("buy");
                                break;
                            case R.id.navigation_sell:
                                Log.v(TAG, "Switching to Sell Activity");
                                changeNavItemSelected("sell");
                                break;
                            case R.id.navigation_messages:
                                Log.v(TAG, "Switching to Messages Activity");
                                changeNavItemSelected("messages");
                                break;
                            case R.id.navigation_more:
                                Log.v(TAG, "Switching to More Activity");
                                changeNavItemSelected("more");
                                break;
                        }
                        return true;
                    }
                });

        changeNavItemSelected("buy");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       super.onViewCreated(view, savedInstanceState);

        /*String username = "",
                password = "";

        Bundle args = getArguments();

        if (args != null) { //data was previously in fields, probably from a previous screen orientation
            username = args.getString(getString(R.string.EXTRA_USERNAME));
            password = args.getString(getString(R.string.EXTRA_PASSWORD));

            //set fields and check button based on what was passed in
            usernameText.setText(username);
            passwordText.setText(password);

        }

        loginController = new LoginController((LoginActivity) getActivity());

        loginController.validData(false, username, password, null, null);*/
    }

    public void changeNavItemSelected(String nav) {
        if (bnv == null) {
            return;
        }

        Menu menu = bnv.getMenu();

        menu.findItem(R.id.navigation_buy).setIcon(
                new IconDrawable(getContext(), FontAwesomeIcons.fa_home)
                        .colorRes(nav.equals("buy") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        menu.findItem(R.id.navigation_sell).setIcon(
                new IconDrawable(getContext(), FontAwesomeIcons.fa_usd)
                        .colorRes(nav.equals("sell") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        menu.findItem(R.id.navigation_messages).setIcon(
                new IconDrawable(getContext(), FontAwesomeIcons.fa_comment)
                        .colorRes(nav.equals("messages") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        menu.findItem(R.id.navigation_more).setIcon(
                new IconDrawable(getContext(), FontAwesomeIcons.fa_bars)
                        .colorRes(nav.equals("more") ? R.color.colorPrimary : R.color.greyedText)
                        .sizeDp(14));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (nav.equals("buy")) {
            BuyFragment buyFrag = new BuyFragment();
            ft.replace(R.id.fragment_main_content, buyFrag, getString(R.string.TAG_buy)).commit();
        } if (nav.equals("sell")) {
            SellFragment sellFragment = new SellFragment();
            ft.replace(R.id.fragment_main_content, sellFragment, getString(R.string.sell));
        }
    }
}
