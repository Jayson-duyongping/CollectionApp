package com.jayson.show.ui.customview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityDrawerBinding;
import com.jayson.show.ui.customview.drawer.AttributesTool;
import com.jayson.show.ui.customview.drawer.BitmapBuffer;
import com.jayson.show.ui.customview.drawer.BitmapHistory;
import com.jayson.show.ui.customview.drawer.SystemParams;
import com.jayson.show.ui.customview.drawer.view.OvalDrawer;
import com.jayson.show.ui.customview.drawer.view.RectDrawer;
import com.jayson.show.ui.customview.drawer.view.abs.ShapeDrawer;

public class DrawerActivity extends AppCompatActivity {

    ActivityDrawerBinding binding;

    ShapeDrawer shapeDrawer;
    AttributesTool attributesTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_drawer);

        attributesTool = AttributesTool.getInstance();
    }

    public void drawRect(View view) {
        shapeDrawer = new RectDrawer(binding.cusIv);
        binding.cusIv.setShapeDrawer(shapeDrawer);
    }

    public void drawOval(View view) {
        shapeDrawer = new OvalDrawer(binding.cusIv);
        binding.cusIv.setShapeDrawer(shapeDrawer);
    }

    public void onRedo(View view) {
        BitmapBuffer.getInstance().redo();
        SystemParams.isRedo = true;
        binding.cusIv.invalidate();
    }

    public void onFill(View view) {
        attributesTool.setFill(!attributesTool.isFill());
    }

    @Override
    protected void onDestroy() {
        //清空所有栈
        BitmapHistory.getInstance().clear();
        super.onDestroy();
    }
}
