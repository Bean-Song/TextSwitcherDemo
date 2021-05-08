package my.demo.TSDemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import my.demo.TSDemo.R;

/**
 * @ProjectName: TextSwitcherDemo
 * @Package: my.demo.TSDemo.adapter
 * @ClassName: TextSwitcherAdapter
 * @Description: java类作用描述
 * @Author: Beans mac
 * @CreateDate: 2021/5/8 10:57 AM
 * @Version: 1.0
 */
public class TextSwitcherAdapter {

    private TextSwitcher textSwitcher;
    private Context context;

    private int index = 0;//textview上下滚动下标
    private Handler handler = new Handler();
    private boolean isFlipping = false; // 是否启用预警信息轮播
    private List<String> textList = new ArrayList<>();

    public TextSwitcherAdapter(Context context, TextSwitcher textSwitcher, List<String> textList) {
        this.textSwitcher = textSwitcher;
        this.context = context;
        this.textList = textList;
        setTextSwitcher();
        setData();
    }

    private void setTextSwitcher() {
        textSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom));
        textSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_top));
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(context);
                textView.setSingleLine();
                textView.setTextSize(12);//字号
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                textView.setSingleLine();
                textView.setGravity(Gravity.CENTER);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                textView.setLayoutParams(params);
                textView.setPadding(25, 0, 25, 0);
                return textView;
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            index++;
            textSwitcher.setText(textList.get(index % textList.size()));
            if (index == textList.size()) {
                index = 0;
            }
            startFlipping();
        }
    };

    //开启信息轮播
    public void startFlipping() {
        if (textList.size() > 1) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, 3000);
        }
    }

    //关闭信息轮播
    public void stopFlipping() {
        if (textList.size() > 1) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
    }

    //设置数据
    private void setData() {
        if (textList.size() == 1) {
            textSwitcher.setText(textList.get(0));
            index = 0;
        }
        if (textList.size() > 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textSwitcher.setText(textList.get(0));
                    index = 0;
                }
            }, 1000);
            textSwitcher.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom));
            textSwitcher.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_top));
            startFlipping();
        }
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopFlipping();
    }*/
}
