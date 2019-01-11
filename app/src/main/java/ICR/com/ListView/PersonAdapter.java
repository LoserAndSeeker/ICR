package ICR.com.ListView;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ICR.com.R;

public class PersonAdapter extends ArrayAdapter<Person2> {
    private int resourceId;
    public PersonAdapter(Context context, int textViewResourceId,
                       List<Person2> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Person2 person = getItem(position);
        View view;
        if(convertView == null)
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
        else
            view = convertView;
        //如果item内有更多textview或者imageview还得加
        TextView first = (TextView) view.findViewById(R.id.reserved_read_item_1);//改这里
        TextView Second = (TextView) view.findViewById(R.id.reserved_read_item_2);//改这里
        first.setText(person.getName());//想设置什么文本
        if(person.getArrive().equals("删除")){
            Second.setText(person.getArrive());
            Second.setTextColor(0xFFE90730);
            first.getLayoutParams().width = 700;
        }
        else if(person.getArrive().equals("null")){
            Second.setText("未到");
            Second.setTextColor(0xFFE90730);
        }
        else
        {
            Second.setText(person.getArrive());
            Second.setTextColor(0xFF49F123);
        }

        //同上

        return view;
    }
}
