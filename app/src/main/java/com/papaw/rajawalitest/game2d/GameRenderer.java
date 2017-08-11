package com.papaw.rajawalitest.game2d;

import android.content.Context;
import android.view.MotionEvent;

import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by duanjunjie on 17-8-10.
 */

public abstract class GameRenderer extends RajawaliRenderer {
    public static final String TAG = GameRenderer.class.getSimpleName();

    public GameRenderer(Context context) {
        super(context, true);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        try {
            GameScene scene = (GameScene) getCurrentScene();
            scene.onTouchEvent(event);
        } catch (Exception ex) {

        }
    }
}
