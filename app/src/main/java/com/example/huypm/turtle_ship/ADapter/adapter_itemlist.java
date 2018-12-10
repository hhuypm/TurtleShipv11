package com.example.huypm.turtle_ship.ADapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CursorAdapter;

import android.widget.TextView;

import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.R;

public class adapter_itemlist extends CursorAdapter {
    public adapter_itemlist(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView lv_Ten = (TextView) view.findViewById(R.id.lv_Ten);
        TextView lv_DiachiGui = (TextView) view.findViewById(R.id.lv_DiachiGui);
        TextView lv_DiachiNhan = (TextView) view.findViewById(R.id.lv_DiachiNhan);
        TextView lv_Sodienthoai = (TextView) view.findViewById(R.id.lv_Sodienthoai);
        // Extract properties from cursor
                lv_Ten.setText(cursor.getString(2).toString());
                TurtleShipManager db = new TurtleShipManager(context);
                Cursor dcgui = db.getDiaChi(cursor.getInt(4));
                Cursor dcnhan = db.getDiaChi(cursor.getInt(5));
                lv_DiachiGui.setText("Địa chỉ gửi hàng:"+dcgui.getString(5)+", "+dcgui.getString(4)+", "+dcgui.getString(3));
                lv_DiachiNhan.setText("Địa chỉ nhận hàng:"+dcnhan.getString(5)+", "+dcnhan.getString(4)+", "+dcnhan.getString(3));
                lv_Sodienthoai.setText(cursor.getString(3));


    }
}



