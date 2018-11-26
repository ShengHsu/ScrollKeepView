package myself.sheng.scrollkeepview;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener{
    private int topHeight;
    private LinearLayout mLinearLayout_Panel;
    private View mView_Panel;
    private NestedScrollView mScrollView_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView_Main = (NestedScrollView) findViewById(R.id.scrollview_main);
        mLinearLayout_Panel = (LinearLayout) findViewById(R.id.LinearLayout_Panel);
        mView_Panel = (View) findViewById(R.id.topPanel);

        mScrollView_Main.setOnScrollChangeListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        topHeight = (int) (statusBarHeight + getSupportActionBar().getHeight());
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int[] location = new int[2];
        mLinearLayout_Panel.getLocationOnScreen(location);
        int locationY = location[1];

        if (locationY <= topHeight && (mView_Panel.getVisibility() == View.GONE || mView_Panel.getVisibility() == View.INVISIBLE)) {
            mView_Panel.setVisibility(View.VISIBLE);
        }

        if (locationY > topHeight && mView_Panel.getVisibility() == View.VISIBLE) {
            mView_Panel.setVisibility(View.GONE);
        }
    }
}
