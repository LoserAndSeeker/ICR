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
 * 房间适配器，几乎可以全复制，只需要改一下下面所有出现了room的地方，以及room类中定义的不同变量
 */
public class RoomAdapter extends ArrayAdapter<Room2> {
    private int resourceId;
    public RoomAdapter(Context context, int textViewResourceId,
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
        TextView first = (TextView) view.findViewById(R.id.room_item_1);//改这里
        TextView Second = (TextView) view.findViewById(R.id.room_item_2);//改这里
        first.setText(Room.getName()+"会议室 "+"位置："+Room.getPlace());//想设置什么文本
        Second.setText("容量："+Room.getSize()+" 多媒体设备："+Room.getStatus());//同上

        return view;
    }
}
