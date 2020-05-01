//Ritvik Singh
// Block 2A
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GDXProject extends ApplicationAdapter{

    Stage stage;
	Texture bg;
	Texture bg2;
	Texture bg3;
	Texture bg4;
	Texture bg5;
	Texture floor;
	SpriteBatch batch;
    int srcX = 0;
    int srcX2 = 0;
    int srcX3 = 0;
    int srcX4 = 0;
    int srcX5 = 0;

    SpikeActor spikeActor;
    SpikeActor spikeActor1;

	@Override
	public void create () {
        batch = new SpriteBatch();

	    bg = new Texture(Gdx.files.internal("bg.png"));
        bg2 = new Texture(Gdx.files.internal("bg2.png"));
        bg3 = new Texture(Gdx.files.internal("bg3.png"));
        bg4 = new Texture(Gdx.files.internal("bg4.png"));
        bg5 = new Texture(Gdx.files.internal("bg5.png"));
        floor = new Texture(Gdx.files.internal("floor.png"));

        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bg2.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bg3.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bg4.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bg5.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        floor.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);


        Gdx.app.log("TEST","Width: "+Gdx.graphics.getWidth());
        Gdx.app.log("TEST","Height: "+Gdx.graphics.getHeight());


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ManActor manActor = new ManActor();
        spikeActor = new SpikeActor();
        spikeActor1 = new SpikeActor();

        stage.addActor(manActor);
        stage.addActor(spikeActor);
        stage.addActor(spikeActor1);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        srcX += 13;
        srcX3 += 11;
        srcX2 +=9;
        srcX5 += 7;
        srcX4 += 5;

        batch.draw(bg4, 0, 0, srcX4, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(bg5, 0, 0, srcX5, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(bg2, 0, 0, srcX2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(bg3, 0, 0, srcX3, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(bg, 0, 0, srcX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(floor, 0, 0, srcX, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        batch.end();



        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());

	}
	
	@Override
	public void dispose () {
        stage.dispose();
        batch.dispose();
	}

}
