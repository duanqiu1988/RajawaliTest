package com.papaw.rajawalitest.game2d.breakout;

import android.util.Log;

import com.papaw.rajawalitest.game2d.Color;
import com.papaw.rajawalitest.game2d.GameObj;

import org.rajawali3d.bounds.IBoundingVolume;
import org.rajawali3d.materials.textures.ATexture;

/**
 * Created by duanjunjie on 17-8-10.
 */

public class Ball extends GameObj {
    public static final String TAG = Ball.class.getSimpleName();
    private float maxX;
    private float maxY;

    public Ball(float x, float y, float w, float h, Color color, ATexture texture, float vx, float vy) {
        super(x, y, w, h, color, texture, vx, vy);
        maxX = (1 - w) / 2;
        maxY = (1 - h) / 2;
    }

    public void move(double deltaTime) {
        double offSetx = deltaTime * mVelocityX;
        double offSety = deltaTime * mVelocityY;
        mPosition.x += offSetx;
        mPosition.y += offSety;
        if (mPosition.x >= maxX) {
            mVelocityX *= -1;
            mPosition.x = maxX;
        }
        if (mPosition.x <= -maxX) {
            mVelocityX *= -1;
            mPosition.x = -maxX;
        }
        if (mPosition.y >= maxY) {
            mVelocityY *= -1;
            mPosition.y = maxY;
        }
        if (mPosition.y <= -maxY) {
            mVelocityY *= -1;
            mPosition.y = -maxY;
        }
        setPosition(mPosition.x, mPosition.y, mPosition.z);
    }

    public void checkCollision(GameObj obj) {
        IBoundingVolume bbox = getGeometry().getBoundingBox();
        bbox.transform(getModelMatrix());

        IBoundingVolume bbox2 = obj.getGeometry().getBoundingBox();
        bbox2.transform(obj.getModelMatrix());
        boolean collision = bbox.intersectsWith(bbox2);
        Log.d(TAG, "checkCollision mVelocityY " + mVelocityY + ", collision " + collision);
        if (collision) {
            mVelocityY *= -1;
        }
    }
}
