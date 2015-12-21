package com.example.tengyu.lifeholder.tomato;

import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tengyu on 2015/12/18.
 */


public class tomatoIO {

    public static List<tomatoTask> testTomatoes(){
        List<tomatoTask> tomatoList = new ArrayList<tomatoTask>();
        tomatoTask tomato1 = new tomatoTask("软件工程前端开发",7,new Date(2015-1900,11,22));
        tomatoList.add(tomato1);
        tomatoTask tomato2 = new tomatoTask("安卓界面原型设计",5,new Date(2015-1900,11,20));
        tomatoList.add(tomato2);
        tomatoTask tomato3 = new tomatoTask("数据库用户级约束",4,new Date(2015-1900,11,23));
        tomatoList.add(tomato3);
        tomatoTask tomato4 = new tomatoTask("数学建模工作",6,new Date(2015-1900,11,25));
        tomatoList.add(tomato4);
        tomatoTask tomato5 = new tomatoTask("锻炼身体",2,new Date(2036-1900,11,31));
        tomatoList.add(tomato5);
        tomatoTask tomato6 = new tomatoTask("用户界面设计",6,new Date(2015-1900,11,19));
        tomatoList.add(tomato6);
        tomatoTask tomato7 = new tomatoTask("图标修正",1,new Date(2015-1900,11,18));
        tomatoList.add(tomato7);
        tomatoTask tomato8 = new tomatoTask("视频剪辑",1,new Date(2016-1900,2,1));
        tomatoList.add(tomato8);
        tomatoTask tomato9 = new tomatoTask("字幕处理",1,new Date(2016-1900,1,22));
        tomatoList.add(tomato9);
        tomatoTask tomato10 = new tomatoTask("冯如杯",7,new Date(2016-1900,3,1));
        tomatoList.add(tomato10);
        Log.d("tomatoTaskInit", "Success");
        return tomatoList;
    }
}
