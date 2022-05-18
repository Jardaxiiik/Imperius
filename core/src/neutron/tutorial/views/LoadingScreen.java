package neutron.tutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import neutron.tutorial.NeutronMain;

public class LoadingScreen implements Screen {
    private NeutronMain parent; // a field to store our orchestrator

    private MenuScreen menuScreen;
    // our constructor with a Box2DTutorial argument
    private Stage stage;


    public LoadingScreen(NeutronMain neutronmain) {
        parent = neutronmain;     // setting the argument to our field.
        stage = new Stage(new ScreenViewport());
        parent.changeScreen(NeutronMain.MENU);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
    }
}
