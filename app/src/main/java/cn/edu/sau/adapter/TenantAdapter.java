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
import cn.edu.sau.pojo.Tenant;
import cn.edu.sau.pojo.VO.TenantVO;
import cn.edu.sau.ui.TenantOne;

public class TenantAdapter extends RecyclerView.Adapter<TenantAdapter.ViewHolder> {

    private List<TenantVO> list;

    //绑定传入的数据源
    public TenantAdapter(List<TenantVO> list) {
        this.list = list;
    }
    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public TenantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tenant, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.e("onClick: ", String.valueOf(position));

                TenantVO tenant = list.get(position);
                //点击事件，跳转详细界面
                Intent intent = new Intent(v.getContext(),TenantOne.class);
                intent.putExtra("id", String.valueOf(tenant.getId()));
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
    public void onBindViewHolder(TenantAdapter.ViewHolder holder, final int position) {
        TenantVO tenant = list.get(position);
        holder.name.setText(String.valueOf(tenant.getName()));
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
            name = itemView.findViewById(R.id.tenantname);
        }
    }
}