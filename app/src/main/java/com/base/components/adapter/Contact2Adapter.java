package com.base.components.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.components.R;
import com.base.components.bean.ContactBean;

import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */

public class Contact2Adapter extends RecyclerView.Adapter<Contact2Adapter.ViewHolder> {

    private List<ContactBean> listContact;
    private Context context;

    public Contact2Adapter(List<ContactBean> listContact, Context context) {
        this.listContact = listContact;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, null);
        return new Contact2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_contact_name.setText(listContact.get(position).getName());
        holder.tv_contact_num.setText(listContact.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_contact_name, tv_contact_num;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_contact_name = itemView.findViewById(R.id.tv_contact_name);
            tv_contact_num = itemView.findViewById(R.id.tv_contact_num);
        }
    }
}
