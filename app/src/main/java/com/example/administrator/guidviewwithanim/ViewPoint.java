package com.example.administrator.guidviewwithanim;

/**
 * Created by Lee_yting on 2016/11/23 0023.
 * 说明：
 */
public class ViewPoint {
    float x, y;
    float x1, y1;
    float x2, y2;
    int opeeation;

    public ViewPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ViewPoint(float x, float y, int opeeation) {
        this.x = x;
        this.y = y;
        this.opeeation = opeeation;
    }

    public ViewPoint(float x, float y, float x1, float y1, int opeeation) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.opeeation = opeeation;
    }

    public ViewPoint(int opeeation, float x, float y, float x1, float y1, float x2, float y2) {
        this.opeeation = opeeation;
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * 设置起点
     **/
    public static ViewPoint moveTo(float x, float y, int opeeation) {
        return new ViewPoint(x, y, opeeation);

    }

    /**
     * 直线移动
     **/
    public static ViewPoint lintTo(float x, float y, int opeeation) {
        return new ViewPoint(x, y, opeeation);
    }

    /**
     * 二阶贝塞尔曲线移动
     **/
    public static ViewPoint quadTo(float x, float y, float x1, float y1, int opeeation) {
        return new ViewPoint(x, y, x1, y1, opeeation);
    }

    /**
     * 三阶贝塞尔曲线移动
     **/
    public static ViewPoint curveTo(float x, float y, float x1, float y1, float x2, float y2, int opeeation) {
        return new ViewPoint(opeeation, x, y, x1, y1, x2, y2);
    }
}
