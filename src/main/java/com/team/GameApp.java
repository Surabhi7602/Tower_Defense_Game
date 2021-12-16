package com.team;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.SystemActions;
import com.almasb.fxgl.core.collection.PropertyChangeListener;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.TimerAction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import javafx.util.Duration;


import java.math.BigInteger;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameApp extends GameApplication {
    public static int UpgradeSpeedHelper = 100;
    static boolean hasBeenUpgraded = false;
    public int launchCounter = 0;

    public static Boolean getHasBeenUpgraded() {
        return hasBeenUpgraded;
    }

    private static boolean upgradeDecreasedMoney= false;

    public static Boolean getUpgradeDecreasedMoney() {
        return upgradeDecreasedMoney;
    }

    private Entity player;
    private Entity temporaryMonument;
    // For the sake of the demo, the boss will be spawned on round 3
    private final int FINAL_GAME_ROUND = 3;


    private static Boolean playerEnemyBulletCollisionDetected = false;

    public static Boolean getPlayerEnemyBulletCollisionDetected() {
        return playerEnemyBulletCollisionDetected;
    }

    private Boolean pressedEnter = true;

    public static String getUnlocked() {
        return unlocked;
    }

    private static String unlocked;

    public static int getCounter() {
        return counter;
    }

    private static int counter = 0;

    public static String getDifficulty() {
        return difficulty;
    }

    private static String difficulty;

    public static String getTower1() {
        return tower1;
    }

    public static void setTower1(String tower1) {
        GameApp.tower1 = tower1;
    }

    public static String getTower2() {
        return tower2;
    }

    public static String getmonumentTypeToDecideShooters() {
        return monumentTypeToDecideShooters;
    }

    public static void setTower2(String tower2) {
        GameApp.tower2 = tower2;
    }

    public static String getTower3() {
        return tower3;
    }

    public static void setTower3(String tower3) {
        GameApp.tower3 = tower3;
    }

    public static String getCoinVisible() {
        return coinVisible;
    }

    public static void setCoinVisible(String coinVisible) {
        GameApp.coinVisible = coinVisible;
    }

    public static StringProperty getCurrScreen() {
        return currScreenProperty;
    }

    private static String coinVisible;
    private static String tower1;
    private static String tower2;
    private static String tower3;
    private static int towerCost;

    public static Boolean getCloseGameExists() {
        return closeGameExists;
    }

    private static Boolean closeGameExists = true;

    public static Boolean getEnemyExists() {
        return enemyExists;
    }

    private static Boolean enemyExists = true;

    public static int getTowerCost() {
        return towerCost;
    }

    private static Boolean restartGameButtonExists = true;

    public static Boolean getRestartGameButtonExists() {
        return restartGameButtonExists;
    }

    private static Boolean roundButtonExists = true;

    public static Boolean getRoundButtonExists() {
        return roundButtonExists;
    }




    private static Boolean  UpgradeButtonExists = false;

    public static Boolean  getUpgradeButton() {
        return UpgradeButtonExists;
    }

    public static void setTowerCost(int towerCost) {
        GameApp.towerCost = towerCost;
    }

    public static String getTowerOneText() {
        return towerOneTextCheck;
    }

    public static String getTowerTwoText() {
        return towerTwoTextCheck;
    }

    public static String getTowerThreeText() {
        return towerThreeTextCheck;
    }

    public static String getConsoleTextCheck() {
        return consoleTextCheck;
    }

    public static String getConsoleTextCheck1() {
        return consoleTextCheck1;
    }

    private static String towerOneTextCheck;
    private static String towerTwoTextCheck;
    private static String towerThreeTextCheck;
    private static String consoleTextCheck;
    private static String consoleTextCheck1;

    public static double getPath() {
        return path;
    }

    private static double path;
    private static double objectLocation;
    private static double objectLocationX;
    private static double enemyLocationX;


    public static double getObjectLocation() {
        return objectLocation;
    }

    public static double getObjectLocationX() {
        return objectLocationX;
    }


    private static double endPlayerMoney;

    public static double getEndPlayerMoney() {
        return endPlayerMoney;
    }

    private static Button bt1;
    private static Button bt2;
    private static Button bt3;
    private static Button btnForOver1;
    private static Button btnForClose1;

    public static Button getBt1() {
        return bt1;
    }

    public static Button getBt2() {
        return bt2;
    }

    public static Button getBt3() {
        return bt3;
    }


    private static Button btStartCombat;

    public static Button getBtnStartCombat() {
        return btStartCombat;
    }

    public static String getBtnStartCombatText() {
        return btStartCombat.getText();
    }


    public static Button getBtnForOver() {
        return btnForOver1;
    }

    public static Button getBtnForClose() {
        return btnForClose1;
    }

    private static Boolean playerMonumentEnemyCollisionDetected;

    public static Boolean getPlayerMonumentEnemyCollisionDetected() {
        return playerMonumentEnemyCollisionDetected;
    }

    private static Boolean playerMonumentEnemyAttackDetected;

    public static Boolean getPlayerMonumentEnemyAttackDetected() {
        return playerMonumentEnemyAttackDetected;
    }

    private static Boolean bulletAttackDetected;

    public static Boolean getBulletAttackDetected() {
        return bulletAttackDetected;
    }

    private static Boolean weakbulletAttackDetected;

    public static Boolean weakgetBulletAttackDetected() {
        return weakbulletAttackDetected;
    }

    private static Boolean enemyHealthExists;

    public static Boolean getEnemyHealthExists() {
        return enemyHealthExists;
    }

    // Check if there exists enemies that vary in health and damage
    private static Boolean enemyVariationExists;

    public static Boolean getEnemyVariationExists() {
        return enemyVariationExists;
    }

    // Check if the destruction of an enemy increases the player's total money
    private static Boolean enemyDestructionIncreasedPlayerMoney;

    public static Boolean getEnemyDestructionIncreasedPlayerMoney() {
        return enemyDestructionIncreasedPlayerMoney;
    }

    public static int getUpgradeSpeedHelper()
    {
        return UpgradeSpeedHelper;
    }

  // Check if the final boss appears
    private static Boolean finalBossExists;
    public static Boolean getFinalBossExists() { return finalBossExists; }

    // Check if game stats are tracked properly
    private static Boolean gameStatsTracked;
    public static Boolean getGameStatsTracked() { return gameStatsTracked; }

    // Check if win game screen appears
    private static Boolean winGameScreen;
    public static Boolean getWinGameScreenExists() { return winGameScreen; }

    private static Boolean movementUp;
    public static Boolean getMovementUp() { return movementUp; }

    private static Boolean canMoveLeft;
    public static Boolean getMoveLeft() { return canMoveLeft; }

    private static Boolean totalGameTimeCheckerWorks;
    public static Boolean getTotalGameTimeCheckerWorks() { return totalGameTimeCheckerWorks; }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1920);
        settings.setHeight(800);
        settings.setManualResizeEnabled(true);
        settings.setPreserveResizeRatio(true);
        settings.setTitle("TOWER DEFENSE GAME");
    }

    protected void initInput() {
        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {

                // Check if player is at the end of the screen
                if (player != null && player.getX() <= 1500) {
                    player.translateX(10);
                }
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                // Check if player is at the end of the screen
                if (player != null && player.getX() >= -100) {
                    canMoveLeft = true;
                    player.translateX(-10);
                }
            }
        }, KeyCode.A);

        //spawn shooters

        getInput().addAction(new UserAction("Shoot") {
            @Override
            protected void onActionBegin() {
                int bulletDelta = 1;
                if (monumentTypeToDecideShooters.equals("MONUMENT 1")) {
                    bulletAttackDetected = true;
                    weakbulletAttackDetected = false;

                }
                if (monumentTypeToDecideShooters.equals("MONUMENT 2")) {
                    bulletDelta = 2;
                    bulletAttackDetected = false;
                    weakbulletAttackDetected = true;

                } else if (monumentTypeToDecideShooters.equals("MONUMENT 3")) {
                    bulletDelta = 3;
                    bulletAttackDetected = false;
                    weakbulletAttackDetected = true;

                }
                SpawnData bulletData = new SpawnData(0, 0);
                bulletData.put("damage", 5 * bulletDelta);
                bulletData.put("speed", UpgradeSpeedHelper/ bulletDelta);
                bulletData.put("x", temporaryMonument.getX() + 220);
                bulletData.put("y", temporaryMonument.getY() + 100);
                getGameWorld().spawn("Bullet", bulletData);

            }
        }, KeyCode.SPACE);

        getInput().addAction(new UserAction("ALIENShoot") {
            @Override
            protected void onActionBegin() {
                getGameWorld().spawn("Alien",
                        temporaryMonument.getX() + 800, temporaryMonument.getY() + 200);
            }
        }, KeyCode.L);

        UserAction moveTowerUp = new UserAction("Move Monument Up") {
            @Override
            protected void onAction() {
                if (temporaryMonument != null && pressedEnter
                        && temporaryMonument.getBottomY() >= 750) {
                    movementUp = true;
                    temporaryMonument.translateY(-1);
                    temporaryMonument.setScaleX(temporaryMonument.getScaleX() - 0.003);
                    temporaryMonument.setScaleY(temporaryMonument.getScaleY() - 0.003);
                }
            }
        };

        UserAction moveTowerDown = new UserAction("Move Monument Down") {
            @Override
            protected void onAction() {
                if (temporaryMonument != null && pressedEnter
                        && temporaryMonument.getBottomY() <= 800) {
                    temporaryMonument.translateY(1);
                    temporaryMonument.setScaleX(temporaryMonument.getScaleX() + 0.003);
                    temporaryMonument.setScaleY(temporaryMonument.getScaleY() + 0.003);
                }
            }
        };


        UserAction moveTowerLeft = new UserAction("Move Monument Left") {
            @Override
            protected void onAction() {
                if (temporaryMonument != null && pressedEnter) {
                    temporaryMonument.translateX(-2);
                }
            }
        };

        UserAction moveTowerRight = new UserAction("Move Monument Right") {
            @Override
            protected void onAction() {
                if (temporaryMonument != null && pressedEnter) {
                    temporaryMonument.translateX(2);
                }
            }
        };

        UserAction pressEnter = new UserAction("Press Enter") {
            @Override
            protected void onAction() {
                objectLocation = temporaryMonument.getBottomY();
                objectLocationX = temporaryMonument.getX();
                pressedEnter = false;
            }
        };


        getInput().addAction(moveTowerUp, KeyCode.UP);
        getInput().addAction(moveTowerDown, KeyCode.DOWN);
        getInput().addAction(moveTowerLeft, KeyCode.LEFT);
        getInput().addAction(moveTowerRight, KeyCode.RIGHT);
        getInput().addAction(pressEnter, KeyCode.ENTER);
    }

    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
        vars.put("currScreen", "welcome"); // Current viewable screen
        vars.put("playerName", ""); // Name of player input in welcome screen
        vars.put("gameDifficulty", ""); // Difficulty of game
        vars.put("playerMoney", 100); // Player's available money
        vars.put("playerMonumentHealth", 100); // Player monument's health
        vars.put("enemyMonumentHealth", 100); // Enemy monument's health
        vars.put("gameRound", 1); // Current round of game
        vars.put("enemyHealthMap",
                new HashMap<BigInteger, Integer>()); // Health of enemies with uid's as keys
        vars.put("gameStats", new HashMap<String, Integer>() {{
            put("damageByEnemy", 0);
            put("damageByPlayer", 0);
            put("gameTime", 0);
        }}); // Game's stats

        getGameWorld().addEntityFactory(new EnemyFactory());
    }

    protected Entity pathLine;


    // Initialize background
    protected void initializeBackground() {
        getGameScene().getPaddingTop().setFill(Color.BLACK);
        getGameScene().getPaddingBot().setFill(Color.BLACK);
        getGameScene().getPaddingRight().setFill(Color.BLACK);
        getGameScene().getPaddingLeft().setFill(Color.BLACK);

        ScrollingBackgroundView layer1 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/01_Sky.png", 1920, 1080), Orientation.HORIZONTAL, 0);
        ScrollingBackgroundView layer2 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/02_Stars.png", 1920, 1080), Orientation.HORIZONTAL, 0);
        ScrollingBackgroundView layer3 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/03_Moon.png", 1920, 1080), Orientation.HORIZONTAL, 0);
        ScrollingBackgroundView layer4 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/04_Cloud.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 8);
        ScrollingBackgroundView layer5 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/05_Cloud.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 8);
        ScrollingBackgroundView layer6 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/06_Cloud.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 8);
        ScrollingBackgroundView layer7 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/07_Cloud.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 8);
        ScrollingBackgroundView layer8 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/08_Cloud.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 8);
        ScrollingBackgroundView layer9 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/09_Cloudsmall.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 4);
        ScrollingBackgroundView layer10 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/10_Cloudsmall.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 4);
        ScrollingBackgroundView layer11 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/11_Mountains.png", 1920, 1080), Orientation.HORIZONTAL, 1.0 / 2);
        ScrollingBackgroundView layer12 = new ScrollingBackgroundView(getAssetLoader().loadTexture(
                "desert/12_Desert.png", 1920, 1080), Orientation.HORIZONTAL, 1);

        entityBuilder()
                .view(layer12)
                .zIndex(-1)
                .buildAndAttach();
        entityBuilder()
                .view(layer11)
                .zIndex(-2)
                .buildAndAttach();
        entityBuilder()
                .view(layer10)
                .zIndex(-3)
                .buildAndAttach();
        entityBuilder()
                .view(layer9)
                .zIndex(-4)
                .buildAndAttach();
        entityBuilder()
                .view(layer8)
                .zIndex(-5)
                .buildAndAttach();
        entityBuilder()
                .view(layer7)
                .zIndex(-6)
                .buildAndAttach();
        entityBuilder()
                .view(layer6)
                .zIndex(-7)
                .buildAndAttach();
        entityBuilder()
                .view(layer5)
                .zIndex(-8)
                .buildAndAttach();
        entityBuilder()
                .view(layer4)
                .zIndex(-9)
                .buildAndAttach();
        entityBuilder()
                .view(layer3)
                .zIndex(-10)
                .buildAndAttach();
        entityBuilder()
                .view(layer2)
                .zIndex(-11)
                .buildAndAttach();
        entityBuilder()
                .view(layer1)
                .zIndex(-12)
                .buildAndAttach();

        Line line = new Line();
        line.setStrokeWidth(2);
        line.setStartX(250);
        line.setStartY(770);
        line.setEndX(3000);
        line.setEndY(770);
        path = line.getStartY();
        pathLine = entityBuilder()
                .view(line)
                .buildAndAttach();
    }

    // Intialize Player
    protected void initalizePlayer() {
        player = entityBuilder()
                .buildAndAttach();

        getGameScene().getViewport().bindToEntity(player, 0, 0);
        getGameScene().getViewport().setLazy(true);
    }

    // Player and enemy monuments
    private Entity playerMonument;
    private Entity playerMonumentHealth;
    private Entity enemyMonument;
    private Entity enemyMonumentHealth;

    // Initalize player and enemy monuments
    protected void initalizeMonuments() {
        // Load player monument image
        Texture playerTower = getAssetLoader().loadTexture("monuments/player_tower.png");
        playerTower.setScaleX(2);
        playerTower.setScaleY(2);
        playerMonument = entityBuilder().type(EntityType.PLAYERMONUMENT)
                .viewWithBBox(playerTower).collidable().at(100, 500).buildAndAttach();
        // Display player monument's health
        HBox playerMonumentHealthHBox = new HBox(10);
        playerMonumentHealthHBox.setTranslateX(100);
        playerMonumentHealthHBox.setTranslateY(350);
        playerMonumentHealthHBox.setStyle("-fx-font-size: 25px; -fx-font-weight: 700");
        Text playerMonumentHealthLabel = new Text("Health:");
        Text playerMonumentHealthText = new Text();
        playerMonumentHealthText.textProperty().bind(getWorldProperties()
                .intProperty("playerMonumentHealth").asString());
        playerMonumentHealthHBox.getChildren()
                .addAll(playerMonumentHealthLabel, playerMonumentHealthText);
        addUINode(playerMonumentHealthHBox);

        // Load enemy monument image
        Texture enemyTower = getAssetLoader().loadTexture("monuments/enemy_tower.png");
        enemyTower.setScaleX(2.6);
        enemyTower.setScaleY(2.6);
        enemyMonument = entityBuilder().at(3000, 500).view(enemyTower).buildAndAttach();
        // Display enemy monument's health
        Text enemyMonumentHealthText = new Text();
        enemyMonumentHealthText.setStyle("-fx-font-size: 25px; -fx-font-weight: 700");
        enemyMonumentHealthText.textProperty()
                .setValue("Health: " + getWorldProperties().getValue("enemyMonumentHealth"));
        enemyMonumentHealth = entityBuilder()
                .at(3000, 350).view(enemyMonumentHealthText).buildAndAttach();


    }

    //spawns enemies
    protected void spawnEnemies() {
        int currGameRound = getWorldProperties().getInt("gameRound");
        if (currGameRound == FINAL_GAME_ROUND) {
            spawnBoss();
            return;
        }
        int enemyMultiplier = (currGameRound - 1) % 3 + 1;
        enemyVariationExists = true;
        for (int i = 0; i < currGameRound; i++) {
            TimerAction enemySpawnerTimer = getGameTimer().runOnceAfter(() -> {
                SpawnData enemyData = new SpawnData(0, 0);
                enemyData.put("health", enemyMultiplier * 20);
                enemyData.put("damage", enemyMultiplier * 5);
                enemyData.put("scale", (double) enemyMultiplier);
                enemyData.put("money", enemyMultiplier * 50);
                enemyData.put("isBoss", false);
                Entity enemy = spawn("enemy", enemyData);
                enemy.addComponent(new EnemyComponent());
            }, Duration.seconds(i * 2));
            PropertyMap state = FXGL.getWorldProperties();
            IntegerProperty playerMonumentHealthProperty
                    = state.intProperty("playerMonumentHealth");
            playerMonumentHealthProperty
                    .addListener((observable, oldHealthValue, newHealthValue) -> {
                        if ((int) newHealthValue == 0) {
                            enemySpawnerTimer.expire();
                        }
                    });
        }
        getWorldProperties().setValue("gameRound", currGameRound + 1);
    }

    // spawns final boss
    protected void spawnBoss() {
        finalBossExists = true;
        SpawnData enemyData = new SpawnData(0, 0);
        enemyData.put("health", 100);
        enemyData.put("damage", 35);
        enemyData.put("scale", 3.0);
        enemyData.put("money", 1000);
        enemyData.put("isBoss", true);
        Entity enemy = spawn("enemy", enemyData);
        enemy.addComponent(new EnemyComponent());
        getWorldProperties().setValue("gameRound", getWorldProperties().getInt("gameRound") + 1);
    }

    protected void incrementGameStat(String statName, Integer incrementByValue) {
        gameStatsTracked = true;
        totalGameTimeCheckerWorks = true;
        HashMap<String, Integer> newGameStats = getWorldProperties().getObject("gameStats");
        Integer currGameStatValue = newGameStats.get(statName);
        newGameStats.put(statName, currGameStatValue + incrementByValue);
        getWorldProperties().setValue("gameStats", newGameStats);
    }


    @Override
    protected void initPhysics() {
        System.out.println("Initializing physics...");


        onCollisionBegin(EntityType.PLAYERMONUMENT, EntityType.ENEMY, (proj, enemy) -> {
            System.out.println("Collision between playermonument and enemy");
            playerMonumentEnemyCollisionDetected = true;
            enemy.removeComponent(ProjectileComponent.class);
            BigInteger currEnemyUid = enemy.getProperties().getValue("uid");
            HashMap<BigInteger, Integer> enemyHealthMap =
                    getWorldProperties().getValue("enemyHealthMap");
            enemyHealthExists = true;
            AtomicInteger currEnemyHealth
                    = new AtomicInteger(enemy.getProperties().getInt("health"));
            AtomicInteger enemyDamage
                    = new AtomicInteger(enemy.getProperties().getInt("damage") / 2);
            TimerAction enemyAttackTimer = getGameTimer().runAtInterval(() -> {
                int currPlayerMonumentHealth
                        = getWorldProperties().getValue("playerMonumentHealth");
                if (currPlayerMonumentHealth <= 0
                        || currEnemyHealth.get() - enemyDamage.get() <= 0) {
                    // Increment game stats
                    incrementGameStat("damageByEnemy", enemyDamage.get());
                    if (enemy.isActive()) {
                        // If the destoryed enemy is the final boss, display win game screen
//                        if (currPlayerMonumentHealth > 0 && enemy.getProperties().getBoolean("isBoss")) {
//                            System.out.println("(currPlayerMonumentHealth:" + currPlayerMonumentHealth);
//
//                            displayEndWinScreen();
//                        }
                        enemy.removeFromWorld();
                        // Remove enemy health from global variable
                        enemyHealthMap.remove(currEnemyUid);
                        getWorldProperties().setValue("enemyHealthMap", enemyHealthMap);
                        getWorldProperties().setValue("playerMoney",
                                getWorldProperties().getInt("playerMoney")
                                        + enemy.getProperties().getInt("money"));
                    }
                } else {
                    int monumentDamage = 10 / 2;
                    int newHealth = currEnemyHealth.addAndGet(-monumentDamage);
                    // Increment game stats
                    incrementGameStat("damageByEnemy", enemyDamage.get());
                    incrementGameStat("damageByPlayer", monumentDamage);
                    // Set the enemy health in the global variable
                    enemyHealthMap.put(currEnemyUid, newHealth);
                    getWorldProperties().setValue("enemyHealthMap",
                            new HashMap<BigInteger, Integer>(enemyHealthMap));
                    getWorldProperties().setValue("playerMonumentHealth",
                            currPlayerMonumentHealth - enemyDamage.get());
                    playerMonumentEnemyAttackDetected = true;
                }
            }, Duration.seconds(1));
            PropertyMap state = FXGL.getWorldProperties();
            IntegerProperty playerMonumentHealthProperty
                    = state.intProperty("playerMonumentHealth");
            playerMonumentHealthProperty.
                    addListener((observable, oldHealthValue, newHealthValue) -> {
                        if ((int) newHealthValue == 0) {
                            enemyAttackTimer.expire();
                        }
                    });

        });

        onCollisionBegin(EntityType.ENEMY, EntityType.BULLET, (enemy, bullet) -> {
            System.out.println("Collision between bullet and enemy");
            playerEnemyBulletCollisionDetected = true;
            bulletAttackDetected = true;
            // Increment game stats
            incrementGameStat("damageByPlayer", enemy.getProperties().getInt("health"));
            if (enemy.getProperties().getBoolean("isBoss")) {
                displayEndWinScreen();
            }
            // Remove bullet from game
            bullet.removeFromWorld();
            // Remove enemy from game
            enemy.removeFromWorld();
            // Increment money
            getWorldProperties().setValue("playerMoney",
                    getWorldProperties().getInt("playerMoney")
                            + enemy.getProperties().getInt("money"));
            enemyDestructionIncreasedPlayerMoney = true;
            enemyExists = false;


        });

        onCollisionBegin(EntityType.WEAKBULLET, EntityType.ENEMY, (weakbullet, enemy) -> {
            weakbulletAttackDetected = true;
            getWorldProperties().setValue("playerMoney",
                    getWorldProperties().getInt("playerMoney")
                            + enemy.getProperties().getInt("money"));
            weakbullet.removeFromWorld();
            enemy.removeFromWorld();
            enemyExists = false;
            playerEnemyBulletCollisionDetected = true;
        });


    }

    private static StringProperty currScreenProperty;


    protected void initGame() {
        PropertyMap state = FXGL.getWorldProperties();
        currScreenProperty = state.stringProperty("currScreen");

        currScreenProperty.addListener((observable, oldScreen, newScreen) -> {
            if (newScreen.equals("game")) {
                initalizePlayer();
                initalizeMonuments();
                initializeBackground();
            }
        });

    }


    // Display the welcome dialog
    protected void displayErrorDialog() {
        // Show welcome screen
        Map<String, Runnable> errorDialog = new LinkedHashMap<>();

        errorDialog.put("Error", () ->
                getDialogService().showErrorBox("Enter a valid name!", () -> {
                }));

        ChoiceBox<String> errorCBDialog =
                getUIFactoryService()
                        .newChoiceBox(FXCollections.observableArrayList(errorDialog.keySet()));

        errorCBDialog.getSelectionModel().selectFirst();
    }


    // Display the welcome dialog
    protected void displayWelcomeDialog() {
        // Show welcome screen
        Map<String, Runnable> welcomeDialog = new LinkedHashMap<>();

        welcomeDialog.put("Input", () ->
                getDialogService().showInputBox("Enter your name", answer -> {
                    if (!answer.isBlank()) {
                        getWorldProperties().setValue("playerName", answer);
                        getWorldProperties().setValue("currScreen", "menu");
                    } else {
                        Map<String, Runnable> errorDialog = new LinkedHashMap<>();
                        errorDialog.put("Error", () ->
                                getDialogService().showErrorBox("Enter a valid name!", () -> {
                                }));
                        ChoiceBox<String> errorCBDialog =
                                getUIFactoryService()
                                        .newChoiceBox(FXCollections.
                                                observableArrayList(errorDialog.keySet()));
                        String errorType = errorCBDialog.getSelectionModel().getSelectedItem();
                        errorDialog.get("Error").run();
                    }
                }));


        ChoiceBox<String> welcomeCBDialog =
                getUIFactoryService().
                        newChoiceBox(FXCollections.observableArrayList(welcomeDialog.keySet()));

        welcomeCBDialog.getSelectionModel().selectFirst();

        Button btnforname = getUIFactoryService().newButton("Get Started");

        btnforname.setOnMouseClicked(e -> {
            String dialogType = welcomeCBDialog.getSelectionModel().getSelectedItem();
            if (welcomeDialog.containsKey(dialogType)) {
                welcomeDialog.get(dialogType).run();
            } else {
                System.out.println("Unknown dialog type");
            }
        });

        VBox welcomeVBox = new VBox(10);
        //welcomeVBox.setTranslateX(60);

        welcomeVBox.getChildren().addAll(

                btnforname
        );

        welcomeVBox.setAlignment(Pos.CENTER);
        welcomeVBox.setTranslateX(850);
        welcomeVBox.setTranslateY(400);
        getGameScene().getRoot().setStyle("-fx-background-color: #A6D9F7");
        getGameScene().addUINode(welcomeVBox);
    }

    private String dialogType;

    // Display the menu
    protected void displayMenu() {
        // Show difficulty dialog
        Map<String, Runnable> difficultyDialog = new LinkedHashMap<>();

        difficultyDialog.put("Easy", () -> {
        });
        difficultyDialog.put("Medium", () -> {
        });
        difficultyDialog.put("Hard", () -> {
        });


        ChoiceBox<String> difficultyCBDialog =
                getUIFactoryService()
                        .newChoiceBox(FXCollections.observableArrayList(difficultyDialog.keySet()));

        difficultyCBDialog.getSelectionModel().selectFirst();

        Button btnbegin = getUIFactoryService().newButton("BEGIN GAME");

        btnbegin.setOnAction(e -> {
            dialogType = difficultyCBDialog.getSelectionModel().getSelectedItem();
            if (difficultyDialog.containsKey(dialogType)) {
                // difficultyDialog.get(dialogType).run();
                getWorldProperties()
                        .setValue("gameDifficulty", dialogType); // Set the game difficulty
                // Set the player's starting money depending on difficulty
                difficulty = dialogType;
                if (dialogType == "Easy") {
                    getWorldProperties().setValue("playerMoney", 1000);
                    getWorldProperties().setValue("enemyMonumentHealth", 100);
                    getWorldProperties().setValue("towerOneMoney", 10);
                    getWorldProperties().setValue("towerTwoMoney", 50);
                    getWorldProperties().setValue("towerThreeMoney", 100);
                } else if (dialogType == "Medium") {
                    getWorldProperties().setValue("playerMoney", 750);
                    getWorldProperties().setValue("enemyMonumentHealth", 150);
                    getWorldProperties().setValue("towerOneMoney", 50);
                    getWorldProperties().setValue("towerTwoMoney", 100);
                    getWorldProperties().setValue("towerThreeMoney", 150);
                } else {
                    getWorldProperties().setValue("playerMoney", 500);
                    getWorldProperties().setValue("enemyMonumentHealth", 200);
                    getWorldProperties().setValue("towerOneMoney", 100);
                    getWorldProperties().setValue("towerTwoMoney", 200);
                    getWorldProperties().setValue("towerThreeMoney", 300);
                }
                //  getWorldProperties().setValue("currScreen", "game"); // Change the current scene
                getWorldProperties().setValue("currScreen", "towermenu");
            } else {
                System.out.println("Unknown dialog type");
            }
        });


        VBox difficultyVBox = new VBox(20);
        //difficultyVBox.setPadding(new Insets(100, 100, 100, 100));
        difficultyVBox.setAlignment(Pos.CENTER);
        difficultyVBox.setTranslateX(850);
        difficultyVBox.setTranslateY(250);
        difficultyVBox.getChildren().addAll(
                getUIFactoryService().newText("Select Difficulty", Color.WHITE, 30),
                difficultyCBDialog,
                btnbegin
        );
        getGameScene().getRoot().setStyle("-fx-background-color: #A6D9F7");
        getGameScene().addUINode(difficultyVBox);
    }

    protected void displayGameOver() {
        // Show Game Over Scene
        Map<String, Runnable> welcomeDialog = new LinkedHashMap<>();
        ChoiceBox<String> welcomeCBDialog =
                getUIFactoryService().
                        newChoiceBox(FXCollections.observableArrayList(welcomeDialog.keySet()));
        welcomeCBDialog.getSelectionModel().selectFirst();
        Button btnForOver = getUIFactoryService().newButton("Restart Game");
        Button btnForClose = getUIFactoryService().newButton("Close Game");
        btnForOver.setOnMouseClicked(e -> {
            FXGL.getWorldProperties().setValue("currScreen", "welcome");
            System.out.println("restart game");

        });

        btnForClose.setOnMouseClicked(e -> {
            System.exit(0);
            System.out.println("close game");
        });

        VBox gameConsolationText = new VBox(10);
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().
                        newText("Keep practicing! You'll get better each time!", Color.BLACK, 30)
        );
        consoleTextCheck = "Keep practicing! You'll get better each time!";
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("You got this!", Color.BLACK, 30)
        );
        consoleTextCheck1 = "You got this!";
        gameConsolationText.getChildren().addAll(
                btnForOver, btnForClose
        );

        //TODO: REMOVE THESE SHELL VARIABLES AND REPLACE CODE BELOW WITH ACTUAL VARIABLES
        System.out.println("---Final boss destroyed, display win game screen---");
        HashMap<String, Integer> gameStatsHashMap = getWorldProperties().getObject("gameStats");
        // Damage inflicted by enemy to player monument
        int damageByEnemy = gameStatsHashMap.get("damageByEnemy");
        System.out.println("Damge by enemy: " + damageByEnemy);
        // Damage inflicted by player monument / tower to enemies
        int damageByPlayer = gameStatsHashMap.get("damageByPlayer");
        System.out.println("Damge by player: " + damageByPlayer);
        // Total playing time in seconds
        int currTime = (int) (new Date().getTime() / 1000);
        int startTime = gameStatsHashMap.get("gameTime");
        int totalGameTime = currTime - startTime;
        System.out.println("Game time: " + totalGameTime + "sec");

        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("Damage by enemy: " + damageByEnemy, Color.BLUE, 30)
        );
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("Damage by player: " + damageByPlayer, Color.BLUE, 30)
        );
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("Total play time: " + totalGameTime + "sec", Color.BLUE, 30)
        );

        gameConsolationText.setAlignment(Pos.CENTER);
        gameConsolationText.setTranslateX(850);
        gameConsolationText.setTranslateY(400);
        getGameScene().getRoot().setStyle("-fx-background-color: #fff");
        getGameScene().addUINode(gameConsolationText);
    }

    protected void displayEndWinScreen() {
        // Show End Game Screen
        winGameScreen = true;
        Map<String, Runnable> welcomeDialog = new LinkedHashMap<>();
        ChoiceBox<String> welcomeCBDialog =
                getUIFactoryService().
                        newChoiceBox(FXCollections.observableArrayList(welcomeDialog.keySet()));
        welcomeCBDialog.getSelectionModel().selectFirst();
        Button btnForOver = getUIFactoryService().newButton("Restart Game");
        Button btnForClose = getUIFactoryService().newButton("Close Game");
        btnForOver.setOnMouseClicked(e -> {
            FXGL.getWorldProperties().setValue("currScreen", "welcome");
            System.out.println("restart game");

        });

        btnForClose.setOnMouseClicked(e -> {
            System.exit(0);
            System.out.println("close game");
        });

        VBox gameConsolationText = new VBox(10);
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().
                        newText("Congratulations, you won!", Color.BLACK, 30)
        );
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("You successfully defended your land from invasion!", Color.BLACK, 30)
        );
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("You will forever be remembered as a HERO!", Color.BLACK, 30)
        );
        gameConsolationText.getChildren().addAll(
                btnForOver, btnForClose
        );

        //TODO: REMOVE THESE SHELL VARIABLES AND REPLACE CODE BELOW WITH ACTUAL VARIABLES
        System.out.println("---Final boss destroyed, display win game screen---");
        HashMap<String, Integer> gameStatsHashMap = getWorldProperties().getObject("gameStats");
        // Damage inflicted by enemy to player monument
        int damageByEnemy = gameStatsHashMap.get("damageByEnemy");
        System.out.println("Damge by enemy: " + damageByEnemy);
        // Damage inflicted by player monument / tower to enemies
        int damageByPlayer = gameStatsHashMap.get("damageByPlayer");
        System.out.println("Damge by player: " + damageByPlayer);
        // Total playing time in seconds
        int currTime = (int) (new Date().getTime() / 1000);
        int startTime = gameStatsHashMap.get("gameTime");
        int totalGameTime = currTime - startTime;
        System.out.println("Game time: " + totalGameTime + "sec");
        getWorldProperties().setValue("currScreen", "endWinScreen");

        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("Damage by enemy: " + damageByEnemy, Color.BLUE, 30)
        );
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("Damage by player: " + damageByPlayer, Color.BLUE, 30)
        );
        gameConsolationText.getChildren().addAll(
                getUIFactoryService().newText("Total play time: " + totalGameTime + "sec", Color.BLUE, 30)
        );

        gameConsolationText.setAlignment(Pos.CENTER);
        gameConsolationText.setTranslateX(850);
        gameConsolationText.setTranslateY(400);
        getGameScene().getRoot().setStyle("-fx-background-color: #fff");
        getGameScene().addUINode(gameConsolationText);
    }

    protected void monumentPurchaseHandler(int monumentPrice) {
        int currPlayerMoney = getWorldProperties().getValue("playerMoney");

        getWorldProperties().setValue("currScreen", "game");
    }

    protected Button getMonumentSelectionButton(String buttonText,
                                                String monumentPriceVariable, String imagePath) {

        Button monumentButton = getUIFactoryService().newButton(buttonText);

        monumentButton.setOnAction(e -> {
            monumentTypeToDecideShooters = buttonText;
            monumentPurchaseHandler(getWorldProperties().getValue(monumentPriceVariable));
            pressedEnter = true;
            temporaryMonument = entityBuilder().type(EntityType.PLAYERTOWER)
                    .collidable()
                    .at(400, 550)
                    .viewWithBBox(texture(imagePath, 250, 250))
                    .buildAndAttach();
        });
        monumentButton.setPrefWidth(400);
        return monumentButton;
    }

    protected Text getMonumentInfo(String monumentInfo) {
        Text monumentInfoText = new Text(monumentInfo);
        monumentInfoText.setWrappingWidth(400);
        monumentInfoText.setFill(Color.BLACK);
        monumentInfoText.setStyle("-fx-font-size: 20px; -fx-font-weight: 500");
        return monumentInfoText;
    }

    protected Text getConsoleInfo(String consoleInfo) {
        Text consoleInfoText = new Text(consoleInfo);
        consoleInfoText.setWrappingWidth(400);
        consoleInfoText.setFill(Color.BLACK);
        consoleInfoText.setStyle("-fx-font-size: 20px; -fx-font-weight: 500");
        return consoleInfoText;
    }

    protected Text getConsoleInfo1(String consoleInfo) {
        Text consoleInfoText = new Text(consoleInfo);
        consoleInfoText.setWrappingWidth(400);
        consoleInfoText.setFill(Color.BLACK);
        consoleInfoText.setStyle("-fx-font-size: 20px; -fx-font-weight: 500");
        return consoleInfoText;
    }

    private static String monumentTypeToDecideShooters;

    protected HBox getMonumentPriceButton(String monumentPriceVariable) {
        HBox priceButtonContainer = new HBox(10);
        priceButtonContainer.setAlignment(Pos.CENTER);
        priceButtonContainer.setPrefWidth(400);
        priceButtonContainer.setStyle("-fx-padding: 10px 0px;");
        Texture moneyIcon = getAssetLoader().loadTexture("/icons/coin-icon.png", 20, 20);
        Text priceText = new Text();
        priceText.textProperty().bind(getWorldProperties()
                .intProperty(monumentPriceVariable).asString());
        priceText.setFill(Color.BLACK);
        priceText.setStyle("-fx-font-size: 20px; -fx-font-weight: 500");
        priceButtonContainer.getChildren().addAll(moneyIcon, priceText);
        return priceButtonContainer;
    }

    protected void displayTowerMenu() {
        VBox selectText = new VBox(20);
        selectText.getChildren().addAll(
                getUIFactoryService().newText("SELECT A MONUMENT", Color.BLACK, 30)
        );
        selectText.setTranslateX(750);
        selectText.setTranslateY(50);

        var monument1 = getAssetLoader().loadTexture("monuments/monument1.png", 400, 400);
        var monument2 = getAssetLoader().loadTexture("monuments/monument2.png", 400, 400);
        var monument3 = getAssetLoader().loadTexture("monuments/monument3.png", 400, 400);

        HBox towerimages = new HBox(100);
        towerimages.setStyle("-fx-background-color: #fff; -fx-color: #fff;");

        towerimages.setAlignment(Pos.CENTER);
        towerimages.getChildren().addAll(monument1, monument2, monument3);

        towerimages.setTranslateX(200);
        towerimages.setTranslateY(250);

        Button btnForMonument1 = getMonumentSelectionButton("MONUMENT 1",
                "towerOneMoney", "monuments/monument1.png");
        Button btnForMonument2 = getMonumentSelectionButton("MONUMENT 2",
                "towerTwoMoney", "monuments/monument2.png");
        Button btnForMonument3 = getMonumentSelectionButton("MONUMENT 3",
                "towerThreeMoney", "monuments/monument3.png");

        bt1 = btnForMonument1;
        bt2 = btnForMonument2;
        bt3 = btnForMonument3;

        setTower1(btnForMonument1.getText());
        setTower2(btnForMonument2.getText());
        setTower3(btnForMonument3.getText());

        HBox towerOptions = new HBox(100);
        towerOptions.setTranslateX(200);
        towerOptions.setTranslateY(650);
        towerOptions.setAlignment(Pos.CENTER);

        towerOptions.getChildren().addAll(btnForMonument1, btnForMonument2, btnForMonument3);

        HBox moneyInfo = new HBox(100);
        //moneyInfo.setStyle("-fx-background-color: #000000;
        // -fx-padding: 10px 50px; -fx-color: #FFFFFF;");
        moneyInfo.setAlignment(Pos.CENTER);
        // Money Text
        HBox monumentOnePriceButton = getMonumentPriceButton("towerOneMoney");
        HBox monumentTwoPriceButton = getMonumentPriceButton("towerTwoMoney");
        HBox monumentThreePriceButton = getMonumentPriceButton("towerThreeMoney");

        moneyInfo.setTranslateX(200);
        moneyInfo.setTranslateY(200);
        moneyInfo.setAlignment(Pos.CENTER);
        moneyInfo.getChildren().addAll(monumentOnePriceButton,
                monumentTwoPriceButton, monumentThreePriceButton);

        Text towerOneText = getMonumentInfo("Blue tower is the coolest "
                + "because it's the color of the sky!");
        Text towerTwoText = getMonumentInfo("Pink tower is the cutest one!");
        Text towerThreeText = getMonumentInfo("Green tower camouflages really well!");

        towerOneTextCheck = towerOneText.getText();
        towerTwoTextCheck = towerTwoText.getText();
        towerThreeTextCheck = towerThreeText.getText();

        HBox towerInfo = new HBox(100);
        towerInfo.setAlignment(Pos.TOP_CENTER);
        towerInfo.getChildren().addAll(towerOneText, towerTwoText, towerThreeText);
        towerInfo.setTranslateX(200);
        towerInfo.setTranslateY(710);

        getGameScene().getRoot().setStyle("-fx-background-color: #fff");

        getGameScene().addUINode(selectText);
        getGameScene().addUINode(towerimages);
        getGameScene().addUINode(towerOptions);
        getGameScene().addUINode(moneyInfo);
        getGameScene().addUINode(towerInfo);
    }

    // Display the player's name
    protected void displayPlayerName() {
        Text playerNameText = new Text();
        playerNameText.setStyle("-fx-font-size: 25px; -fx-font-weight: 700");
        playerNameText.setTranslateX(300); // x = 50
        playerNameText.setTranslateY(50); // y = 100

        playerNameText.textProperty().setValue("Player Name: "
                + getWorldProperties().getValue("playerName"));

        Text difficultyText = new Text("Difficulty: " + difficulty);
        difficultyText.setStyle("-fx-font-size: 25px; -fx-font-weight: 700");
        difficultyText.setTranslateX(800); // x = 800
        difficultyText.setTranslateY(50); // y = 100
        difficultyText.textProperty().setValue("Difficulty: " + difficulty);

        Button btnStartCombat = getUIFactoryService().newButton("BEGIN ROUND 1");
        btnStartCombat.setFocusTraversable(false);
        btnStartCombat.setPrefWidth(400);

        btnStartCombat.setTranslateX(1500);
        btnStartCombat.setTranslateY(40);
        btnStartCombat.setTranslateZ(150);

        btnStartCombat.setOnAction(e -> {
            spawnEnemies();
        });
        btStartCombat = btnStartCombat;

        FXGL.getWorldProperties().addListener("gameRound", new PropertyChangeListener<>() {
            @Override
            public void onChange(Object oldValueObj, Object newValueObj) {
                // The game round changes when a round starts
                // Disable the button until all enemies have been sent

                btnStartCombat.setDisable(true);
                btnStartCombat.setOpacity(0.5);

                if (getWorldProperties().getInt("gameRound") <= FINAL_GAME_ROUND) {
                    // After the cool down, enable the button and change the game round text if not on final round
                    getGameTimer().runOnceAfter(() -> {
                        int newValue = (int) newValueObj;
                        btnStartCombat.setText("BEGIN ROUND " + newValue);
                        btnStartCombat.setDisable(false);
                        btnStartCombat.setOpacity(1);
                        // We add a 1 second buffer in addition to waiting for the enemies to spawn
                    }, Duration.seconds(2 * (getWorldProperties().getInt("gameRound") - 1) + 1));
                }
            }
        });



        Button btnUpgrade = getUIFactoryService().newButton("UPGRADE TOWER");
        UpgradeButtonExists = true;
        btnUpgrade.setOnAction(e -> {
            hasBeenUpgraded = true;

            UpgradeSpeedHelper = UpgradeSpeedHelper  + 130;
            int currPlayerMoney = getWorldProperties().getValue("playerMoney");
            getWorldProperties().setValue("playerMoney", currPlayerMoney - 100);
            upgradeDecreasedMoney = true;
        });

        btnUpgrade.setFocusTraversable(false);
        btnUpgrade.setPrefWidth(400);
        btnUpgrade.setTranslateX(1500);
        btnUpgrade.setTranslateY(80);


        getGameScene().addUINode(difficultyText);
        getGameScene().addUINode(playerNameText);
        getGameScene().addUINode(btnStartCombat);
        getGameScene().addUINode(btnUpgrade);

    }


    // Display the player's money
    protected void displayPlayerMoney() {
        // Container
        HBox gameInfo = new HBox();
        gameInfo.setStyle("-fx-background-color: #000; -fx-padding: 10px 50px; -fx-color: #fff;");
        gameInfo.setSpacing(10);
        gameInfo.setAlignment(Pos.CENTER);
        // Money Text
        Text moneyText = new Text();
        moneyText.setStyle("-fx-font-size: 20px; -fx-font-weight: 500;");
        moneyText.setFill(Color.WHITE);
        int currPlayerMoney = getWorldProperties().getValue("playerMoney");
        endPlayerMoney = currPlayerMoney;
        moneyText.textProperty().bind(getWorldProperties().intProperty("playerMoney").asString());

        // Money Icon
        var moneyIcon = getAssetLoader().loadTexture("/icons/coin-icon.png", 20, 20);
        gameInfo.getChildren().addAll(moneyIcon, moneyText);

        getGameScene().addUINode(gameInfo); // add to the scene graph
        setCoinVisible(moneyText.getText());


        Text enemyMonumentHealthText = new Text();
        enemyMonumentHealthText.setStyle("-fx-font-size: 25px; -fx-font-weight: 700");
        enemyMonumentHealthText.textProperty()
                .setValue("Health: " + getWorldProperties().getValue("enemyMonumentHealth"));
    }






    // Get all that is necessary for the game
    protected void getGameUI() {
        displayPlayerMoney();
        displayPlayerName();
        initalizeMonuments();
        getWorldProperties().addListener("playerMonumentHealth", new PropertyChangeListener<>() {
            @Override
            public void onChange(Object o, Object t1) {
                int t2 = (int) t1;
                int original = (int) o;
                List<Entity> entitiesList = getGameWorld().getEntities();
                if (t2 <= 0) {
                    FXGL.getWorldProperties().setValue("currScreen", "endGameScreen");
                    System.out.println("game over");
                }
            }
        });
        if (launchCounter <= 0) {
            getInput().addAction(new UserAction("Unlock tower") {
                @Override
                protected void onAction() {
                    if (!pressedEnter) {
                        pressedEnter = true;
                        unlocked = "unlocked";
                        counter++;
                    }
                }
            }, KeyCode.X);
        }
        launchCounter += 1;
    }

    @Override
    protected void initUI() {
        PropertyMap state = FXGL.getWorldProperties();
        StringProperty currScreenProperty = state.stringProperty("currScreen");
        displayWelcomeDialog();
        currScreenProperty.addListener((observable, oldScreen, newScreen) -> {
            getGameScene().clearUINodes();

            if (newScreen.equals("welcome")) {
                getWorldProperties().setValue("playerMonumentHealth", 150);
                getWorldProperties().setValue("gameRound", 1);
                displayWelcomeDialog();
            } else if (newScreen.equals("menu")) {
                displayMenu();
            } else if (newScreen.equals("towermenu")) {
                displayTowerMenu();
            } else if (newScreen.equals("game")) {
                getGameUI();
                HashMap<String, Integer> gameStats = getWorldProperties().getObject("gameStats");
                int currTime = (int) (new Date().getTime() / 1000);
                gameStats.put("gameTime", currTime);
                getWorldProperties().setValue("gameStats", gameStats);
            } else if (newScreen.equals("endGameScreen")) {
                List<Entity> entitiesList = getGameWorld().getEntities();
                for (int i = entitiesList.size() - 1; i >= 0; i--) {
                    Entity currEntity = entitiesList.get(i);
                    currEntity.removeFromWorld();
                }
                displayGameOver();
            } else if (newScreen.equals("endWinScreen")) {
                List<Entity> entitiesList = getGameWorld().getEntities();
                for (int i = entitiesList.size() - 1; i >= 0; i--) {
                    Entity currEntity = entitiesList.get(i);
                    currEntity.removeFromWorld();
                }
                displayEndWinScreen();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

    public String getDialogType() {

        return dialogType;
    }

}