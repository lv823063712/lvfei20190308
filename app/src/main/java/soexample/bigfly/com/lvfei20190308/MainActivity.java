package soexample.bigfly.com.lvfei20190308;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.yzq.zxinglibrary.common.Constant;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.bigfly.com.lvfei20190308.base.BaseActivity;
import soexample.bigfly.com.lvfei20190308.bean.MyHomeData;
import soexample.bigfly.com.lvfei20190308.mvp.ipresenter.IPresenterImpl;
import soexample.bigfly.com.lvfei20190308.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190308.ui.activity.Main2Activity;
import soexample.bigfly.com.lvfei20190308.ui.activity.Main3Activity;
import soexample.bigfly.com.lvfei20190308.ui.activity.Main4Activity;
import soexample.bigfly.com.lvfei20190308.ui.adapter.MyFenLeiAdapter;
import soexample.bigfly.com.lvfei20190308.ui.adapter.MyMiaoShaAdapter;
import soexample.bigfly.com.lvfei20190308.ui.adapter.MyTuiJianAdapter;
import soexample.bigfly.com.lvfei20190308.utils.api.Constans;

public class MainActivity extends BaseActivity implements IView {
    @BindView(R.id.MyEdit)
    EditText MyEdit;
    @BindView(R.id.MyBanner)
    XBanner MyBanner;
    @BindView(R.id.MyGridRecy)
    RecyclerView MyGridRecy;
    @BindView(R.id.MyContentRecy)
    RecyclerView MyContentRecy;
    @BindView(R.id.MyTuijiantRecy)
    RecyclerView MyTuijiantRecy;
    @BindView(R.id.zxing)
    ImageView zxing;
    private IPresenterImpl iPresenter;
    private ArrayList<MyHomeData.DataBean.BannerBean> BannerData = new ArrayList<>();
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<MyHomeData.DataBean.FenleiBean> FenLeiData = new ArrayList<>();
    private MyFenLeiAdapter fenLeiAdapter;
    private ArrayList<MyHomeData.DataBean.MiaoshaBean> MiaoShaData = new ArrayList<>();
    private MyMiaoShaAdapter myMiaoShaAdapter;
    private ArrayList<MyHomeData.DataBean.TuijianBean> TuiJianData = new ArrayList<>();
    private MyTuiJianAdapter myTuiJianAdapter;
    private final int REQUEST_CODE_SCAN = 1001;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }


    @Override
    protected void setOnClick() {
        //九宫格
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        MyGridRecy.setLayoutManager(manager);
        //秒杀
        LinearLayoutManager miaoshaManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        MyContentRecy.setLayoutManager(miaoshaManager);
        //推荐
        GridLayoutManager tuijianManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        MyTuijiantRecy.setLayoutManager(tuijianManager);

        MyEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });

    }


    @OnClick(R.id.zxing)
    public void onViewClicked() {
        if (!initpermission()) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("没有开启摄像机权限,是否去设置开启")
                    .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //调用系统内部去开启权限
                            ApplicationInfo(MainActivity.this);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫面二维码/条码
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                data.getStringExtra(Constant.CODED_CONTENT);

            }
        }
    }

    public static void ApplicationInfo(Activity activity) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 9) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        activity.startActivity(intent);

    }


    public boolean initpermission() {
        String camera = Manifest.permission.CAMERA;
        boolean fat = false;
        //6.0以上的动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fat = PermissionChecker.checkSelfPermission(MainActivity.this, camera) == PermissionChecker.PERMISSION_GRANTED;
        }
        return fat;
    }


    @Override
    protected void preLogic() {
        iPresenter = new IPresenterImpl(this);
        iPresenter.startRequest(Constans.HOME_URL, MyHomeData.class);
    }

    @Override
    public void success(Object data) {
        final MyHomeData myHomeData = (MyHomeData) data;
        List<MyHomeData.DataBean.BannerBean> banner = myHomeData.getData().getBanner();
        for (int i = 0; i < banner.size(); i++) {
            datas.add(banner.get(i).getIcon());
        }
        if (!datas.isEmpty()) {
            MyBanner.setData(myHomeData.getData().getBanner(), null);
            MyBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(MainActivity.this).load(myHomeData.getData().getBanner().get(position).getIcon()).into((ImageView) view);
                }
            });
            //横向移动banner图
            MyBanner.setPageTransformer(Transformer.Default);
        }
        BannerData.addAll(myHomeData.getData().getBanner());
        //分类
        FenLeiData.addAll(myHomeData.getData().getFenlei());
        fenLeiAdapter = new MyFenLeiAdapter(FenLeiData, this);
        MyGridRecy.setAdapter(fenLeiAdapter);
        //秒杀
        MiaoShaData.add(myHomeData.getData().getMiaosha());
        myMiaoShaAdapter = new MyMiaoShaAdapter(MiaoShaData, this);
        MyContentRecy.setAdapter(myMiaoShaAdapter);
        myMiaoShaAdapter.setClickCallBack(new MyMiaoShaAdapter.ClickCallBack() {
            @Override
            public void callback(String urls) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });


        //推荐
        TuiJianData.add(myHomeData.getData().getTuijian());
        myTuiJianAdapter = new MyTuiJianAdapter(TuiJianData, this);
        MyTuijiantRecy.setAdapter(myMiaoShaAdapter);

    }


    @Override
    public void error(Object error) {
        Log.e("错误警告", "error: " + error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDatacth();
    }

}
