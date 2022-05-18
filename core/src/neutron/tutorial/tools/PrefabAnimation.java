package neutron.tutorial.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PrefabAnimation {

    private static float FRAME_DURATION = .5f;

    private TextureAtlas charset;

    private TextureRegion currentFrame;

    private Animation runningAnimation;

    private float elapsed_time = 0f;

    private Vector2 animationVector;

    public PrefabAnimation(Vector2 vector1) {
        charset = new TextureAtlas( Gdx.files.internal("charset.atlas") );

        Array<TextureAtlas.AtlasRegion> runningFrames = charset.findRegions("running");

        runningAnimation = new Animation(FRAME_DURATION, runningFrames, Animation.PlayMode.LOOP);

        TextureRegion firstTexture = runningFrames.first();
        animationVector = vector1;
    }

    public float GetElapsedTime() {
        return elapsed_time;
    }

    public void SetElapsetTime(float et) {
        elapsed_time = et;
    }

    public Animation GetAnimaton() {
        return runningAnimation;
    }

    public void SetCurretnFrame(TextureRegion ss) {
        currentFrame = ss;
    }

    public TextureRegion GetCurretnFrame() {
        return currentFrame;
    }

    public Vector2 GetVector() {
        return animationVector;
    }
}
