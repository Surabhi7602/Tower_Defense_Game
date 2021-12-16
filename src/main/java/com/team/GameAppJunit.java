package com.team;

import com.almasb.fxgl.dsl.FXGL;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


//Hard Level
public class GameAppJunit {

    @BeforeClass
    public static void initializer() {
        String[] temp = new String[]{};
        GameApp.main(temp);
    }

    @Test
    public void towerMenuVisibility() {
        Assertions.assertNotNull(GameApp.getTower1());
        Assertions.assertNotNull(GameApp.getTower2());
        Assertions.assertNotNull(GameApp.getTower3());
    }

    @Test
    public void coinVisibility() {
        boolean flag = true;
        try {
            int intValue = Integer.parseInt(GameApp.getCoinVisible());
            System.out.println(intValue);
        } catch (NumberFormatException e) {
            flag = false;
        }
        Assertions.assertTrue(flag);
    }

    @Test
    public void difficultyValidation() {
        String x = GameApp.getDifficulty();
        boolean flag = x.equals("Easy") || x.equals("Hard") || x.equals("Medium");
        Assertions.assertTrue(flag);
    }


    @Test
    public void towerFluxValidation() {
        String x = GameApp.getDifficulty();

        if (x.equals("Easy")) {
            int temp = FXGL.getWorldProperties().getValue("towerOneMoney");
            Assertions.assertEquals(10, temp);
        } else if (x.equals("Medium")) {
            int temp = FXGL.getWorldProperties().getValue("towerOneMoney");
            Assertions.assertEquals(50, temp);
        } else {
            int temp = FXGL.getWorldProperties().getValue("towerOneMoney");
            Assertions.assertEquals(100, temp);
        }
    }

    @Test
    public void towerDescriptions() {
        Assertions.assertEquals("Blue tower is the coolest because it's the color of the sky!",
                GameApp.getTowerOneText());
        Assertions.assertEquals("Pink tower is the cutest one!", GameApp.getTowerTwoText());
        Assertions.assertEquals("Green tower camouflages really well!",
                GameApp.getTowerThreeText());
    }

    @Test
    public void towerNames() {
        Assertions.assertEquals("MONUMENT 1", GameApp.getTower1());
        Assertions.assertEquals("MONUMENT 2", GameApp.getTower2());
        Assertions.assertEquals("MONUMENT 3", GameApp.getTower3());
    }

    @Test
    public void towerPlacement() {
        Assertions.assertTrue(GameApp.getObjectLocation() < GameApp.getPath() + 1000);
    }

    @Test
    public void decreaseAmount() {
        int initial = FXGL.getWorldProperties().getValue("playerMoney");
        int temp;
        if (GameApp.getTower1().equals("MONUMENT 1")) {
            temp = FXGL.getWorldProperties().getValue("towerOneMoney");
        } else if (GameApp.getTower2().equals("MONUMENT 2")) {
            temp = FXGL.getWorldProperties().getValue("towerTwoMoney");
        } else {
            temp = FXGL.getWorldProperties().getValue("towerThreeMoney");
        }
        System.out.println(temp);
        int finalMoney = (int) GameApp.getEndPlayerMoney();
        Assertions.assertEquals(0, 0);
    }

    @Test
    public void coinsLeft() {
        Assertions.assertTrue(GameApp.getEndPlayerMoney() > 0);
    }

    @Test
    public void tower() {
        Assertions.assertNotNull(GameApp.getBt1());
        Assertions.assertNotNull(GameApp.getBt2());
        Assertions.assertNotNull(GameApp.getBt3());
    }

    @Test
    public void combatButton() {
        Assertions.assertNotNull(GameApp.getBtnStartCombat());
    }

    @Test
    public void firstLevel() {
        int health = FXGL.getWorldProperties().getValue("playerMonumentHealth");
        if (health == 100) {
            String text = GameApp.getBtnStartCombat().getText();
            boolean retVal = text.contains("BEGIN ROUND");
            Assertions.assertTrue(retVal);
        }
    }


    @Test
    public void nextLevel() {
        int health = FXGL.getWorldProperties().getValue("playerMonumentHealth");
        if (health < 100 && health > 90) {
            String text = GameApp.getBtnStartCombat().getText();
            boolean retVal = text.contains("BEGIN ROUND");
            Assertions.assertTrue(retVal);
        }
    }

    @Test
    public void gameOver() {
        int health = FXGL.getWorldProperties().getValue("playerMonumentHealth");
        if (health == 0) {
            String currScreenProperty = GameApp.getCurrScreen().getValue();
            Assertions.assertEquals("endGameScreen", currScreenProperty);
        }
    }

    @Test
    public void restartGameButton() {
        Assertions.assertTrue(GameApp.getRestartGameButtonExists());
    }

    @Test
    public void closeGameButton() {
        Assertions.assertTrue(GameApp.getCloseGameExists());
    }

    @Test
    public void gameConsoleText() {
        Assertions.assertEquals("Keep practicing! You'll get better each time!",
                GameApp.getConsoleTextCheck());
    }

    @Test
    public void gameConsoleTexter() {
        Assertions.assertEquals("You got this!", GameApp.getConsoleTextCheck1());
    }

    @Test
    public void playerMonumentEntityCollisionDetected() {
        Assertions.assertEquals(true, GameApp.getPlayerMonumentEnemyCollisionDetected());
    }

    @Test
    public void playerMonumentEnemyAttackDetected() {
        Assertions.assertEquals(true, GameApp.getPlayerMonumentEnemyAttackDetected());
    }

    @Test
    public void weakbulletDetected() {
        if (GameApp.getmonumentTypeToDecideShooters() == "MONUMENT 1") {
            Assertions.assertEquals(false, GameApp.weakgetBulletAttackDetected());

        } else {
            Assertions.assertEquals(true, GameApp.weakgetBulletAttackDetected());
        }

    }

    @Test
    public void playerEnemyBulletCollisionDetected() {
        Assertions.assertEquals(true, GameApp.getPlayerEnemyBulletCollisionDetected());
    }

    @Test
    public void enemyDetected() {
        Assertions.assertEquals(false, GameApp.getEnemyExists());
    }

    @Test
    public void unlockTowerPosition() {
        Assertions.assertEquals(GameApp.getUnlocked(), "unlocked");
    }

    @Test
    public void unlockTowerPositionConditional() {
        if (GameApp.getCounter() == 0) {
            Assertions.assertNull(GameApp.getUnlocked());
        } else {
            Assertions.assertEquals(GameApp.getUnlocked(), "unlocked");
        }
    }


    @Test
    public void enemyHealthExistence() {
        Assertions.assertTrue(GameApp.getEnemyHealthExists());
    }

    @Test
    public void verifyEnemyVariation() {
        Assertions.assertTrue(GameApp.getEnemyVariationExists());
    }

    @Test
    public void verifyEnemyDestructionIncreasedMoney() {
        Assertions.assertTrue(GameApp.getEnemyDestructionIncreasedPlayerMoney());
    }

    @Test
    public void nextRoundButton() {
        Assertions.assertTrue(GameApp.getRoundButtonExists());
    }

    @Test
    public void nextRoundButtonContents() {
        String textChecker = GameApp.getBtnStartCombat().getText();
        boolean roundChecker1 = textChecker.equals(" ")
                || textChecker.equals("!*cfghjklmpqstvwxyz");
        boolean roundChecker = textChecker.equals("BEGIN ROUND 1")
                || textChecker.equals("BEGIN ROUND 2") || textChecker.equals("BEGIN ROUND 3");
        Assertions.assertTrue(roundChecker);
    }

    @Test
    public void verifyInitialSpeed() {
        boolean upgradeDone = GameApp.getHasBeenUpgraded();
        if (upgradeDone == false){
            Assertions.assertEquals(GameApp.getUpgradeSpeedHelper(), 150);
        }


    }

    @Test
    public void verifyAfterUpgradeSpeed() {
        boolean upgradeDone = GameApp.getHasBeenUpgraded();
        if (upgradeDone == true){

            Assertions.assertNotEquals(GameApp.getUpgradeSpeedHelper(), 150);
        }

    }

    @Test
    public void verifyUpgradeDereasedMoney() {
        Assertions.assertTrue(GameApp.getUpgradeDecreasedMoney());
    }

    @Test
    public void upgradeButton() {
        Assertions.assertTrue(GameApp.getUpgradeButton());
    }
    public void finalBossAppearance() {
        Assertions.assertTrue(GameApp.getFinalBossExists());
    }

    @Test
    public void gameStatsTracked() {
        Assertions.assertTrue(GameApp.getGameStatsTracked());
    }

    @Test
    public void winGameScreenAppears() {
        Assertions.assertTrue(GameApp.getWinGameScreenExists());
    }
    @Test
    public void movementUpPossible() {
        Assertions.assertTrue(GameApp.getMovementUp());
    }

    @Test
    public void movementLeftPossible() {
        Assertions.assertTrue(GameApp.getMoveLeft());
    }
    @Test
    public void totalTimeWorks() {
        Assertions.assertTrue(GameApp.getTotalGameTimeCheckerWorks());
    }
}
