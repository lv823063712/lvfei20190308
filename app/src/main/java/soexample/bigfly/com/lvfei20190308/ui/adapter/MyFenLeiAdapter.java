package soexample.bigfly.com.lvfei20190308.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import soexample.bigfly.com.lvfei20190308.R;
import soexample.bigfly.com.lvfei20190308.bean.MyHomeData;

/**
 * <p>文件描述：<p>
 * <p>作者：${吕飞}<p>
 * <p>创建时间：2019/3/8   9:38<p>
 * <p>更改时间：2019/3/8   9:38<p>
 * <p>版本号：1<p>
 */

public class MyFenLeiAdapter extends RecyclerView.Adapter<MyFenLeiAdapter.ViewHolder> {
    private ArrayList<MyHomeData.DataBean.FenleiBean> datas;
    private Context context;


    public MyFenLeiAdapter(ArrayList<MyHomeData.DataBean.FenleiBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //引入视图
        this.context = viewGroup.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.fenlei_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.MyFenleiTitle.setText(datas.get(i).getName());
        String icon = datas.get(i).getIcon();
        Uri parse = Uri.parse(icon);
        viewHolder.MyFenleiImg.setImageURI(parse);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView MyFenleiImg;
        private TextView MyFenleiTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MyFenleiImg = itemView.findViewById(R.id.MyFenleiImg);
            MyFenleiTitle = itemView.findViewById(R.id.MyFenleiTitle);
        }
    }
}
