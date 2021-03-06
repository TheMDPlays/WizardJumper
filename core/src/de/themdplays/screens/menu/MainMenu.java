package de.themdplays.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.themdplays.main.WizardJumper;
import de.themdplays.screens.Editor;
import de.themdplays.screens.menu.Options.Options;
import de.themdplays.util.Assets;
import de.themdplays.util.ButtonHandler;
import de.themdplays.util.SpriteActor;

public class MainMenu implements Screen {
	
	private Stage stage;
	private Skin skin;
	private TextButton b_play, b_options, b_editor, b_exit;

	@Override
	public void show() {
		//declaring stuff
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
        skin = Assets.manager.get(Assets.menuSkin, Skin.class);

		Table table = new Table();

        //init menu
        registerButtons();
		
        //setup table
		table.setFillParent(true);
		table.center();

		Sprite logo = new Sprite(Assets.manager.get(Assets.logo));

		table.add(new SpriteActor(logo)).center().width(Gdx.graphics.getWidth()*0.6f).height(Gdx.graphics.getWidth()*0.6f/(logo.getWidth()/logo.getHeight())).spaceBottom(Gdx.graphics.getHeight()/10).row();

		//adding buttons to table
        ButtonHandler.addButtonToTable(b_play, table);
        ButtonHandler.addButtonToTable(b_editor, table);
        ButtonHandler.addButtonToTable(b_options, table);
        ButtonHandler.addButtonToTable(b_exit, table);
		
		//adding table to stage
		stage.addActor(table);

        //FADEINEFFECT
        stage.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(.25f)));
		
	}

	@Override
	public void render(float delta) {
		//CLEAR SCREEN
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isKeyPressed(Input.Keys.F11)) Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

		//RENDER MENU
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void resume() {


	}

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

	/**
	 * Registers the menu buttons
	 */
	private void registerButtons() {
		//DECLARING BUTTONS
		b_play = new TextButton(WizardJumper.langManager.get("play"), skin, "big");
		b_options = new TextButton(WizardJumper.langManager.get("options"), skin, "big");
        b_editor = new TextButton(WizardJumper.langManager.get("editor"), skin, "big");
        b_exit = new TextButton(WizardJumper.langManager.get("exit"), skin, "big");


        //ADDING CLICK LISTENER
        ButtonHandler.addClicklistener(b_play, stage, new LevelMenu());
        ButtonHandler.addClicklistener(b_editor, stage, new Editor());
        ButtonHandler.addClicklistener(b_options, stage, new Options());

        //ADDING EXIT BUTTON
        b_exit.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.fadeOut(.25f), Actions.run(new Runnable() {

                    @Override
                    public void run() {
                        Gdx.app.exit();
                    }
                })));

            }

        });

	}

}
