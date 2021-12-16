package com.team;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.collection.PropertyChangeListener;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.UUID;

import static com.almasb.fxgl.dsl.FXGL.*;


public class EnemyFactory implements EntityFactory {

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        BigInteger enemyUid = new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16);

        int enemyHealth = data.get("health");
        int enemyDamage = data.get("damage");
        double enemyScale = data.get("scale");
        int enemyMoney = data.get("money");
        boolean enemyIsBoss = data.get("isBoss");

        Texture enemyTower = getAssetLoader().loadTexture("monuments/enemy_tower.png");
        enemyTower.setScaleX(enemyScale);
        enemyTower.setScaleY(enemyScale);

        VBox enemyVbox = new VBox(10);

        Text enemyHealthText = new Text(Integer.toString(enemyHealth));

        // Set the enemy health in the global variable
        HashMap<BigInteger, Integer> enemyHealthMap =
                getWorldProperties().getValue("enemyHealthMap");
        enemyHealthMap.put(enemyUid, enemyHealth);
        getWorldProperties().setValue("enemyHealthMap", enemyHealthMap);

        getWorldProperties().addListener("enemyHealthMap", new PropertyChangeListener<>() {
            @Override
            public void onChange(Object oldValueObj, Object newValueObj) {
                HashMap<BigInteger, Integer> newEnemyHealthMap =
                        (HashMap<BigInteger, Integer>) newValueObj;
                if (!newEnemyHealthMap.containsKey(enemyUid)) {
                    return;
                }
                int newHealth = newEnemyHealthMap.get(enemyUid);
                enemyHealthText.textProperty().setValue(Integer.toString(newHealth));
            }
        });

        enemyVbox.getChildren().addAll(enemyHealthText, enemyTower);


        return FXGL.entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox(enemyVbox)
                .collidable()
                .with(new ProjectileComponent(new Point2D(-1, 0), 250))
                .at(2850, 650)
                .with("uid", enemyUid)
                .with("health", enemyHealth)
                .with("damage", enemyDamage)
                .with("money", enemyMoney)
                .with("isBoss", enemyIsBoss)
                .rotate(180)
                .build();
    }

    @Spawns("WeakBullet")
    public Entity newAlien(SpawnData data) {
        var view = new Rectangle(200, 400, Color.BLUE);

        return entityBuilder()
                .type(EntityType.WEAKBULLET)
                .from(data)
                .viewWithBBox(view)
                .collidable()
                .with(new ProjectileComponent(new Point2D(1, 0), FXGLMath.random(50, 150)))
                .build();
    }


    @Spawns("Bullet")
    public Entity newBullet(SpawnData data) {
        int bulletDamage = data.get("damage");
        int bulletSpeed = data.get("speed");
        double xPos = data.get("x");
        double yPos = data.get("y");

        Rectangle bulletRectangle = new Rectangle(200, 400, Color.RED);

        return FXGL.entityBuilder()
                .type(EntityType.BULLET)
                .from(data)
                .viewWithBBox(bulletRectangle)
                .at(xPos, yPos)
                .collidable()
                .with(new ProjectileComponent(new Point2D(1, 0), bulletSpeed))
                .with("damage", bulletDamage)
                .build();
    }


}