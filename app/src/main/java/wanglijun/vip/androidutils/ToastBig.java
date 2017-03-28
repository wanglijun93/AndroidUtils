package wanglijun.vip.androidutils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.travelsky.airportapp.R;

/**
 * Created by iwanglijun on 2016/10/11.
 */
public class ToastBig {

    private static Toast toast;

    public static void toast(Context context, String chapterName) {
        toast = new Toast(context);
        if (toast == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.book_reading_seekbar_toast, null);
            TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
            chapterNameTV.setText(chapterName);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, PixelFormat.formatDipToPx(context, 70));
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);

        }else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.book_reading_seekbar_toast, null);
            TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
            chapterNameTV.setText(chapterName);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, PixelFormat.formatDipToPx(context, 70));
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
        }

        toast.show();
    }


}
