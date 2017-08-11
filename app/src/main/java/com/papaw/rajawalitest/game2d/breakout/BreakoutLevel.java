package com.papaw.rajawalitest.game2d.breakout;

import android.content.Context;

import com.papaw.rajawalitest.R;
import com.papaw.rajawalitest.game2d.Color;

import org.rajawali3d.Object3D;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by duanjunjie on 17-7-27.
 */

public class BreakoutLevel {
    public static final String TAG = BreakoutLevel.class.getSimpleName();
    Collection<Object3D> blocks;
    private ATexture texBlock;
    private ATexture texBlockSolid;

    public BreakoutLevel(Context context, int levelRes) {
        blocks = new ArrayList<>();
        texBlock = new Texture("block", R.drawable.block);
        texBlockSolid = new Texture("block_solid", R.drawable.block_solid);
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(levelRes)));
        String line;
        List<List<String>> levels = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
                levels.add(Arrays.asList(line.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (levels.isEmpty()) {
            return;
        }
        int height = levels.size();
        int width = levels.get(0).size();

        for (int j = 0; j < height; j++) {
            List<String> list = levels.get(j);
            for (int i = 0; i < width; i++) {
                float x = (float) i / width;
                float y = (float) j / height / 2;
                float w = 1f / width;
                float h = 1f / height / 2;

                Color color = null;
                ATexture sprite = texBlock;
                boolean isSolid = false;
                int value = Integer.valueOf(list.get(i));
                switch (value) {
                    case 0:
                        continue;
                    case 1:
                        color = new Color(0.8f, 0.8f, 0.7f);
                        sprite = texBlockSolid;
                        isSolid = true;
                        break;
                    case 2:
                        color = new Color(0.2f, 0.6f, 1.0f);
                        break;
                    case 3:
                        color = new Color(0.0f, 0.7f, 0.0f);
                        break;
                    case 4:
                        color = new Color(0.8f, 0.8f, 0.4f);
                        break;
                    case 5:
                        color = new Color(1.0f, 0.5f, 0.0f);
                        break;
                }

                blocks.add(new Block(x, y, w, h, color, sprite, isSolid));
            }
        }
    }
}
