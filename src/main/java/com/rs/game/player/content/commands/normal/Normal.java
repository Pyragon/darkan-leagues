// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Copyright © 2021 Trenton Kress
//  This file is part of project: Darkan
//
package com.rs.game.player.content.commands.normal;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.db.WorldDB;
import com.rs.game.World;
import com.rs.game.ge.Offer;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.commands.Command;
import com.rs.game.player.content.commands.Commands;
import com.rs.game.player.content.dialogue.Dialogue;
import com.rs.lib.game.Rights;
import com.rs.lib.util.Utils;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.annotations.ServerStartupEvent;

@PluginEventHandler
public class Normal {
	
	@ServerStartupEvent
	public static void loadCommands() {

		Commands.add(Rights.PLAYER, "commandlist,commands", "Displays all the commands the player has permission to use.", (p, args) -> {
			p.getPackets().setIFText(275, 1, "Commands List");
			int componentId = 10;
			
			for (int rights = p.getRights().ordinal();rights >= 0;rights--) {
				p.getPackets().setIFText(275, componentId++, "<u><col=FF0000><shad=000000>"+Utils.formatPlayerNameForDisplay(Rights.values()[rights].name())+" Commands</shad></col></u>");
				for (Command c : Commands.getCommands(Rights.values()[rights])) {
					if (componentId > 288)
						break;
					p.getPackets().setIFText(275, componentId++, "::" + c.getUsage());
				}
			}
			
			p.getPackets().sendRunScript(1207, componentId - 10);
			p.getInterfaceManager().sendInterface(275);
		});

        Commands.add(Rights.PLAYER, "resett", "Displays all the commands the player has permission to use.", (p, args) -> {
            p.getSlayer().removeTask();
            p.updateSlayerTask();
        });

        Commands.add(Rights.PLAYER, "tokenator", "Gives the specified player dungeoneering tokens.", (p, args) -> {
                p.getDungManager().addTokens(200000);
                p.sendMessage("Successfully gave tokens..");
        });

		Commands.add(Rights.PLAYER, "drops [npcId numberKilled]", "Emulates a number of NPC kills and displays the collected loot.", (p, args) -> {
			int npcId = Integer.valueOf(args[0]);
			int npcAmount = Integer.valueOf(args[1].replace("k", "000"));
			if (npcId < 0 || npcId > Utils.getNPCDefinitionsSize())
				return;
			if ((npcAmount < 0 || npcAmount > 50000) && !p.hasRights(Rights.DEVELOPER)) {
				p.sendMessage("You can only see drops for up to 50000 NPCs.");
				return;
			}
			NPC.displayDropsFor(p, npcId, npcAmount);
		});
		
//		Commands.add(Rights.PLAYER, "cluesim [difficulty]", "Emulates opening a clue of specific difficulty", (p, args) -> {
//			Item[] rewards = null;
//			switch (args[0].toLowerCase()) {
//				case "easy":				
//					rewards = TreasureTrailsManager.generateRewards(p, 0);
//					break;
//				case "medium":
//					rewards = TreasureTrailsManager.generateRewards(p, 1);
//					break;
//				case "hard":
//					rewards = TreasureTrailsManager.generateRewards(p, 2);
//					break;
//				case "elite":
//					rewards = TreasureTrailsManager.generateRewards(p, 3);
//					break;
//				default:
//					rewards = TreasureTrailsManager.generateRewards(p, 0);
//			}
//
//			p.getInterfaceManager().sendInterface(364);
//			p.getPackets().sendInterSetItemsOptionsScript(364, 4, 141, 3, 4, "Examine");
//			p.getPackets().setIFRightClickOps(364, 4, 0, rewards.length, 0);
//			p.getPackets().sendItems(141, rewards);
//		});
		
		Commands.add(Rights.PLAYER, "buyoffers", "Displays all buy offers currently active in the Grand Exchange.", (p, args) -> {
			WorldDB.getGE().getAllOffersOfType(false, offers -> {
				p.getPackets().sendRunScript(1207, offers.size());
				p.getInterfaceManager().sendInterface(275);
				p.getPackets().setIFText(275, 1, "Grand Exchange Buy Offers");
				int num = 10;
				for (Offer offer : offers) {
					if (num > 288)
						break;
					p.getPackets().setIFText(275, num, "[" + Utils.formatPlayerNameForDisplay(offer.getOwner()) + "]: " + offer.amountLeft() + " " + ItemDefinitions.getDefs(offer.getItemId()).getName() + " for " + offer.getPrice() + " ea");
					num++;
				}
			});
		});
		
		Commands.add(Rights.PLAYER, "selloffers", "Displays all sell offers currently active in the Grand Exchange.", (p, args) -> {
			WorldDB.getGE().getAllOffersOfType(true, offers -> {
				p.getPackets().sendRunScript(1207, offers.size());
				p.getInterfaceManager().sendInterface(275);
				p.getPackets().setIFText(275, 1, "Grand Exchange Sell Offers");
				int num = 10;
				for (Offer offer : offers) {
					if (num > 288)
						break;
					p.getPackets().setIFText(275, num, "[" + Utils.formatPlayerNameForDisplay(offer.getOwner()) + "]: " + offer.amountLeft() + " " + ItemDefinitions.getDefs(offer.getItemId()).getName() + " for " + offer.getPrice() + " ea");
					num++;
				}
			});
		});
		
		Commands.add(Rights.PLAYER, "checkbank [player name]", "Displays the contents of another player's bank.", (p, args) -> {
			World.forceGetPlayer(Utils.concat(args), target -> {
				if (target == null) {
					p.sendMessage("Player not found.");
					return;
				}
				p.getBank().openBankOther(target);
			});
		});
		
		Commands.add(Rights.PLAYER, "searchnpc,searchn,findnpc,getnpcid [npc name]", "Displays all NPC ids containing the query searched.", (p, args) -> {
			p.getPackets().sendDevConsoleMessage("Searching for npcs containing name: " + Utils.concat(args));
			for (int i = 0; i < Utils.getNPCDefinitionsSize(); i++) {
				if (NPCDefinitions.getDefs(i).getName().toLowerCase().contains(Utils.concat(args).toLowerCase())) {
					p.getPackets().sendDevConsoleMessage("Result found: " + i + " - " + NPCDefinitions.getDefs(i).getName() + " (" + NPCDefinitions.getDefs(i).combatLevel + ")");
				}
			}
		});
		
//		Commands.add(Rights.PLAYER, "yell,shout [text]", "Will broadcast your message to the whole server.", (p, args) -> {
//			Commands.sendYell(p, Utils.fixChatMessage(Utils.concat(args)), false);
//		});
		
		Commands.add(Rights.PLAYER, "hideyell", "Hides yell from your chat box.", (p, args) -> {
			p.setYellOff(!p.isYellOff());
			p.sendMessage("You have turned " + (p.isYellOff() ? "off" : "on") + " yell.");
		});
		
		Commands.add(Rights.PLAYER, "owner", "Gives you owner rank if you're Trent :)", (p, args) -> {
			if (Settings.isOwner(p.getUsername())) {
				p.setRights(Rights.OWNER);
				p.getAppearance().generateAppearanceData();
			}
		});
		
		Commands.add(Rights.PLAYER, "title", "Sets your title to display your XP rate.", (p, args) -> {
			Dialogue switchTitle = new Dialogue();
				switchTitle.addOption("Would you like to change your title to display your XP rate and mode?", "Yes.", "No.");
				switchTitle.addSimple("Your title has been changed.", () -> p.applyAccountTitle());
			p.startConversation(switchTitle);
		});

        Commands.add(Rights.PLAYER, "dunginfo", "Shows dungeon seed", (p, args) -> {
            try {
                int floor = p.getDungManager().getParty().getFloor();
                long seed = p.getDungManager().getParty().getDungeon().getDungeon().getSeed();
                p.getPackets().sendGameMessage("floor: " + String.valueOf(floor));
                p.getPackets().sendGameMessage("seed: " + String.valueOf(seed));
                p.getPackets().sendGameMessage("difficulty: " + p.getDungManager().getParty().getDificulty());
                p.getPackets().sendGameMessage("size: " + p.getDungManager().getParty().getSize());
                p.getPackets().sendGameMessage("complexity: " + p.getDungManager().getParty().getComplexity());

                System.out.println("floor seed difficulty size complexity");
                System.out.print(" " + String.valueOf(floor));
                System.out.print(" " + String.valueOf(seed));
                System.out.print(" " + p.getDungManager().getParty().getDificulty());
                System.out.print(" " + p.getDungManager().getParty().getSize());
                System.out.print(" " + p.getDungManager().getParty().getComplexity());
            } catch(NullPointerException e) {
                p.getPackets().sendGameMessage("You need to be in a dungeon");
            }
        });
	}

}
