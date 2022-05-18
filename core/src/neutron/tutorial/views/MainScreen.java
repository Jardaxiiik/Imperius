package neutron.tutorial.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import neutron.tutorial.NeutronMain;
import neutron.tutorial.gamedata.Abilities;
import neutron.tutorial.gamedata.Card;
import neutron.tutorial.gamedata.Filters;
import neutron.tutorial.gamedata.Game;
import neutron.tutorial.gamedata.Path;
import neutron.tutorial.gamedata.Player;
import neutron.tutorial.gamedata.Sector;
import neutron.tutorial.gamedata.Unit;
import neutron.tutorial.tools.AssetDescriptors;
import neutron.tutorial.tools.Assets;
import neutron.tutorial.tools.PrefabAnimation;
import neutron.tutorial.tools.TextureActor;

import static neutron.tutorial.gamedata.Card.GenerateCardsOffer;

public class MainScreen implements Screen {

    //Game Variables
        private final NeutronMain parent;
        public Game game;
        public boolean gamePaused = false;
        public int gamemode = 1; //PvP

    // textures
        private Stage battleStage;
        private Stage pauseStage;

        private Skin skin;
        private final SpriteBatch batch;
        public ArrayList<PrefabAnimation> prefabAnimations = new ArrayList<>();

        private Texture background;
        //private Actor backgroundActor = new TextureActor(background, "background");

        ArrayList<Color> allColors = new ArrayList<>();
        ArrayList<Pixmap> allPixmaps = new ArrayList<>();
        ArrayList<TextureRegionDrawable> allTextureRegionsDrawable = new ArrayList<>();

    // Variabless
        private final float heightindex = 2620/14;
        private final float widthindex = 1440/8;

        private final AssetManager manager;
        private Camera camera;

        public UnitTable selectedUnitTable;
        ArrayList<UnitTable> unitTables = new ArrayList<>();

        SectorButton selectedSectorButton;
        ArrayList<SectorButton> sectorButtons = new ArrayList<>();

        AbilityTable selectedAbilityTable;
        ArrayList<AbilityTable> abilityTables = new ArrayList<>();

        ArrayList<CardButton> cardButtons = new ArrayList<>();

        public Abilities casterAbility = null;
        public Sector casterSector = null;

    // ROOT TABLES
        Table battleTable = new Table();

    //alerts
        Table nonBattleTable = new Table();
        Table alertMenuTab = new Table();

    // Menu Tab
        Table menuTab = new Table(skin);
        TextButton nextTurnButton;
        TextButton resourceButton;
        TextButton techButton;

    // Unit Tab
        Table unitTab = new Table(skin);
        ScrollPane unitScrollPane;
        ArrayList<UnitTable> units = new ArrayList<>();

    // Actions Tab
        Table actionTabDivider = new Table(skin);
        Slider actionSlider;
        Table actionTab = new Table(skin);
        ScrollPane actionScrollPane;

    // Shop Tab
        Table shoppingTab = new Table(skin);
        ScrollPane shoppingScrollPane;

    // Sectors Tab
        Table sectorTab = new Table(skin);
        ScrollPane sectorScrollPane;

    // Message Tab
        Table messageTab = new Table(skin);
        Label messageWriter;

    // Bot Tab
        Table botTable = new Table(skin);

    // our constructor with a Box2DTutorial argument
    public MainScreen(NeutronMain neutronmain) {
        // a field to store our orchestrator
        parent = neutronmain;
        manager = new AssetManager();

        //stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("Skin/set-One/set-one.json"));

        batch = new SpriteBatch();

        //Colors
        MakeNewColor(new Color(0,0,0,0)); // 0
        MakeNewColor(new Color(255,255,255,1f)); // 1
        MakeNewColor(new Color(169,169,169,.5f)); // 2 dark grey
        MakeNewColor(new Color(211,211,211,.5f)); // 3 light grey
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(widthindex*8,heightindex*14);

        // important
        Viewport viewport = new ExtendViewport(widthindex * 8, heightindex * 14, camera);// FitViewport(widthindex*8, heightindex*14, camera);
        battleStage = new Stage(viewport);
        pauseStage = new Stage(viewport);

        loadAssets();
        Gdx.input.setInputProcessor(battleStage);
        background = manager.get(AssetDescriptors.background);

        skin.setScale(4);
        //MyActor myAsset1 = new MyActor(manager.get(AssetDescriptors.brokenRocket),AssetDescriptors.brokenRocket.fileName);
        //myAsset1.spritePos((int) widthindex*4,(int)heightindex*7);
        //myAsset1.setBounds((int) widthindex*0,(int)heightindex*0,(int) widthindex*8,(int)heightindex*10);

        //stage.addActor(myAsset1);
        //table.setBounds(0,0,widthindex*8,heightindex*14);

        battleTable.setFillParent(true);
        battleTable.setWidth(widthindex*8);
        battleTable.setHeight(heightindex*14);
        nonBattleTable.setFillParent(true);
        //alertMenuTab.setFillParent(true);

        battleStage.addActor(battleTable);
        pauseStage.addActor(nonBattleTable);
        pauseStage.addActor(alertMenuTab);

        //battleTable.setDebug(true); // turn on all debug lines (table, cell, and widget)

        battleTable.setVisible(true);
        nonBattleTable.setVisible(false);
        alertMenuTab.setVisible(false);

        // Pause Table
            alertMenuTab.setPosition(widthindex*2,heightindex*3.5f);
            alertMenuTab.setWidth(widthindex*4);
            alertMenuTab.setHeight(heightindex*7);
            alertMenuTab.setBackground(allTextureRegionsDrawable.get(2));
            TextButton resumeButton = new TextButton("Resume", skin);
            resumeButton.getLabel().setFontScale(3,3);
            TextButton quitButton = new TextButton("Quit", skin);
            quitButton.getLabel().setFontScale(3,3);

            alertMenuTab.add(resumeButton).height(heightindex*1).width(widthindex*2);
            resumeButton.getChild(0).addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    ResumeButtonCLicked();
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            alertMenuTab.row();
            alertMenuTab.add(quitButton).height(heightindex*1).width(widthindex*2);;
            quitButton.getChild(0).addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    QuitButtonCLicked();
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

        // nonBattleTable
            //nonBattleTable.add(backgroundActor);
            Label waitingLabel = new Label("Tap screen to start game",skin);
            waitingLabel.setFontScale(6, 6);
            nonBattleTable.setBackground(allTextureRegionsDrawable.get(1));
            //TextButton waitingButton = new TextButton("hello",skin);
            //waitingButton.getLabel().setFontScale(3, 3);
            nonBattleTable.add(waitingLabel);
            //alertTable.add(waitingButton);
            nonBattleTable.addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    WaitingTableClicked();
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

        // BattleTable
        actionSlider = new Slider(1, 3, 1, false, skin);
        actionSlider.setWidth(widthindex*5);
        battleTable.add(menuTab).height(heightindex*1).expand().fill().colspan(2);
        battleTable.row();
        unitScrollPane = new ScrollPane(unitTab,skin);
        battleTable.add(unitScrollPane).height(heightindex*5).expand().fill();
        actionScrollPane = new ScrollPane(actionTab,skin);
        actionTabDivider.add(actionScrollPane).height(heightindex*5);
        actionTabDivider.row();
        actionTabDivider.add(actionSlider).width(widthindex*1f).height(heightindex*1);
        //actionScrollPane.addActor(new TextButton("tjis", skin));
        battleTable.add(actionTabDivider).height(heightindex*5).expand().fill();
        battleTable.row();
        shoppingScrollPane = new ScrollPane(shoppingTab,skin);
        battleTable.add(shoppingScrollPane).height(heightindex*1.25f).expand().fill().colspan(2);
        battleTable.row();
        sectorScrollPane = new ScrollPane(sectorTab,skin);
        battleTable.add(sectorScrollPane).height(heightindex*2.75f).expand().fill().colspan(2);
        battleTable.row();
        battleTable.add(messageTab).height(heightindex*3).expand().fill().colspan(2);

        messageWriter = new Label("",skin);
        messageWriter.setFontScale(10);
        messageTab.add(messageWriter).pad(widthindex*0.2f);
        //messageWriter.setFillParent(true);
        battleTable.row();
        battleTable.add(botTable).height(heightindex*1).expand().fill().colspan(2);
        messageWriter.setDebug(true);

        //resourceButton
            resourceButton = new TextButton("111", skin);
            resourceButton.getLabel().setFontScale(3, 3);
            //resourceLabel.setStyle();
            resourceButton.setColor(Color.WHITE);
            resourceButton.setWidth(widthindex*1);
            menuTab.add(resourceButton).pad((float)(widthindex*0.25)).width((float)(widthindex*1));
            resourceButton.addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    ResourceClicked();
                    return true;
                }
            });

        //techButton
            techButton = new TextButton("1", skin);
            techButton.getLabel().setFontScale(3, 3);
            techButton.setColor(Color.WHITE);
            techButton.setWidth(widthindex*1);
            menuTab.add(techButton).pad((float)(widthindex*0.25)).width((float)(widthindex*1));
            techButton.addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    TechnologyClicked();
                    return true;
                }

            });

        //nextTurnButton
            TextButton nextTurnButton = new TextButton("Next Turn", skin);
            nextTurnButton.getLabel().setFontScale(3, 3);
            nextTurnButton.getLabel().setFontScale(5);
            nextTurnButton.setWidth(widthindex*1);
            menuTab.add(nextTurnButton).pad((float)(widthindex*0.25)).width(widthindex*3);

            nextTurnButton.addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    NextTurnClicked();
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

        //menuButton
            TextButton menuButton = new TextButton("MENU", skin);
            menuButton.getLabel().setFontScale(3, 3);
            menuButton.getLabel().setFontScale(3);
            menuButton.setWidth(widthindex*1);
            menuTab.add(menuButton).pad((float)(widthindex*0.25)).width(widthindex*1);
            menuButton.addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    MenuClicked();
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

        actionTab.top();
        unitTab.top();

    /*
        for (int j = 0; j < LevelFiles.maxLvl; j += 3) {

            //Level Boxes
            for (int i = j; i < j + 3; i++) {
                unitScrollPane.addActor(new TextButton("Tjis", skin));
                if(i == j)actionTab.add(levelBoxes.getValueAt(i).getImage()).center().size(200, 300).padBottom(70);
                if(i == j+1)actionTab.add(levelBoxes.getValueAt(i).getImage()).center().size(200, 300).pad(0, 100, 0, 100).padBottom(70);
                if(i == j+2)scrollTable.add(levelBoxes.getValueAt(i).getImage()).center().size(200, 300).padBottom(70);
            }
            scrollTable.row();

            //Fonts
            for (int i = j; i < j + 3; i++) {
                scrollTable.add(new Label(i+1 + "", l)).padTop(-450);
            }
            scrollTable.row();

            //Stars
            for (int i = j; i < j + 3; i++) {
                if((i+1) <= LevelFiles.maxLvl){
                    scrollTable.add(threeStars[i]).prefHeight(100).prefWidth(262).padRight(0).padTop(-200);
                }
            }
            scrollTable.row();

        }
    */

        //ShoppingTab

        //Drawable background = new TextureRegionDrawable(new TextureRegion(new Texture(Assets.BROKENROCKET)));

        //private Texture texture;
        //private Sprite sprite;
        //private ArrayList<Texture> images= new ArrayList<Texture>();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        battleStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        game = new Game(this);
    }

    @Override
    public void render(float delta) {
        //camera.position.set(widthindex*3,heightindex*5,0);
        camera.update();
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!gamePaused) {
            batch.setProjectionMatrix(camera.view);

            for (PrefabAnimation animator:prefabAnimations) {
                animator.SetElapsetTime(animator.GetElapsedTime() + Gdx.graphics.getDeltaTime());

                animator.SetCurretnFrame((TextureRegion) animator.GetAnimaton().getKeyFrame(animator.GetElapsedTime()));
            }

            //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            //shapeRenderer.setColor(Color.GREEN);
            //shapeRenderer.rect(widthindex*1, heightindex*4, widthindex*5.5f, heightindex*7);
            //shapeRenderer.end();
            batch.begin();
            //batch.draw(texture,0,0,(heightindex*14),(widthindex*8),0,0,1,1);
            batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            batch.end();

            battleStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            battleStage.draw();


            batch.begin();
            //batch.draw(texture,0,0,(heightindex*14),(widthindex*8),0,0,1,1);

            for (PrefabAnimation animator:prefabAnimations) {
                batch.draw(animator.GetCurretnFrame(), animator.GetVector().x, animator.GetVector().y);
            }

            batch.end();
        } else {
            pauseStage.draw();
        }
    }

    //Back Code
        @Override
        public void resize(int width, int height) {
            battleStage.getViewport().update(width, height, true);
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
            battleStage.dispose();
            for(Pixmap p : allPixmaps) {
                p.dispose();
            }
            for(TextureRegionDrawable d : allTextureRegionsDrawable) {
                //they said to dispose it but i cant...
                //d.di
            }
        }

    //Tools
        private void loadAssets() {
                manager.load(Assets.BROKENROCKET, Texture.class);
                manager.load(Assets.BACKGROUND, Texture.class);

                manager.finishLoading();
            }

        private void ShowSomething() {
            TextureActor myAsset1 = new TextureActor(manager.get(AssetDescriptors.brokenRocket), AssetDescriptors.brokenRocket.fileName);
            myAsset1.spritePos((int) widthindex * 4, (int) heightindex * 7);
            myAsset1.setBounds((int) widthindex * 0, (int) heightindex * 0, (int) widthindex * 8, (int) heightindex * 10);


            battleStage.addActor(myAsset1);
        }

        public void MakeNewColor(Color c) {
            allColors.add(c);
            Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
            bgPixmap.setColor(c);
            bgPixmap.fill();
            TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
            allPixmaps.add(bgPixmap);
            allTextureRegionsDrawable.add(textureRegionDrawableBg);
        }

        private void RecolorUnitOnClick(UnitTable unitTable) {
            selectedUnitTable = unitTable;
            if(selectedUnitTable != null) {
                unitTable.table.setBackground(allTextureRegionsDrawable.get(2));
            }
        }

        private void ResetColorUnitOnClick() {
            if(selectedUnitTable != null) {
                selectedUnitTable.table.setBackground(allTextureRegionsDrawable.get(0));
                selectedUnitTable = null;
            }
        }

        private void RecolorSectorOnClicked(SectorButton sectorButton) {
            selectedSectorButton = sectorButton;
            if(selectedSectorButton != null) {
                System.out.println("... sector recolored 2");
                selectedSectorButton.button.setColor(allColors.get(2));
            }
        }

        private void ResetSectorRecolorOnClicked() {
            if(selectedSectorButton != null) {
                selectedSectorButton.button.setColor(allColors.get(1));
                selectedSectorButton = null;
            }
        }

        private void AbilityRecolorOnClicked(AbilityTable abilityTable) {
            selectedAbilityTable = abilityTable;
            if(selectedAbilityTable != null) {
                selectedAbilityTable.table.setBackground(allTextureRegionsDrawable.get(2));
            }
        }

        private void ResetAbilityRecolorOnClicked() {
            if(selectedAbilityTable != null) {
                selectedAbilityTable.table.setBackground(allTextureRegionsDrawable.get(0));
                selectedAbilityTable = null;
            }
        }

        public UnitTable GetUnitTable(Table t, Unit u) {
            UnitTable result = new UnitTable(null,null);
            for(UnitTable ut : units) {
                if(ut.table == t) {
                    return ut;
                }
                if(ut.unit == u) {
                    return ut;
                }
            }
            return result;
        }

        public void ResetAllTouches() {
            ResetAbilityRecolorOnClicked();
            ResetColorUnitOnClick();
            ResetSectorRecolorOnClicked();
        }

        public SectorButton GetSectorButton(TextButton tb, Sector s) {
            SectorButton result = new SectorButton(null,null);
            for(SectorButton sb : sectorButtons) {
                if(sb.button == tb) {
                    System.out.println("sectorgetter");
                    return sb;
                }
                if(sb.sector == s) {
                    System.out.println("sectorgetter");
                    return sb;
                }
            }
            return result;
        }

        public AbilityTable GetAbilityTab(Table t, Abilities a) {
            AbilityTable result = new AbilityTable(null,null);
            for(AbilityTable at : abilityTables) {
                if(at.table == t) {
                    return at;
                }
                if(at.abilities == a) {
                    return at;
                }
            }
            return result;
        }

    //Pause
        private void PauseGame() {
            if(gamePaused) {
                gamePaused = false;
                alertMenuTab.setVisible(false);
                Gdx.input.setInputProcessor(battleStage);
            } else {
                gamePaused = true;
                Gdx.input.setInputProcessor(pauseStage);
            }
        }

        private void GameSetPaused() {
            if(!gamePaused) {
                PauseGame();
            }
        }

        private void GameSetUnpaused() {
            if(gamePaused) {
                PauseGame();
            }
        }

        private void AlertClicked() {
            GameSetUnpaused();
        }

        private void ResumeButtonCLicked() {
            GameSetUnpaused();
        }

        private void QuitButtonCLicked() {
            Gdx.app.exit();
        }

    //MenuTab
        public void UpdateResources() {
            String res = game.playerToShow.resources+"\n +"+game.playerToShow.resourceIncome;
            resourceButton.setText(res);
        }

        public void UpdateTechnology() {
            String tech = ""+game.playerToShow.technology;
            techButton.setText(tech);
        }

        public void ResourceClicked() {

        }

        public void TechnologyClicked() {

        }

        public void NextTurnClicked() {
            game.ClickNextTurn(game.playerToShow);
        }

        public void MenuClicked() {
            if(!gamePaused) {
                PauseGame();
            }
            nonBattleTable.setVisible(false);
            alertMenuTab.setVisible(true);
        }

    //UnitsTab
        public void UpdateUnitsTab(Sector s) {
            casterSector = s;
            ResetSectorRecolorOnClicked();
            RecolorSectorOnClicked(GetSectorButton(null,s));
            ResetUnitAbilitiesTab();

            if(s == null) {
                unitTab.reset();
                units.clear();
                return;
            }
            if(unitTab != null) {
                unitTab.reset();
            }
            if(units != null) {
                units.clear();
            }
            if(s.units.isEmpty() && s.pathsIn.isEmpty()) {
                return;
            }

            boolean playerSeeSector = false;
            for(Unit u : s.units) {
                if(u.owner == game.playerToShow) {
                    playerSeeSector = true;
                    break;
                }
            }
            if(playerSeeSector)
            {
                for(Unit u : s.units) {
                    AddUnitToUnitTab(u);
                }
            }

            if(s.pathsIn.isEmpty()) {
                return;
            }
            for(Path p : s.pathsIn) {
                for(Unit u : Path.GetKnownUnitsOnPath(game.playerToShow, p)) {
                    AddUnitToUnitTab(u);
                }
            }
        }

        private void AddUnitToUnitTab(final Unit u) {
            final Table tableT = new Table();
            final UnitTable unitTableT = new UnitTable(tableT, u);
            units.add(unitTableT);

            tableT.setBackground(allTextureRegionsDrawable.get(0));
            unitTab.add(tableT).top();
            unitTab.row();
            //1
            if(u.icon != null) {
                tableT.add(u.icon).height(heightindex).width(widthindex);
            } else {
                tableT.add().height(heightindex).width(widthindex);
            }
            //2
            Table tableL = new Table();
            tableT.add(tableL).height(heightindex);
            Label j = new Label("Power: "+Unit.DamageOutput(u,new Filters(),u.count)+" Life: "+Unit.GetFullLife(u),skin);
            j.setFontScale(2);
            tableL.add(j);
            tableL.row();
            Label k = new Label(u.typeName, skin);
            k.setFontScale(4);
            tableL.add(k);
            if(u.currentSector != null) {
                Label l = new Label(u.currentSector.name,skin);
                l.setFontScale(3f);
                tableL.add(l);
            } else if(u.currentPath != null) {
                System.out.println(u.currentPath.in.name);
                Label w = new Label(Path.GetUnitDistanceToTarget(u,u.currentPath)+" to "+u.currentPath.in.name,skin);
                w.setFontScale(2f);
                tableL.add(w);
            }
            tableL.row();
            Label f = new Label("Count: "+u.count+" Player: "+u.owner.playerName,skin);
            f.setFontScale(2f);
            tableL.add(f);
            tableT.setTouchable(Touchable.enabled);
            tableT.debug();
            tableT.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    UnitClicked(unitTableT);
                }
            });
        }

        public void ResetUnitsTab() {
            if(unitTab != null) {unitTab.reset();}
        }

        private void UnitClicked(UnitTable ut) {
            if(ut == selectedUnitTable) {
                ResetColorUnitOnClick();
                System.out.println("Unit Unselected");

            } else {
                ResetColorUnitOnClick();
                System.out.println("Unit Selected");
                RecolorUnitOnClick(ut);
            }

            ShowUnitAbilities(ut.unit);
        }

    //AbilitiesTab
        public void ShowUnitAbilities(final Unit u) {
            if(actionTab != null) {actionTab.reset(); abilityTables.clear();}

            for(final Abilities a : u.abilities) {
                final Table tableA = new Table();

                final AbilityTable at = new AbilityTable(tableA,a);
                abilityTables.add(at);

                tableA.setBackground(allTextureRegionsDrawable.get(0));
                actionTab.add(tableA);
                Label l = new Label(a.abilityName, skin);
                l.setFontScale(3.5f);
                tableA.add(l).height(heightindex).width(widthindex);
                tableA.debug();
                tableA.setTouchable(Touchable.enabled);
                if(u.owner == game.playerToShow) {
                    tableA.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                           AbilitiesOnClickActions(at);
                        }
                    });
                }
            }
        }

        public void ShowUnitAbilities() {
            ShowUnitAbilities(selectedUnitTable.unit);
        }

        public void ResetUnitAbilitiesTab() {
            if(actionTab != null) {actionTab.reset();}
        }

        private void AbilitiesOnClickActions(AbilityTable at) {
            if(at == selectedAbilityTable) {
                ResetAbilityRecolorOnClicked();
                System.out.println("Ability "+at.abilities.abilityName +" clicked again!");
            } else {
                ResetAbilityRecolorOnClicked();
                System.out.println("Ability "+at.abilities.abilityName +" clicked!");
                AbilityRecolorOnClicked(at);
                Abilities.CallAbility(at.abilities);
            }
        }

    //ShopTab
        public void ShowCardsInShop(final Player p) {
            if(shoppingTab != null) {
                shoppingTab.reset();
                cardButtons.clear();
            }
            int t;
            t = game.turnCount;

            ArrayList<Card> cards = GenerateCardsOffer(p,t);
            for(final Card c : cards) {
                Table shopT = new Table();

                shoppingTab.add(shopT);
                if(c.icon != null) {
                    shopT.add(c.icon).width(widthindex);
                } else {
                    shopT.add().width(widthindex);
                }
                TextButton buyC = new TextButton(c.name,skin);
                buyC.setWidth(widthindex*1.5f);
                buyC.setHeight(heightindex*1f);
                buyC.getLabel().setFontScale(3f,3f);
                shopT.add(buyC).width(widthindex*1.5f);

                Label buyL = new Label( " -"+c.resourceCost,skin);
                buyL.setWidth(widthindex*0.5f);
                buyL.setHeight(heightindex*1f);
                buyL.setFontScale(3f,3f);
                shopT.add(buyL);
                final CardButton cardButton = new CardButton(buyC,c,buyL);

                buyC.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        CardOnClickActions(p,cardButton);
                    }
                });
            }
        }

        private void CardOnClickActions(Player p, CardButton cardButton) {
            System.out.println(cardButton.card.name+" card being selected");
            Card.BuyCard(p,cardButton.card,selectedSectorButton.sector);
        }

        public void UpdateCardTab() {
            ShowCardsInShop(game.playerToShow);
        }

    //SectorTab
        public void GenerateSectors() {
            for(final Sector s : game.sectors) {
                TextButton sectorButton = new TextButton(s.name,skin);
                sectorButton.getLabel().setFontScale(3, 3);
                final SectorButton sb = new SectorButton(sectorButton,s);
                sectorButtons.add(sb);

                sectorButton.setWidth(widthindex);
                sectorButton.setHeight(heightindex);
                sectorTab.add(sectorButton).width(widthindex).height(heightindex);

                sectorButton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        SectorOnClickActions(sb);
                    }
                });

            }
        }

        public void UpdateSectorTab() {
            if(game.playerToShow.visibleSectors == null) {
                System.out.println("Error, player has no visible sectors");
                return;
            }

            for(SectorButton b : sectorButtons) {
                b.button.setVisible(false);
            }

            for(Sector s : game.playerToShow.visibleSectors) {
                TextButton b = GetSectorButton(null,s).button;
                b.setVisible(true);
            }
        }

        public void ResetSectorTab() {
            if(sectorTab != null) {
                sectorTab.reset();
            }
        }

        public void StopChoosingSector() {
            System.out.println("stop choosing sector to apply effect on");
            casterAbility = null;
            for(SectorButton sb : sectorButtons) {
                sb.button.setColor(allColors.get(1));
            }
        }

        public void AbilityChooseSector(Abilities a) {
            if(casterAbility== a) {
                StopChoosingSector();
                return;
            }
            System.out.println("Start choosing sector to apply ability on");
            casterAbility = a;
            for(SectorButton sb : sectorButtons) {
                sb.button.getLabel().setColor(new Color(100,200,100,1f));
            }
        }

        private void SectorOnClickAbility(SectorButton sb) {
            System.out.println("Applying ability for "+ sb.sector.name);
            if(casterAbility.moveToTargetSector && casterAbility.casterUnit.currentSector != sb.sector) {
                game.ApplyAbilityForSector(sb.sector);
            }

        }

        private void SectorOnClickActions(SectorButton sb) {
            System.out.println(sb.sector.name+" sector being selected");
            if(casterAbility != null) {
                SectorOnClickAbility(sb);
            }else {
                UpdateUnitsTab(sb.sector);
            }
        }

    //MessageTab
        public void IterateToNextWrittenMessage() {
            if(!game.playerToShow.turnSummary.isEmpty()) {
                String s = game.playerToShow.turnSummary.get(0);
                game.playerToShow.turnSummary.remove(0);
                messageWriter.setText(s);
            }
        }

    //alertTable
        public void ApplyWaitingScreen(String text) {
            alertMenuTab.setVisible(false);
            nonBattleTable.setVisible(true);
            SetTextAlert(text);
            GameSetPaused();
        }

        public void RemoverAlertScreen() {
            alertMenuTab.setVisible(true);
            nonBattleTable.setVisible(false);
            GameSetUnpaused();
        }

        public void WaitingTableClicked() {
            //Code for fade animation: backgroundActor.addAction(Actions.sequence(Actions.fadeOut(0.15f), Actions.fadeIn(0.15f)));
            RemoverAlertScreen();
        }

        public void SetTextAlert(String text) {
            Label l = (Label) nonBattleTable.getChild(0);
            l.setText(text);
        }

        public void ShowAlert(String text) {
            alertMenuTab.setVisible(false);
            nonBattleTable.setVisible(true);
            SetTextAlert(text);
            GameSetPaused();
        }
}


class UnitTable {
    Table table;
    Unit unit;

    public UnitTable(Table newTable, Unit newUnit) {
        table = newTable;
        unit = newUnit;
    }
}

class SectorButton {
    TextButton button;
    Sector sector;

    public SectorButton(TextButton newButton, Sector newSector) {
        button = newButton;
        sector = newSector;
    }
}

class AbilityTable {
    Table table;
    Abilities abilities;

    public AbilityTable(Table newTable, Abilities newAbilities) {
        table = newTable;
        abilities = newAbilities;
    }
}

class CardButton {
    TextButton button;
    Card card;
    Label label;

    public CardButton(TextButton newButton, Card newCard, Label newLabel) {
        card = newCard;
        button = newButton;
        label = newLabel;
    }
}