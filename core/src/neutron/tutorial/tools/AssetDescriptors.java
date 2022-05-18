package neutron.tutorial.tools;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class AssetDescriptors {
    public static final AssetDescriptor<Texture> skeleton = new AssetDescriptor<Texture>(Assets.SKELETON, Texture.class);
    public static final AssetDescriptor<Texture> brokenRocket = new AssetDescriptor<Texture>(Assets.BROKENROCKET, Texture.class);
    public static final AssetDescriptor<Texture> background = new AssetDescriptor<Texture>(Assets.BACKGROUND, Texture.class);
    public static final AssetDescriptor<Texture> characterRun = new AssetDescriptor<Texture>(Assets.CHARACTERRUN, Texture.class);
    public static final AssetDescriptor<Texture> nullImg = new AssetDescriptor<Texture>(Assets.NULLIMG, Texture.class);
    public static final AssetDescriptor<Texture> rocket = new AssetDescriptor<Texture>(Assets.ROCKET, Texture.class);

    private AssetDescriptors(){}
}