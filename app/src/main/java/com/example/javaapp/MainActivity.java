package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int count,inc,countw,countc;
    Button bt,bt1,bt2;
    TextView items;
    TextView netprice,OrderSummarytext;
    CheckBox whippedcream;
    CheckBox Chocolate;
    EditText NameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count =1;
        inc=0;

        items= (TextView) findViewById(R.id.quantity);
        netprice= (TextView) findViewById(R.id.order_summary);
        OrderSummarytext= (TextView) findViewById(R.id.order_summary);
        whippedcream = (CheckBox) findViewById(R.id.WhippedCream);
        Chocolate = (CheckBox) findViewById(R.id.chocolate);
        NameField = (EditText) findViewById(R.id.name_field);

    }
    public void increaseOrder (View view)
    {
        inc++;
        display (inc);
        displayPrice((inc)*10);


    }
    public void decreaseOrder (View view)
    {
        inc--;
        if (inc<=0){
            inc=0;
        display (0);
        displayPrice (0);}
        else{
        display (inc);
        displayPrice((inc)*10);}

    }
    public void submitOrder (View view){
        //Toast.makeText(MainActivity.this,"Order Placed",Toast.LENGTH_LONG).show();
        String personname = NameField.getText().toString();
        boolean hasWhippedCream = whippedcream.isChecked();
        boolean haschocolate = Chocolate.isChecked();
        String str=ordersummary(personname,hasWhippedCream,haschocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "java app " + personname);
        intent.putExtra(Intent.EXTRA_TEXT,str);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displaymessage(str);

    }


    private void display (int counts){
        items.setText(Integer.toString(counts));

    }
    private String ordersummary (String person,boolean addWhippedCream,boolean addchocolate){

        String str= " Name :" + person;
            str = str+ "\n Quantity :"+ inc ;
            int price=inc*10;
        countw=0;
        if (addWhippedCream) {


            str = str + "\n Add Whipped Cream ? " + addWhippedCream;

                str += "\n Whipped Cream cost :" + inc * 10;
                price += inc * 10;

        }
        if (addchocolate){

            str =str+"\n Add Chocolate ? " + addchocolate;

                str += "\n Chocolate cost :" + inc * 5;
                price += inc * 5;

            }



        str = str+ "\n Price $:"+ price;
        str =str+ "\n Thank you for ordering!";
        return str;
    }
    private void displaymessage (String message){
        OrderSummarytext.setText (message);
    }



    private void displayPrice (int number){
        netprice.setText(NumberFormat.getCurrencyInstance().format(number));

    }

}
