package com.example.database;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        PersonSQLiteOpenHelper helper= new PersonSQLiteOpenHelper(this);
        //执行这样一句话，数据库才会去创建
        helper.getWritableDatabase();
        
        
    }


   
    
}
