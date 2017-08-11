package com.papaw.rajawalitest.game2d.breakout;

import com.papaw.rajawalitest.game2d.Color;
import com.papaw.rajawalitest.game2d.GameObj;

import org.rajawali3d.materials.textures.ATexture;

/**
 * Created by duanjunjie on 17-8-10.
 */

public class Paddle extends GameObj {
    public static final String TAG = Paddle.class.getSimpleName();
    private float maxX;

    public Paddle(float x, float y, float w, float h, Color color, ATexture texture, float vx, float vy) {
        super(x, y, w, h, color, texture, vx, vy);
        maxX = (1 - w) / 2;
    }

    public void move(float offSet) {
        offSet *= mVelocityX;
        mPosition.x += offSet;
        if (mPosition.x >= maxX) {
            mPosition.x = maxX;
        }
        if (mPosition.x <= -maxX) {
            mPosition.x = -maxX;
        }
        setPosition(mPosition.x, mPosition.y, mPosition.z);
    }
}
