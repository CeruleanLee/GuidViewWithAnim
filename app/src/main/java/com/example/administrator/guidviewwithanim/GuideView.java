package com.example.administrator.guidviewwithanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Lee_yting on 2016/11/23 0023.
 * 说明：
 */
public class GuideView extends RelativeLayout {

    int mHeight, mWith;
    int dp80 = Utils.dp2px(getContext(), 80);
    boolean mStart = false;
    ImageView pink, purple, yellow, blue, icon;

    public GuideView(Context context) {
        super(context);
        init();
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL, TRUE);//设置布局水平居中
        lp.addRule(CENTER_VERTICAL, TRUE);//垂直居中
        lp.setMargins(0, 0, 0, dp80);

        pink = new ImageView(getContext());
        pink.setLayoutParams(lp);
        pink.setImageResource(R.mipmap.pink);
        addView(pink);

        purple = new ImageView(getContext());
        purple.setLayoutParams(lp);
        purple.setImageResource(R.mipmap.purple);
        addView(purple);

        yellow = new ImageView(getContext());
        yellow.setLayoutParams(lp);
        yellow.setImageResource(R.mipmap.yellow);
        addView(yellow);

        blue = new ImageView(getContext());
        blue.setLayoutParams(lp);
        blue.setImageResource(R.mipmap.blue);
        addView(blue);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWith = getMeasuredWidth();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && mStart == false) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                    mStart = true;
                }
            }, 500);
        }
    }

    private void start() {
        // 粉色球动画轨迹
        ViewPath redPath = new ViewPath();//偏移坐标
        redPath.moveTo(0, 0);
        redPath.lineTo(mWith / 5 - mWith / 2, 0);
        ViewPath redPath2 = new ViewPath();//偏移坐标
        redPath2.moveTo(mWith / 5 - mWith / 2, 0);
        redPath2.curveTo(-700, -mHeight / 2, mWith / 3 * 2, -mHeight / 3 * 2, 0, -dp80);
        setAnimation(pink, redPath, redPath2);

        // 紫色球动画轨迹
        ViewPath purplePath = new ViewPath();//偏移坐标
        purplePath.moveTo(0, 0);
        purplePath.lineTo(mWith / 5*2 - mWith / 2, 0);
        ViewPath purplePath2 = new ViewPath();//偏移坐标
        purplePath2.moveTo(mWith / 5*2 - mWith / 2, 0);
        purplePath2.curveTo(-300, -mHeight / 2, mWith, -mHeight / 9 * 5, 0, -dp80);
        setAnimation(purple, purplePath, purplePath2);

        //黄色球动画轨迹
        ViewPath yellowPath = new ViewPath();//偏移坐标
        yellowPath.moveTo(0, 0);
        yellowPath.lineTo(mWith / 5*3 - mWith / 2, 0);
        ViewPath yellowPath2 = new ViewPath();//偏移坐标
        yellowPath2.moveTo(mWith / 5*3 - mWith / 2, 0);
        yellowPath2.curveTo(300, mHeight, -mWith, -mHeight / 9 * 5, 0, -dp80);
        setAnimation(yellow, yellowPath, yellowPath2);

        //蓝色球动画轨迹
        ViewPath bluePath = new ViewPath();//偏移坐标
        bluePath.moveTo(0, 0);
        bluePath.lineTo(mWith / 5*4 - mWith / 2, 0);
        ViewPath bluePath2 = new ViewPath();//偏移坐标
        bluePath2.moveTo(mWith / 5*4 - mWith / 2, 0);
        bluePath2.curveTo(700, mHeight/3*2, -mWith/2, mHeight /2, 0, -dp80);
        setAnimation(blue, bluePath, bluePath2);

    }

    private void setAnimation(ImageView target, ViewPath path1, ViewPath path2) {
        //左右平移
        ObjectAnimator anim1 = ObjectAnimator.ofObject(new ImgView(target), "position", new ViewPathEvaluator(), path1.getPointts().toArray());
        anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        anim1.setDuration(800);

        //贝塞尔曲线
        ObjectAnimator anim2 = ObjectAnimator.ofObject(new ImgView(target), "position", new ViewPathEvaluator(), path2.getPointts().toArray());
        anim2.setInterpolator(new AccelerateDecelerateInterpolator());
        addAnimation(anim1, anim2, target);
    }

    private void addAnimation(ObjectAnimator anim1, ObjectAnimator anim2, ImageView target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0.5f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 1, getScale(target), 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 1, getScale(target), 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1800);
        set.playTogether(alpha, scaleX, scaleY, anim2);
        set.addListener(new AnimEndListener(set));
        AnimatorSet set2 = new AnimatorSet();
        set2.playSequentially(anim1, set);
        set2.start();


    }

    private float getScale(ImageView target) {
        if (target == pink) {
            return 3.0f;
        } else if (target == purple) {
            return 2.0f;
        } else if (target == yellow) {
            return 4.5f;
        } else if (target == blue) {
            return 3.5f;
        }
        return 2f;
    }

    private class ImgView {
        private final ImageView img;

        public ImgView(ImageView target) {
            this.img = target;
        }

        public void setPosition(ViewPoint loc) {
            img.setTranslationX(loc.x);
            img.setTranslationY(loc.y);
        }
    }

    private void showLogo() {

        View view = View.inflate(getContext(), R.layout.logo, this);
        icon = (ImageView) view.findViewById(R.id.img);
        ObjectAnimator animator = ObjectAnimator.ofFloat(icon, View.ALPHA, 0, 1f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(100);
        animator.start();
    }

    private class AnimEndListener extends AnimatorListenerAdapter {
        AnimatorSet set;

        public AnimEndListener(AnimatorSet set) {
            this.set = set;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeAllViews();
            showLogo();
        }
    }
}
