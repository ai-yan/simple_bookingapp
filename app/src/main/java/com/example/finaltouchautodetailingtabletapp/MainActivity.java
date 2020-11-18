package com.example.finaltouchautodetailingtabletapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    private CheckBox mDeluxe, mGold, mPremium, mPaint;
    private Button mResultButton;
    private ArrayList<String> mResult;
    DialogClass dialogClass;
    SharedPreferences sh;
    private int cost1, cost2, cost3, cost4, final_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDeluxe = findViewById(R.id.txtDeluxe);
        mGold = findViewById(R.id.txtGold);
        mPremium = findViewById(R.id.txtPremium);
        mPaint = findViewById(R.id.txtPaint);

        mResultButton = findViewById(R.id.button);
        mResult = new ArrayList<>();

        mDeluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDeluxe.isChecked()) {
                    mResult.add("Deluxe");
                    cost1 = 70;
                }else {
                    mResult.remove("Deluxe");
                    cost1 = 0;
                }
            }
        });

        mGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGold.isChecked()) {
                    mResult.add("Gold");
                    cost2 = 120;
                }else{
                    mResult.remove("Gold");
                    cost2 = 0;
                }
            }
        });

        mPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPremium.isChecked()) {
                    mResult.add("Premium");
                    cost3 = 170;
                }else{
                    mResult.remove("Premium");
                    cost3 = 0;
                }
            }
        });

        mPaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPaint.isChecked()) {
                    mResult.add("Paint");
                    cost4 = 50;
                }else{
                    mResult.remove("Paint");
                    cost4 = 0;
                }
            }
        });

        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String s :mResult)
                    stringBuilder.append(s).append("\n");

                final_cost = cost1 + cost2 + cost3 + cost4;

                sh = getSharedPreferences("service_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("details", stringBuilder.toString());
                editor.putString("cost", String.valueOf(final_cost));
                editor.commit();
                dialogClass = new DialogClass(MainActivity.this);
                dialogClass.show();
            }
        });
    }
}