package com.papaw.rajawalitest.game2d.breakout;

import android.opengl.GLES20;

import com.papaw.rajawalitest.R;

import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.shaders.FragmentShader;
import org.rajawali3d.materials.shaders.VertexShader;
import org.rajawali3d.util.RawShaderLoader;


public class BreakoutMaterial extends Material {
    public BreakoutMaterial() {
        super();
        mCustomVertexShader = new MinimalVertexShader();
        mCustomFragmentShader = new ColorFragmentShader();
    }

    public void setColor(float[] color) {
        ((ColorFragmentShader) mCustomFragmentShader).setColor(color);
    }

    private static class MinimalVertexShader extends VertexShader {
        public MinimalVertexShader() {
            super();
            mNeedsBuild = false;
            mShaderString = RawShaderLoader.fetch(R.raw.sprite_vertex);
        }
    }

    private static class ColorFragmentShader extends FragmentShader {
        private int spriteColorHandle;
        private float[] spriteColor;

        public ColorFragmentShader() {
            super();
            mNeedsBuild = false;
            spriteColor = new float[3];
            mShaderString = RawShaderLoader.fetch(R.raw.sprite_frag);
        }

        @Override
        public void setLocations(final int programHandle) {
            super.setLocations(programHandle);
            spriteColorHandle = getUniformLocation(programHandle, "spriteColor");
        }

        @Override
        public void applyParams() {
            super.applyParams();
            GLES20.glUniform3fv(spriteColorHandle, 1, spriteColor, 0);
        }

        public void setColor(float[] color) {
            spriteColor[0] = color[0];
            spriteColor[1] = color[1];
            spriteColor[2] = color[2];
        }
    }
}
