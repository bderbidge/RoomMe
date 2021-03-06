package com.example.brandonderbidge.myapplication.sell;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brandonderbidge.myapplication.model.Contract;
import com.example.brandonderbidge.myapplication.model.FilterModel;
import com.example.brandonderbidge.myapplication.main.MainController;
import com.example.brandonderbidge.myapplication.R;
import com.example.brandonderbidge.myapplication.buy.FilterDialog;
import com.example.brandonderbidge.myapplication.model.Model;
import com.example.brandonderbidge.myapplication.model.MyData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by justinbrunner on 10/15/17.
 */

public class SellFragment extends Fragment {
    private String TAG = "SellFragment";
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    FloatingActionButton fabNew;
    private SellAdapter adapter;
    private MainController mainController;
    private static ArrayList<Contract> listOfContracts;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Apartments");

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell, container, false);
        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.sell_recycler_view);
        fabNew = view.findViewById(R.id.create_new_contract);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new SellAdapter(listOfContracts, this);
        recyclerView.setAdapter(adapter);

        loadSellContracts();
        getActivity().setTitle(R.string.sell_contracts);

        fabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.instance().setSelectedContract(null);
                NewContractFragment  newContractFragment = new NewContractFragment();
                newContractFragment.setMainController(mainController);

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.activity_main_fragment_container, newContractFragment, getString(R.string.TAG_new_contract))
                        .addToBackStack(getString(R.string.TAG_new_contract)).commit();
            }
        });

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        loadSellContracts();
                    }
                });

        return view;
    }

    @Override
    public void onResume() {

        loadSellContracts();
        Log.e("DEBUG", "onResume of HomeFragment");
        super.onResume();
    }

    public void loadSellContracts() {
        listOfContracts = new ArrayList<>();

        for (int i = 0; i < Model.instance().getCurrentUser().getMyContractsToSell().size(); i++) {
            Contract contract = Model.instance().getCurrentUser().getMyContractsToSell().get(i);
            listOfContracts.add(contract);
        }

        adapter.setDataSet(listOfContracts);
        adapter.notifyDataSetChanged();
    }

    public void showDialog() {
        Log.v(TAG, "Showing Dialog");

        if (getView() != null && getView().findViewById(R.id.filter_layout) == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FilterDialog newFragment = new FilterDialog();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                    .commit();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    public void editContract() {
        NewContractFragment  newContractFragment = new NewContractFragment();
        newContractFragment.setMainController(mainController);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.activity_main_fragment_container, newContractFragment, getString(R.string.TAG_new_contract))
                .addToBackStack(getString(R.string.TAG_new_contract)).commit();
    }
}
