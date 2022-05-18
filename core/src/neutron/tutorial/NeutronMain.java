package neutron.tutorial;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import neutron.tutorial.views.ChooseGameStandardScreen;
import neutron.tutorial.views.SettingsScreen;
import neutron.tutorial.views.EndScreen;
import neutron.tutorial.views.MainScreen;
import neutron.tutorial.views.LoadingScreen;
import neutron.tutorial.views.MenuScreen;
import neutron.tutorial.views.SwitchPlayerScreen;

//extends game
public class NeutronMain extends Game {

	private LoadingScreen loadingScreen;
	private SettingsScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private AppSettings preferences;
	private SwitchPlayerScreen switchPlayerScreen;
	private ChooseGameStandardScreen chooseGameStandardScreen;

	public final static int MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	public final static int GAMECHOOSE = 4;
	public final static int SWITCH = 5;

	private float heightindex;
	private float widthindex;

	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		preferences = new AppSettings();
		setScreen(loadingScreen);
	}
	
	@Override
	public void dispose () {
	}

	public AppSettings getPreferences() {
		return this.preferences;
	}

	public void changeScreen(int screen){
		switch(screen){
			case MENU:
				if(menuScreen == null)
					menuScreen = new neutron.tutorial.views.MenuScreen(this);
					this.setScreen(menuScreen);
					break;
			case PREFERENCES:
				if(preferencesScreen == null)
					preferencesScreen = new SettingsScreen(this);
					this.setScreen(preferencesScreen);
					break;

			case APPLICATION:
				if(mainScreen == null)
					mainScreen = new neutron.tutorial.views.MainScreen(this);
					this.setScreen(mainScreen);
					break;
			case ENDGAME:
				if(endScreen == null)
					endScreen = new neutron.tutorial.views.EndScreen(this);
					this.setScreen(endScreen);
					break;

			case SWITCH:
				if(switchPlayerScreen == null)
					switchPlayerScreen = new neutron.tutorial.views.SwitchPlayerScreen(this);
				this.setScreen(switchPlayerScreen);
				break;

			case GAMECHOOSE:
				if(chooseGameStandardScreen == null)
					chooseGameStandardScreen = new neutron.tutorial.views.ChooseGameStandardScreen(this);
				this.setScreen(chooseGameStandardScreen);
				break;
		}
	}
}
