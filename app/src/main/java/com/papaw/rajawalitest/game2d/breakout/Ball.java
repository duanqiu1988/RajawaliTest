package com.papaw.rajawalitest.game2d.breakout;

import android.util.Log;

import com.papaw.rajawalitest.game2d.Color;
import com.papaw.rajawalitest.game2d.GameObj;

import org.rajawali3d.bounds.BoundingBox;
import org.rajawali3d.bounds.IBoundingVolume;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.math.vector.Vector3;

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
        boolean collision = intersectsWith((BoundingBox) bbox, (BoundingBox) bbox2);
        Log.d(TAG, "checkCollision mVelocityY " + mVelocityY + ", collision " + collision + ", y " + mPosition.y);
        if (collision && mVelocityY < 0) {
            mVelocityY *= -1;
            if (mPosition.y < obj.getY() + mHeight) {
                mPosition.y = obj.getY() + mHeight;
            }
        }
    }

    public static boolean intersectsWith(BoundingBox boundingBox, BoundingBox otherBoundingBox) {
        if (!(boundingBox instanceof BoundingBox
                && otherBoundingBox instanceof BoundingBox)) {
            return false;
        }
        Vector3 otherMin = otherBoundingBox.getTransformedMin();
        Vector3 otherMax = otherBoundingBox.getTransformedMax();
        Vector3 min = boundingBox.getTransformedMin();
        Vector3 max = boundingBox.getTransformedMax();

        return (min.x < otherMax.x) && (max.x > otherMin.x) &&
                (min.y < otherMax.y) && (max.y > otherMin.y);
    }
}
