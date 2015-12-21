package com.example.tengyu.lifeholder.tomato;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by tengyu on 2015/12/18.
 */
public class tomatoTask  implements Parcelable {
        private String title;
        private String tag;
        private int tomato;
        private int imageId;
        private int lev;
        private boolean ifRemind;
        private Date time;
        private Date deadline;

    public tomatoTask(String title,int tomato, Date time,int lev,boolean ifRemind){
        this.title = new String(title);
        this.tomato = tomato;
        this.tag = "";
        this.lev = lev;
        this.ifRemind = ifRemind;
        this.imageId = 0;
        this.time = new Date();
        this.deadline = time;
    }

    public tomatoTask(String title,int tomato, Date time){
        this.title = new String(title);
        this.tomato = tomato;
        this.tag = "";
        this.lev = 2;
        this.ifRemind = false;
        this.imageId = 0;
        this.time = new Date();
        this.deadline = time;
    }

    public tomatoTask(String title,int tomato){
            this.title = new String(title);
            this.tomato = tomato;
            this.tag = "";
            this.lev = 2;
            this.ifRemind = false;
            this.imageId = 0;
            this.time = new Date();
            this.deadline = new Date();
        }
    public tomatoTask(){
        this.title = "";
        this.tomato = 1;
        this.tag = "";
        this.lev = 2;
        this.ifRemind = false;
        this.imageId = 0;
        this.time = new Date();
        this.deadline = new Date();
    }

        public void setLev(int lev) { this.lev = lev; }

        public void setIfRemind(boolean ifRemind) { this.ifRemind = ifRemind;}

        public void setTitle(String title) { this.title = title; }

        public void setTag(String tag){
            this.tag = new String(tag);
        }

        public void setImage(int imageId){
            this.imageId = imageId;
        }

        public void setDeadline(Date date){
            this.deadline = date;
        }

        public void setDate(Date date){
            this.time = new Date(date.getTime());
        }

        public void setTomato(int tomato){
            this.tomato = tomato;
        }

        public Date getDeadline() { return this.deadline; }

        public int getLev() { return this.lev;  }

        public boolean IfRemind() { return this.ifRemind; }

        public boolean tomato(){
            if(tomato>0)
                tomato--;
            if(tomato==0)
                return false;
            else
                return true;
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

    public static final Parcelable.Creator<tomatoTask> CREATOR = new Creator<tomatoTask>() {

        @Override
        public tomatoTask createFromParcel(Parcel source) {
            int ifRemind;
            tomatoTask tomato = new tomatoTask();
            tomato.deadline = new Date();
            tomato.title = source.readString();
            tomato.tag = source.readString();
            tomato.tomato = source.readInt();
            tomato.lev = source.readInt();
            tomato.imageId = source.readInt();
            tomato.deadline.setTime(source.readLong());
            ifRemind = source.readInt();
            if(ifRemind==0)
                tomato.ifRemind = false;
            else
                tomato.ifRemind = true;
            return tomato;
        }

        @Override
        public tomatoTask[] newArray(int size) {
            return new tomatoTask[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.title);
        parcel.writeString(this.tag);
        parcel.writeInt(this.tomato);
        parcel.writeInt(this.lev);
        parcel.writeInt(this.imageId);
        parcel.writeLong(this.deadline.getTime());
        if(this.ifRemind)
            parcel.writeInt(1);
        else
            parcel.writeInt(0);
    }
}
