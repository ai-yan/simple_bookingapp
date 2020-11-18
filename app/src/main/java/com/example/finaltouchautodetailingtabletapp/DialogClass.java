package com.example.finaltouchautodetailingtabletapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class DialogClass extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button book, cancel;
    SharedPreferences preferences;
    String details, selected_date, final_price;
    DatePicker picker;

    public DialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        book = (Button) findViewById(R.id.btn_yes);
        cancel = (Button) findViewById(R.id.btn_no);
        picker = (DatePicker) findViewById(R.id.datePicker1);
        book.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                preferences = getContext().getSharedPreferences("service_details", Context.MODE_PRIVATE);
                details = preferences.getString("details", null);
                final_price = preferences.getString("cost", null);
                selected_date = picker.getDayOfMonth() + "/" + (picker.getMonth() + 1) + "/" + picker.getYear() + "\n";

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // set the custom layout
                final View customLayout  = getLayoutInflater().inflate(R.layout.details_dialog, null);
                TextView date = customLayout.findViewById(R.id.selected_date_view);
                date.setText(selected_date);
                TextView details_view = customLayout.findViewById(R.id.details_service_view);
                details_view.setText(details);
                TextView cost_view = customLayout.findViewById(R.id.final_cost_view);
                cost_view.setText(final_price);
                builder.setView(customLayout);

                // add a button
                builder.setPositiveButton(
                                "BOOK NOW",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.clear();
                                        editor.commit();
                                        dialog.dismiss();
                                    }
                                });

                // create and show
                // the alert dialog
                AlertDialog dialog
                        = builder.create();
                dialog.show();

                //Alert Dialog
//                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
//                alertDialog.setTitle(R.string.btnDate);
//                alertDialog.setMessage("SELECTED DATE: " + selected_date + "\n" + "SELECTED SERVICES:" + "\n" + details + "\n" + "FINAL COST: " + final_price);
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "BOOK NOW",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.clear();
//                                editor.commit();
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
