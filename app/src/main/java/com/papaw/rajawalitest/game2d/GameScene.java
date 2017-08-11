package com.papaw.rajawalitest.game2d;

import android.content.Context;
import android.view.MotionEvent;

import org.rajawali3d.cameras.Camera2D;
import org.rajawali3d.renderer.RajawaliRenderer;
import org.rajawali3d.scene.RajawaliScene;

/**
 * Created by duanjunjie on 17-8-10.
 */

public abstract class GameScene extends RajawaliScene {
    protected Context context;

    public GameScene(Context context, RajawaliRenderer renderer) {
        super(renderer);
        this.context = context;
        switchCamera(new Camera2D());
        initScene();
    }

    @Override
    public abstract void initScene();

    public void onTouchEvent(MotionEvent event) {

    }
}
