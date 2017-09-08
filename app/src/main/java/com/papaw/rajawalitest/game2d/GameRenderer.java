package com.papaw.rajawalitest.game2d;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by duanjunjie on 17-8-10.
 */

public abstract class GameRenderer extends RajawaliRenderer {
    public static final String TAG = GameRenderer.class.getSimpleName();
    private GestureDetector detector;
    private OnGestureAdapter gestureAdapter = new OnGestureAdapter() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            GameScene scene = (GameScene) getCurrentScene();
            scene.onScroll(distanceX, distanceY);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            GameScene scene = (GameScene) getCurrentScene();
            scene.onSingleTapUp();
            return super.onSingleTapUp(e);
        }
    };

    public GameRenderer(Context context) {
        super(context, true);
        detector = new GestureDetector(context, gestureAdapter, new Handler(context.getMainLooper()));
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
    }
}
