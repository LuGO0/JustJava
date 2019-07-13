package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URI;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int currentOrderQuantity=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSelectWhippedCream(View view)
    {


        //do nothing for now
    }


    public void increment(View view){

        display(++currentOrderQuantity);
    }


    public void decrement(View view){
        if(currentOrderQuantity-1>=0)
        display(--currentOrderQuantity);
    }



    public void submitOrder(View view) {



        EditText TextName=(EditText) findViewById(R.id.editText_customersName);
        String customerName=TextName.getText().toString();

        CheckBox checkCream=(CheckBox)findViewById(R.id.checkbox_whippedCream);
        boolean isChecked=checkCream.isChecked();

        //tag and message
       // Log.v("MainActivity","does has checked cream:"+isChecked);


        display(currentOrderQuantity);
        displayPrice(createOrderSummary(5,isChecked,customerName));


        Intent mailOrder=new Intent(Intent.ACTION_SEND);
        mailOrder.setType("*/*");
        mailOrder.putExtra(mailOrder.EXTRA_EMAIL,"2017232@iiitdmj.ac.in");
        mailOrder.putExtra(mailOrder.EXTRA_SUBJECT,"Order details");
        mailOrder.putExtra(mailOrder.EXTRA_TEXT,createOrderSummary(5,isChecked,customerName));

        if(mailOrder.resolveActivity(getPackageManager())!= null){
            startActivity(mailOrder);
        }
    }



    public String createOrderSummary(int price,boolean isChecked,String customerName)
    {
        String message="Name:"+customerName+"\nQuatity:"+currentOrderQuantity+"\nTotal:"+currentOrderQuantity*5+"\n" +"with whipped cream?"+isChecked+"\nThank You";
        return message;
    }
    
    public void displayPrice(String message){
        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }
}