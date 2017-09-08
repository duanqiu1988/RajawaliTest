package com.papaw.rajawalitest.game2d.breakout;

import android.content.Context;

import com.papaw.rajawalitest.R;
import com.papaw.rajawalitest.game2d.Color;
import com.papaw.rajawalitest.game2d.GameObj;
import com.papaw.rajawalitest.game2d.GameScene;

import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by duanjunjie on 17-8-10.
 */

public class BreakoutScene extends GameScene {
    private static final String TAG = BreakoutScene.class.getSimpleName();
    private GameObj bg;
    private BreakoutLevel level;
    private Paddle paddle;
    private Ball ball;
    private static float frameRate = 60;
    private static float paddleH = 0.05f;
    private static float paddleW = 0.15f;
    private static float paddleVelocityX = 0.0007f;
    private static float ballRadius = 0.03f;
    private static float ballVelocityX = 0.5f;
    private static float ballVelocityY = 1f;
    private State mState;

    public BreakoutScene(Context context, RajawaliRenderer renderer) {
        super(context, renderer);
    }

    @Override
    public void initScene() {
        mRenderer.setFrameRate(frameRate);
        mState = State.PAUSE;
        initChildren();
    }

    private void initChildren() {
        bg = new GameObj(0, 0, 1, 1, new Color(1), new Texture("bg", R.drawable.background), 0, 0);
        level = new BreakoutLevel(context, R.raw.level_1);
        paddle = new Paddle(0, 1 - paddleH, paddleW, paddleH, new Color(1),
                new Texture("paddle", R.drawable.paddle), paddleVelocityX, 0);

        ball = new Ball(0.5f, 1 - paddleH - 2 * ballRadius,
                ballRadius * 2 * mRenderer.getViewportHeight() / mRenderer.getViewportWidth(), ballRadius * 2,
                new Color(1),
                new Texture("ball", R.drawable.awesomeface),
                ballVelocityX, ballVelocityY);

        addChild(bg);
        addChildren(level.blocks);
        addChild(paddle);
        addChild(ball);
    }

    @Override
    public void onScroll(float distanceX, float distanceY) {
        paddle.move(-distanceX);
    }

    @Override
    public void update(long sceneTime, double deltaTime) {
        if (mState == State.ACTIVE) {
            ball.move(deltaTime);
            ball.checkCollision(paddle);
        }
    }

    @Override
    public void onSingleTapUp() {
        if (mState == State.PAUSE) {
            mState = State.ACTIVE;
        } else if (mState == State.ACTIVE) {
            mState = State.PAUSE;
        }
    }

    enum State {
        PAUSE,
        ACTIVE,
    }
}
