package library.sontruong.com.carouselmenu.view;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by truongthanhson on 1/15/17.
 */

public class CarouselView extends ViewGroup {
    private List<CarouselItem> elements;
    private int mainItemPos = -1;
    private int radius;

    public CarouselView(Context context) {
        super(context);
    }

    public void setElements(List<CarouselItem> items) {
        elements.addAll(items);
        setMainItemPos(elements.size() / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        radius = getMeasuredHeight() * 2;

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    public void setMainItemPos(int mainItemPos) {
        this.mainItemPos = mainItemPos;
    }
}
