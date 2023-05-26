package com.github.xuefm.element;


import com.github.xuefm.enums.Direction;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Accessors(chain = true)
public abstract class Element {

    protected int x;                          //起始坐标x，相对左上角
    protected int y;                          //起始坐标y，相对左上角
    protected boolean center;                 //是否居中
    protected Direction direction = Direction.LeftRight;    //绘制方向
    @Setter
    protected Float alpha;            // 透明度，取值范围为0~1，值越小越透明
    protected boolean repeat;                 //平铺
    protected int repeatPaddingHorizontal;   //平铺水平间距
    protected int repeatPaddingVertical;     //平铺垂直间距

    protected Element(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
