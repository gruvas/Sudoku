package com.example.sudoku408;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_sudoku;
    Button btn_solver;
    Button btn_rating;
    Button btn_shutdown;
    Button Exit;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        btn_sudoku = (Button) findViewById(R.id.btn_sudoku);
        btn_solver = (Button) findViewById(R.id.btn_solver);
        btn_rating = (Button) findViewById(R.id.btn_rating);
        btn_shutdown = (Button) findViewById(R.id.btn_shutdown);
        Exit = (Button) findViewById(R.id.Exit_to_change);

//        MyDbManeger myDbManeger = new MyDbManeger(getApplicationContext());
//        MyDbManeger.openDb();
//        myDbManeger.insertToDb("user", "100");
//        myDbManeger.insertToDb("EliteEat", "15");
//        myDbManeger.insertToDb("ZyevKriu", "228");
//        myDbManeger.insertToDb("ZXC", "225");
//        myDbManeger.insertToDb("ПашаБицепс", "1337");

        mcontext = GetContext();

        btn_sudoku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, difName.class);
                startActivity(intent);
            }
        });

        btn_shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        btn_solver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rating.class);
                startActivity(intent);
            }
        });

        btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Solver .class);
                startActivity(intent);
            }
        });
    }
    public Context GetContext() {
        return mcontext;
    }

//    public void onExit(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}