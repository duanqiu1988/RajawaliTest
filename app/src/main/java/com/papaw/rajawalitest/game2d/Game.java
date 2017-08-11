package com.papaw.rajawalitest.game2d;

import android.content.Context;
import android.util.Log;

import com.papaw.rajawalitest.game2d.breakout.BreakoutScene;

/**
 * Created by duanjunjie on 17-8-10.
 */

public class Game {
    public static final String TAG = Game.class.getSimpleName();
    private Context context;
    private GameRenderer renderer;

    public Game(Context context) {
        this.context = context;
        renderer = new GameRenderer(context) {
            @Override
            protected void initScene() {
                init();
            }
        };
    }

    private void init() {
        Log.d(TAG, "init");
        renderer.addAndSwitchScene(new BreakoutScene(Game.this.context, renderer));
    }

    public GameRenderer getRenderer() {
        return renderer;
    }
}
