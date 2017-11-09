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
    private TextView textView1;

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
        textView1 = (TextView) findViewById(R.id.text1);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()) {
            //计算第一行的整数化简
            case R.id.buttonl:
                //用于存储输入的数字的字符串形式
                String inputText = editText1.getText().toString();
                //判断越界（无效）
                if (Integer.parseInt(inputText) > 390000000) {
                    Toast.makeText(MainActivity.this, "输入数值不要超过390000000",
                    Toast.LENGTH_SHORT).show();
                    break;
                }
                //用于存储输入数字的int形式
                int inputNum = Integer.parseInt(inputText);
                if (inputNum == 0) {    //如果输入0则直接输出0
                    textView.setText("0");
                    break;
                }
                /*
                    用于存储输出数字的int型，其中，第0个元素存储为-1时说明所输入数开方能够开尽，为0时说明所输入数
                    为最简二次根式，不符合以上两种情况时，所储存数为二次根式系数。在第三种情况下，第1个元素所储存
                    的为化简后根号下剩余的数。
                 */
                int outputNum[];
                outputNum = sqrth(inputNum);
                //输出结果
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
//                int inputNum2 = 1;
                int inputNum3 = Integer.parseInt(inputText3);
                if (inputNum2 == 0) {
                    textView.setText("0");
                    break;
                }
                if (inputNum3 == 0) {
                    Toast.makeText(MainActivity.this, "分母不能为0",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                if (inputNum2 == 1) {
                    int outPutNumG[];
                    outPutNumG = sqrth(inputNum3);
                    if (outPutNumG[0] == -1) {
                        String outputText = String.valueOf((int) Math.sqrt(inputNum2));
                        String outputText1 = String.valueOf((int) Math.sqrt(inputNum3));
                        textView.setText(outputText);
                        textView1.setText(outputText1);
                    } else if (outPutNumG[0] == 0) {
                        String outputText = "√" + String.valueOf(inputNum3);
                        String outputText1 = String.valueOf(inputNum3);
                        textView.setText(outputText);
                        textView1.setText(outputText1);
                    } else {
                        String outputText = "√" + String.valueOf(outPutNumG[1]);
                        String outputText1 = String.valueOf(outPutNumG[0] * outPutNumG[1]);
                        textView.setText(outputText);
                        textView1.setText(outputText1);
                    }
                }
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
