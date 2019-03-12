package soexample.bigfly.com.lvfei20190308.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soexample.bigfly.com.lvfei20190308.utils.api.ApiService;
import soexample.bigfly.com.lvfei20190308.utils.api.Constans;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   8:45<p>
 * <p>更改时间：2019/3/8   8:45<p>
 * <p>版本号：1<p>
 */

public class RetrofitUtils {

    private final ApiService apiService;

    private RetrofitUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constans.BASE_URL)
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    //静态内部类
    public static RetrofitUtils getInstance() {
        return ViewHolder.RETROFIT_UTILS;
    }

    private static class ViewHolder {
        private static final RetrofitUtils RETROFIT_UTILS = new RetrofitUtils();
    }

    //封装get方法
    public void get(String url, final myHttpListener listener) {
        apiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener!=null){
                            listener.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (listener!=null){
                            try {
                                listener.success(responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //接口回调
    public interface myHttpListener<T> {
        void success(T data);

        void error(T error);
    }


}
