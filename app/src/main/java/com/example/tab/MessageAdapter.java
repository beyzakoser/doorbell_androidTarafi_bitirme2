package com.example.tab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {
    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView date;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textMessage);
            date=itemView.findViewById(R.id.date);
        }
    }


    List<ResponseMessage> responseMessageList;
    List<ResponseMessage> dateList;


    public MessageAdapter(List<ResponseMessage> responseMessageList) {
        this.responseMessageList=responseMessageList;

    }

    @Override
    public int getItemViewType(int position) {
        if(responseMessageList.get(position).isSender()){
            return R.layout.send_bubble;

        }
        return R.layout.receive_bubble;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.CustomViewHolder holder, int position) {
        holder.textView.setText(responseMessageList.get(position).getTextMesage());
        holder.date.setText(responseMessageList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return responseMessageList.size();
    }
}
