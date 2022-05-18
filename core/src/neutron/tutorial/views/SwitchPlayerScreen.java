package neutron.tutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import neutron.tutorial.NeutronMain;

public class SwitchPlayerScreen implements Screen {

    private final NeutronMain parent; // a field to store our orchestrator

    private MenuScreen menuScreen;
    // our constructor with a Box2DTutorial argument
    private final Stage stage;

    private final float heightindex;
    private final float widthindex;

    public SwitchPlayerScreen(NeutronMain neutronmain) {
        parent = neutronmain;     // setting the argument to our field.
        stage = new Stage(new ScreenViewport());
        parent.changeScreen(NeutronMain.MENU);

        heightindex = (int) Gdx.graphics.getWidth()/14;
        widthindex =(int) Gdx.graphics.getHeight()/8;
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage); // Screen sends any input from the user to the stage so it can respond
        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("Skin/set-One/set-one.json"));
        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        // WIDGET DECLARATION:
        TextButton nextPlayer = new TextButton("Player ", skin);

        // WIDGET PROPERTIES:
        nextPlayer.getLabel().setFontScale(6);
        table.add(nextPlayer).fillX().uniformX().width(widthindex*3).height(heightindex*2);

        //ACTION LISTENERS:
        nextPlayer.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                parent.changeScreen(NeutronMain.APPLICATION);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
