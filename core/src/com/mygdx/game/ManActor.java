//Ritvik Singh
// Block 2A
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class ManActor extends Actor implements GestureDetector.GestureListener{

    TextureAtlas textureAtlasRun = new TextureAtlas(Gdx.files.internal("CharacterRun/CharacterRun.atlas"));
    TextureRegion textureRegion = textureAtlasRun.findRegion("run1");

    TextureAtlas textureAtlasJump = new TextureAtlas(Gdx.files.internal("CharacterJump/CharacterJump.atlas"));
    TextureAtlas textureAtlasJumpDown = new TextureAtlas(Gdx.files.internal("CharacterJumpDown/CharacterJumpDown.atlas"));

    Sprite sprite = new Sprite(textureRegion);


    Animation<TextureRegion> runAnimation = new Animation(1/10f,textureAtlasRun.getRegions());
    Animation<TextureRegion> jumpAnimation = new Animation(1/10f,textureAtlasJump.getRegions());
    Animation<TextureRegion> jumpdownAnimation = new Animation(1/4f,textureAtlasJumpDown.getRegions());

    float timeForRun = 0.0f;
    float timeForJump = 0.0f;
    boolean jump = false;
    int inAir = 0;
    boolean goingUp = false;
    boolean goingDown = false;
    float charX = 40;
    float charY = 105;
    int jumpSpeed = 25;

    int score = 0;
    BitmapFont font = new BitmapFont();
    Rectangle rectangle;
    boolean add = true;
    boolean drawSubtract = false;

    ShapeRenderer shapeRenderer = new ShapeRenderer();
    int tmp = (int) ( Math.random() * 5 + 1);


    public ManActor(){
        sprite.setPosition(charX,charY);
        rectangle = new Rectangle();
        setBounds(getX(),getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        GestureDetector gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        timeForRun += delta;
        timeForJump += delta;

        rectangle.set(charX,charY,sprite.getWidth(),sprite.getHeight());

        if (getStage() != null) {
            for (Actor a : getStage().getActors()) {
                if(a instanceof SpikeActor){
                    if (rectangle.overlaps(((SpikeActor) a).getJumpRectangle())){
                        if(add){
                            score++;
                            add = false;
                        }

                    }
                    if (rectangle.overlaps(((SpikeActor)a).getRectangle()) ){
                        score = 0;
                        drawSubtract = true;
                    }
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (jump && !goingDown) {
            tmp = (int) ( Math.random() * 5 + 1);
            timeForRun=0;
            if(inAir == 1) {
                goingUp = true;
                batch.draw(jumpAnimation.getKeyFrame(timeForJump,false),charX,charY );
                charY += jumpSpeed;
            }else {
                if(goingDown)
                    batch.draw(jumpdownAnimation.getKeyFrame(timeForJump,false),charX,charY );

                if(goingUp)
                    batch.draw(jumpAnimation.getKeyFrame(timeForJump,false),charX,charY );
            }
            jumpSpeed--;
            if(jumpSpeed<0){
                jump = false;
                goingUp = false;
                goingDown = true;
            }else{
                jump = true;
            }
        }else{
            charY-=jumpSpeed;
            jumpSpeed++;
            if (charY < 104) {
                goingDown = false;
                charY = 105;
                inAir = 0;
                jumpSpeed = 25;
            }

            if(charY == 105) {
                add = true;
                batch.draw(runAnimation.getKeyFrame(timeForRun, true), charX, charY);
            }else{
                batch.draw(jumpdownAnimation.getKeyFrame(timeForJump,false),charX,charY );
            }
            timeForJump = 0;

           /*rectangle.set(charX,charY,sprite.getWidth(),sprite.getHeight());
           shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
           shapeRenderer.rect(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),sprite.getHeight());
           shapeRenderer.rect(charX,charY-100,sprite.getWidth(),50);
            shapeRenderer.rect(charX,0,sprite.getWidth(),50);
           //shapeRenderer.rect(charX,charY,sprite.getWidth(),sprite.getHeight());
           shapeRenderer.end();*/
        }

        if(drawSubtract){
            String msg = "";
            switch (tmp){
                case 1: msg = "L";
                    break;
                case 2: msg = "Rip";
                    break;
                case 3: msg = "Boi";
                    break;
                case 4: msg = "#*$@";
                    break;
                case 5: msg = "Ouch";
                    break;
                default: msg = "Switch Error";
            }
            font.draw(batch,msg,charX,450);
            drawSubtract = false;
        }




        font.getData().setScale(4);
        font.draw(batch,"Score: "+score,1600,1000);

    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public Rectangle getRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.set(charX,charY,sprite.getWidth(),sprite.getHeight());
        return rectangle;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        jump = true;
        inAir++;
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
