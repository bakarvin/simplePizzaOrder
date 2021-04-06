package com.bakarvin.pizzatime.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bakarvin.pizzatime.R;
import com.bakarvin.pizzatime.databinding.ItemOrderDetailBinding;
import com.bakarvin.pizzatime.databinding.ItemToppingsBinding;

import java.util.ArrayList;
import java.util.Map;

public class AdapterExtraMap extends BaseAdapter {

    private  final ArrayList mTop;

    public AdapterExtraMap(Map<String, String> map) {
        mTop = new ArrayList();
        mTop.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mTop.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int postion) {
        return (Map.Entry) mTop.get(postion);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final  View result;
        if (convertView == null){
//            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//            ItemOrderDetailBinding detailBinding = ItemOrderDetailBinding.inflate(layoutInflater, parent, false);
            result=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
//             result = detailBinding.getRoot();
        } else {
            result = convertView;
        }
        Map.Entry<String, String> item = getItem(position);

        ((TextView) result.findViewById(R.id.itemTopNama)).setText(item.getKey());
        ((TextView) result.findViewById(R.id.itemTopPrice)).setText(item.getValue());

        return result;
    }
}
