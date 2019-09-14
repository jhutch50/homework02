package com.example.homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG_IMAGE = "main";
    Button button_addTopping;
    Button button_clearPizza;
    Button button_checkout;


    CheckBox cb_delivery =null;

    ArrayList<ImageView> iv_topping_top = new ArrayList<>();
    ArrayList<ImageView> iv_topping_bottom = new ArrayList<>();


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
        button_checkout = findViewById(R.id.button_checkout);
        cb_delivery = findViewById(R.id.checkBox);

        final boolean[] flag_delivery = new boolean[1];
        final String[] flag_toppingImage ={""};
        final int[] toppingQuantity = new int[1];


        //select delivery
       if(!cb_delivery.isChecked()){
            flag_delivery[0] = false;
        }else{
            flag_delivery[0] = true;
        }

        //add topping button, case statement with topping strings that correspond to images, display toppings

        final String[] toppings = getResources().getStringArray(R.array.toppings_array);
        final ArrayList<String> selectedToppings = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.pick_topping).setItems(toppings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("demo","Clicked " + toppings[i]);
                    if(!(selectedToppings.size()>10)){
                        selectedToppings.add(toppings[i]);
                        if(selectedToppings.size()<6){
                            /*ImageView currentImage = null;
                            int resourceID =getResources().getIdentifier(toppings[i].toLowerCase(),"drawable",getPackageName());
                            Log.d("resourceID=", ""+resourceID+" which is " + toppings[i]);
                            currentImage.setImageDrawable();
                            currentImage.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                            relativeLayout.addView(currentImage);
                            setContentView(relativeLayout);
*/
                        }else if(selectedToppings.size()<11){
                            /*ImageView currentImage = null;
                            int resourceID =getResources().getIdentifier(toppings[i],"drawable",getPackageName());
                            currentImage.setImageDrawable(getDrawable(resourceID));
*/
                        }
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
        //remove topping from arraylist when topping picture is clicked


        //clear button
        button_clearPizza.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                clearButton();
            }
        });

        //check out button + create order + set values to order
        button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);

                Order order = new Order(flag_delivery[0],selectedToppings);
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

    public void clearButton(){
        //et_length1.setVisibility(View.VISIBLE);
        //et_length2.setVisibility(View.VISIBLE);
      //  tv_result.setVisibility(View.VISIBLE);
       // et_length1.setText("");
       // et_length2.setText("");
        //tv_result.setText("");
//        tv_shape.setText("Select a shape");
//        triangle.setVisibility(View.VISIBLE);
//        circle.setVisibility(View.VISIBLE);

    }
}
