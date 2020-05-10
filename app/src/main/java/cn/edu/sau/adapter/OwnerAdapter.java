package cn.edu.sau.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.VO.OwnerVO;
import cn.edu.sau.ui.HouseOne;
import cn.edu.sau.ui.OwnerOne;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ViewHolder> {

    private List<OwnerVO> list;

    //绑定传入的数据源
    public OwnerAdapter(List<OwnerVO> list) {
        this.list = list;
    }
    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public OwnerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_owner, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.e("onClick: ", String.valueOf(position));
                OwnerVO owner = list.get(position);
                //点击事件，跳转详细界面
                Intent intent = new Intent(v.getContext(),OwnerOne.class);
                intent.putExtra("id", String.valueOf(owner.getId()));
                v.getContext().startActivity(intent);

                //点击事件，跳转详细界面
           /*     Intent intent = new Intent(v.getContext(),WorkerOne.class);
                intent.putExtra("workername", worker.getName());
                intent.putExtra("workerpassword", worker.getPassword());
                v.getContext().startActivity(intent);*/
            }
        });
        return holder;
    }
    //实现onBindViewHolder方法，设置子Item上各个实例
    @Override
    public void onBindViewHolder(OwnerAdapter.ViewHolder holder, final int position) {
        OwnerVO owner = list.get(position);
        holder.name.setText(String.valueOf(owner.getName()));
    }
    //返回子项个数
    @Override
    public int getItemCount() {
        return list.size();
    }
    //定义一个内部类ViewHolder，继承自RecyclerView.ViewHolder，用来缓存子项的各个实例，提高效率
    class ViewHolder extends RecyclerView.ViewHolder {
        View listview;
        TextView name;
        ViewHolder(View itemView) {
            super(itemView);
            listview = itemView;
            name = itemView.findViewById(R.id.ownername);
        }
    }
}