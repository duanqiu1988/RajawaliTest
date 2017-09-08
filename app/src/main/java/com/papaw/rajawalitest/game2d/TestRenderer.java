package com.papaw.rajawalitest.game2d;

import android.content.Context;
import android.view.MotionEvent;

import com.papaw.rajawalitest.R;
import com.papaw.rajawalitest.game2d.breakout.BreakoutMaterial;

import org.rajawali3d.cameras.Camera2D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.primitives.Plane;
import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by duanjunjie on 17-9-8.
 */

public class TestRenderer extends RajawaliRenderer {
    public TestRenderer(Context context) {
        super(context);
    }

    @Override
    protected void initScene() {
        Plane plane = new Plane(0.3f, 0.3f, 1, 1);
        Material material = new BreakoutMaterial();
        material.setColor(new float[]{1, 1, 1});
        try {
            material.addTexture(new Texture("ball", R.drawable.awesomeface));
            plane.setMaterial(material);
            plane.setTransparent(true);
        } catch (ATexture.TextureException e) {
            e.printStackTrace();
        }

        Plane plane2 = new Plane(0.3f, 0.3f, 1, 1);
        Material material2 = new BreakoutMaterial();
        material2.setColor(new float[]{1, 1, 1});
        try {
            material2.addTexture(new Texture("background", R.drawable.background));
            plane2.setMaterial(material2);
            plane2.setTransparent(true);
        } catch (ATexture.TextureException e) {
            e.printStackTrace();
        }

        getCurrentScene().switchCamera(new Camera2D());
        getCurrentScene().setBackgroundColor(0.2f, 0.3f, 0.3f, 1f);
        getCurrentScene().addChild(plane2);
        getCurrentScene().addChild(plane);
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }
}
