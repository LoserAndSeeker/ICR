package ICR.com.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ICR.com.R;

public class ReserveAdapter extends ArrayAdapter<Reserved2> {
    private int resourceId;
    public ReserveAdapter(Context context, int textViewResourceId,
                       List<Reserved2> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Reserved2 reserve = getItem(position);
        View view;
        if(convertView == null)
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
        else
            view = convertView;
        //如果item内有更多textview或者imageview还得加
        TextView first = (TextView) view.findViewById(R.id.reserved_item_1);//改这里
        TextView Second = (TextView) view.findViewById(R.id.reserved_item_2);//改这里
        //TextView Third = (TextView) view.findViewById(R.id.reserved_item_3);
        first.setText(reserve.getConfer_name());//想设置什么文本
        Second.setText(reserve.getName()+"会议室 "+reserve.getDate()+" "+reserve.getStart()+"-"+reserve.getEnd());//同上

        return view;
    }
}