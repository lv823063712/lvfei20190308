package soexample.bigfly.com.lvfei20190308.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.bigfly.com.lvfei20190308.R;
import soexample.bigfly.com.lvfei20190308.bean.MyXiangQingData;
import soexample.bigfly.com.lvfei20190308.mvp.ipresenter.IPresenterImpl;
import soexample.bigfly.com.lvfei20190308.mvp.iview.IView;
import soexample.bigfly.com.lvfei20190308.utils.api.Constans;

public class Main2Activity extends AppCompatActivity implements IView {


    @BindView(R.id.MyImg)
    SimpleDraweeView MyImg;
    @BindView(R.id.MyTitle)
    TextView MyTitle;
    @BindView(R.id.MyPrice)
    TextView MyPrice;
    @BindView(R.id.MyXiangqing)
    TextView MyXiangqing;
    @BindView(R.id.MyXiangqingImg)
    SimpleDraweeView MyXiangqingImg;
    private IPresenterImpl iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        iPresenter = new IPresenterImpl(this);
        iPresenter.startRequest(Constans.CONTENT_URL, MyXiangQingData.class);
    }

    @Override
    public void success(Object data) {
        MyXiangQingData qingData = (MyXiangQingData) data;
        String images = qingData.getData().getImages();
        Uri parse = Uri.parse(images);
        MyImg.setImageURI(parse);
        MyTitle.setText(qingData.getData().getTitle());
        MyPrice.setText("¥" + qingData.getData().getPrice());
        MyXiangqing.setText(qingData.getData().getSubhead());
        String icon = qingData.getSeller().getIcon();
        Uri icons = Uri.parse(icon);
        MyXiangqingImg.setImageURI(icons);
    }

    @Override
    public void error(Object error) {
        Log.e("xiangqingye", "error: "+error );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.onDatacth();
    }
}
