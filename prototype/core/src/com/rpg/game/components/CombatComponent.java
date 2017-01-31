package com.rpg.game.components;

import com.badlogic.ashley.core.Component;

import java.util.Map;

/**
 * Created by Andrei on 10.08.2016.
 */
public class CombatComponent implements Component {
    // could use a Map to store stats, if it gets more complex
    // each key will be a stat
    private int health = 0;
    private int maxHealth = 0;

    private int damage = 0; //damage per second, rather
    private float attackSpeed = 0.5f;
    private float attackTimer = 0.0f;

//    private Map<String, Integer> stats;
//
//    public int getStat(String stat) {
//        return stats.get(stat);
//    }

    public CombatComponent(int maxHealth, int damage) {
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.damage = damage;
    }

    public void modHealth(int deltaHealth) {
        health += deltaHealth;

        if (health > maxHealth)
            health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void modDamage(int damage) {
        this.damage += damage;
    }

    // public void modDamage(float percentDamage) {
    // this.damage *= (1+percentDamage);
    // }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public float getAttackTimer() {
        return attackTimer;
    }

    public void setAttackTimer(float attackTimer) {
        this.attackTimer = attackTimer;
    }

    public void updateAttackTimer(float deltaTime) {
        attackTimer += deltaTime;
    }
}
