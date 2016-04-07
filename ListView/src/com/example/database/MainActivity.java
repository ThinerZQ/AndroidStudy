package com.example.database;


import java.util.List;

import com.example.database.dao.PersonDao;
import com.example.domain.Person;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       LinearLayout l_root =(LinearLayout) findViewById(R.id.l_root);
       
       PersonDao dao = new PersonDao(this);
       List<Person> persons = dao.findAll();
       for(Person p :persons){
    	   
    	   String info = p.toString();
    	   TextView tv = new TextView(this);
    	   tv.setTextSize(20);
    	   tv.setTextColor(Color.BLUE);
    	   tv.setText(info);
    	   l_root.addView(tv);
       }
       
       
       
       
       
       
        
        
    }


   
    
}
