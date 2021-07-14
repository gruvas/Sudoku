//package com.example.sudoku408;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class Rating extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_rating);
//        setContentView(R.layout.activity_test);
//    }
//}

package com.example.sudoku408;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.stream.IntStream;

public class Rating extends AppCompatActivity  implements AdapterView.OnItemSelectedListener  {

    private GridView mGridView;
    private Test mGame;
    TextView tV;
    int Pos;
    Context mContext;
    int[][] xui = new int[9][9];

    private static int SUBSECTION__SIZE = 3;
    private static int NO__VALUE = 0;
    private static int CONSTRAINTS = 4;
    private static int MIN__VALUE = 1;
    private static int MAX__VALUE = 9;
    private static int COVER__START__INDEX = 1;
    private static int BOARD__SIZE = 9;
    private static int BOARD__START__INDEX = 0;

    public static int[][] Array;
    int[][] mass = new int[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tV = (TextView) findViewById(R.id.textView1);

        mGridView = (GridView) findViewById(R.id.field);
//        ListAdapter mGame = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        mGame = new Test(this, android.R.layout.simple_list_item_1);
        mGridView.setAdapter(mGame);
        mGridView.setOnItemSelectedListener(this);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                tV.setText("Выбранный элемент: " + mGame.getItem(position, 0));
                Pos = position;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        tV.setText("Выбраный элемент: " + mGame.getItem(position, 0));
        Pos = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
//        tV.setText("Выбраный элемент: ничего");
    }
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.btn1:
                    mGame.getItem(Pos, 1);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn2:
                    mGame.getItem(Pos, 2);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn3:
                    mGame.getItem(Pos, 3);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn4:
                    mGame.getItem(Pos, 4);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn5:
                    mGame.getItem(Pos, 5);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn6:
                    mGame.getItem(Pos, 6);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn7:
                    mGame.getItem(Pos, 7);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn8:
                    mGame.getItem(Pos, 8);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn9:
                    mGame.getItem(Pos, 9);
                    mass = mGame.GetArrayTest();
                    mGridView.setAdapter(mGame);
                    break;
            }
        } catch (Exception e){};
    }
    //Получение массива из GridView
    int[][] SpeciallyArray = new int[9][9];
    public int[][] GetArray(int[][] SpecArray) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SpeciallyArray[i][j] = SpecArray[i][j];
            }
        }
        return SpeciallyArray;
    }

//    Нажатие на кнопку "решить судоку"
    public void onClick_end(View view) {

        //Вызов метода по решению судоку Game.solve(Array);
        Game game = new Game(this, android.R.layout.simple_list_item_1);
        game.solve(mass);
        //Метод по заполнению GridView который находиться active_test.xml
        Test test = new Test(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                test.getItem(i*9+j,mass[i][j]);
                mGridView.setAdapter(mGame);
            }
        }
    }

    public void ExitSolver(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }
}