package soexample.bigfly.com.lvfei20190308.mvp.ipresenter;

import soexample.bigfly.com.lvfei20190308.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190308.mvp.moudle.MyMoudleImpl;
import soexample.bigfly.com.lvfei20190308.mvp.mycallback.MyCallBack;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   9:01<p>
 * <p>更改时间：2019/3/8   9:01<p>
 * <p>版本号：1<p>
 */

public class IPresenterImpl implements IPresenter {
    private IView iView;
    private MyMoudleImpl myMoudle;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        myMoudle = new MyMoudleImpl();
    }

    @Override
    public void startRequest(String url, Class clazz) {
        myMoudle.startRequest(url, clazz, new MyCallBack() {
            @Override
            public void success(Object data) {
                iView.success(data);
            }

            @Override
            public void error(Object error) {
                iView.error(error);

            }
        });

    }

    //防止内存泄漏
    public void onDatacth() {
        if (myMoudle != null) {
            myMoudle = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
