package neutron.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppSettings {
    private static final String PREF_MUSIC_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREF_SOUND_VOL = "sound";
    private static final String PREFS_NAME = "tat";
    private Preferences preferences;

    //APPLICATION VARIABLES AND SAVES:
    private static final String GAME_TYPE = "game.type";
    //Multiplayer

    //Local
    private static final String GAME_Safe_One = "game.safe.one";
    private static final String GAME_Safe_Two = "game.safe.two";

    protected Preferences Prefs() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences(PREFS_NAME);
        }
            return preferences;
    }

    public boolean isSoundEffectsEnabled() {
        return Prefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        Prefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        Prefs().flush();
    }

    public boolean isMusicEnabled() {
        return Prefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        Prefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        Prefs().flush();
    }

    public float getMusicVolume() {
        return Prefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public void setMusicVolume(float volume) {
        Prefs().putFloat(PREF_MUSIC_VOLUME, volume);
        Prefs().flush();
    }

    public float getSoundVolume() {
        return Prefs().getFloat(PREF_SOUND_VOL, 0.5f);
    }

    public void setSoundVolume(float volume) {
        Prefs().putFloat(PREF_SOUND_VOL, volume);
        Prefs().flush();
    }
}
