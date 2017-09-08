package com.papaw.rajawalitest.game2d.breakout;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.papaw.rajawalitest.R;
import com.papaw.rajawalitest.game2d.ASceneFrameAdapter;
import com.papaw.rajawalitest.game2d.Color;
import com.papaw.rajawalitest.game2d.GameObj;
import com.papaw.rajawalitest.game2d.GameScene;
import com.papaw.rajawalitest.game2d.OnGestureAdapter;

import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.renderer.RajawaliRenderer;
import org.rajawali3d.scene.ASceneFrameCallback;

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
    private GestureDetector detector;
    private State mState;
    private OnGestureAdapter gestureAdapter = new OnGestureAdapter() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            paddle.move(-distanceX);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onTapUp();
            return super.onSingleTapUp(e);
        }
    };
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

    public BreakoutScene(Context context, RajawaliRenderer renderer) {
        super(context, renderer);
        registerFrameCallback(frameCallback);
        detector = new GestureDetector(context, gestureAdapter, new Handler(context.getMainLooper()));
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
    public void onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
    }

    private void update(long sceneTime, double deltaTime) {
        if (mState == State.ACTIVE) {
            ball.move(deltaTime);
            ball.checkCollision(paddle);
        }
    }

    private void onTapUp() {
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
