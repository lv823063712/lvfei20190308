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

public class MyTuiJianAdapter extends RecyclerView.Adapter<MyTuiJianAdapter.ViewHolder> {
    private ArrayList<MyHomeData.DataBean.TuijianBean> datas;
    private Context context;


    public MyTuiJianAdapter(ArrayList<MyHomeData.DataBean.TuijianBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //引入视图
        this.context = viewGroup.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.tuijian_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.MyTuiJianTitle.setText(datas.get(i).getList().get(i).getTitle());
        //切割图片接口
        String icon = datas.get(i).getList().get(i).getImages();
        if (!icon.isEmpty()){
            String[] split = icon.split("\\|");
            if (split!=null){
                for (int j = 0; j < split.length; j++) {
                    String replace = split[j].replace("https", "http");
                    Uri parse = Uri.parse(replace);
                    viewHolder.MyTuiJianImg.setImageURI(parse);
                }
            }else{
                String replace = icon.replace("https", "http");
                Uri parse = Uri.parse(replace);
                viewHolder.MyTuiJianImg.setImageURI(parse);
            }

        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView MyTuiJianImg;
        private TextView MyTuiJianTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MyTuiJianImg = itemView.findViewById(R.id.MyTuiJianImg);
            MyTuiJianTitle = itemView.findViewById(R.id.MyTuiJianTitle);
        }
    }
}
