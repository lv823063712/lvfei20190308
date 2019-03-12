package soexample.bigfly.com.lvfei20190308.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   9:07<p>
 * <p>更改时间：2019/3/8   9:07<p>
 * <p>版本号：1<p>
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void setOnClick();

    protected abstract void preLogic();

    void init() {
        if (getLayout() != 0) {
            setContentView(getLayout());
            initView();
            setOnClick();
            preLogic();
        }
    }

}
