package com.example.sudoku408;

import android.content.Context;
import android.widget.ArrayAdapter;

public class Test extends ArrayAdapter{

    Context mContext;

    private static final String[] Sudoku_convert = new String[81];

    //Не трогать, травмоопасно!!!!
    int[][] SpeciallyArray = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    int Zero = 81;


    //Конструктор
    public Test(Context context, int textViewResourceId) {
        super(context, textViewResourceId, Sudoku_convert);
        this.mContext = context;

        Change_cells(Sudoku_convert);
    }

    private void Change_cells(String[] Sudoku_convert) {
        for (int i = 0; i < 81; i++) {
            Sudoku_convert[i] = " ";
        }
    }

    public String getItem(int position, int Z) {
        //Заполнение массива для отправки в Rating.java
        SpeciallyArray[position / 9][position % 9] = Z;
        GetArrayTest();

        if (Z == 0) {
            return Sudoku_convert[position];
        }
        else {
            Zero--;
            Sudoku_convert[position] = Integer.toString(Z);
//            return "";
            return Sudoku_convert[position];
        }
    }
    public int[][] GetArrayTest() {
        Rating rating = new Rating();
        return rating.GetArray(SpeciallyArray);
    }
}
