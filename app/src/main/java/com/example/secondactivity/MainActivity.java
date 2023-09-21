package com.example.secondactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String imgs[] = {"img_1", "img_2", "img_3"};
    int index = 0;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product = new Product("Pepperoni Pizza", 8, "Pepperoni, Cheese and Sauce");

        TextView name = findViewById(R.id.name_value);
        name.setText(product.name);

        TextView description = findViewById(R.id.description_value);
        description.setText(product.description);

        TextView price = findViewById(R.id.price_value);

        Spinner spinner = findViewById(R.id.spinner);

        List<String> data = new ArrayList<>();
        data.add("USD");
        data.add("EURO");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        price.setText("" + product.price);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle item selection here
                Spinner currency = findViewById(R.id.spinner);
                TextView price = findViewById(R.id.price_value);
                String curr = currency.getSelectedItem().toString();
                if (curr.equals("USD")) {
                    price.setText("" + product.price);
                } else {
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String formattedResult = decimalFormat.format(product.price / 1.06);
                    price.setText("" +formattedResult);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected if needed
            }
        });

    }

    public void GetPrev(View view) {
        ImageView img = findViewById(R.id.imageView);
        if (index == 0) {
            index = 3;
            img.setImageResource(R.drawable.img_3);
        } else if (index == 1) {
            index = 0;
            img.setImageResource(R.drawable.img_1);
        } else {
            index = 1;
            img.setImageResource(R.drawable.img_2);
        }

    }

    public void GetNext(View view) {
        ImageView img = findViewById(R.id.imageView);
        if (index == 0) {
            index = 1;
            img.setImageResource(R.drawable.img_2);
        } else if (index == 1) {
            index = 2;
            img.setImageResource(R.drawable.img_3);
        } else {
            index = 0;
            img.setImageResource(R.drawable.img_1);
        }

    }

    public void order(View view){
        CharSequence text = "Order Confirmed!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }
}