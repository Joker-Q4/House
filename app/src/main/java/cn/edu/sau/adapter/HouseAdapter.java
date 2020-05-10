package cn.edu.sau.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.VO.HouseVO;
import cn.edu.sau.ui.HouseOne;
import cn.edu.sau.url.UrlString;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    private List<HouseVO> list;
    private Context context;

    //绑定传入的数据源
    public HouseAdapter(List<HouseVO> list) {
        this.list = list;
    }
    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public HouseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_house, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.e("onClick: ", String.valueOf(position));
                HouseVO house = list.get(position);

                //点击事件，跳转详细界面
                Intent intent = new Intent(v.getContext(),HouseOne.class);
                intent.putExtra("id", String.valueOf(house.getId()));
                intent.putExtra(KeyValue.IMAGE1, String.valueOf(house.getImage1()));
                intent.putExtra(KeyValue.IMAGE2, String.valueOf(house.getImage2()));
                intent.putExtra(KeyValue.IMAGE3, String.valueOf(house.getImage3()));
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    //实现onBindViewHolder方法，设置子Item上各个实例
    @Override
    public void onBindViewHolder(HouseAdapter.ViewHolder holder, final int position) {
        HouseVO house = list.get(position);
        Glide.with(context)
                .load(UrlString.BASE_URL + house.getImage1())
                .into(holder.image);
        holder.apart.setText("户型：" + house.getApartment());
        holder.rent.setText("租金：" + String.valueOf(house.getRent()));
    }
    //返回子项个数
    @Override
    public int getItemCount() {
        return list.size();
    }
    //定义一个内部类ViewHolder，继承自RecyclerView.ViewHolder，用来缓存子项的各个实例，提高效率
    class ViewHolder extends RecyclerView.ViewHolder {
        View listview;
        ImageView image;
        TextView apart;
        TextView rent;
        ViewHolder(View itemView) {
            super(itemView);
            listview = itemView;
            image = itemView.findViewById(R.id.src);
            apart = itemView.findViewById(R.id.apart);
            rent = itemView.findViewById(R.id.rent);
        }
    }
}