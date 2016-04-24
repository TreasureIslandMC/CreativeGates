package com.massivecraft.creativegates;

import org.bukkit.Bukkit;

import com.massivecraft.creativegates.zcore.*;

public class CreativeGates extends MPlugin {

	// Our single plugin instance
	private static CreativeGates instance;

	public static CreativeGates getInstance() {
		return instance;
	}

	public CreativeGates() {
		instance = this;
	}

	public void onEnable() {
		if (!preEnable())
			return;

		// TODO fix config auto update routine... ?
		Conf.load();

		Gates.INSTANCE.loadFromDisc();
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				Gates.INSTANCE.openAllOrDetach();
			}
		});

		// Register events
		new TheListener();

		postEnable();
	}

	public void onDisable() {
		Gates.INSTANCE.emptyAll();
		super.onDisable();
	}

}
