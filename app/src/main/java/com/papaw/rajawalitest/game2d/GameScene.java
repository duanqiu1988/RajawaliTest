package com.papaw.rajawalitest.game2d;

import android.content.Context;

import org.rajawali3d.cameras.Camera2D;
import org.rajawali3d.renderer.RajawaliRenderer;
import org.rajawali3d.scene.ASceneFrameCallback;
import org.rajawali3d.scene.RajawaliScene;

/**
 * Created by duanjunjie on 17-8-10.
 */

public abstract class GameScene extends RajawaliScene {
    protected Context context;
    private ASceneFrameCallback frameCallback = new ASceneFrameAdapter() {
        @Override
        public void onPreFrame(long sceneTime, double deltaTime) {
            update(sceneTime, deltaTime);
        }

        @Override
        public boolean callPreFrame() {
            return true;
        }
    };

    public GameScene(Context context, RajawaliRenderer renderer) {
        super(renderer);
        this.context = context;
        switchCamera(new Camera2D());
        registerFrameCallback(frameCallback);
        initScene();
    }

    @Override
    public abstract void initScene();

    public void onScroll(float distanceX, float distanceY) {

    }

    public void onSingleTapUp() {

    }

    public void update(long sceneTime, double deltaTime) {
    }
}
