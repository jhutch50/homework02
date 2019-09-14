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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG_IMAGE = "main";


    Button button_addTopping;
    Button button_clearPizza;
    Button button_checkout;
    CheckBox cb_delivery;
    LinearLayout topping_top_row;
    LinearLayout topping_bottom_row;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pizza Store ");

        final RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));




        //take values from page
        button_addTopping = findViewById(R.id.button_addTopping);
        button_clearPizza = findViewById(R.id.button_clearPizza);
        button_checkout= findViewById(R.id.button_checkout);
        cb_delivery = findViewById(R.id.checkBox);
        topping_top_row =findViewById(R.id.topping_top_row);
        topping_bottom_row =findViewById(R.id.topping_bottom_row);




        final boolean[] flag_delivery = new boolean[1];
        final String[] flag_toppingImage ={""};
        final int[] toppingQuantity = new int[1];


        //select delivery
       if(cb_delivery.isChecked()){
           cb_delivery.setChecked(true);
           flag_delivery[0] = true;
        }else{
           cb_delivery.setChecked(false);
           flag_delivery[0] = false;
        }

       Log.d("Checked","" + cb_delivery.isChecked());

        //add topping button, case statement with topping strings that correspond to images, display toppings

        final String[] toppings = getResources().getStringArray(R.array.toppings_array);
        final ArrayList<String> selectedToppings = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.pick_topping).setItems(toppings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("demo","Clicked " + toppings[i]);
                    if(!(selectedToppings.size()>9)){
                        selectedToppings.add(toppings[i]);
                        final ImageView currentImage = new ImageView(getBaseContext());
                        switch(toppings[i]){
                            case "Garlic":
                                currentImage.setImageDrawable(getDrawable(R.drawable.garlic));
                                break;
                            case "Bacon":
                                currentImage.setImageDrawable(getDrawable(R.drawable.bacon));
                                break;
                            case "Cheese":
                                currentImage.setImageDrawable(getDrawable(R.drawable.cheese));
                                break;
                            case "Green Pepper":
                                currentImage.setImageDrawable(getDrawable(R.drawable.green_pepper));
                                break;
                            case "Red Pepper":
                                currentImage.setImageDrawable(getDrawable(R.drawable.red_pepper));
                                break;
                            case "Mushroom":
                                currentImage.setImageDrawable(getDrawable(R.drawable.mushroom));
                                break;
                            case "Olives":
                                currentImage.setImageDrawable(getDrawable(R.drawable.olive));
                                break;
                            case "Onions":
                                currentImage.setImageDrawable(getDrawable(R.drawable.onion));
                                break;
                        }
                        if(selectedToppings.size()<6){
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(16,0,16,0);
                            currentImage.setLayoutParams(params);
                            currentImage.getLayoutParams().height = 150;
                            currentImage.getLayoutParams().width = 150;
                            currentImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    selectedToppings.remove(topping_top_row.indexOfChild(view));
                                    topping_top_row.removeView(view);

                                }
                            });
                            topping_top_row.addView(currentImage);


                        }else if(selectedToppings.size()<11){

                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(16,0,16,0);
                            currentImage.setLayoutParams(params);
                            currentImage.getLayoutParams().height = 150;
                            currentImage.getLayoutParams().width = 150;
                            currentImage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    selectedToppings.remove(5+topping_bottom_row.indexOfChild(view));
                                    topping_bottom_row.removeView(view);
                                }
                            });
                            topping_bottom_row.addView(currentImage);


                        }
                    }else{
                        AlertDialog.Builder max_builder = new AlertDialog.Builder(MainActivity.this);
                        max_builder.setTitle("Warning").setMessage("Maximum Topping capacity reached!");

                        final AlertDialog max = max_builder.create();
                        max.show();
                    }

                }

            });

            final AlertDialog alert = builder.create();


            Log.d("The current Size",selectedToppings.size()+"");


        button_addTopping.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                alert.show();
            }

        });

        //clear button
        button_clearPizza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectedToppings.clear();
                topping_top_row.removeAllViews();
                topping_bottom_row.removeAllViews();
            }
        });

        //check out button + create order + set values to order
        button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);

                Order order = new Order(cb_delivery.isChecked(),selectedToppings);
                Log.d("Values before Intent: ", order.toString());
                Bundle sentData = new Bundle();
                sentData.putSerializable("order", order);
                intent.putExtra(TAG_IMAGE, sentData);

                //setResult(MainActivity.RESULT_OK, intent);
                startActivity(intent);
                finish();
            }
        });



    }


}
