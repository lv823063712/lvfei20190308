package soexample.bigfly.com.lvfei20190308.utils.api;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   8:39<p>
 * <p>更改时间：2019/3/8   8:39<p>
 * <p>版本号：1<p>
 */

public interface ApiService {
    @GET
    Observable<ResponseBody> get(@Url String url);
}
