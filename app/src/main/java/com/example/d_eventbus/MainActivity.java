package com.example.d_eventbus;
//1.加入APIimplementation 'de.greenrobot:eventbus:3.0.0-beta1'
//2.準備JavaBean Message
//3.註冊監聽的頁面 register(Object subscriber)
//4.準備接收監聽取得Message的MainThread => 可以操作ui
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private Button btnOk;
    private String TAG ="hank";
    private TextView tvAccount;
    private EditText editMsg;
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //3.註冊監聽的頁面 register(Object subscriber)
        EventBus.getDefault().register(this);
        init();

        Log.v(TAG, "onCreate() = > register ");
    }

    private void init() {
        btnOk = findViewById(R.id.btnOk);
        tvAccount = findViewById(R.id.tvAccount);
        editMsg = findViewById(R.id.editMsg);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //5.使用者輸入文字時,bean建構式並將物件傳送給messageEventBus接收
                EventBus.getDefault().post(new Message(editMsg.getText().toString(),"123456"));
                Log.v("hank","onClick = >post() ");
            }
        });
    }

    //4.準備接收監聽取得Message的MainThread => 可以操作ui
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void messageEventBus(Message event) {
        Log.v(TAG, "@Subscribe = > ThreadMode.MainThread ");
        tvAccount.setText(event.getAccount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.v(TAG,"onDestroy => unregister()解除");
    }
}