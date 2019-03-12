package soexample.bigfly.com.lvfei20190308.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import java.util.ArrayList;

import soexample.bigfly.com.lvfei20190308.R;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   12:15<p>
 * <p>更改时间：2019/3/8   12:15<p>
 * <p>版本号：1<p>
 */

public class user_defined_View extends LinearLayout {

    private final int widthPixels;
    private String mColor;

    public user_defined_View(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        //像素的宽度
        widthPixels = displayMetrics.widthPixels;
        setOrientation(VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GroupDemo);
        if (typedArray != null) {
            mColor = (String) typedArray.getText(R.styleable.GroupDemo_textColor);
        }
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    //添加数据
    public void setData(ArrayList<String> datas) {
        //创建一个线性布局
        LinearLayout linera = getLinera();
        //进行for循环
        for (int i = 0; i < datas.size(); i++) {
            String name = datas.get(i);
            int numWidth = 0;

        }
    }

    private LinearLayout getLinera() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        this.addView(linearLayout);
        return linearLayout;
    }

}
