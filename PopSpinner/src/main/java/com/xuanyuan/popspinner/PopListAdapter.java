package com.xuanyuan.popspinner;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.xuanyuan.popspinner.databinding.ItemSpinerStringBinding;

import java.util.List;


/**
 * @author 罗发新
 * 创建时间：2021/4/29  13:38
 * 邮件：424533553@qq.com
 * CSDN：https://blog.csdn.net/luo_boke
 * <p>
 * 更新时间：2021/4/29  13:38
 * <p>
 * 文件说明：元素显示列表
 */
public class PopListAdapter<T> extends RecyclerView.Adapter<PopListAdapter.ViewHolder> {
    private List<T> list;

    private PopSpinnerItemClickListener<T> listener;

    public void setListener(@NonNull PopSpinnerItemClickListener<T> listener) {
        this.listener = listener;
    }

    public void setDatas(@Nullable  List<T> list) {
        if(list!=null){
            this.list = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    public T getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        ItemSpinerStringBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),  R.layout.item_spiner_string, parent, false);
        return new ViewHolder(binding);
    }

    public void removeList(int position) {
        if (list != null) {
            //删除数据源,移除集合中当前下标的数据
            list.remove(position);
            //刷新被删除的地方
            notifyItemRemoved(position);
            //刷新被删除数据，以及其后面的数据
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        T t = list.get(position);
        if (listener != null) {
            holder.getBinding().textView.setText(listener.setContent(t));
        } else {
            holder.getBinding().textView.setText("");
        }


        holder.getBinding().getRoot().setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemSelected(position, t, listener.setContent(t));
            }
        });
        //立即执行绑定
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private void removeItemByPosition(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSpinerStringBinding binding;

        ViewHolder(ItemSpinerStringBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        ItemSpinerStringBinding getBinding() {
            return binding;
        }
    }
}
