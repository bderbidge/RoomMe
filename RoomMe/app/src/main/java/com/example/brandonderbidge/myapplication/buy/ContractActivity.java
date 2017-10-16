package com.example.brandonderbidge.myapplication.buy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.brandonderbidge.myapplication.Model;
import com.example.brandonderbidge.myapplication.R;

public class ContractActivity extends AppCompatActivity {
    TextView apartmentName ;
    TextView costOfRent ;
    TextView cityState ;
    TextView apartmentType;
    TextView sellerName;
    TextView sellBy;
    TextView notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        apartmentName = (TextView)findViewById(R.id.apartmentName);
        costOfRent = (TextView)findViewById(R.id.costOfRent);
        cityState = (TextView)findViewById(R.id.cityState);
        apartmentType = (TextView)findViewById(R.id.genderRoomType);
        sellerName = (TextView)findViewById(R.id.sellerName);
        sellBy = (TextView)findViewById(R.id.sellBy);
        notes = (TextView)findViewById(R.id.notes);



        notes.setText(Model.instance().getSelectedContract().getAdditionalNotes());
        sellBy.setText(Model.instance().getSelectedContract().getSellBy());
        sellerName.setText(Model.instance().getSelectedContract().getSellerName());
        apartmentName.setText(Model.instance().getSelectedContract().getApartmentName());

        if(Model.instance().getSelectedContract().getMaritalStatus().equals("Married"))
            apartmentType.setText(Model.instance().getSelectedContract().getMaritalStatus());
        else
            apartmentType.setText(Model.instance().getSelectedContract().getMaritalStatus() + " " +
            Model.instance().getSelectedContract().getSex());

        costOfRent.setText("$" + Model.instance().getSelectedContract().getPrice().toString());
        cityState.setText(Model.instance().getSelectedContract().getCity() + " " + Model.instance().getSelectedContract().getState());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}