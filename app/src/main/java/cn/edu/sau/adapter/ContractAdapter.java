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
import cn.edu.sau.pojo.VO.ContractVO;
import cn.edu.sau.ui.ContractOne;
import cn.edu.sau.url.UrlString;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {

    private List<ContractVO> list;
    private Context context;

    //绑定传入的数据源
    public ContractAdapter(List<ContractVO> list) {
        this.list = list;
    }
    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public ContractAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_contract, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.e("onClick: ", String.valueOf(position));
                ContractVO contract = list.get(position);

                //点击事件，跳转详细界面
                Intent intent = new Intent(v.getContext(),ContractOne.class);
                intent.putExtra("id", String.valueOf(contract.getId()));
                intent.putExtra(KeyValue.CP, String.valueOf(contract.getContract_photo()));
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    //实现onBindViewHolder方法，设置子Item上各个实例
    @Override
    public void onBindViewHolder(ContractAdapter.ViewHolder holder, final int position) {
        ContractVO contract = list.get(position);
        Glide.with(context)
                .load(UrlString.BASE_URL + contract.getContract_photo())
                .into(holder.image);
        holder.ownername.setText("房主：" + contract.getOwner_name());
        holder.tenantname.setText("租户：" + contract.getRenter_name());
        Log.e("图片：", contract.getContract_photo());
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
        TextView ownername;
        TextView tenantname;
        ViewHolder(View itemView) {
            super(itemView);
            listview = itemView;
            image = itemView.findViewById(R.id.image);
            ownername = itemView.findViewById(R.id.ownername);
            tenantname = itemView.findViewById(R.id.tenantname);
        }
    }
}