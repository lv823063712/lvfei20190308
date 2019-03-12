package soexample.bigfly.com.lvfei20190308.mvp.moudle;

import com.google.gson.Gson;

import soexample.bigfly.com.lvfei20190308.mvp.mycallback.MyCallBack;
import soexample.bigfly.com.lvfei20190308.utils.RetrofitUtils;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   8:58<p>
 * <p>更改时间：2019/3/8   8:58<p>
 * <p>版本号：1<p>
 */

public class MyMoudleImpl implements MyMoudle {

    @Override
    public void startRequest(String url, final Class clazz, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().get(url, new RetrofitUtils.myHttpListener() {
            @Override
            public void success(Object data) {
                Gson gson = new Gson();
                Object o = gson.fromJson((String) data, clazz);
                myCallBack.success(o);
            }

            @Override
            public void error(Object error) {
                myCallBack.error(error);
            }
        });

    }
}
