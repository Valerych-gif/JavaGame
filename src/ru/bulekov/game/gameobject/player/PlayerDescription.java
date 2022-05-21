package ru.bulekov.game.gameobject.player;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import ru.bulekov.game.config.asset.AssetLoader;
import ru.bulekov.game.gameobject.ObjectDescription;
import ru.bulekov.game.render.Animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDescription implements ObjectDescription {
    private String objectName;
    private float weight;
    private float maxSpeed;

    private Map<String, Animation> animations;

    @Override
    public Animation getAnimation(String animationName) {
        return animations.get(animationName);
    }

    @JsonGetter("object_name")
    public String getObjectName() {
        return objectName;
    }

    @JsonSetter("object_name")
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @JsonGetter("weight")
    public float getWeight() {
        return weight;
    }

    @JsonSetter("weight")
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @JsonGetter("max_speed")
    public float getMaxSpeed() {
        return maxSpeed;
    }

    @JsonSetter("max_speed")
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @JsonGetter("states_list")
    public List<String> getAnimations() {
        List<String> animationList = new ArrayList<>();
        animations.forEach((name, animation)->animationList.add(name));
        return animationList;
    }

    @JsonSetter("states_list")
    public void setAnimations(List<String> statesList) {
        Map<String, Animation> animationMap = new HashMap<>();
        for (String state: statesList){
            Animation value = AssetLoader.getAnimation();
            animationMap.put(state, value);
        }
        this.animations = animationMap;
    }
}
