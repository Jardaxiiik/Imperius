package neutron.tutorial.views;

import neutron.tutorial.NeutronMain;

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

public class MenuScreen implements Screen {

    private final NeutronMain parent;
    private final Stage stage;
    private final Skin skin;

    private final float heightindex;
    private final float widthindex;

    //constructor with a Box2DTutorial argument
    public MenuScreen(NeutronMain neutronmain) {
        //FIRST?
        parent = neutronmain;     // setting the argument to our field.

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Skin/set-One/set-one.json"));
        heightindex = (int) Gdx.graphics.getHeight()/14;
        widthindex =(int) Gdx.graphics.getWidth()/8;
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage); // Screen sends any input from the user to the stage so it can respond

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // WIDGET DECLARATION:
        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        // WIDGET PROPERTIES:
        newGame.getLabel().setFontScale(6);
        table.add(newGame).fillX().uniformX().width(widthindex*5).height(heightindex*2);

        preferences.getLabel().setFontScale(6);
        table.row().pad((int)(heightindex*0.5), 0, (int)(heightindex*0.5), 0);
        table.add(preferences).fillX().width(widthindex*5).height(heightindex*2);

        exit.getLabel().setFontScale(6);
        table.row();
        table.add(exit).fillX().width(widthindex*5).height(heightindex*2);

        //ACTION LISTENERS:
        exit.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        preferences.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                parent.changeScreen(NeutronMain.PREFERENCES);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        newGame.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               // parent.changeScreen(NeutronMain.GAMECHOOSE);
                parent.changeScreen(parent.APPLICATION);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.draw();
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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
