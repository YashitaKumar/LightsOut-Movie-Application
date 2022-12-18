package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Seats extends AppCompatActivity {

    ViewGroup layout;

    String seats = "_UUUUUUAAAAARRRR_/"
            + "_________________/"
            + "UUA__AAAARRRRR__RR/"
            + "UUA__UUUAAAAAA__AA/"
            + "AAR__AAAAAAAAA__AA/"
            + "AAU__AARUUUURR__AA/"
            + "UUA__UUUA_RRRR__AA/"
            + "AAA__AAAA_RRAA__UU/"
            + "AAU__AARR_UUUU__RR/"
            + "AAR__UUAA_UURR__RR/"
            + "_________________/"
            + "UUU_AAAAAAAUUUU_RR/"
            + "RRA_AAAAAAAAAAA_AA/"
            + "AAU_UUAAAAAUUUU_AA/"
            + "AAU_AAAAAAUUUUU_AA/"
            + "__________________/"
            + "___STTTTTTTTTTTTTTQ__/";

    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    int SCREEN = 4;
    String selectedIds = "";
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        layout = findViewById(R.id.layoutSeat);

        seats = "/" + seats;

        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        layout.addView(layoutSeat);

        LinearLayout layout = null;

        int count = 0;

        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
            } else if (seats.charAt(index) == 'U') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_booked);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_BOOKED);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ((int) view.getTag() == STATUS_AVAILABLE) {
                            if (selectedIds.contains(view.getId() + ",")) {
                                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                                view.setBackgroundResource(R.drawable.ic_seats_book);
                            } else {
                                selectedIds = selectedIds + view.getId() + ",";
                                view.setBackgroundResource(R.drawable.ic_seats_selected);
                            }
                        } else if ((int) view.getTag() == STATUS_BOOKED) {
                            Toast.makeText(Seats.this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
                        } else if ((int) view.getTag() == STATUS_RESERVED) {
                            Toast.makeText(Seats.this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (seats.charAt(index) == 'A') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_book);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ((int) view.getTag() == STATUS_AVAILABLE) {
                            if (selectedIds.contains(view.getId() + ",")) {
                                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                                view.setBackgroundResource(R.drawable.ic_seats_book);
                            } else {
                                selectedIds = selectedIds + view.getId() + ",";
                                view.setBackgroundResource(R.drawable.ic_seats_selected);
                            }
                        } else if ((int) view.getTag() == STATUS_BOOKED) {
                            Toast.makeText(Seats.this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
                        } else if ((int) view.getTag() == STATUS_RESERVED) {
                            Toast.makeText(Seats.this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (seats.charAt(index) == 'R') {
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(count);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.ic_seats_reserved);
                view.setText(count + "");
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                view.setTextColor(Color.WHITE);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ((int) view.getTag() == STATUS_AVAILABLE) {
                            if (selectedIds.contains(view.getId() + ",")) {
                                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                                view.setBackgroundResource(R.drawable.ic_seats_book);
                            } else {
                                selectedIds = selectedIds + view.getId() + ",";
                                view.setBackgroundResource(R.drawable.ic_seats_selected);
                            }
                        } else if ((int) view.getTag() == STATUS_BOOKED) {
                            Toast.makeText(Seats.this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
                        } else if ((int) view.getTag() == STATUS_RESERVED) {
                            Toast.makeText(Seats.this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
            else if (seats.charAt(index)=='T')
            {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(0, 0, 0, 0);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.screen_background);
                view.setTag(SCREEN);
                layout.addView(view);
                seatViewList.add(view);
            }
            else if (seats.charAt(index)=='S')
            {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(0, 0, 0, 0);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.screen);
                view.setTag(SCREEN);
                layout.addView(view);
                seatViewList.add(view);
            }
            else if (seats.charAt(index)=='Q')
            {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(0, 0, 0, 0);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.screen_2);
                view.setTag(SCREEN);
                layout.addView(view);
                seatViewList.add(view);
            }
        }

        btn = findViewById(R.id.bookNow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Seats.this,"Seats Booked!!!",Toast.LENGTH_SHORT);

            }
        });

    }

}