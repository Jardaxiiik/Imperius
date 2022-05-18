package neutron.tutorial.views;

import neutron.tutorial.NeutronMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {

    private NeutronMain parent;
    private Stage stage;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    private final float heightindex;
    private final float widthindex;

    // our constructor with a Box2DTutorial argument
    public SettingsScreen(NeutronMain neutronmain) {
        parent = neutronmain;     // setting the argument to our field.
        stage = new Stage(new ScreenViewport());

        heightindex = (int) Gdx.graphics.getHeight()/14;
        widthindex =(int) Gdx.graphics.getWidth()/8;
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("Skin/set-One/set-one.json"));
        // Create a table that fills the screen. Everything else will go inside
        // this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        // music volume
        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeMusicSlider.setValue(parent.getPreferences().getMusicVolume());
        volumeMusicSlider.getStyle().background.setMinWidth((float) (widthindex*2));
        volumeMusicSlider.getStyle().background.setMinHeight((float) (heightindex*2));
        volumeMusicSlider.addListener(new EventListener() {

            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                //updateVolumeLabel();
                return false;
            }
        });

        // sound volume
        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundMusicSlider.setValue(parent.getPreferences().getSoundVolume());
        soundMusicSlider.getStyle().knob.setMinHeight((float)(heightindex*0.2));
        soundMusicSlider.getStyle().knob.setMinWidth((float) (widthindex*0.2));
        soundMusicSlider.getStyle().background.setMinWidth((float) (widthindex*1));
        soundMusicSlider.getStyle().background.setMinHeight((float) (heightindex*0.25));
        soundMusicSlider.setWidth(widthindex*20);
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setSoundVolume(soundMusicSlider.getValue());
                // updateVolumeLabel();
                return false;
            }
        });

        // music on/off
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked(parent.getPreferences().isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled(enabled);
                return false;
            }
        });

        // sound on/off
        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked(parent.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.getImage().setScaling(Scaling.fill);
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(NeutronMain.MENU);

            }
        });

        titleLabel = new Label( "Preferences", skin );
        volumeMusicLabel = new Label( "Music Volume", skin );
        volumeSoundLabel = new Label( "Sound Volume", skin );
        musicOnOffLabel = new Label( "Music", skin );
        soundOnOffLabel = new Label( "Sound Effect", skin );
        table.add(titleLabel).colspan(2).minWidth((int) (widthindex*2)).minHeight((int)(heightindex*1)).getActor().setFontScale(8);
        table.row().pad((int) (widthindex*0.05),0,0,(int) (widthindex*0.05));
        table.add(volumeMusicLabel).left().minWidth((int) (widthindex*1)).minHeight((int)(heightindex*1)).getActor().setFontScale(4);
        table.add(volumeMusicSlider).minWidth(widthindex*2);
        table.row().pad((int) (widthindex*0.05),0,0,(int) (widthindex*0.05));
        table.add(musicOnOffLabel).left().minWidth((int) (widthindex*2)).minHeight((int)(heightindex*1)).getActor().setFontScale(4);
        table.add(musicCheckbox).size(widthindex*1, heightindex*1);
        table.row().pad((int) (widthindex*0.05),0,0,(int) (widthindex*0.1));
        table.add(volumeSoundLabel).left().minWidth((int) (widthindex*1)).minHeight((int)(heightindex*1)).getActor().setFontScale(4);
        table.add(soundMusicSlider).minWidth(widthindex*2);
        table.row().pad((int) (widthindex*0.05),0,0,(int) (widthindex*0.05));
        table.add(soundOnOffLabel).left().minWidth((int) (widthindex*2)).minHeight((int)(heightindex*1)).getActor().setFontScale(4);
        table.add(soundEffectsCheckbox).size(widthindex*1, heightindex*1);
        table.row().pad((int) (widthindex*0.05),0,0,(int) (widthindex*0.05));
        table.add(backButton).colspan(2).minWidth((int) (widthindex*1)).minHeight((int)(heightindex*1)).getActor().getLabel().setFontScale(6);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
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
        // TODO Auto-generated method stub
    }
}
