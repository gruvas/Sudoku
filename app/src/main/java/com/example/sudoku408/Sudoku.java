package com.example.sudoku408;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.content.pm.ActivityInfo;
    import android.os.Bundle;
    import android.os.SystemClock;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.Button;
    import android.widget.Chronometer;
    import android.widget.GridView;
    import android.widget.TextView;

public class Sudoku extends AppCompatActivity  implements AdapterView.OnItemSelectedListener  {

    private GridView mGridView;
    private Game mGame;
    TextView tV;
    int Pos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tV = (TextView) findViewById(R.id.textView1);

        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        long startTime = SystemClock.elapsedRealtime();
        chronometer.setBase(startTime);
        chronometer.start();

        mGridView = (GridView) findViewById(R.id.field);
        mGame = new Game(getApplicationContext(), android.R.layout.simple_list_item_1);
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
                case R.id.btn1: mGame.getItem(Pos, 1);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn2: mGame.getItem(Pos, 2);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn3: mGame.getItem(Pos, 3);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn4: mGame.getItem(Pos, 4);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn5: mGame.getItem(Pos, 5);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn6: mGame.getItem(Pos, 6);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn7: mGame.getItem(Pos, 7);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn8: mGame.getItem(Pos, 8);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.btn9: mGame.getItem(Pos, 9);
                    mGridView.setAdapter(mGame);
                    break;
                case R.id.Exit_to_change:
                    Intent i = new Intent(this, difName.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    this.finish();
                    break;
            }
        } catch (Exception e){};
    }
}