package com.example.tengyu.lifeholder.com.example.tengyu.lifeholder.tomato;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.List;
import com.example.tengyu.lifeholder.R;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 * Created by tengyu on 2015/12/18.
 */
public class tomatoTaskAdapter extends ArrayAdapter<tomatoTask> {
    private int resourceId;

    public tomatoTaskAdapter(Context context, int textViewResourceId,List<tomatoTask> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        tomatoTask tomato = getItem(position);
        View view = null;
        ViewHolder viewholder = null;
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);

        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewholder = new ViewHolder();
            //查找每个ViewItem中,各个子View,放进holder中
            viewholder.titles = (TextView) view.findViewById(R.id.tomatotask_title);
            viewholder.subtitles = (TextView) view.findViewById(R.id.tomatotask_subtitle);
            viewholder.tomato = (ImageView) view.findViewById(R.id.tomatotask_image);
            //保存对每个显示的ViewItem中, 各个子View的引用对象
            view.setTag(viewholder);
            Log.d("tomatoTaskAdapter", "InitViewHolderSuccess");
        }
        else// I think this a bug, program can not run here!!!--linc2014.11.12
        {
            view = convertView;
            viewholder = (ViewHolder)view.getTag();
        }

        //获取当前要显示的数据

        viewholder.titles.setText(tomato.getTitle());
        viewholder.subtitles.setText(dateFormat.format(tomato.getDate()));
        Log.d("SetTextView",tomato.getTitle());
        switch (tomato.getTomato()) {
            case 1:
                viewholder.tomato.setImageResource(R.drawable.stomato_1);
                break;
            case 2:
                viewholder.tomato.setImageResource(R.drawable.stomato_2);
                break;
            case 3:
                viewholder.tomato.setImageResource(R.drawable.stomato_3);
                break;
            case 4:
                viewholder.tomato.setImageResource(R.drawable.stomato_4);
                break;
            case 5:
                viewholder.tomato.setImageResource(R.drawable.stomato_5);
                break;
            case 6:
                viewholder.tomato.setImageResource(R.drawable.stomato_6);
                break;
            case 7:
                viewholder.tomato.setImageResource(R.drawable.stomato_7);
                break;
            default:
                viewholder.tomato.setImageResource(R.drawable.stomato_1);
                break;
        }
        return view;
    }

    private static class ViewHolder
    {
        TextView titles;
        TextView subtitles;
        ImageView tomato;
    }
}
