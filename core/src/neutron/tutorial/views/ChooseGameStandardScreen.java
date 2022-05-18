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

public class ChooseGameStandardScreen implements Screen {
    private NeutronMain parent;
    private Stage stage;
    private Skin skin;

    private float heightindex;
    private float widthindex;

    public ChooseGameStandardScreen(NeutronMain neutronmain) {
        //FIRST?
        parent = neutronmain;     // setting the argument to our field.

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Skin/set-One/set-one.json"));
        heightindex = (int) Gdx.graphics.getHeight()/14;
        widthindex =(int) Gdx.graphics.getWidth()/8;
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
        TextButton vsAI = new TextButton("Player vs AI", skin);
        TextButton onDevice = new TextButton("PvP on one device", skin);
        TextButton loadGame = new TextButton("Load Game", skin);
        TextButton exitToMainMenu = new TextButton("Main Menu", skin);

        // WIDGET PROPERTIES:
        vsAI.getLabel().setFontScale(6);
        table.row().pad((int)(heightindex*0.5), 0, (int)(heightindex*0.5), 0);
        table.add(vsAI).fillX().width(widthindex*5).height(heightindex*2);

        onDevice.getLabel().setFontScale(6);
        table.row();
        table.add(onDevice).fillX().width(widthindex*6).height(heightindex*2);

        loadGame.getLabel().setFontScale(6);
        table.row();
        table.add(loadGame).fillX().width(widthindex*5).height(heightindex*2);

        exitToMainMenu.getLabel().setFontScale(6);
        table.row();
        table.add(exitToMainMenu).fillX().width(widthindex*5).height(heightindex*2);

        //ACTION LISTENERS:
        exitToMainMenu.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                parent.changeScreen(parent.MENU);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        onDevice.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                parent.changeScreen(parent.APPLICATION);
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
