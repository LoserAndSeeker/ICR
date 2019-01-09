package ICR.com.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ICR.com.R;

public class TimeAdapter extends ArrayAdapter<Time2> {
    private int resourceId;
    public TimeAdapter(Context context, int textViewResourceId,
                       List<Time2> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }//改“Time”
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Time2 time = getItem(position);
        View view;
        if(convertView == null)
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
        else
            view = convertView;
        //如果item内有更多textview或者imageview还得加
        TextView first = (TextView) view.findViewById(R.id.time_item_1);//改这里
        TextView Second = (TextView) view.findViewById(R.id.time_item_2);//改这里
        first.setText(time.getTime());//想设置什么文本
        Second.setText(time.getCan());//同上
        if(time.getCan().equals("可预订"))
            Second.setTextColor(0xFF00CFFF);
        else
            Second.setTextColor(0x77765B5B);
        return view;
    }
}

