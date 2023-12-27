package com.rs.game.content.leagues;

import com.rs.game.content.skills.crafting.GemCutting;
import com.rs.game.content.skills.farming.RakeAction;
import com.rs.game.content.skills.fletching.Fletching;
import com.rs.game.content.skills.herblore.CraftablePotion;
import com.rs.game.content.skills.herblore.Herblore;
import com.rs.game.content.skills.mining.Mining;
import com.rs.game.content.skills.mining.Pickaxe;
import com.rs.game.content.skills.mining.RockType;
import com.rs.game.content.skills.smithing.Smelting;
import com.rs.game.content.skills.smithing.Smithing;
import com.rs.game.content.skills.thieving.PickPocketAction;
import com.rs.game.content.skills.util.CreateAction;
import com.rs.game.model.entity.actions.Action;
import com.rs.game.model.entity.player.Player;
import com.rs.lib.game.Item;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.handlers.ItemAddedToInventoryHandler;
import com.rs.plugin.handlers.ItemEquipHandler;
import com.rs.plugin.handlers.NPCDeathHandler;
import com.rs.plugin.handlers.NPCDropHandler;

import java.util.ArrayList;
import java.util.Arrays;

@PluginEventHandler
public class LeaguesPlugin {

    public static Object[][] ARMOUR_WEAPON_TASKS = {
            {
                LeaguesTask.EQUIP_MITHRIL_SET, new int[] { 1159, 1121 }, new int[] { 1071, 1085 }
            },
            {
                LeaguesTask.EQUIP_SOME_BLACK_ARMOUR, new int[] { }, new int[] { 1077, 1125, 1165, 1089 }
            },
            {
                LeaguesTask.EQUIP_SOME_STEEL_ARMOUR, new int[] { }, new int[] { 1069, 1119, 1157, 1083 }
            },
            {
                LeaguesTask.EQUIP_ADAMANT_SET, new int[] { 1161, 1123 }, new int[] { 1073, 1091 }
            },
            {
                LeaguesTask.EQUIP_RUNE_SET, new int[] { 1163, 1127 }, new int[] { 1079, 1093 }
            },
            {
                LeaguesTask.EQUIP_BLUE_DRAGONHIDE_SET, new int[] { 2499, 2493, 2487 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_GREEN_DRAGONHIDE_SET, new int[] { 1135, 1099, 1065 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_RED_DRAGONHIDE_SET, new int[] { 2495, 2489, 2501 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_FULL_GRAAHK, new int[] { 10051, 10049, 10047 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_FULL_GRAAHK, new int[] { 10045, 10043, 10041 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_FULL_GRAAHK, new int[] { 10039, 10037, 10035 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_LEAF_BLADED_SWORD, new int[] { 13290 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_SOME_MYSTIC, new int[] { }, new int[] { 4089, 4091, 4093, 4095, 4097, 4099, 4101, 4103, 4105, 4107, 4109, 4111, 4113, 4115, 4117 }
            },
            {
                LeaguesTask.EQUIP_WIZARD_ROBE_HAT, new int[] { 577 }, new int[] { 579, 1017 }
            },
            {
                LeaguesTask.EQUIP_HARPIE_LANTERN, new int[] { 7053 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_FROG_MASK, new int[] { 6188 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_YEW_SHORTBOW, new int[] { 857 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_ADAMANT_WEAPON, new int[] { }, new int[] { 1211, 1357, 1271, 1430, 13097, 1287, 1331, 11375, 1301, 1345, 1371, 3100, 1245, 3200, 1317 }
            },
            {
                LeaguesTask.EQUIP_RUNE_WEAPON, new int[] { }, new int[] { 1373, 13099, 3101, 1213, 11377, 1359, 1303, 1432, 1275, 1333, 1289, 1347 }
            },
            {
                LeaguesTask.EQUIP_TRIMMED_AMULET, new int[] { }, new int[] { 10362, 10364, 10366 }
            },
            {
                LeaguesTask.EQUIP_BATTLESTAFF_MYSTIC_STAFF, new int[] { }, new int[] { 1397, 1399, 1393, 1395, 21777, 1405, 1403, 1407, 1401 }
            },
            {
                LeaguesTask.EQUIP_MAPLE_SHORTBOW, new int[] { 853 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_MITHRIL_WEAPON, new int[] { }, new int[] { 1369, 3099, 1209, 11373, 1355, 1299, 1428, 1273, 1329, 1285, 1343 }
            },
            {
                LeaguesTask.EQUIP_SPINY_HELMET, new int[] { 4551 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_STUDDED_BODY_CHAPS, new int[] { 1133, 1097 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_ELEMENTAL_STAFF, new int[] { }, new int[] { 1381, 1383, 1385, 1387 }
            },
            {
                LeaguesTask.EQUIP_DRAGON_WEAPON, new int[] { }, new int[] { 1377, 14484, 1215, 6739, 1305, 1434, 15259, 4587 }
            },
            {
                LeaguesTask.EQUIP_BLACK_DRAGONHIDE_SET, new int[] { 2491, 2497, 2503 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_GILDED_TRIMMED_WIZARD_SET, new int[] { 7396, 10687, 7388 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_GILDED_TRIMMED_WIZARD_SET, new int[] { 7394, 10686, 7386 }, new int[] { }
            },
            {
                new Object[] { LeaguesTask.EQUIP_COMBINATION_STAFF, LeaguesTask.EQUIP_BATTLESTAFF_MYSTIC_STAFF }, new int[] { }, new int[] { 6562, 11736, 3054, 6563, 11738 }
            },
            {
                new Object[] { LeaguesTask.EQUIP_COMBINATION_STAFF, LeaguesTask.EQUIP_BATTLESTAFF_MYSTIC_STAFF, LeaguesTask.EQUIP_LAVA_BATTLESTAFF }, new int[] { 3053 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_MAGIC_SHORTBOW, new int[] { 861 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_RUNE_CROSSBOW, new int[] { 9185 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_RANGER_BOOTS, new int[] { 2577 }, new int[] { }
            },
            {
                LeaguesTask.EQUIP_GILDED_SET, new int[] { 2619, 2615 }, new int[] { 2617, 3476 }
            },
            {
                LeaguesTask.EQUIP_GILDED_SET, new int[] { 7370, 7378 }, new int[] {  }
            },
            {
                LeaguesTask.EQUIP_GOD_DRAGONHIDE_SET, new int[] { 10376, 10378, 10380, 10382 }, new int[] { } //guthix
            },
            {
                LeaguesTask.EQUIP_GOD_DRAGONHIDE_SET, new int[] { 19443, 19445, 19447, 19449 }, new int[] { } //ancient
            },
            {
                LeaguesTask.EQUIP_GOD_DRAGONHIDE_SET, new int[] { 19451, 19453, 19455, 19457 }, new int[] { } //bandos
            },
            {
                LeaguesTask.EQUIP_GOD_DRAGONHIDE_SET, new int[] { 19459, 19461, 19463, 19465 }, new int[] { } //armadyl
            },
            {
                LeaguesTask.EQUIP_GOD_DRAGONHIDE_SET, new int[] { 10384, 10386, 10388, 10390 }, new int[] { } //saradomin
            },
            {
                LeaguesTask.EQUIP_GOD_DRAGONHIDE_SET, new int[] { 10368, 10370, 10372, 10374 }, new int[] { } //zamorak
            },
            {
                LeaguesTask.EQUIP_GOD_RUNE_SET, new int[] { 2661, 2663, 2665, 2667 }, new int[] { } //saradomin
            },
            {
                LeaguesTask.EQUIP_GOD_RUNE_SET, new int[] { 2653, 2655, 2657, 2659 }, new int[] { } //zamorak
            },
            {
                LeaguesTask.EQUIP_GOD_RUNE_SET, new int[] { 2669, 2671, 2673, 2675 }, new int[] { } //guthix
            },
            {
                LeaguesTask.EQUIP_GOD_RUNE_SET, new int[] { 19401, 19407, 19398, 19410 }, new int[] { } //ancient
            },
            {
                LeaguesTask.EQUIP_GOD_RUNE_SET, new int[] { 19437, 19428, 19431, 19440 }, new int[] { } //bandos
            },
            {
                LeaguesTask.EQUIP_GOD_RUNE_SET, new int[] { 19413, 19416, 19422, 19425 }, new int[] { } //armadyl
            }
    };

    static Object[] EQUIP_ITEMS;

    public static int[] GEMS = { 1617, 1619, 1621, 1623 };

    public static Object[] ADDED_TO_INVENT = { 843, 52, 884, 9442, 121, 436, 440, 453, 995,
            18645, 6055, 2349, 2351, 2353, 2363, 40, 1155, 1087, 1759, 1607, 1613, 1617, 1619, 1621, 1623
    };

    static {
        ArrayList<Integer> items = new ArrayList<>();
        for(Object[] set : ARMOUR_WEAPON_TASKS) {
            for(int id : (int[]) set[1])
                items.add(id);
            for(int id : (int[]) set[2])
                items.add(id);
        }
        EQUIP_ITEMS = new Object[items.size()];
        for(int i = 0; i < items.size(); i++)
            EQUIP_ITEMS[i] = items.get(i);
    }

    public static ItemAddedToInventoryHandler itemAddedToInventoryHandler = new ItemAddedToInventoryHandler(ADDED_TO_INVENT, e -> {
        Player player = e.getPlayer();
        Action action = player.getActionManager().getAction();
        if(action == null) return;
        switch (action) {
            case CreateAction create -> {
                Item[] items = create.getProducts();
                if(items.length == 1 && items[0].getId() == 1759 && !player.getLeaguesManager().getTask(LeaguesTask.SPIN_WOOL))
                    player.getLeaguesManager().completeTask(LeaguesTask.SPIN_WOOL);
            }
            case GemCutting gem -> {
                if(gem.getGem() == GemCutting.Gem.SAPPHIRE && !player.getLeaguesManager().getTask(LeaguesTask.CUT_SAPPHIRE))
                    player.getLeaguesManager().completeTask(LeaguesTask.CUT_SAPPHIRE);
                if(gem.getGem() == GemCutting.Gem.RED_TOPAZ)
                    player.getLeaguesManager().completeTask(LeaguesTask.CUT_RED_TOPAZ);
            }
            case Fletching fletching -> {
                Fletching.Fletch fletch = fletching.getFletch();
                if (fletch == Fletching.Fletch.STRUNG_OAK_SHORT_BOW && !player.getLeaguesManager().getTask(LeaguesTask.FLETCH_OAK_SHORTBOW))
                    player.getLeaguesManager().completeTask(LeaguesTask.FLETCH_OAK_SHORTBOW);
                else if (e.getItem().getId() == 52) {
                    if (!player.getLeaguesManager().getTask(LeaguesTask.FLETCH_ARROW_SHAFT))
                        player.getLeaguesManager().completeTask(LeaguesTask.FLETCH_ARROW_SHAFT);
                    if (player.getLeaguesManager().getTask(LeaguesTask.FLETCH_1000_ARROW_SHAFTS))
                        return;
                    int amount = (int) player.getLeaguesManager().getAttribute(LeaguesTask.FLETCH_1000_ARROW_SHAFTS);
                    amount += e.getItem().getAmount();
                    player.getLeaguesManager().setAttribute(LeaguesTask.FLETCH_1000_ARROW_SHAFTS, amount);
                    if (amount >= 1000)
                        player.getLeaguesManager().completeTask(LeaguesTask.FLETCH_1000_ARROW_SHAFTS);
                } else if (fletch == Fletching.Fletch.IRON_ARROWS && !player.getLeaguesManager().getTask(LeaguesTask.FLETCH_150_IRON_ARROW)) {
                    int amount = (int) player.getLeaguesManager().getAttribute(LeaguesTask.FLETCH_150_IRON_ARROW);
                    amount += e.getItem().getAmount();
                    player.getLeaguesManager().setAttribute(LeaguesTask.FLETCH_150_IRON_ARROW, amount);
                    if (amount >= 150)
                        player.getLeaguesManager().completeTask(LeaguesTask.FLETCH_150_IRON_ARROW);
                } else if (e.getItem().getId() == 9442 && !player.getLeaguesManager().getTask(LeaguesTask.FLETCH_25_OAK_STOCKS)) {
                    int amount = (int) player.getLeaguesManager().getAttribute(LeaguesTask.FLETCH_25_OAK_STOCKS);
                    amount += e.getItem().getAmount();
                    player.getLeaguesManager().setAttribute(LeaguesTask.FLETCH_25_OAK_STOCKS, amount);
                    if (amount >= 25)
                        player.getLeaguesManager().completeTask(LeaguesTask.FLETCH_25_OAK_STOCKS);

                }
            }
            case Herblore herblore -> {
                CraftablePotion potion = herblore.getPotion();
                if (potion == CraftablePotion.ATTACK_POTION && !player.getLeaguesManager().getTask(LeaguesTask.MAKE_ATTACK_POTION))
                    player.getLeaguesManager().completeTask(LeaguesTask.MAKE_ATTACK_POTION);
            }
            case Mining mining -> {
                for(int gem : GEMS) {
                    if(e.getItem().getId() == gem && !player.getLeaguesManager().getTask(LeaguesTask.OBTAIN_GEM))
                        player.getLeaguesManager().completeTask(LeaguesTask.OBTAIN_GEM);
                }
                if ((mining.getPick() == Pickaxe.STEEL || mining.getPick() == Pickaxe.STEEL_G) && !player.getLeaguesManager().getTask(LeaguesTask.MINE_STEEL_PICK))
                    player.getLeaguesManager().completeTask(LeaguesTask.MINE_STEEL_PICK);
                if (mining.getRock() == RockType.COPPER && !player.getLeaguesManager().getTask(LeaguesTask.MINE_COPPER_ORE))
                    player.getLeaguesManager().completeTask(LeaguesTask.MINE_COPPER_ORE);
                else if (mining.getRock() == RockType.TIN && !player.getLeaguesManager().getTask(LeaguesTask.MINE_10_TIN_ORE)) {
                    int amount = (int) player.getLeaguesManager().getAttribute(LeaguesTask.MINE_10_TIN_ORE);
                    amount += e.getItem().getAmount();
                    player.getLeaguesManager().setAttribute(LeaguesTask.MINE_10_TIN_ORE, amount);
                    if (amount >= 10)
                        player.getLeaguesManager().completeTask(LeaguesTask.MINE_10_TIN_ORE);
                } else if (mining.getRock() == RockType.COAL && !player.getLeaguesManager().getTask(LeaguesTask.MINE_15_COAL)) {
                    int amount = (int) player.getLeaguesManager().getAttribute(LeaguesTask.MINE_15_COAL);
                    amount += e.getItem().getAmount();
                    player.getLeaguesManager().setAttribute(LeaguesTask.MINE_15_COAL, amount);
                    if (amount >= 15)
                        player.getLeaguesManager().completeTask(LeaguesTask.MINE_15_COAL);
                }
            }
            case PickPocketAction pickpocket -> {
                if ((pickpocket.getNPC().getName().equals("Man") || pickpocket.getNPC().getName().equals("Woman")) && !player.getLeaguesManager().getTask(LeaguesTask.PICKPOCKET_MAN_WOMAN))
                    player.getLeaguesManager().completeTask(LeaguesTask.PICKPOCKET_MAN_WOMAN);
            }
            case RakeAction rake -> {
                if(!player.getLeaguesManager().getTask(LeaguesTask.RAKE_PATCH))
                    player.getLeaguesManager().completeTask(LeaguesTask.RAKE_PATCH);
            }
            case Smelting smelting -> {
                if(smelting.bar == Smelting.SmeltingBar.BRONZE && !player.getLeaguesManager().getTask(LeaguesTask.SMELT_BRONZE_BAR))
                    player.getLeaguesManager().completeTask(LeaguesTask.SMELT_BRONZE_BAR);
                else if(smelting.bar == Smelting.SmeltingBar.IRON && !player.getLeaguesManager().getTask(LeaguesTask.SMELT_IRON_BAR))
                    player.getLeaguesManager().completeTask(LeaguesTask.SMELT_IRON_BAR);
                else if(smelting.bar == Smelting.SmeltingBar.STEEL && !player.getLeaguesManager().getTask(LeaguesTask.SMELT_STEEL_BAR))
                    player.getLeaguesManager().completeTask(LeaguesTask.SMELT_STEEL_BAR);
                else if(smelting.bar == Smelting.SmeltingBar.RUNE && !player.getLeaguesManager().getTask(LeaguesTask.SMELT_RUNITE_BAR))
                    player.getLeaguesManager().completeTask(LeaguesTask.SMELT_RUNITE_BAR);
            }
            case Smithing smithing -> {
                if(e.getItem().getId() == 40 && !player.getLeaguesManager().getTask(LeaguesTask.SMITH_150_IRON_ARROWHEADS)) {
                    int amount = (int) player.getLeaguesManager().getAttribute(LeaguesTask.SMITH_150_IRON_ARROWHEADS);
                    amount += e.getItem().getAmount();
                    player.getLeaguesManager().setAttribute(LeaguesTask.SMITH_150_IRON_ARROWHEADS, amount);
                    if (amount >= 150)
                        player.getLeaguesManager().completeTask(LeaguesTask.SMITH_150_IRON_ARROWHEADS);
                } else if(e.getItem().getId() == 1155 && !player.getLeaguesManager().getTask(LeaguesTask.SMITH_BRONZE_HELM))
                    player.getLeaguesManager().completeTask(LeaguesTask.SMITH_BRONZE_HELM);
                else if(e.getItem().getId() == 1087 && !player.getLeaguesManager().getTask(LeaguesTask.SMITH_BRONZE_PLATESKIRT))
                    player.getLeaguesManager().completeTask(LeaguesTask.SMITH_BRONZE_PLATESKIRT);
            }
            default -> {
            }
        }
    });

    public static ItemEquipHandler handleArmourEquip = new ItemEquipHandler(EQUIP_ITEMS, e -> {
        try {
            if (!e.equip()) return;
            Player player = e.getPlayer();
            s:
            for (Object[] set : ARMOUR_WEAPON_TASKS) {
                LeaguesTask[] tasks;
                if (set[0] instanceof Object[]) {
                    Object[] obj = (Object[]) set[0];
                    tasks = new LeaguesTask[obj.length];
                    for (int i = 0; i < obj.length; i++)
                        tasks[i] = (LeaguesTask) obj[i];
                } else
                    tasks = new LeaguesTask[] { (LeaguesTask) set[0] };
                int[] mandatory = (int[]) set[1];
                int[] optional = (int[]) set[2];
                boolean completed = true;
                for (LeaguesTask leagueTasks : tasks) {
                    if (player.getLeaguesManager().getTask(leagueTasks)) continue;
                    completed = false;
                    break;
                }
                if (completed) continue;
                for (int id : mandatory) {
                    if (id == e.getItem().getId()) continue;
                    if (!player.getEquipment().contains(id)) continue s;
                }
                if (optional.length == 0) {
                    for (LeaguesTask leagueTasks : tasks)
                        player.getLeaguesManager().completeTask(leagueTasks);
                    continue;
                }
                for (int id : optional) {
                    if (id == e.getItem().getId() || player.getEquipment().contains(id)) {
                        for (LeaguesTask leagueTasks : tasks)
                            player.getLeaguesManager().completeTask(leagueTasks);
                        continue s;
                    }
                }
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    });

    public static NPCDropHandler handleChromaticDragonDeath = new NPCDropHandler(new Object[] { "Green dragon", "Blue dragon", "Red dragon", "Black dragon" }, null, e -> {
        if(!e.getPlayer().getLeaguesManager().getTask(LeaguesTask.DEFEAT_CHROMATIC_DRAGON))
            e.getPlayer().getLeaguesManager().completeTask(LeaguesTask.DEFEAT_CHROMATIC_DRAGON);
    });

    public static NPCDropHandler handleGoblinDeath = new NPCDropHandler(new Object[] {"Goblin"}, null, e -> {
        if(!e.getPlayer().getLeaguesManager().getTask(LeaguesTask.KILL_GOBLIN))
            e.getPlayer().getLeaguesManager().completeTask(LeaguesTask.KILL_GOBLIN);
    });

    public static NPCDropHandler handleGuardDeath = new NPCDropHandler(new Object[] {"Guard"}, null, e -> {
        if(!e.getPlayer().getLeaguesManager().getTask(LeaguesTask.KILL_GUARD))
            e.getPlayer().getLeaguesManager().completeTask(LeaguesTask.KILL_GUARD);
    });

    public static NPCDropHandler handleMossGiant = new NPCDropHandler(new Object[] {"Moss giant"}, null, e -> {
        if(!e.getPlayer().getLeaguesManager().getTask(LeaguesTask.KILL_MOSS_GIANT))
            e.getPlayer().getLeaguesManager().completeTask(LeaguesTask.KILL_MOSS_GIANT);
    });
}
