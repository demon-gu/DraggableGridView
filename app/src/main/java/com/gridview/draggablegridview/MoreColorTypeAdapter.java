package com.gridview.draggablegridview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;



/**
 * Created by Demon.
 */
public class MoreColorTypeAdapter extends BaseAdapter {

    private Context mContext = null;
    private AbsListView.LayoutParams mLayoutParams = null;
    private List<Data> list = null;
    private OnGridItemClickListener listener;
    private final int mCell;
    private boolean isDisplayFloor;

    public MoreColorTypeAdapter(Context context, boolean isDisplayFloor, List<Data> classifyList, int numColumns) {
        super();
        this.mContext = context;
        this.list = classifyList;
        //                int cell = (Utils.getScreenWidth(context) - Utils.dp2px(mContext,15 + 3 * 15)) / 3;
        //                int cell = Utils.getScreenWidth(context) / count;
        //        mCell = (Utils.getScreenWidth(mContext) - (count * Utils.dp2px(mContext, count))) / count;
        mCell = Utils.getScreenWidth(context) / numColumns;
//        mLayoutParams = new AbsListView.LayoutParams(mCell, ViewGroup.LayoutParams.WRAP_CONTENT);
        //        mLayoutParams = new RelativeLayout.LayoutParams(cell, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.isDisplayFloor = isDisplayFloor;
    }

    public void setGridList(List<Data> classifyList) {
        this.list = classifyList;
        notifyDataSetChanged();
    }

    public void setGridList1(List<Data> classifyList) {
        this.list = classifyList;
//        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Data getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_classify, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.reset();
        }
//        convertView.setLayoutParams(mLayoutParams);
//        viewHolder.gridItemImage.setLayoutParams(mLayoutParams);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onItemClick(i);
//            }
//        });
        initItemView(viewHolder,getItem(i));
        return convertView;
    }

    private void initItemView(ViewHolder viewHolder, Data item) {
        viewHolder.gridItemImage.setImageResource(item.getImage());
        viewHolder.gridItemText.setText(item.getName());
        viewHolder.gridItemState.setText(item.getTag());
        viewHolder.gridItemTip.setVisibility(!TextUtils.isEmpty(item.getTip()) ? View.VISIBLE : View.GONE);

        ViewGroup.LayoutParams layoutParams = viewHolder.gridItemTip.getLayoutParams();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutParams);
        lp.setMargins(mCell / 2 + 30, 0, 0, 0);
        viewHolder.gridItemTip.setLayoutParams(lp);

        if (!TextUtils.isEmpty(item.getTip())) {
            viewHolder.gridItemTip.setText(item.getTip());
        }
        if (item.getFooterType() == 0) {
            viewHolder.gridItemState.setBackgroundResource(R.color.colorWhite);
            viewHolder.gridItemState.setTextColor(mContext.getResources().getColor(R.color.textGrayColor));
        } else if (item.getFooterType() == 1) {
            viewHolder.gridItemState.setBackgroundResource(R.drawable.shadow_bg);
            viewHolder.gridItemState.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            viewHolder.gridItemState.setPadding(5, 0, 5, 0);
        }
    }

    public interface OnGridItemClickListener {
        void onItemClick(int position);
    }

    public void setOnGridItemClickListener(OnGridItemClickListener listener) {
        this.listener = listener;
    }


    class ViewHolder {
        private ImageView gridItemImage;
        private TextView gridItemText;
        private TextView gridItemState;
        private TextView gridItemTip;

        ViewHolder(View view) {
            gridItemImage = view.findViewById(R.id.grid_item_image);
            gridItemText = view.findViewById(R.id.grid_item_text);
            gridItemState = view.findViewById(R.id.grid_item_state);
            gridItemTip = view.findViewById(R.id.grid_item_tip);
            view.setTag(this);
        }

        void reset() {

        }
    }
}
