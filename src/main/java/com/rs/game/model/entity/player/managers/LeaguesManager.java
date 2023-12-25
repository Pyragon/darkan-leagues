package com.rs.game.model.entity.player.managers;

import com.rs.game.content.leagues.LeaguesTask;
import com.rs.game.model.entity.player.Player;
import com.rs.game.model.entity.player.Skills;

import java.util.HashMap;

public class LeaguesManager {

	//start with level 3 herblore, 5 runecraft, and 10 agility

	public static LeaguesTask[] REACH_99_SKILLS = {
		LeaguesTask.REACH_99_ATTACK,
		LeaguesTask.REACH_99_DEFENCE,
		LeaguesTask.REACH_99_STRENGTH,
		LeaguesTask.REACH_99_HITPOINTS,
		LeaguesTask.REACH_99_RANGED,
		LeaguesTask.REACH_99_PRAYER,
		LeaguesTask.REACH_99_MAGIC,
		LeaguesTask.REACH_99_COOKING,
		LeaguesTask.REACH_99_WOODCUTTING,
		LeaguesTask.REACH_99_FLETCHING,
		LeaguesTask.REACH_99_FISHING,
		LeaguesTask.REACH_99_FIREMAKING,
		LeaguesTask.REACH_99_CRAFTING,
		LeaguesTask.REACH_99_SMITHING,
		LeaguesTask.REACH_99_MINING,
		LeaguesTask.REACH_99_HERBLORE,
		LeaguesTask.REACH_99_AGILITY,
		LeaguesTask.REACH_99_THIEVING,
		LeaguesTask.REACH_99_SLAYER,
		LeaguesTask.REACH_99_FARMING,
		LeaguesTask.REACH_99_RUNECRAFTING,
		LeaguesTask.REACH_99_HUNTER,
		LeaguesTask.REACH_99_CONSTRUCTION,
		LeaguesTask.REACH_99_SUMMONING,
		LeaguesTask.REACH_99_DUNGEONEERING
	};
	private transient Player player;

	private HashMap<LeaguesTask, Boolean> tasks;
	private HashMap<LeaguesTask, Object> attributes;

	private int points;

	public LeaguesManager() {
		tasks = new HashMap<>();
		attributes = new HashMap<>();
	}

	public Object getAttributes(LeaguesTask task) {
		if(!attributes.containsKey(task))
			return null;
		return attributes.get(task);
	}

	public Boolean getTask(LeaguesTask task) {
		if(!tasks.containsKey(task))
			return false;
		return tasks.get(task);
	}

	public void completeTask(LeaguesTask task) {
		tasks.put(task, true);
		points += task.getPoints();
		player.sendMessage("You have completed the task: " + task.getName() + " and have been awarded " + task.getPoints() + " points.");
	}

	public void setPlayer(Player player) {
		this.player = player;
		for(LeaguesTask task : LeaguesTask.values()) {
			if(tasks.containsKey(task))
				continue;
			tasks.put(task, false);
		}
	}

	public void reset() {
		tasks.clear();
		attributes.clear();
		points = 0;
		for(LeaguesTask task : LeaguesTask.values())
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
