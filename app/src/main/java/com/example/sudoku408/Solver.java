package com.example.sudoku408;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Solver extends AppCompatActivity {

    DbHelper dbHelper;
    MyDbManeger myDbManeger;

    private EditText test_name, test_score, test_id;
    private TextView test_show;
    private TextView test_show_2;
    int place = 4;
    int score_obj = 0;
    int score_if = 0;

    Button btn_comeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        btn_comeBack = (Button) findViewById(R.id.btn_comeBack);

        btn_comeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Solver.this, MainActivity.class);
                startActivity(intent);
            }
        });

        test_name = findViewById(R.id.test_name);
        test_score = findViewById(R.id.test_score);
        test_show = findViewById(R.id.test_show);
        test_show_2 = findViewById(R.id.test_show2);
        test_id = findViewById(R.id.test_id);

        dbHelper = new DbHelper(this);
        myDbManeger = new MyDbManeger(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManeger.openDb();

        for (String name : myDbManeger.getFromOb()) {
            place--;
        }


        for (String name : myDbManeger.getFromOb()) {
            place++;
            test_show.append(place + ". " + name + "\n" + "\n");
        }
        for(String score : myDbManeger.getFromScore()) {
            test_show_2.append(score + "\n" + "\n");
        }
    }

    public void testClick(View view) {

        place = 1;

        test_show.setText("");
        myDbManeger.insertToDb(test_name.getText().toString(), test_score.getText().toString());

        for(String name : myDbManeger.getFromOb()){
            test_show.append(place + ". " + name + "\n" + "\n");
            place++;
//            test_show.append("\n");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dbHelper.close();
//        myDbManeger.close();
    }

    public void onClick_100(View view) {
//        myDbManeger.updateToDb(test_name.getText().toString(), test_score.getText().toString(), test_id.getText().toString());

        myDbManeger.del_and_insert_db();
    }

    public void Exit_to_Menu(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }
}