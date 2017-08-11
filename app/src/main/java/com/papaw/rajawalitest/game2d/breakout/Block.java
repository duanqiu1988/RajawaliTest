package com.papaw.rajawalitest.game2d.breakout;

import com.papaw.rajawalitest.game2d.Color;
import com.papaw.rajawalitest.game2d.GameObj;

import org.rajawali3d.materials.textures.ATexture;

/**
 * Created by duanjunjie on 17-8-10.
 */

public class Block extends GameObj {
    protected boolean isSolid;
    protected boolean destroyed;

    public Block(float x, float y, float w, float h, Color color, ATexture texture, boolean isSolid) {
        super(x, y, w, h, color, texture, 0, 0);
        this.isSolid = isSolid;
        destroyed = false;
    }
}
