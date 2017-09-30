package com.example.wxy.twpc_q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        Button button1 = (Button) findViewById(R.id.buttonl);
        Button button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.text);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.buttonl:
                String inputText = editText1.getText().toString();
                if (Integer.parseInt(inputText) > 390000000) {
                    Toast.makeText(MainActivity.this, "输入数值不要超过390000000",
                    Toast.LENGTH_SHORT).show();
                }
                int inputNum = Integer.parseInt(inputText);
                if (inputNum == 0) {
                    textView.setText("0");
                    break;
                }
                int outputNum[];
                outputNum = sqrth(inputNum);
                if (outputNum[0] == -1) {
                    String outputText = String.valueOf(Math.sqrt(inputNum));
                    textView.setText(outputText);
                } else if (outputNum[0] == 0) {
                    String outputText = "√" + String.valueOf(inputNum);
                    textView.setText(outputText);
                } else {
                    String outputText = String.valueOf(outputNum[0]) + "√" + String.valueOf(outputNum[1]);
                    textView.setText(outputText);
                }
                break;

            case R.id.button2:
                String inputText2 = editText2.getText().toString();
                String inputText3 = editText3.getText().toString();
                int inputNum2 = Integer.parseInt(inputText2);
                int inputNum3 = Integer.parseInt(inputText3);
                if (inputNum2 == 0) {
                    textView.setText("0");
                    break;
                }
                int outPutNumG[];
                int outPutNum2;
                int outPutNum3;
                break;
            default:
                break;
        }
    }

    private static int[] sqrth(int n)
    {
        boolean book[] = new boolean[n + 1];

        int i;
        int j;
        int error[] = new int[] {-1, -1};
        int out[] = new  int[] {0, 0};

        for (i = 0; i <= n; i++) {
            book[i] = false;
        }

        for (i = 2; i <= (int)Math.sqrt(n); i++) {
            book[i * i] = true;
        }


        if (!book[n]){
            for (j = 2; j <= n; j++) {
                if (n % j == 0 && book[j]) {
                    out[0] = j;
                }
            }
            out[0] = (int)Math.sqrt((double)out[0]);
            if (out[0] != 0) {
                out[1] = n / out[0] / out[0];
            } else {
                out[1] = 0;
            }
            return out;
        } else {
            return error;
        }
    }

}
