package cofh.ensorcellation.config;

import cofh.ensorcellation.enchantment.*;
import cofh.lib.config.IBaseConfig;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;
import static cofh.lib.util.references.EnsorcReferences.*;

public class BaseEnchantmentConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {

        String treasure = "This sets whether or not the Enchantment is considered a 'treasure' enchantment.";
        String level = "This option adjusts the maximum allowable level for the Enchantment.";

        builder.push("Enchantments");

        // ARMOR
        builder.push("Magic Protection");
        enableProtectionMagic = builder
                .comment("If TRUE, the Magic Protection Enchantment is available for Armor and Horse Armor.")
                .define("Enable", true);
        treasureProtectionMagic = builder
                .comment(treasure)
                .define("Treasure", false);
        levelProtectionMagic = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Displacement");
        enableDisplacement = builder
                .comment("If TRUE, the Displacement Enchantment is available for Armor, Shields, and Horse Armor.")
                .define("Enable", false);
        treasureDisplacement = builder
                .comment(treasure)
                .define("Treasure", true);
        levelDisplacement = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        chanceDisplacement = builder
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 20, 1, 100);
        allowMobsDisplacement = builder
                .comment("If TRUE, mobs wearing armor with this Enchantment can teleport players.")
                .define("Mobs Teleport Players", DisplacementEnchantment.mobsAffectPlayers);
        builder.pop();

        builder.push("Flaming Rebuke");
        enableFireRebuke = builder
                .comment("If TRUE, the Flaming Rebuke Enchantment is available for Armor, Shields, and Horse Armor.")
                .define("Enable", false);
        treasureFireRebuke = builder
                .comment(treasure)
                .define("Treasure", true);
        levelFireRebuke = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        chanceFireRebuke = builder
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 20, 1, 100);
        allowMobsFireRebuke = builder
                .comment("If TRUE, mobs wearing armor with this Enchantment can knockback players.")
                .define("Mobs Knockback Players", FireRebukeEnchantment.mobsAffectPlayers);
        builder.pop();

        builder.push("Chilling Rebuke");
        enableFrostRebuke = builder
                .comment("If TRUE, the Chilling Rebuke Enchantment is available for Armor, Shields, and Horse Armor.")
                .define("Enable", false);
        treasureFrostRebuke = builder
                .comment(treasure)
                .define("Treasure", true);
        levelFrostRebuke = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        chanceFrostRebuke = builder
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 20, 1, 100);
        allowMobsFrostRebuke = builder
                .comment("If TRUE, mobs wearing armor with this Enchantment can knockback players.")
                .define("Mobs Knockback Players", FrostRebukeEnchantment.mobsAffectPlayers);
        builder.pop();

        // HELMET
        builder.push("Air Affinity");
        enableAirAffinity = builder
                .comment("If TRUE, the Air Affinity Enchantment is available for Helmets.")
                .define("Enable", true);
        treasureAirAffinity = builder
                .comment(treasure)
                .define("Treasure", false);
        builder.pop();

        builder.push("Insight");
        enableXpBoost = builder
                .comment("If TRUE, the Insight Enchantment is available for Helmets.")
                .define("Enable", true);
        treasureXpBoost = builder
                .comment(treasure)
                .define("Treasure", false);
        levelXpBoost = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        amountXpBoost = builder
                .comment("Adjust this to change the max experience awarded per level of the Enchantment.")
                .defineInRange("Experience Amount", 4, 1, 1000);
        builder.pop();

        builder.push("Gourmand");
        enableGourmand = builder
                .comment("If TRUE, the Gourmand Enchantment is available for Helmets.")
                .define("Enable", false);
        treasureGourmand = builder
                .comment(treasure)
                .define("Treasure", false);
        levelGourmand = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        // CHESTPLATE
        builder.push("Reach");
        enableReach = builder
                .comment("If TRUE, the Reach Enchantment is available for Chestplates.")
                .define("Enable", true);
        treasureReach = builder
                .comment(treasure)
                .define("Treasure", true);
        levelReach = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Vitality");
        enableVitality = builder
                .comment("If TRUE, the Vitality Enchantment is available for Chestplates.")
                .define("Enable", true);
        treasureVitality = builder
                .comment(treasure)
                .define("Treasure", true);
        levelVitality = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        healthLevelVitality = builder
                .comment("Adjust this value to set the health granted per level of the Enchantment. (There are 2 health per heart icon.)")
                .defineInRange("Health / Level", 4, 1, 10);
        builder.pop();

        // WEAPONS
        builder.push("Ender Disruption");
        enableDamageEnder = builder
                .comment("If TRUE, the Ender Disruption Enchantment is available for various Weapons.")
                .define("Enable", false);
        treasureDamageEnder = builder
                .comment(treasure)
                .define("Treasure", true);
        levelDamageEnder = builder
                .comment(level)
                .defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Vigilante");
        enableDamageIllager = builder
                .comment("If TRUE, the Vigilante Enchantment is available for various Weapons.")
                .define("Enable", true);
        treasureDamageIllager = builder
                .comment(treasure)
                .define("Treasure", true);
        levelDamageIllager = builder
                .comment(level)
                .defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Outlaw");
        enableDamageVillager = builder
                .comment("If TRUE, the Outlaw Enchantment is available for various Weapons.")
                .define("Enable", false);
        treasureDamageVillager = builder
                .comment(treasure)
                .define("Treasure", false);
        levelDamageVillager = builder
                .comment(level)
                .defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        dropsDamageVillager = builder
                .comment("If TRUE, the Outlaw Enchantment causes Villagers (and Iron Golems) to drop Emeralds when killed.")
                .define("Emerald Drops", true);
        builder.pop();

        builder.push("Cavalier");
        enableCavalier = builder
                .comment("If TRUE, the Cavalier Enchantment is available for various Weapons.")
                .define("Enable", false);
        treasureCavalier = builder
                .comment(treasure)
                .define("Treasure", true);
        levelCavalier = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Frost Aspect");
        enableFrostAspect = builder
                .comment("If TRUE, the Frost Aspect Enchantment is available for various Weapons.")
                .define("Enable", true);
        treasureFrostAspect = builder
                .comment(treasure)
                .define("Treasure", true);
        levelFrostAspect = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Instigating");
        enableInstigating = builder
                .comment("If TRUE, the Instigating Enchantment is available for various Weapons.")
                .define("Enable", false);
        treasureInstigating = builder
                .comment(treasure)
                .define("Treasure", true);
        builder.pop();

        builder.push("Leech");
        enableLeech = builder
                .comment("If TRUE, the Leech Enchantment is available for various Weapons.")
                .define("Enable", true);
        treasureLeech = builder
                .comment(treasure)
                .define("Treasure", false);
        levelLeech = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Magic Edge");
        enableMagicEdge = builder
                .comment("If TRUE, the Magic Edge Enchantment is available for various Weapons.")
                .define("Enable", false);
        treasureMagicEdge = builder
                .comment(treasure)
                .define("Treasure", true);
        levelMagicEdge = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Vorpal");
        enableVorpal = builder
                .comment("If TRUE, the Vorpal Enchantment is available for various Weapons.")
                .define("Enable", true);
        treasureVorpal = builder
                .comment(treasure)
                .define("Treasure", true);
        levelVorpal = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        critBaseVorpal = builder
                .comment("Adjust this value to set the base critical hit chance of the Enchantment (in percentage).")
                .defineInRange("Base Critical Chance", 5, 0, 100);
        critLevelVorpal = builder
                .comment("Adjust this value to set the additional critical hit chance per level of the Enchantment (in percentage).")
                .defineInRange("Critical Chance / Level", 5, 0, 100);
        critDamageVorpal = builder
                .comment("Adjust this value to set the critical hit damage multiplier.")
                .defineInRange("Critical Damage Multiplier", 5, 0, 1000);
        headBaseVorpal = builder
                .comment("Adjust this value to set the base head drop chance for the Enchantment (in percentage).")
                .defineInRange("Base Head Drop Chance", 10, 0, 100);
        headLevelVorpal = builder
                .comment("Adjust this value to set the head drop chance per level of the Enchantment (in percentage).")
                .defineInRange("Head Drop Chance / Level", 10, 0, 100);
        builder.pop();

        // TOOLS
        builder.push("Excavating");
        enableExcavating = builder
                .comment("If TRUE, the Excavating Enchantment is available for various Tools.")
                .define("Enable", true);
        treasureExcavating = builder
                .comment(treasure)
                .define("Treasure", true);
        //        levelExcavating = builder
        //                .comment(level)
        //                .defineInRange("Max Level", 1, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        // BOWS
        builder.push("Hunter's Bounty");
        enableHunter = builder
                .comment("If TRUE, the Hunter's Bounty Enchantment is available for Bows.")
                .define("Enable", true);
        treasureHunter = builder
                .comment(treasure)
                .define("Treasure", true);
        levelHunter = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        chanceHunter = builder
                .comment("Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).")
                .defineInRange("Effect Chance", 50, 1, 100);
        builder.pop();

        builder.push("Quick Draw");
        enableQuickDraw = builder
                .comment("If TRUE, the Quick Draw Enchantment is available for various Bows.")
                .define("Enable", true);
        treasureQuickDraw = builder
                .comment(treasure)
                .define("Treasure", false);
        levelQuickDraw = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Trueshot");
        enableTrueshot = builder
                .comment("If TRUE, the Trueshot Enchantment is available for various Bows.")
                .define("Enable", true);
        treasureTrueshot = builder
                .comment(treasure)
                .define("Treasure", false);
        levelTrueshot = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Volley");

        enableVolley = builder
                .comment("If TRUE, the Volley Enchantment is available for various Bows.")
                .define("Enable", true);
        treasureVolley = builder
                .comment(treasure)
                .define("Treasure", false);
        builder.pop();

        // FISHING RODS
        builder.push("Angler's Bounty");
        enableAngler = builder
                .comment("If TRUE, the Angler's Bounty Enchantment is available for Fishing Rods.")
                .define("Enable", true);
        treasureAngler = builder
                .comment(treasure)
                .define("Treasure", true);
        levelAngler = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        chanceAngler = builder
                .comment("Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).")
                .defineInRange("Effect Chance", 50, 1, 100);
        builder.pop();

        builder.push("Pilfering");
        enablePilfering = builder
                .comment("If TRUE, the Pilfering Enchantment is available for Fishing Rods.")
                .define("Enable", false);
        treasurePilfering = builder
                .comment(treasure)
                .define("Treasure", true);
        playerStealPilfering = builder
                .comment("This sets whether or not the Enchantment works on Players.")
                .define("Allow Player Stealing", true);
        builder.pop();

        // HOES
        //        builder.push("Furrowing");
        //        enableFurrowing = builder
        //                .comment("If TRUE, the Furrowing Enchantment is available for Hoes.")
        //                .define("Enable", true);
        //        treasureFurrowing = builder
        //                .comment(treasure)
        //                .define("Treasure", false);
        //        levelFurrowing = builder
        //                .comment(level)
        //                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        //        builder.pop();
        //
        //        builder.push("Tilling");
        //
        //        enableTilling = builder
        //                .comment("If TRUE, the Tilling Enchantment is available for Hoes.")
        //                .define("Enable", true);
        //        treasureTilling = builder
        //                .comment(treasure)
        //                .define("Treasure", true);
        //        levelTilling = builder
        //                .comment(level)
        //                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        //        builder.pop();
        //
        //        builder.push("Weeding");
        //        enableWeeding = builder
        //                .comment("If TRUE, the Weeding Enchantment is available for Hoes.")
        //                .define("Enable", false);
        //        treasureWeeding = builder
        //                .comment(treasure)
        //                .define("Treasure", false);
        //        builder.pop();

        // SHIELDS
        builder.push("Bulwark");
        enableBulwark = builder
                .comment("If TRUE, the Bulwark Enchantment is available for Shields.")
                .define("Enable", true);
        treasureBulwark = builder
                .comment(treasure)
                .define("Treasure", false);
        builder.pop();

        builder.push("Phalanx");
        enablePhalanx = builder
                .comment("If TRUE, the Phalanx Enchantment is available for Shields.")
                .define("Enable", true);
        treasurePhalanx = builder
                .comment(treasure)
                .define("Treasure", false);
        levelPhalanx = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        // MISC
        builder.push("Soulbound");
        enableSoulbound = builder
                .comment("If TRUE, the Soulbound Enchantment is available.")
                .define("Enable", true);
        treasureSoulbound = builder
                .comment(treasure)
                .define("Treasure", false);
        levelSoulbound = builder
                .comment("This option adjusts the maximum allowable level for the Enchantment. If permanent, this setting is ignored.")
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        permanentSoulbound = builder
                .comment("If TRUE, the Soulbound Enchantment is permanent (and will remove excess levels when triggered).")
                .define("Permanent", true);
        builder.pop();

        // CURSES
        builder.push("Curse of Foolishness");
        enableCurseFool = builder
                .comment("If TRUE, the Curse of Foolishness Enchantment is available for Helmets.")
                .define("Enable", true);
        builder.pop();

        builder.push("Curse of Mercy");
        enableCurseMercy = builder
                .comment("If TRUE, the Curse of Mercy Enchantment is available for various Weapons.")
                .define("Enable", true);
        builder.pop();

        builder.pop();
    }

    @Override
    public void refresh() {

        // These should NEVER actually be null, but who knows in a multi-mod setup. ¯\_(ツ)_/¯

        // ARMOR
        if (PROTECTION_MAGIC instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PROTECTION_MAGIC).setEnable(enableProtectionMagic.get());
            ((EnchantmentCoFH) PROTECTION_MAGIC).setTreasureEnchantment(treasureProtectionMagic.get());
            ((EnchantmentCoFH) PROTECTION_MAGIC).setMaxLevel(levelProtectionMagic.get());
        }
        if (DISPLACEMENT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DISPLACEMENT).setEnable(enableDisplacement.get());
            ((EnchantmentCoFH) DISPLACEMENT).setTreasureEnchantment(treasureDisplacement.get());
            ((EnchantmentCoFH) DISPLACEMENT).setMaxLevel(levelDisplacement.get());
            DisplacementEnchantment.chance = chanceDisplacement.get();
            DisplacementEnchantment.mobsAffectPlayers = allowMobsDisplacement.get();
        }
        if (FIRE_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_REBUKE).setEnable(enableFireRebuke.get());
            ((EnchantmentCoFH) FIRE_REBUKE).setTreasureEnchantment(treasureFireRebuke.get());
            ((EnchantmentCoFH) FIRE_REBUKE).setMaxLevel(levelFireRebuke.get());
            FireRebukeEnchantment.chance = chanceFireRebuke.get();
            FireRebukeEnchantment.mobsAffectPlayers = allowMobsFireRebuke.get();
        }
        if (FROST_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_REBUKE).setEnable(enableFrostRebuke.get());
            ((EnchantmentCoFH) FROST_REBUKE).setTreasureEnchantment(treasureFrostRebuke.get());
            ((EnchantmentCoFH) FROST_REBUKE).setMaxLevel(levelFrostRebuke.get());
            FrostRebukeEnchantment.chance = chanceFrostRebuke.get();
            FrostRebukeEnchantment.mobsAffectPlayers = allowMobsFrostRebuke.get();
        }
        // HELMET
        if (AIR_AFFINITY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) AIR_AFFINITY).setEnable(enableAirAffinity.get());
            ((EnchantmentCoFH) AIR_AFFINITY).setTreasureEnchantment(treasureAirAffinity.get());
        }
        if (XP_BOOST instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) XP_BOOST).setEnable(enableXpBoost.get());
            ((EnchantmentCoFH) XP_BOOST).setTreasureEnchantment(treasureXpBoost.get());
            ((EnchantmentCoFH) XP_BOOST).setMaxLevel(levelXpBoost.get());
            XpBoostEnchantment.xp = amountXpBoost.get();
        }
        if (GOURMAND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) GOURMAND).setEnable(enableGourmand.get());
            ((EnchantmentCoFH) GOURMAND).setTreasureEnchantment(treasureGourmand.get());
            ((EnchantmentCoFH) GOURMAND).setMaxLevel(levelGourmand.get());
        }
        // CHESTPLATE
        if (REACH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) REACH).setEnable(enableReach.get());
            ((EnchantmentCoFH) REACH).setTreasureEnchantment(treasureReach.get());
            ((EnchantmentCoFH) REACH).setMaxLevel(levelReach.get());
        }
        if (VITALITY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VITALITY).setEnable(enableVitality.get());
            ((EnchantmentCoFH) VITALITY).setTreasureEnchantment(treasureVitality.get());
            ((EnchantmentCoFH) VITALITY).setMaxLevel(levelVitality.get());
            VitalityEnchantment.health = healthLevelVitality.get();
        }
        // WEAPONS
        if (DAMAGE_ENDER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_ENDER).setEnable(enableDamageEnder.get());
            ((EnchantmentCoFH) DAMAGE_ENDER).setTreasureEnchantment(treasureDamageEnder.get());
            ((EnchantmentCoFH) DAMAGE_ENDER).setMaxLevel(levelDamageEnder.get());
        }
        if (DAMAGE_ILLAGER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setEnable(enableDamageIllager.get());
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setTreasureEnchantment(treasureDamageIllager.get());
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setMaxLevel(levelDamageIllager.get());
        }
        if (DAMAGE_VILLAGER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setEnable(enableDamageVillager.get());
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setTreasureEnchantment(treasureDamageVillager.get());
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setMaxLevel(levelDamageVillager.get());
            DamageVillagerEnchantment.enableEmeraldDrops = dropsDamageVillager.get();
        }
        if (CAVALIER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CAVALIER).setEnable(enableCavalier.get());
            ((EnchantmentCoFH) CAVALIER).setTreasureEnchantment(treasureCavalier.get());
            ((EnchantmentCoFH) CAVALIER).setMaxLevel(levelCavalier.get());
        }
        if (FROST_ASPECT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_ASPECT).setEnable(enableFrostAspect.get());
            ((EnchantmentCoFH) FROST_ASPECT).setTreasureEnchantment(treasureFrostAspect.get());
            ((EnchantmentCoFH) FROST_ASPECT).setMaxLevel(levelFrostAspect.get());
        }
        if (INSTIGATING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) INSTIGATING).setEnable(enableInstigating.get());
            ((EnchantmentCoFH) INSTIGATING).setTreasureEnchantment(treasureInstigating.get());
        }
        if (LEECH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) LEECH).setEnable(enableLeech.get());
            ((EnchantmentCoFH) LEECH).setTreasureEnchantment(treasureLeech.get());
            ((EnchantmentCoFH) LEECH).setMaxLevel(levelLeech.get());
        }
        if (MAGIC_EDGE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MAGIC_EDGE).setEnable(enableMagicEdge.get());
            ((EnchantmentCoFH) MAGIC_EDGE).setTreasureEnchantment(treasureMagicEdge.get());
            ((EnchantmentCoFH) MAGIC_EDGE).setMaxLevel(levelMagicEdge.get());
        }
        if (VORPAL instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VORPAL).setEnable(enableVorpal.get());
            ((EnchantmentCoFH) VORPAL).setTreasureEnchantment(treasureVorpal.get());
            ((EnchantmentCoFH) VORPAL).setMaxLevel(levelVorpal.get());
            VorpalEnchantment.critBase = critBaseVorpal.get();
            VorpalEnchantment.critLevel = critLevelVorpal.get();
            VorpalEnchantment.critDamage = critDamageVorpal.get();
            VorpalEnchantment.headBase = headBaseVorpal.get();
            VorpalEnchantment.headLevel = headLevelVorpal.get();
        }
        // TOOLS
        if (EXCAVATING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) EXCAVATING).setEnable(enableExcavating.get());
            ((EnchantmentCoFH) EXCAVATING).setTreasureEnchantment(treasureExcavating.get());
            //            ((EnchantmentCoFH) EXCAVATING).setMaxLevel(levelExcavating.get());
        }
        // BOWS
        if (HUNTER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) HUNTER).setEnable(enableHunter.get());
            ((EnchantmentCoFH) HUNTER).setTreasureEnchantment(treasureHunter.get());
            ((EnchantmentCoFH) HUNTER).setMaxLevel(levelHunter.get());
            HunterEnchantment.chance = chanceHunter.get();
        }
        if (QUICK_DRAW instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) QUICK_DRAW).setEnable(enableQuickDraw.get());
            ((EnchantmentCoFH) QUICK_DRAW).setTreasureEnchantment(treasureQuickDraw.get());
            ((EnchantmentCoFH) QUICK_DRAW).setMaxLevel(levelQuickDraw.get());
        }
        if (TRUESHOT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TRUESHOT).setEnable(enableTrueshot.get());
            ((EnchantmentCoFH) TRUESHOT).setTreasureEnchantment(treasureTrueshot.get());
            ((EnchantmentCoFH) TRUESHOT).setMaxLevel(levelTrueshot.get());
        }
        if (VOLLEY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VOLLEY).setEnable(enableVolley.get());
            ((EnchantmentCoFH) VOLLEY).setTreasureEnchantment(treasureVolley.get());
        }
        // FISHING RODS
        if (ANGLER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) ANGLER).setEnable(enableAngler.get());
            ((EnchantmentCoFH) ANGLER).setTreasureEnchantment(treasureAngler.get());
            ((EnchantmentCoFH) ANGLER).setMaxLevel(levelAngler.get());
            AnglerEnchantment.chance = chanceAngler.get();
        }
        if (PILFERING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PILFERING).setEnable(enablePilfering.get());
            ((EnchantmentCoFH) PILFERING).setTreasureEnchantment(treasurePilfering.get());
            PilferingEnchantment.allowPlayerStealing = playerStealPilfering.get();
        }
        // HOES
        if (FURROWING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FURROWING).setEnable(enableFurrowing.get());
            ((EnchantmentCoFH) FURROWING).setTreasureEnchantment(treasureFurrowing.get());
            ((EnchantmentCoFH) FURROWING).setMaxLevel(levelFurrowing.get());
        }
        if (TILLING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TILLING).setEnable(enableTilling.get());
            ((EnchantmentCoFH) TILLING).setTreasureEnchantment(treasureTilling.get());
            ((EnchantmentCoFH) TILLING).setMaxLevel(levelTilling.get());
        }
        if (WEEDING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) WEEDING).setEnable(enableWeeding.get());
            ((EnchantmentCoFH) WEEDING).setTreasureEnchantment(treasureWeeding.get());
        }
        // SHIELDS
        if (BULWARK instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) BULWARK).setEnable(enableBulwark.get());
            ((EnchantmentCoFH) BULWARK).setTreasureEnchantment(treasureBulwark.get());
        }
        if (PHALANX instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PHALANX).setEnable(enablePhalanx.get());
            ((EnchantmentCoFH) PHALANX).setTreasureEnchantment(treasurePhalanx.get());
            ((EnchantmentCoFH) PHALANX).setMaxLevel(levelPhalanx.get());
        }
        // MISC
        if (SOULBOUND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) SOULBOUND).setEnable(enableSoulbound.get());
            ((EnchantmentCoFH) SOULBOUND).setTreasureEnchantment(treasureSoulbound.get());
            ((EnchantmentCoFH) SOULBOUND).setMaxLevel(levelSoulbound.get());
            SoulboundEnchantment.permanent = permanentSoulbound.get();
        }
        // CURSES
        if (CURSE_FOOL instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CURSE_FOOL).setEnable(enableCurseFool.get());
        }
        if (CURSE_MERCY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CURSE_MERCY).setEnable(enableCurseMercy.get());
        }
    }

    // region CONFIG VARIABLES

    // ARMOR
    private ForgeConfigSpec.BooleanValue enableProtectionMagic;
    private ForgeConfigSpec.BooleanValue treasureProtectionMagic;
    private ForgeConfigSpec.IntValue levelProtectionMagic;

    private ForgeConfigSpec.BooleanValue enableDisplacement;
    private ForgeConfigSpec.BooleanValue treasureDisplacement;
    private ForgeConfigSpec.IntValue levelDisplacement;
    private ForgeConfigSpec.IntValue chanceDisplacement;
    private ForgeConfigSpec.BooleanValue allowMobsDisplacement;

    private ForgeConfigSpec.BooleanValue enableFireRebuke;
    private ForgeConfigSpec.BooleanValue treasureFireRebuke;
    private ForgeConfigSpec.IntValue levelFireRebuke;
    private ForgeConfigSpec.IntValue chanceFireRebuke;
    private ForgeConfigSpec.BooleanValue allowMobsFireRebuke;

    private ForgeConfigSpec.BooleanValue enableFrostRebuke;
    private ForgeConfigSpec.BooleanValue treasureFrostRebuke;
    private ForgeConfigSpec.IntValue levelFrostRebuke;
    private ForgeConfigSpec.IntValue chanceFrostRebuke;
    private ForgeConfigSpec.BooleanValue allowMobsFrostRebuke;

    // HELMET
    private ForgeConfigSpec.BooleanValue enableAirAffinity;
    private ForgeConfigSpec.BooleanValue treasureAirAffinity;

    private ForgeConfigSpec.BooleanValue enableXpBoost;
    private ForgeConfigSpec.BooleanValue treasureXpBoost;
    private ForgeConfigSpec.IntValue levelXpBoost;
    private ForgeConfigSpec.IntValue amountXpBoost;

    private ForgeConfigSpec.BooleanValue enableGourmand;
    private ForgeConfigSpec.BooleanValue treasureGourmand;
    private ForgeConfigSpec.IntValue levelGourmand;

    // CHESTPLATE
    private ForgeConfigSpec.BooleanValue enableReach;
    private ForgeConfigSpec.BooleanValue treasureReach;
    private ForgeConfigSpec.IntValue levelReach;

    private ForgeConfigSpec.BooleanValue enableVitality;
    private ForgeConfigSpec.BooleanValue treasureVitality;
    private ForgeConfigSpec.IntValue levelVitality;
    private ForgeConfigSpec.IntValue healthLevelVitality;

    // WEAPONS
    private ForgeConfigSpec.BooleanValue enableDamageEnder;
    private ForgeConfigSpec.BooleanValue treasureDamageEnder;
    private ForgeConfigSpec.IntValue levelDamageEnder;

    private ForgeConfigSpec.BooleanValue enableDamageIllager;
    private ForgeConfigSpec.BooleanValue treasureDamageIllager;
    private ForgeConfigSpec.IntValue levelDamageIllager;

    private ForgeConfigSpec.BooleanValue enableDamageVillager;
    private ForgeConfigSpec.BooleanValue treasureDamageVillager;
    private ForgeConfigSpec.IntValue levelDamageVillager;
    private ForgeConfigSpec.BooleanValue dropsDamageVillager;

    private ForgeConfigSpec.BooleanValue enableCavalier;
    private ForgeConfigSpec.BooleanValue treasureCavalier;
    private ForgeConfigSpec.IntValue levelCavalier;

    private ForgeConfigSpec.BooleanValue enableFrostAspect;
    private ForgeConfigSpec.BooleanValue treasureFrostAspect;
    private ForgeConfigSpec.IntValue levelFrostAspect;

    private ForgeConfigSpec.BooleanValue enableInstigating;
    private ForgeConfigSpec.BooleanValue treasureInstigating;

    private ForgeConfigSpec.BooleanValue enableLeech;
    private ForgeConfigSpec.BooleanValue treasureLeech;
    private ForgeConfigSpec.IntValue levelLeech;

    private ForgeConfigSpec.BooleanValue enableMagicEdge;
    private ForgeConfigSpec.BooleanValue treasureMagicEdge;
    private ForgeConfigSpec.IntValue levelMagicEdge;

    private ForgeConfigSpec.BooleanValue enableVorpal;
    private ForgeConfigSpec.BooleanValue treasureVorpal;
    private ForgeConfigSpec.IntValue levelVorpal;
    private ForgeConfigSpec.IntValue critBaseVorpal;
    private ForgeConfigSpec.IntValue critLevelVorpal;
    private ForgeConfigSpec.IntValue critDamageVorpal;
    private ForgeConfigSpec.IntValue headBaseVorpal;
    private ForgeConfigSpec.IntValue headLevelVorpal;

    // TOOLS
    private ForgeConfigSpec.BooleanValue enableExcavating;
    private ForgeConfigSpec.BooleanValue treasureExcavating;

    // BOWS
    private ForgeConfigSpec.BooleanValue enableHunter;
    private ForgeConfigSpec.BooleanValue treasureHunter;
    private ForgeConfigSpec.IntValue levelHunter;
    private ForgeConfigSpec.IntValue chanceHunter;

    private ForgeConfigSpec.BooleanValue enableQuickDraw;
    private ForgeConfigSpec.BooleanValue treasureQuickDraw;
    private ForgeConfigSpec.IntValue levelQuickDraw;

    private ForgeConfigSpec.BooleanValue enableTrueshot;
    private ForgeConfigSpec.BooleanValue treasureTrueshot;
    private ForgeConfigSpec.IntValue levelTrueshot;

    private ForgeConfigSpec.BooleanValue enableVolley;
    private ForgeConfigSpec.BooleanValue treasureVolley;

    // FISHING RODS
    private ForgeConfigSpec.BooleanValue enableAngler;
    private ForgeConfigSpec.BooleanValue treasureAngler;
    private ForgeConfigSpec.IntValue levelAngler;
    private ForgeConfigSpec.IntValue chanceAngler;

    private ForgeConfigSpec.BooleanValue enablePilfering;
    private ForgeConfigSpec.BooleanValue treasurePilfering;
    private ForgeConfigSpec.BooleanValue playerStealPilfering;

    // HOES
    private ForgeConfigSpec.BooleanValue enableFurrowing;
    private ForgeConfigSpec.BooleanValue treasureFurrowing;
    private ForgeConfigSpec.IntValue levelFurrowing;

    private ForgeConfigSpec.BooleanValue enableTilling;
    private ForgeConfigSpec.BooleanValue treasureTilling;
    private ForgeConfigSpec.IntValue levelTilling;

    private ForgeConfigSpec.BooleanValue enableWeeding;
    private ForgeConfigSpec.BooleanValue treasureWeeding;

    // SHIELDS
    private ForgeConfigSpec.BooleanValue enableBulwark;
    private ForgeConfigSpec.BooleanValue treasureBulwark;

    private ForgeConfigSpec.BooleanValue enablePhalanx;
    private ForgeConfigSpec.BooleanValue treasurePhalanx;
    private ForgeConfigSpec.IntValue levelPhalanx;

    // MISC
    private ForgeConfigSpec.BooleanValue enableSoulbound;
    private ForgeConfigSpec.BooleanValue treasureSoulbound;
    private ForgeConfigSpec.IntValue levelSoulbound;
    private ForgeConfigSpec.BooleanValue permanentSoulbound;

    // CURSES
    private ForgeConfigSpec.BooleanValue enableCurseFool;

    private ForgeConfigSpec.BooleanValue enableCurseMercy;
    // endregion
}
