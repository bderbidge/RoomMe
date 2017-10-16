package com.example.brandonderbidge.myapplication.buy;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.brandonderbidge.myapplication.FilterModel;
import com.example.brandonderbidge.myapplication.R;

/**
 * Created by justinbrunner on 10/12/17.
 */

public class FilterDialog extends DialogFragment {
    ConstraintLayout filterLayout;
    ImageButton closeBtn;
    EditText priceLow;
    EditText priceHigh;
    Button maleBtn;
    Button femaleBtn;
    Button singleBtn;
    Button marriedBtn;
    LinearLayout selectSexContainer;
    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_buy, container, false);

        filterLayout = view.findViewById(R.id.filter_layout);
        closeBtn = view.findViewById(R.id.close_btn);
        priceLow = view.findViewById(R.id.price_low);
        priceHigh = view.findViewById(R.id.price_high);
        maleBtn = view.findViewById(R.id.male_btn);
        femaleBtn = view.findViewById(R.id.female_btn);
        singleBtn = view.findViewById(R.id.single);
        marriedBtn = view.findViewById(R.id.married);
        selectSexContainer = view.findViewById(R.id.select_sex_container);

        filterLayout.setBackground(new ColorDrawable(Color.WHITE));

        String priceLowText = FilterModel.getInstance().getPriceLow() == null ? "" : Double.toString(FilterModel.getInstance().getPriceLow());
        priceLow.setText(priceLowText);

        String priceHighText = FilterModel.getInstance().getPriceHigh() == null ? "" : Double.toString(FilterModel.getInstance().getPriceHigh());
        priceHigh.setText(priceHighText);

        if (FilterModel.getInstance().getSex() != null) {
            if (FilterModel.getInstance().getSex().equalsIgnoreCase("male")) {
                changeBtnStyle(maleBtn, R.drawable.toggle_button_left_clicked, R.color.White);
            } else if (FilterModel.getInstance().getSex().equalsIgnoreCase("female")) {
                changeBtnStyle(femaleBtn, R.drawable.toggle_button_left_clicked, R.color.White);
            }
        }

        selectSexContainer.setVisibility(View.GONE);

        if (FilterModel.getInstance().getMaritalStatus() != null) {
            if (FilterModel.getInstance().getMaritalStatus().equalsIgnoreCase("single")) {
                changeBtnStyle(singleBtn, R.drawable.toggle_button_left_clicked, R.color.White);
                selectSexContainer.setVisibility(View.VISIBLE);
            } else if (FilterModel.getInstance().getMaritalStatus().equalsIgnoreCase("married")) {
                changeBtnStyle(marriedBtn, R.drawable.toggle_button_left_clicked, R.color.White);
            }
        }

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        maleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FilterModel.getInstance().getSex() != null && FilterModel.getInstance().getSex().equalsIgnoreCase("male")) {
                    FilterModel.getInstance().setSex(null);
                    changeBtnStyle(maleBtn, R.drawable.toggle_button_left, R.color.greyedText);
                } else {
                    FilterModel.getInstance().setSex("male");
                    changeBtnStyle(maleBtn, R.drawable.toggle_button_left_clicked, R.color.White);
                    changeBtnStyle(femaleBtn, R.drawable.toggle_button_right, R.color.greyedText);
                }

                ((BuyActivity) getActivity()).loadFakeData();
            }
        });

        femaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FilterModel.getInstance().getSex() != null && FilterModel.getInstance().getSex().equalsIgnoreCase("female")) {
                    FilterModel.getInstance().setSex(null);
                    changeBtnStyle(femaleBtn, R.drawable.toggle_button_right, R.color.greyedText);
                } else {
                    FilterModel.getInstance().setSex("female");
                    changeBtnStyle(femaleBtn, R.drawable.toggle_button_right_clicked, R.color.White);
                    changeBtnStyle(maleBtn, R.drawable.toggle_button_left, R.color.greyedText);
                }

                ((BuyActivity) getActivity()).loadFakeData();
            }
        });

        singleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FilterModel.getInstance().getMaritalStatus() != null
                        && FilterModel.getInstance().getMaritalStatus().equalsIgnoreCase("single")) {
                    FilterModel.getInstance().setMaritalStatus(null);
                    changeBtnStyle(singleBtn, R.drawable.toggle_button_left, R.color.greyedText);
                    selectSexContainer.setVisibility(View.GONE);
                } else {
                    FilterModel.getInstance().setMaritalStatus("single");
                    changeBtnStyle(singleBtn, R.drawable.toggle_button_left_clicked, R.color.White);
                    changeBtnStyle(marriedBtn, R.drawable.toggle_button_right, R.color.greyedText);
                    selectSexContainer.setVisibility(View.VISIBLE);
                }

                ((BuyActivity) getActivity()).loadFakeData();
            }
        });

        marriedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FilterModel.getInstance().getMaritalStatus() != null
                        && FilterModel.getInstance().getMaritalStatus().equalsIgnoreCase("married")) {
                    FilterModel.getInstance().setMaritalStatus(null);
                    changeBtnStyle(marriedBtn, R.drawable.toggle_button_right, R.color.greyedText);
                    selectSexContainer.setVisibility(View.VISIBLE);
                } else {
                    FilterModel.getInstance().setMaritalStatus("married");
                    changeBtnStyle(marriedBtn, R.drawable.toggle_button_right_clicked, R.color.White);
                    changeBtnStyle(singleBtn, R.drawable.toggle_button_left, R.color.greyedText);
                    selectSexContainer.setVisibility(View.GONE);

                }

                ((BuyActivity) getActivity()).loadFakeData();
            }
        });

        priceLow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatePriceLow();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        priceHigh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updatePriceHigh();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return view;
    }

    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.close_btn) {
            dismiss();
        }

        return super.onOptionsItemSelected(item);
    }

    private void updatePriceLow() {
        Double priceLowText = (priceLow.getText().toString().equals("")) ? null : Double.parseDouble(priceLow.getText().toString());
        FilterModel.getInstance().setPriceLow(priceLowText);

        ((BuyActivity) getActivity()).loadFakeData();
    }

    private void updatePriceHigh() {
        Double priceHighText = (priceHigh.getText().toString().equals("")) ? null : Double.parseDouble(priceHigh.getText().toString());
        FilterModel.getInstance().setPriceHigh(priceHighText);

        ((BuyActivity) getActivity()).loadFakeData();
    }

    private void changeBtnStyle(Button btn, int drawable, int  textColor) {
        btn.setTextColor(ContextCompat.getColor(getContext(), textColor));
        btn.setBackground(getResources().getDrawable(drawable, null));
    }
}