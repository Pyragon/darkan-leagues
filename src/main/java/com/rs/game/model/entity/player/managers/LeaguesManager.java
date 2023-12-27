package com.rs.game.model.entity.player.managers;

import com.rs.game.content.leagues.LeaguesTask;
import com.rs.game.model.entity.player.Player;

import java.util.HashMap;

public class LeaguesManager {

    //TODO - start with level 3 herblore, 5 runecraft, and 10 agility
    //TODO - stackable clues
    //TODO - rewrite cooking tasks to use: https://i.imgur.com/gbXD8g0.png
    //TODO - Complete tutorial island task/Complete leagues tutorial task
    //TODO - Complete any advanced agility course (Change points amount)
    //TODO - Triple check that black wizard robe tops don't exist in 727, can only find hat
    //TODO - Smith 150 iron arrowheads is sending 1 as the amount of items being added to inventory instead of 15
    //TODO - Move woodcutting logic to LeaguesPlugin class

    public static LeaguesTask[][] SKILLS = {
            { LeaguesTask.REACH_99_DEFENCE, LeaguesTask.OBTAIN_25M_DEFENCE, LeaguesTask.OBTAIN_35M_DEFENCE, LeaguesTask.OBTAIN_50M_DEFENCE },
            { LeaguesTask.REACH_99_ATTACK, LeaguesTask.OBTAIN_25M_ATTACK, LeaguesTask.OBTAIN_35M_ATTACK, LeaguesTask.OBTAIN_50M_ATTACK },
			{ LeaguesTask.REACH_99_STRENGTH, LeaguesTask.OBTAIN_25M_STRENGTH, LeaguesTask.OBTAIN_35M_STRENGTH, LeaguesTask.OBTAIN_50M_STRENGTH },
			{ LeaguesTask.REACH_99_HITPOINTS, LeaguesTask.OBTAIN_25M_HITPOINTS, LeaguesTask.OBTAIN_35M_HITPOINTS, LeaguesTask.OBTAIN_50M_HITPOINTS },
			{ LeaguesTask.REACH_99_RANGED, LeaguesTask.OBTAIN_25M_RANGED, LeaguesTask.OBTAIN_35M_RANGED, LeaguesTask.OBTAIN_50M_RANGED },
			{ LeaguesTask.REACH_99_PRAYER, LeaguesTask.OBTAIN_25M_PRAYER, LeaguesTask.OBTAIN_35M_PRAYER, LeaguesTask.OBTAIN_50M_PRAYER },
			{ LeaguesTask.REACH_99_MAGIC, LeaguesTask.OBTAIN_25M_MAGIC, LeaguesTask.OBTAIN_35M_MAGIC, LeaguesTask.OBTAIN_50M_MAGIC },
			{ LeaguesTask.REACH_99_COOKING, LeaguesTask.OBTAIN_25M_COOKING, LeaguesTask.OBTAIN_35M_COOKING, LeaguesTask.OBTAIN_50M_COOKING },
			{ LeaguesTask.REACH_99_WOODCUTTING, LeaguesTask.OBTAIN_25M_WOODCUTTING, LeaguesTask.OBTAIN_35M_WOODCUTTING, LeaguesTask.OBTAIN_50M_WOODCUTTING },
			{ LeaguesTask.REACH_99_FLETCHING, LeaguesTask.OBTAIN_25M_FLETCHING, LeaguesTask.OBTAIN_35M_FLETCHING, LeaguesTask.OBTAIN_50M_FLETCHING },
			{ LeaguesTask.REACH_99_FISHING, LeaguesTask.OBTAIN_25M_FISHING, LeaguesTask.OBTAIN_35M_FISHING, LeaguesTask.OBTAIN_50M_FISHING },
			{ LeaguesTask.REACH_99_FIREMAKING, LeaguesTask.OBTAIN_25M_FIREMAKING, LeaguesTask.OBTAIN_35M_FIREMAKING, LeaguesTask.OBTAIN_50M_FIREMAKING },
            { LeaguesTask.REACH_99_CRAFTING, LeaguesTask.OBTAIN_25M_CRAFTING, LeaguesTask.OBTAIN_35M_CRAFTING, LeaguesTask.OBTAIN_50M_CRAFTING },
            { LeaguesTask.REACH_99_SMITHING, LeaguesTask.OBTAIN_25M_SMITHING, LeaguesTask.OBTAIN_35M_SMITHING, LeaguesTask.OBTAIN_50M_SMITHING },
            { LeaguesTask.REACH_99_MINING, LeaguesTask.OBTAIN_25M_MINING, LeaguesTask.OBTAIN_35M_MINING, LeaguesTask.OBTAIN_50M_MINING },
			{ LeaguesTask.REACH_99_HERBLORE, LeaguesTask.OBTAIN_25M_HERBLORE, LeaguesTask.OBTAIN_35M_HERBLORE, LeaguesTask.OBTAIN_50M_HERBLORE },
			{ LeaguesTask.REACH_99_AGILITY, LeaguesTask.OBTAIN_25M_AGILITY, LeaguesTask.OBTAIN_35M_AGILITY, LeaguesTask.OBTAIN_50M_AGILITY },
			{ LeaguesTask.REACH_99_THIEVING, LeaguesTask.OBTAIN_25M_THIEVING, LeaguesTask.OBTAIN_35M_THIEVING, LeaguesTask.OBTAIN_50M_THIEVING },
			{ LeaguesTask.REACH_99_SLAYER, LeaguesTask.OBTAIN_25M_SLAYER, LeaguesTask.OBTAIN_35M_SLAYER, LeaguesTask.OBTAIN_50M_SLAYER },
			{ LeaguesTask.REACH_99_FARMING, LeaguesTask.OBTAIN_25M_FARMING, LeaguesTask.OBTAIN_35M_FARMING, LeaguesTask.OBTAIN_50M_FARMING },
			{ LeaguesTask.REACH_99_RUNECRAFTING, LeaguesTask.OBTAIN_25M_RUNECRAFTING, LeaguesTask.OBTAIN_35M_RUNECRAFTING, LeaguesTask.OBTAIN_50M_RUNECRAFTING },
			{ LeaguesTask.REACH_99_HUNTER, LeaguesTask.OBTAIN_25M_HUNTER, LeaguesTask.OBTAIN_35M_HUNTER, LeaguesTask.OBTAIN_50M_HUNTER },
			{ LeaguesTask.REACH_99_CONSTRUCTION, LeaguesTask.OBTAIN_25M_CONSTRUCTION, LeaguesTask.OBTAIN_35M_CONSTRUCTION, LeaguesTask.OBTAIN_50M_CONSTRUCTION },
			{ LeaguesTask.REACH_99_SUMMONING, LeaguesTask.OBTAIN_25M_SUMMONING, LeaguesTask.OBTAIN_35M_SUMMONING, LeaguesTask.OBTAIN_50M_SUMMONING },
			{ LeaguesTask.REACH_99_DUNGEONEERING, LeaguesTask.OBTAIN_25M_DUNGEONEERING, LeaguesTask.OBTAIN_35M_DUNGEONEERING, LeaguesTask.OBTAIN_50M_DUNGEONEERING }
    };
    private transient Player player;

    private HashMap<LeaguesTask, Boolean> tasks;
    private HashMap<LeaguesTask, Object> attributes;

    private int points;

    public LeaguesManager() {
        tasks = new HashMap<>();
        attributes = new HashMap<>();
    }

    public Object getAttribute(LeaguesTask task) {
        if (!attributes.containsKey(task))
            return null;
        return attributes.get(task);
    }

    public void setAttribute(LeaguesTask task, Object value) {
        attributes.put(task, value);
    }

    public Boolean getTask(LeaguesTask task) {
        if (!tasks.containsKey(task))
            return false;
        return tasks.get(task);
    }

    public void completeTask(LeaguesTask task) {
        tasks.put(task, true);
        points += task.getPoints();
        player.sendMessage("You have completed the task: " + task.getName() + " and have been awarded " + task.getPoints() + " points.");
        attributes.remove(task);
    }

    public void setPlayer(Player player) {
        this.player = player;
        for (LeaguesTask task : LeaguesTask.values()) {
            if (tasks.containsKey(task))
                continue;
            tasks.put(task, false);
            if (task.getAttributesDefault() != null)
                attributes.put(task, task.getAttributesDefault());
        }
    }

    public void reset() {
        tasks.clear();
        attributes.clear();
        points = 0;
        for (LeaguesTask task : LeaguesTask.values())
            tasks.put(task, false);
    }

    public HashMap<LeaguesTask, Boolean> getTasks() {
        return tasks;
    }

    public HashMap<LeaguesTask, Object> getAttributes() {
        return attributes;
    }

    public int getTasksCompleted() {
        return tasks.values().stream().mapToInt(t -> t ? 1 : 0).sum();
    }

    public int getPoints() {
        return points;
    }
}
