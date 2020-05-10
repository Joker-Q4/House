package cn.edu.sau.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import cn.edu.sau.joker.R;
import cn.edu.sau.pojo.Worker;
import cn.edu.sau.ui.WorkerOne;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private List<Worker> list;
    //绑定传入的数据源
    public WorkerAdapter(List<Worker> list) {
        this.list = list;
    }
    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public WorkerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_worker, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.e("onClick: ", String.valueOf(position));
                Worker worker = list.get(position);

                //点击事件，跳转详细界面
                Intent intent = new Intent(v.getContext(),WorkerOne.class);
                intent.putExtra("workername", worker.getName());
                intent.putExtra("workerpassword", worker.getPassword());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    //实现onBindViewHolder方法，设置子Item上各个实例
    @Override
    public void onBindViewHolder(WorkerAdapter.ViewHolder holder, final int position) {
        Worker worker = list.get(position);
        holder.name.setText(worker.getName());
        holder.password.setText(worker.getPassword());
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
        TextView password;
        ViewHolder(View itemView) {
            super(itemView);
            listview = itemView;
            name = itemView.findViewById(R.id.workername);
            password = itemView.findViewById(R.id.workerpass);
        }
    }

}
