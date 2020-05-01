//Ritvik Singh
// Block 2A
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class SpikeActor extends Actor {

    Texture spike1 = new Texture(Gdx.files.internal("spike1.png"));
    Texture spike2 = new Texture(Gdx.files.internal("spike2.png"));

    Sprite sprite;

    int SpikeX = (int)(Math.random() * 2200 + 1900);
    int SpikeY = 100;
    int tmp;

    ShapeRenderer shapeRenderer = new ShapeRenderer();


    public SpikeActor(){
        tmp = (int) ( Math.random() * 2 + 1);
        if(tmp == 1){
            sprite = new Sprite(spike1);
        }else{
            sprite = new Sprite(spike2);
        }
        sprite.setPosition(SpikeX,SpikeY);
        setBounds(getX(),getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.disabled);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        SpikeX -= 13;
        sprite.setPosition(SpikeX,SpikeY);
        if(SpikeX < -200){
            SpikeX = (int)(Math.random() * 2200 + 1900);
            tmp = (int) ( Math.random() * 2 + 1);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(tmp == 1){
            sprite = new Sprite(spike1);
        }else{
            sprite = new Sprite(spike2);
        }
        batch.draw(sprite,SpikeX,SpikeY);

        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //shapeRenderer.rect(sprite.getBoundingRectangle().getX(), sprite.getBoundingRectangle().getY(), sprite.getBoundingRectangle().getWidth(), sprite.getBoundingRectangle().getHeight());
        shapeRenderer.rect(SpikeX,SpikeY,sprite.getWidth(),sprite.getHeight());
        shapeRenderer.rect(SpikeX,SpikeY+350,sprite.getWidth(),sprite.getHeight()+200);
        shapeRenderer.end();*/

    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }

    public Rectangle getJumpRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.set(SpikeX,SpikeY+350,sprite.getWidth(),sprite.getHeight()+200);
        return rectangle;
    }

    public Rectangle getRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.set(SpikeX,SpikeY,sprite.getWidth(),sprite.getHeight());
        return rectangle;
    }
}
