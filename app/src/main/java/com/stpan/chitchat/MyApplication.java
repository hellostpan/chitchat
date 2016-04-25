package com.stpan.chitchat;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.stpan.chitchat.injector.components.ChitChatApplicationComponent;
import com.stpan.chitchat.injector.components.DaggerChitChatApplicationComponent;
import com.stpan.chitchat.injector.modules.ChitChatApplicationModule;


/**
 * 功能：
 * 创建时间:2016/4/9 13:39
 * 作者:pst
 */
public class MyApplication extends Application {
    private static MyApplication myApplication;
    private ChitChatApplicationComponent chitChatApplicationComponent;
    private SharedPreferences sharedPreferences;
    private Typeface typeface;

    private static final String USER_ID = "userId";
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initData();
    }

    public static MyApplication getInstance(){
        return myApplication;
    }


    private void initData(){
        chitChatApplicationComponent = DaggerChitChatApplicationComponent.builder()
                .chitChatApplicationModule(new ChitChatApplicationModule(this))
                .build();
        typeface = Typeface.createFromAsset(getAssets(),"fontawesome-webfont.ttf");
        sharedPreferences = getSharedPreferences("ChitChat",MODE_PRIVATE);
    }

    public ChitChatApplicationComponent getChitChatApplicationComponent() {
        return chitChatApplicationComponent;
    }

    public void setUserId(String userId){
        sharedPreferences.edit().putString(USER_ID,userId);
    }

    public String getUserId(){
        return sharedPreferences.getString(USER_ID,null);
    }

    public Typeface getTypeface() {
        return typeface;
    }


}
