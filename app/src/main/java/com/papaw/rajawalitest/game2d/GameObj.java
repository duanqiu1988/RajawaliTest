package com.papaw.rajawalitest.game2d;

import com.papaw.rajawalitest.game2d.breakout.BreakoutMaterial;

import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.primitives.Plane;

/**
 * Created by duanjunjie on 17-8-10.
 */

public class GameObj extends Plane {
    public static final String TAG = GameObj.class.getSimpleName();
//    protected float mPositionX;
//    protected float mPositionY;
    protected Color mColor;
    protected ATexture mTexture;
    protected float mVelocityX;
    protected float mVelocityY;

    public GameObj(float x, float y, float w, float h, Color color, ATexture texture, float vx, float vy) {
        super(w, h, 1, 1, 1);
//        mPositionX = x;
//        mPositionY = y;
        mVelocityX = vx;
        mVelocityY = vy;
        mMaterial = new BreakoutMaterial();
        mColor = color;
        mTexture = texture;
        if (mColor != null) {
            mMaterial.setColor(this.mColor.floatValue());
        }
        if (mTexture != null) {
            try {
                mMaterial.addTexture(mTexture);
            } catch (ATexture.TextureException e) {
                e.printStackTrace();
            }
        }
        setMaterial(mMaterial);
        setTransparent(true);
        position(x, y);
    }

    protected void position(float x, float y) {
        float xOff = (1 - mWidth) / 2 - x;
        float yOff = (1 - mHeight) / 2 - y;
        moveRight(-xOff);
        moveUp(yOff);
    }
}
