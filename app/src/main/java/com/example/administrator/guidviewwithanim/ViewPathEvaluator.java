package com.example.administrator.guidviewwithanim;

import android.animation.TypeEvaluator;

/**
 * Created by Lee_yting on 2016/11/23 0023.
 * 说明：求值函数
 */

/**
 * 动画过程中TypeEvaluator（估值器）的作用：
 * 当我们ValueAnimator.ofObject()函数来做动画效果的时候就会用到估值器了，
 * 估值器说白了就是用来确定在动画过程中每时每刻动画的具体值得换句话说就是
 * 确定ValueAnimator.getAnimatedValue()返回的具体对象类型
 * <p/>
 * TypeEvaluator（估值器）的使用是在KeyframeSet类里面的getValue()函数里面
 * <p/>
 * 估值器计算出来的值最直接反映到的地方就是ValueAnimator类的getAnimatedValue()函数得到的值
 **/
public class ViewPathEvaluator implements TypeEvaluator<ViewPoint> {

    public ViewPathEvaluator() {
    }


    /**
     * 三个参数:fraction: 表示当前这段数值变化值得比例，
     * startValue：表示当前这段数值变化的开始值，
     * endValue: 表示当前这段数据变化的结束值。
     **/
    @Override
    public ViewPoint evaluate(float t, ViewPoint startValue, ViewPoint endValue) {
        float x, y;
        float startX, startY;

        if (endValue.opeeation == ViewPath.LINE) {
            startX = (startValue.opeeation == ViewPath.QUAD) ? startValue.x1 : startValue.x;
            startX = (startValue.opeeation == ViewPath.CUEVE) ? startValue.x2 : startX;
            startY = (startValue.opeeation == ViewPath.QUAD) ? startValue.y1 : startValue.y;
            startY = (startValue.opeeation == ViewPath.CUEVE) ? startValue.y2 : startY;
            x = startX + t * (endValue.x - startX);
            y = startY + t * (endValue.y - startY);


        } else if (endValue.opeeation == ViewPath.CUEVE) {
            startX = (startValue.opeeation == ViewPath.QUAD) ? startValue.x1 : startValue.x;
            startY = (startValue.opeeation == ViewPath.QUAD) ? startValue.y1 : startValue.y;
            float oneMinusT = 1 - t;

            x = oneMinusT * oneMinusT * oneMinusT * startX +
                    3 * oneMinusT * oneMinusT * t * endValue.x +
                    3 * oneMinusT * t * t * endValue.x1 +
                    t * t * t * endValue.x2;

            y = oneMinusT * oneMinusT * oneMinusT * startY +
                    3 * oneMinusT * oneMinusT * t * endValue.y +
                    3 * oneMinusT * t * t * endValue.y1 +
                    t * t * t * endValue.y2;

        } else if (endValue.opeeation == ViewPath.MOVE) {

            x = endValue.x;
            y = endValue.y;

        } else if (endValue.opeeation == ViewPath.QUAD) {


            startX = (startValue.opeeation == ViewPath.CUEVE) ? startValue.x2 : startValue.x;
            startY = (startValue.opeeation == ViewPath.CUEVE) ? startValue.y2 : startValue.y;

            float oneMinusT = 1 - t;
            x = oneMinusT * oneMinusT * startX +
                    2 * oneMinusT * t * endValue.x +
                    t * t * endValue.x1;

            y = oneMinusT * oneMinusT * startY +
                    2 * oneMinusT * t * endValue.y +
                    t * t * endValue.y1;

        } else {
            x = endValue.x;
            y = endValue.y;


        }


        return new ViewPoint(x,y);
    }
}
