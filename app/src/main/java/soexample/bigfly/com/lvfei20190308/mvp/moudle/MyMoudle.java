package soexample.bigfly.com.lvfei20190308.mvp.moudle;

import soexample.bigfly.com.lvfei20190308.mvp.mycallback.MyCallBack;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   8:57<p>
 * <p>更改时间：2019/3/8   8:57<p>
 * <p>版本号：1<p>
 */

public interface MyMoudle {

    void startRequest(String url, Class clazz, MyCallBack myCallBack);
}
