package com.example.tengyu.lifeholder.com.example.tengyu.lifeholder.tomato;

import java.util.Date;

/**
 * Created by tengyu on 2015/12/18.
 */
public class tomatoTask {
        private String title;
        private String tag;
        private int tomato;
        private int imageId;
        private Date time;


    public tomatoTask(String title,int tomato, Date time){
        this.title = new String(title);
        this.tomato = tomato;
        this.tag = "";
        this.imageId = 0;
        this.time = time;
    }

    public tomatoTask(String title,int tomato){
            this.title = new String(title);
            this.tomato = tomato;
            this.tag = "";
            this.imageId = 0;
            this.time = new Date();
        }

        public void setTag(String tag){
            this.tag = new String(tag);
        }

        public void setImage(int imageId){
            this.imageId = imageId;
        }

        public String getTitle(){
            return this.title;
        }

        public String getTag(){
            return this.tag;
        }

        public int getTomato(){
            return this.tomato;
        }

        public int getImageId(){
            return this.imageId;
        }

        public Date getDate()   {return this.time;  }
}
