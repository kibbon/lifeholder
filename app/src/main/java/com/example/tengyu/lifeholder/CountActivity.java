package com.example.tengyu.lifeholder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.tengyu.lifeholder.tomato.tomatoNotes;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.sefford.circularprogressdrawable.CircularProgressDrawable;

import java.util.Random;

import cn.iwgang.countdownview.CountdownView;

public class CountActivity extends AppCompatActivity {
    ImageView tomato_progress;
    CircularProgressButton tomato_fight;
    private CircularProgressDrawable drawable;
    private CountdownView tomato_count;
    private NiftyDialogBuilder cancelWarning;
    private Random seed = new Random();
    private Handler mHandler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tomato_fight.setProgress(0);
            simulateSuccessProgress(tomato_fight);
        }
    };

    private Runnable mRunnable3 = new Runnable() {
        @Override
        public void run() {
            mHandler3.sendEmptyMessage(1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tomatotask_count_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView tomato_title = (TextView) findViewById(R.id.tomatotask_count_title);
        tomato_title.setText(getIntent().getStringExtra("TITLE"));
        tomato_title.setSelected(true);
        tomato_progress = (ImageView) findViewById(R.id.tomato_drawable);

        drawable = new CircularProgressDrawable.Builder()
                .setRingWidth(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(getResources().getColor(android.R.color.darker_gray))
                .setRingColor(getResources().getColor(R.color.themeHolyLight))
                .setCenterColor(getResources().getColor(R.color.themeLight))
                .create();
        drawable.setCircleScale(0);

        tomato_progress.setImageDrawable(drawable);

        tomato_fight = (CircularProgressButton) findViewById(R.id.tomatotask_count_fightbtn);
        tomato_fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tomato_fight.getProgress() == 0) {
                    tomato_fight.setProgress(-1);
                    prepareStyle1Animation().start();
                } else if (tomato_fight.getProgress() == 100) {
                    Intent intent = new Intent(CountActivity.this,MainActivity.class);
                    intent.putExtra("TOMATO", "TRUE");
                    setResult(RESULT_FIRST_USER,intent);
                    finish();
                } else
                    tomato_fight.setProgress(-1);
            }
        });

        tomato_count =(CountdownView) findViewById(R.id.tomatotask_counts);

        cancelWarning = NiftyDialogBuilder.getInstance(this);
        cancelWarning.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelWarning.dismiss();//DO NOTHING
                //Intent intent = new Intent();
                if(tomato_fight.getProgress()==100){
                    Intent intent = new Intent();
                    intent.putExtra("TOMATO", "TRUE");
                    setResult(RESULT_FIRST_USER, intent);
                    finish();
                }
                else{
                    Intent intent = new Intent();
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        });
        cancelWarning.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelWarning.dismiss();//DO NOTHING
            }
        });
        cancelWarning.setCancelable(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), tomatoNotes.sntArray[seed.nextInt(tomatoNotes.LENGTH)],Toast.LENGTH_LONG).show();
            }
        });

        if(savedInstanceState != null){
            Long tempDate = savedInstanceState.getLong("TIME_REMAIN");
            tomato_count.start(tempDate);
            mHandler3 .postDelayed(mRunnable3, tempDate);
        }
    }

    private Animator prepareStyle1Animation() {
        AnimatorSet animation = new AnimatorSet();
        final long stime =  (long)60 * 25 * 1000;
        final Animator indeterminateAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0, 3600);
        indeterminateAnimation.setDuration(3600);

        Animator innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0f, 0.75f);
        innerCircleAnimation.setDuration(3600);
        innerCircleAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                drawable.setIndeterminate(true);
                tomato_count.start(stime);
                mHandler3 .postDelayed(mRunnable3, stime);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                indeterminateAnimation.end();
                drawable.setIndeterminate(false);
                drawable.setProgress(0);
            }
        });

        animation.playTogether(innerCircleAnimation, indeterminateAnimation);
        return animation;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        long tempDate = tomato_count.getRemainTime();
        outState.putLong("TIME_REMAIN",tempDate);
    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(tomato_fight.getProgress()==100){
            Intent intent = new Intent();
            intent.putExtra("TOMATO", "TRUE");
            setResult(RESULT_FIRST_USER,intent);
            finish();
        }
        else if(tomato_fight.getProgress()==-1){
            cancelWarning.withTitle("Warning")
                    .withMessage(" Are you sure to leave ? ")             //.withMessage(null)  no Msg
                    .withDialogColor(getResources().getColor(R.color.themeLight))
                    .withDialogColor(getResources().getColor(R.color.theme))
                    .withIcon(getResources().getDrawable(R.drawable.ic_action_warning))
                    .withDuration(600)                                          //def
                    .withEffect(Effectstype.RotateBottom)                               //def Effectstype.Slidetop
                    .withButton1Text("OK")                                      //def gone
                    .withButton2Text("Canceled")                                  //def gone
                    .isCancelableOnTouchOutside(true).show();
        }
        else{
            Intent intent = new Intent();
            setResult(RESULT_CANCELED);
            finish();
        }

    }

}
