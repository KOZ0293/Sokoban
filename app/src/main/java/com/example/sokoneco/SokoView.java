package com.example.sokoneco;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View{

    Bitmap[] bmp;

    int lx = 10;
    int ly = 10;

    int width;
    int height;

    int player = 46;
    boolean goalX = false;
    boolean move = false;
    int velocity;


    private int level[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,0,0,0,0,0,0,0,0,1,
            1,0,2,3,3,2,1,0,0,1,
            1,0,1,3,2,3,2,0,0,1,
            1,0,2,3,3,2,4,0,0,1,
            1,0,1,3,2,3,2,0,0,1,
            1,0,2,3,3,2,1,0,0,1,
            1,0,0,0,0,0,0,0,0,1,
            1,0,0,0,0,0,0,0,0,1,
            1,1,1,1,1,1,1,1,1,1
    };

    public SokoView(Context context) {
        super(context);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        bmp = new Bitmap[6];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.boxok);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                if (y < getHeight() / 8) {
                    moveUp();
                }
                if (y > getHeight() - getHeight() / 8) {
                    moveDown();
                }
                if (x < getWidth() / 8) {
                    moveLeft();
                }
                if (x > getWidth() - getWidth() / 8) {
                    moveRight();
                }
        }
        invalidate();
        return true;
    }

    public void moveUp() {
        velocity = -10;
        if (level[player + velocity] == 2 && ((level[player + velocity] + level[player + (velocity * 2)] == 2) && (level[player + velocity] + level[player + velocity * 2] == 5))) {
            velocity = 0;
        }

       if (level[player + velocity] == 5) {
            velocity = 0;
        }

        if (level[player + velocity] == 2) {
            move = true;
        }

        if (level[player + velocity] == 1) {
            velocity = 0;
        }

        if (goalX!= true) {
            level[player] = 0;
        }
        else {
            goalX = false;
            level[player] = 3;
        }
        player += velocity;
        if (level[player] == 3 || level[player] == 5) {
            goalX = true;
        }
        level[player] = 4;
        if (move==  true) {
            move = false;
            level[player + velocity] += 2;
        }
    }

    public void moveDown() {
        velocity = 10;
        if (level[player + velocity] == 2 && ((level[player + velocity] + level[player + (velocity * 2)] == 2) && (level[player + velocity] + level[player + velocity * 2] == 5))) {
            velocity = 0;
        }

        if (level[player + velocity] == 5) {
            velocity = 0;
        }
        if (level[player + velocity] == 2) {
            move = true;
        }
        if (level[player + velocity] == 1) {
            velocity = 0;
        }

        if (goalX!= true) {
            level[player] = 0;
        }
        else {
            goalX = false;
            level[player] = 3;
        }
        player += velocity;
        if (level[player] == 3 || level[player] == 5) {
            goalX = true;
        }
        level[player] = 4;
        if (move==  true) {
            move = false;
            level[player + velocity] += 2;
        }
    }

    public void moveRight() {
        velocity = 1;
        if (level[player + velocity] == 2 && ((level[player + velocity] + level[player + (velocity * 2)] == 2) && (level[player + velocity] + level[player + velocity * 2] == 5))) {
            velocity = 0;
        }

        if (level[player + velocity] == 5) {
            velocity = 0;
        }
        if (level[player + velocity] == 2) {
            move = true;
        }

        if (level[player + velocity] == 1) {
            velocity = 0;
        }

        if (goalX!= true) {
            level[player] = 0;
        }
        else {
            goalX = false;
            level[player] = 3;
        }
        player += velocity;
        if (level[player] == 3 || level[player] == 5) {
            goalX = true;
        }
        level[player] = 4;
        if (move==  true) {
            move = false;
            level[player + velocity] += 2;
        }
    }
    public void moveLeft() {
        velocity = -1;
        if (level[player + velocity] == 2 && ((level[player + velocity] + level[player + (velocity * 2)] == 2) && (level[player + velocity] + level[player + velocity * 2] == 5))) {
            velocity = 0;
        }

        if (level[player + velocity] == 5) {
            velocity = 0;
        }
        if (level[player + velocity] == 2) {
            move = true;
        }

        if (level[player + velocity] == 1) {
            velocity = 0;
        }

        if (goalX != true) {
            level[player] = 0;
        } else {
            goalX = false;
            level[player] = 3;
        }
        player += velocity;
        if (level[player] == 3 || level[player] == 5) {
            goalX = true;
        }
        level[player] = 4;
        if (move == true) {
            move = false;
            level[player + velocity] += 2;
        }
    }
}
