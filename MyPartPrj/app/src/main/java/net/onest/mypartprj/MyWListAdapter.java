package net.onest.mypartprj;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.onest.mypartprj.beans.QuestionBank;
import net.onest.mypartprj.beans.SingleChoice;

import java.util.List;

public class MyWListAdapter extends BaseAdapter {
    private Context myContext;
    private List<SingleChoice> mQList;
    private LayoutInflater layoutinflater;//视图容器，用来导入布局

    static class ViewHolder {
        private TextView tvOpt;
        private TextView tvStemItem;
        private LinearLayout llDetail;
    }
    /*
     * 实例化Adapter
     */
    public MyWListAdapter(Context context, List<SingleChoice> dataSet)
    {
        this.myContext = context;
        this.mQList = dataSet;
        this.layoutinflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mQList.size();
    }

    @Override
    public Object getItem(int position) {
        return mQList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            holder= new ViewHolder();
            view= layoutinflater.inflate(R.layout.wrong_item, null);
            //获取控件
            holder.tvOpt = view.findViewById(R.id.tv_opt);
            holder.tvStemItem = view.findViewById(R.id.tv_stem_item);
            holder.llDetail = view.findViewById(R.id.ll_detail);
            view.setTag(holder);
            if(mQList.get(position).getKeyNum()>1){
                holder.tvOpt.setText("(多选题)");
            }else {
                holder.tvOpt.setText("(单选题)");
            }

            holder.tvStemItem.setText(mQList.get(position).getStem());

            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(myContext, WrongDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.putExtra("fenlei",mQList.get(position).getStem()+"&"+mQList.get(position).getKeyNum());
                    myContext.startActivity(intent);
                }
            });
        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
            if(mQList.get(position).getKeyNum()>1){
                holder.tvOpt.setText("(多选题)");
            }else {
                holder.tvOpt.setText("(单选题)");
            }

            holder.tvStemItem.setText(mQList.get(position).getStem());

            holder.llDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(myContext, WrongDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.putExtra("fenlei",mQList.get(position).getStem()+"&"+mQList.get(position).getKeyNum());
                    myContext.startActivity(intent);
                }
            });
        }


        return view;
    }

}
