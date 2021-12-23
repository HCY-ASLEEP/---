package com.example.final_work;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    private Context context;

    public ItemAdapter(Context context,List<Item> itemList){
        this.context=context;
        this.itemList=itemList;
    }

    public void changeList(List<Item> itemList){
        this.itemList=itemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View temp=View.inflate(context,R.layout.alert_dialog,null);
                builder.setView(temp);

                TextView material=temp.findViewById(R.id.alert_material);
                ImageView image=temp.findViewById(R.id.alert_image);
               Button type=temp.findViewById(R.id.alert_class);
                TextView quest=temp.findViewById(R.id.alert_quest);

                material.setText(holder.type_material.getText().toString());
                type.setText(holder.type_class.getText().toString());

                if(type.getText().toString()=="厨余垃圾"){
                    image.setImageResource(R.drawable.food);
                    quest.setText(R.string.quest_food);
                }
                if(type.getText().toString()=="有害垃圾"){
                    image.setImageResource(R.drawable.harmful);
                    quest.setText(R.string.quest_harmful);
                }
                if(type.getText().toString()=="其他垃圾"){
                    image.setImageResource(R.drawable.other);
                    quest.setText(R.string.quest_other);
                }
                if(type.getText().toString()=="可回收垃圾"){
                    image.setImageResource(R.drawable.recycle);
                    quest.setText(R.string.quest_recycle);
                }

                builder.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item=itemList.get(position);
        holder.imageView.setImageResource(item.getImageId());
        holder.type_material.setText(item.getTypeMaterialId());
        holder.type_class.setText(item.getTypeClassId());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView type_material;
        TextView type_class;
        public ViewHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.item_image);
            type_class=view.findViewById(R.id.type_class);
            type_material=view.findViewById(R.id.type_material);
        }
    }
}
