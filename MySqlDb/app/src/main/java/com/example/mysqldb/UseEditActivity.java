package com.example.mysqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UseEditActivity extends AppCompatActivity {
    private EditText et_uname,et_upass;
    private Userinfo userEdit;//当前要修改的用户信息
    private Handler mainHandler;
    private UserDao userDao;//用户数据操作类实例
    private TextView tv_createDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_edit);

        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);

        tv_createDt = findViewById(R.id.tv_createDt);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            userEdit = (Userinfo) bundle.getSerializable("userEdit");

            et_uname.setText(userEdit.getUname());
            et_upass.setText(userEdit.getUpass());
            tv_createDt.setText(userEdit.getCreateDt());

        }

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
            userEdit.setUname(uname);
            userEdit.setUpass(upass);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = userDao.editUser(userEdit);

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