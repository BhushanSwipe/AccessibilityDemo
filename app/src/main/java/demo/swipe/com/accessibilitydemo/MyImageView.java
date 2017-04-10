package demo.swipe.com.accessibilitydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by USer on 26-10-2016.
 */

public class MyImageView extends View {
    private Paint mPaintText;
    private Path mArc;

    public MyImageView(Context context) {
        super(context);
        mArc = new Path();
        mArc.addCircle(20, 20, 50, Path.Direction.CW);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintText.setColor(Color.GREEN);
        mPaintText.setTextSize(20f);

        /*mArc = new Path();
        RectF oval = new RectF(50,100,200,250);

        mArc.addArc(oval, -180, 200);*/



    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawTextOnPath("MY_TEXT", mArc, 0, 20, mPaintText);
        canvas.drawTextOnPath("Mon Tues Wed Thur Fri Sat", mArc, 20, 0, mPaintText);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}