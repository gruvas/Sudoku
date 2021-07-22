package com.example.sudoku408;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

public class Game extends ArrayAdapter  {

    private final int SUBSECTION__SIZE = 3;
    private final int NO__VALUE = 0;
    private final int MIN__VALUE = 1;
    private final int MAX__VALUE = 9;
    private final int BOARD__SIZE = 9;
    private final int BOARD__START__INDEX = 0;
    private int N = 9;

    int Diff = 0;

    public final String Difficult ="Easy";

    Context mContext;
    MyDbManeger myDbManeger;
    Random random = new Random();



    private int[][] Sudoku = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                             {4, 5, 6, 7, 8, 9, 1, 2, 3},
                             {7, 8, 9, 1, 2, 3, 4, 5, 6},
                             {2, 3, 4, 5, 6, 7, 8, 9, 1},
                             {5, 6, 7, 8, 9, 1, 2, 3, 4},
                             {8, 9, 1, 2, 3, 4, 5, 6, 7},
                             {3, 4, 5, 6, 7, 8, 9, 1, 2},
                             {6, 7, 8, 9, 1, 2, 3, 4, 5},
                             {9, 1, 2, 3, 4, 5, 6, 7, 8}};
    private int[][] Sudoku_temp = new int[9][9];
    private static final String[] Sudoku_convert = new String[81];
    private int[][] Sudoku_convert_double = new int[9][9];
    int Zero = 81;


    //Конструктор
    public Game(Context context, int textViewResourceId) {
        super(context, textViewResourceId, Sudoku_convert);
        // TODO Auto-generated constructor stub
        this.mContext = context;



        if (MyConstants.Difficult == "Hard") {
            Diff = (random.nextInt(35 - 30) + 1) + 30;
            Zero = Zero - Diff;
        }
        else if (MyConstants.Difficult == "Normal") {
            Diff = (random.nextInt(30 - 25) + 1) + 25;
            Zero = Zero - Diff;
        }
        else if (MyConstants.Difficult == "Easy") {
            Diff = (random.nextInt(25 - 20) + 1) + 20;
            Zero = Zero - Diff;
        }
        Transparent(Sudoku, N);
        for (int i = 0; i < 5;i++)
            SwapStr(Sudoku, N);
        for (int i = 0; i < 5; i++)
            SwapCol(Sudoku, N);
        for (int i = 0; i < 5; i++)
            SwapNumber(Sudoku, N);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Sudoku_temp[i][j] = Sudoku[i][j];
                System.out.print(Sudoku_temp[i][j] + " ");
            }
            System.out.print("\n");
        }
        Remove_cells(Sudoku, Diff);
        int Z = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                Sudoku_convert[Z] = Integer.toString(Sudoku[i][j]);
                Z++;
            }
        }
        Change_cells(Sudoku_convert);
    }
    public void Transparent(int[][] Sudoku, int N) {
        for (int str = 0; str < N; str++) {
            for (int j = str+1; j < N; j++) {
                int temp = Sudoku[str][j];
                Sudoku[str][j] = Sudoku[j][str];
                Sudoku[j][str] = temp;
            }
        }
    }
    public void SwapNumber(int[][] Sudoku, int N) {
        Random random = new Random();
        int Z = random.nextInt(9) + 1; int X = random.nextInt(9) + 1;
        while ((Z == X || Z == 0 || X == 0) && Z < X) {
            Z = random.nextInt(9) + 1;
            X = random.nextInt(9) + 1;
        }
        int V = 0;
        for (int i = 0; i < 81; i++) {
            if (Sudoku[i / 9][i % 9] == Z) {
                Sudoku[i / 9][i % 9] = X;
                continue;
            }
            if (Sudoku[i / 9][i % 9] == X) {
                Sudoku[i / 9][i % 9] = Z;
                continue;
            }
        }
    }
    public String getItem(int position, int Z) {
        int B = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Sudoku_convert[B] != " ")
                    Sudoku_convert_double[i][j] = Integer.parseInt(Sudoku_convert[B]);
                B++;
            }
        }

        if (Z == 0) {
            return Sudoku_convert[position];
        }
        else {
            if ((Sudoku[position / 9][position % 9]) != 0) {
                Toast.makeText(mContext, "Нельзя изменять предварительно заполненную ячейку", Toast.LENGTH_SHORT).show();
                return "";
            }
            Zero--;
            Sudoku_convert[position] = Integer.toString(Z);

            // Проверка на решение судоку
            if (Sudoku_temp[position / 9][position % 9] != Integer.parseInt(Sudoku_convert[position])) {
                System.out.println("Не совпадают: i = " + position / 9 + " j = " + position % 9 + " Числа: " + Sudoku_temp[position / 9][position % 9] + ", " + Integer.parseInt(Sudoku_convert[position]));
                return Sudoku_convert[position];
            }
            Sudoku_check(Sudoku_convert);
            return "";
        }
    }
    public void SwapStr(int[][] Sudoku, int N) {
        Random random = new Random();
        int Z = random.nextInt(N+1);
        for (int str = 0; str < N; str++) {
            if (str == Z && str >= 0 && str <= 2) {
                int V = (random.nextInt(2 - 0) + 1) + 0;
                while (V == Z) {
                    V = (random.nextInt(2 - 0) + 1) + 0;
                }
                for (int j = 0; j < N; j++) {
                    int X;
                    X = Sudoku[str][j];
                    Sudoku[str][j] = Sudoku[V][j];
                    Sudoku[V][j] = X;
                }
                break;
            }
            else if (str == Z && str >= 3 && str <= 5) {
                int V = (random.nextInt(5 - 3) + 1) + 3;
                while (V == Z) {
                    V = (random.nextInt(5 - 3) + 1) + 3;
                }
                for (int j = 0; j < N; j++) {
                    int X;
                    X = Sudoku[str][j];
                    Sudoku[str][j] = Sudoku[V][j];
                    Sudoku[V][j] = X;
                }
                break;
            }
            else if (str == Z && str >= 6 && str <= 8) {
                int V = (random.nextInt(8 - 6) + 1) + 6;
                while (V == Z) {
                    V = (random.nextInt(8 - 6) + 1) + 6;
                }
                for (int j = 0; j < N; j++) {
                    int X;
                    X = Sudoku[str][j];
                    Sudoku[str][j] = Sudoku[V][j];
                    Sudoku[V][j] = X;
                }
                break;
            }
        }
    }
    public void SwapCol (int[][] Sudoku, int N) {
        Random random = new Random();
        int Z = random.nextInt(N+1);
        for (int col = 0; col < N; col++) {
            if (col == Z && col >= 0 && col <= 2) {
                int V = (random.nextInt(2 - 0) + 1) + 0;
                while (V == Z) {
                    V = (random.nextInt(2 - 0) + 1) + 0;
                }
                for (int j = 0; j < N; j++) {
                    int X;
                    X = Sudoku[j][col];
                    Sudoku[j][col] = Sudoku[j][V];
                    Sudoku[j][V] = X;
                }
                break;
            }
            else if (col == Z && col >= 3 && col <= 5) {
                int V = (random.nextInt(5 - 3) + 1) + 3;
                while (V == Z) {
                    V = (random.nextInt(5 - 3) + 1) + 3;
                }
                for (int j = 0; j < N; j++) {
                    int X;
                    X = Sudoku[j][col];
                    Sudoku[j][col] = Sudoku[j][V];
                    Sudoku[j][V] = X;
                }
                break;
            }
            else if (col == Z && col >= 6 && col <= 8) {
                int V = (random.nextInt(8 - 6) + 1) + 6;
                while (V == Z) {
                    V = (random.nextInt(8 - 6) + 1) + 6;
                }
                for (int j = 0; j < N; j++) {
                    int X;
                    X = Sudoku[j][col];
                    Sudoku[j][col] = Sudoku[j][V];
                    Sudoku[j][V] = X;
                }
                break;
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    boolean solve(int[][]board) {
        for (int row = BOARD__START__INDEX; row < BOARD__SIZE; row++) {
            for (int column = BOARD__START__INDEX; column < BOARD__SIZE; column++) {
                if (board[row][column]== NO__VALUE) {
                    for (int k = MIN__VALUE; k <= MAX__VALUE; k++) {
                        board[row][column]= k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column]= NO__VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean isValid(int[][]board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean rowConstraint(int[][]board, int row) {
        boolean[]constraint = new boolean[BOARD__SIZE];
        return IntStream.range(BOARD__START__INDEX, BOARD__SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean columnConstraint(int[][]board, int column) {
        boolean[]constraint = new boolean[BOARD__SIZE];
        return IntStream.range(BOARD__START__INDEX, BOARD__SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean subsectionConstraint(int[][]board, int row, int column) {
        boolean[]constraint = new boolean[BOARD__SIZE];
        int subsectionRowStart = (row/SUBSECTION__SIZE) * SUBSECTION__SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION__SIZE;

        int subsectionColumnStart = (column/SUBSECTION__SIZE) * SUBSECTION__SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION__SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    boolean checkConstraint(
            int[][]board,
            int row,
            boolean[]constraint,
            int column) {
        if (board[row][column]!= NO__VALUE) {
            if (!constraint[board[row][column]- 1]) {
                constraint[board[row][column]- 1]= true;
            } else {
                return false;
            }
        }
        return true;
    }
    public int[][] Remove_cells(int[][] Sudoku, int Diff) {
        int a = 0;
        int b = 9;
        int random_number_1 = 0;
        int random_number_2 = 0;
        int Z = 0;

        for(int i = Diff; i < 81; i++){
            random_number_1 = a + (int) (Math.random() * b);
            random_number_2 = a + (int) (Math.random() * b);

            if (Sudoku[random_number_1][random_number_2] != 0) {
                Sudoku[random_number_1][random_number_2] = 0;
                Z++;
            }
            else
                i--;
        }
        return Sudoku;
    }
    private void Change_cells(String[] Sudoku_convert) {
        for (int i = 0; i < 81; i++) {
            if (Integer.parseInt(Sudoku_convert[i]) == 0)
                Sudoku_convert[i] = " ";
        }
    }
    public String Sudoku_check(String[] sudoku_convert) {
        int Error = 0;
        int Except = 0;
        for (int i = 0; i < 81; i++) {
            if (Sudoku_convert[i] == " ")
                Except++;
            if (Sudoku_convert[i] != " ") {
                if (Sudoku_temp[i / 9][i % 9] != Integer.parseInt(Sudoku_convert[i])) {
                    Error++;
                    Except--;
                }
            }
        }
        if (Error == 0 && Except == 0) {
            myDbManeger.del_and_insert_db();
            Toast.makeText(mContext, "Вы победили!!!", Toast.LENGTH_SHORT).show();
        }

        return "";
    }
}
