package library.sontruong.com.carouselmenu.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import static android.R.attr.angle;

/**
 * Created by truongthanhson on 1/15/17.
 */

public class CarouselView extends ViewGroup {
    private int circleHeight, circleWidth;

    public enum FirstChildPosition {
        EAST(0), SOUTH(90), WEST(180), NORTH(270);

        private int angle;

        FirstChildPosition(int angle) {
            this.angle = angle;
        }

        public int getAngle() {
            return angle;
        }

    }
    private List<CarouselItem> elements;
    private int mainItemPos = -1;
    private int radius;

    public CarouselView(Context context) {
        super(context);
    }

    public void setElements(List<CarouselItem> items) {
        elements.addAll(items);
        for(CarouselItem item:items){
            addView(new ImageView(getContext()));
        }
        setMainItemPos(elements.size() / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        radius = getMeasuredHeight() * 2;
        circleHeight = getHeight();
        circleWidth = getWidth();
        setChildAngles();

    }

    public void setMainItemPos(int mainItemPos) {
        this.mainItemPos = mainItemPos;
    }
    private void setChildAngles() {
        int left, top, childWidth, childHeight, childCount = getChildCount();
        float angleDelay = 360.0f / childCount;
        float halfAngle = angleDelay / 2;
        float localAngle = angle;

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            if (localAngle > 360) {
                localAngle -= 360;
            } else if (localAngle < 0) {
                localAngle += 360;
            }

            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();
            left = Math
                    .round((float) (((circleWidth / 2.0) - childWidth / 2.0) + radius
                            * Math.cos(Math.toRadians(localAngle))));
            top = Math
                    .round((float) (((circleHeight / 2.0) - childHeight / 2.0) + radius
                            * Math.sin(Math.toRadians(localAngle))));

            child.setTag(localAngle);

//            float distance = Math.abs(localAngle - firstChildPosition.getAngle());
//            boolean isFirstItem = distance <= halfAngle || distance >= (360 - halfAngle);
//            if (isFirstItem && selectedView != child) {
//                selectedView = child;
//                if (onItemSelectedListener != null && isRotating) {
//                    onItemSelectedListener.onItemSelected(child);
//                }
//            }

            child.layout(left, top, left + childWidth, top + childHeight);
            localAngle += angleDelay;
        }
    }
}
