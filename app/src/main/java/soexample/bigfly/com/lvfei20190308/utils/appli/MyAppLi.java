package soexample.bigfly.com.lvfei20190308.utils.appli;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   9:47<p>
 * <p>更改时间：2019/3/8   9:47<p>
 * <p>版本号：1<p>
 */

public class MyAppLi extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //磁盘缓存的配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(getCacheDir())
                .setMaxCacheSize(8 * 1024 * 1024)
                .build();

        //把磁盘缓存配置  设置到三级缓存中
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        //fresco初始化
        Fresco.initialize(this, config);

    }
}
