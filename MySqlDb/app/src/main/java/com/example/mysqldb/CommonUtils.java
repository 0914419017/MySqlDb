package com.example.mysqldb;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*自定义通用工具类*/
public class CommonUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static String getDateStrFromNow(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return df.format(new Date());
    }

    /**
     * 从日期时间中获取时间字符串
     * @return
     */
    public static String getStringFromDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return df.format(df);
    }

    /**
     * 显示短信息
     * @param context 上下文
     * @param msg 要显示的信息
     */
    public static void showShortMsg(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示长信息
     *
     * @param context 上下文
     * @param msg     要显示的信息
     */
    public static void showLogMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showDlgMsg(Context context,String msg){
        new AlertDialog.Builder(context)
                .setTitle("提示信息")
                .setMessage(msg)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create().show();
    }
}
