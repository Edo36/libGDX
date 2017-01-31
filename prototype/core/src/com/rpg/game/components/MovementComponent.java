package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Andrei on 30.07.2016.
 */
public class MovementComponent implements Component {
	private Vector2 velocity;
	private Vector2 destination;

	private Circle destinationC;

	private int speed;

	public MovementComponent(Vector2 velocity, Vector2 destination, int speed){
		this.velocity = velocity;
		this.destination = destination;
		this.speed = speed;
	}

	public void setVelocity(float x, float y) {
		velocity.set(speed*x, speed*y);
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	// todo: patrols - destinations could be an array of vectors
	public void setDestination(float x, float y) {
		destination.set(x, y);
	}

	public void setDestinationC(Circle dest) {
		this.destinationC = dest;
	}

	public Circle getDestinationC() {
		return destinationC;
	}

	public Vector2 getDestination() {
		return destination;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
