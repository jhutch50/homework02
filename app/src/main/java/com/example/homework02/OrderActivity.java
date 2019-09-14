/*
Team 11:
Homework 2
Names:
Joshua Hutcheson
Hailey Brown
Ormelia Robinson
 */
package com.example.homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    //initialize variables
    TextView tv_basePrice;
    TextView tv_toppingsPrice;
    TextView tv_toppingsList;
    TextView tv_deliveryPrice;
    TextView tv_total;

    float total = 0.00f;
    StringBuilder pizzaToppingsList = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Pizza Store");

        //set base price
        tv_basePrice = findViewById(R.id.tv_basePrice);
        tv_toppingsPrice = findViewById(R.id.tv_toppingsPrice);
        tv_toppingsList = findViewById(R.id.tv_toppingsList);
        tv_deliveryPrice = findViewById(R.id.tv_deliveryPrice);
        tv_total = findViewById(R.id.tv_total);

        final Bundle extrasFromDisplay = getIntent().getExtras().getBundle(MainActivity.TAG_IMAGE);
        Order order = (Order) extrasFromDisplay.getSerializable("order");
        Log.d("this order", order.toString());

        if(order!=null){
            tv_basePrice.setText(order.getPizzaPrice() + "$");
            total+=order.getPizzaPrice();

            if(order.getToppings().isEmpty()==false){
                //logic for topping total based on size of arraylist * price
                tv_toppingsPrice.setText(order.getToppingPrice()*order.getToppingQuantity() + "$");
                total+=(order.getToppingPrice()*order.getToppingQuantity());

                //display all strings in topping arraylist
                for(int i = 0; i < order.getToppingQuantity(); i++){
                    if(i<order.getToppingQuantity()-1){
                        pizzaToppingsList.append(order.getToppings().get(i) + ", ");
                    }else{
                        pizzaToppingsList.append(order.getToppings().get(order.getToppingQuantity()-1) + "");
                    }

                }


                tv_toppingsList.setText(pizzaToppingsList.toString());
            }else{
                tv_toppingsPrice.setText("0.00$");
                tv_toppingsList.setText("");
            }

            //display delivery cost if delivery=true
            if (order.isDelivery()==true){
                tv_deliveryPrice.setText(order.getDeliveryPrice() + "$");
                total+=order.getDeliveryPrice();
            }else{
                tv_deliveryPrice.setText("0.0$");
            }

            tv_total.setText(total + "$");


        }







    }
}
