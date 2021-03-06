package com.example.administrator.guidviewwithanim;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Lee_yting on 2016/11/23 0023.
 * 说明：偏移坐标
 */
public class ViewPath {
    //四种路径操作  移动 线条 环绕  弧线
    public static final int MOVE=0;
    public static final int LINE=1;
    public static final int QUAD=2;
    public static final int CUEVE=3;


    private ArrayList<ViewPoint> mPoints;

    public ViewPath() {
        this.mPoints = new ArrayList<>();
    }
    /**添加重置起点的路径**/
    public void moveTo(float x,float y){
        mPoints.add(ViewPoint.moveTo(x,y,MOVE));
    }
    /**添加直线移动的路径**/
    public void lineTo(float x,float y){
        mPoints.add(ViewPoint.lintTo(x,y,LINE));
    }
    /**添加三阶贝塞尔曲线的路径**/
    public void curveTo(float x,float y,float x1,float y1,float x2,float y2){
        mPoints.add(ViewPoint.curveTo(x,y,x1,y1,x2,y2,CUEVE));
    }
    /**添加二阶贝塞尔曲线的路径**/
    public void quadTo(float x,float y,float x1,float y1){
        mPoints.add(ViewPoint.quadTo(x,y,x1,y1,QUAD));
    }

    public Collection<ViewPoint> getPointts(){
        return mPoints;

    }
}
