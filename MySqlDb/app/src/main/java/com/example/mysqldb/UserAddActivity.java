package com.example.mysqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/*添加用户的业务代码*/
public class UserAddActivity extends AppCompatActivity {
    private EditText et_uname,et_upass;

    private Handler mainHandler;
    private UserDao userDao;//用户数据操作类实例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);
        userDao = new UserDao();

        mainHandler = new Handler(getMainLooper());
    }

    public void btn_ok_click(View view) {
        final String uname = et_uname.getText().toString().trim();
        final String upass = et_upass.getText().toString().trim();
        if (TextUtils.isEmpty(uname)) {
            CommonUtils.showShortMsg(this, "请输入用户名");
            et_uname.requestFocus();
        } else if (TextUtils.isEmpty(upass)) {
            CommonUtils.showShortMsg(this, "请输入用户密码");
            et_upass.requestFocus();
        } else {
            final Userinfo item = new Userinfo();

            item.setUname(uname);
            item.setUpass(upass);
            item.setCreateDt(CommonUtils.getDateStrFromNow());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = userDao.addUser(item);

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setResult(1);//使用参数表示当前界面操作成功 并返回主界面
                            finish();
                        }
                    });
                }
            }).start();
        }
    }
}