package ICR.com.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ICR.com.R;

/**
 * 会议室状态适配器，几乎可以全复制，只需要改一下下面所有出现了status的地方，以及status类中定义的不同变量
 */
public class StatusAdapter extends ArrayAdapter<Room2> {
    private int resourceId;
    public StatusAdapter(Context context, int textViewResourceId,
                       List<Room2> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }//改“Room”
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Room2 Room = getItem(position);
        View view;
        if(convertView == null)
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
        else
            view = convertView;
        //如果item内有更多textview或者imageview还得加
        TextView Third = (TextView) view.findViewById(R.id.room_status_item_1);
        TextView Fourth = (TextView) view.findViewById(R.id.room_status_item_2);
        Third.setText(Room.getName()+"会议室");
        Fourth.setText(Room.getStatus());
        if(Room.getStatus().equals("可用"))
            Fourth.setTextColor(0xFF00CFFF);
        else
            Fourth.setTextColor(0x77765B5B);
        return view;
    }
}

