package cofh.ensorcellation.common.config;

import cofh.core.common.config.IBaseConfig;
import cofh.ensorcellation.common.enchantment.*;
import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static cofh.core.util.references.EnsorcIDs.*;
import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;
import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;

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
        if (ENCHANTMENTS.get(ID_PROTECTION_MAGIC) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableProtectionMagic.get());
            enc.setTreasureEnchantment(treasureProtectionMagic.get());
            enc.setMaxLevel(levelProtectionMagic.get());
        }
        if (ENCHANTMENTS.get(ID_DISPLACEMENT) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableDisplacement.get());
            enc.setTreasureEnchantment(treasureDisplacement.get());
            enc.setMaxLevel(levelDisplacement.get());
            DisplacementEnchantment.chance = chanceDisplacement.get();
            DisplacementEnchantment.mobsAffectPlayers = allowMobsDisplacement.get();
        }
        if (ENCHANTMENTS.get(ID_FIRE_REBUKE) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableFireRebuke.get());
            enc.setTreasureEnchantment(treasureFireRebuke.get());
            enc.setMaxLevel(levelFireRebuke.get());
            FireRebukeEnchantment.chance = chanceFireRebuke.get();
            FireRebukeEnchantment.mobsAffectPlayers = allowMobsFireRebuke.get();
        }
        if (ENCHANTMENTS.get(ID_FROST_REBUKE) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableFrostRebuke.get());
            enc.setTreasureEnchantment(treasureFrostRebuke.get());
            enc.setMaxLevel(levelFrostRebuke.get());
            FrostRebukeEnchantment.chance = chanceFrostRebuke.get();
            FrostRebukeEnchantment.mobsAffectPlayers = allowMobsFrostRebuke.get();
        }
        // HELMET
        if (ENCHANTMENTS.get(ID_AIR_AFFINITY) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableAirAffinity.get());
            enc.setTreasureEnchantment(treasureAirAffinity.get());
        }
        if (ENCHANTMENTS.get(ID_XP_BOOST) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableXpBoost.get());
            enc.setTreasureEnchantment(treasureXpBoost.get());
            enc.setMaxLevel(levelXpBoost.get());
            XpBoostEnchantment.xp = amountXpBoost.get();
        }
        if (ENCHANTMENTS.get(ID_GOURMAND) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableGourmand.get());
            enc.setTreasureEnchantment(treasureGourmand.get());
            enc.setMaxLevel(levelGourmand.get());
        }
        // CHESTPLATE
        if (ENCHANTMENTS.get(ID_REACH) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableReach.get());
            enc.setTreasureEnchantment(treasureReach.get());
            enc.setMaxLevel(levelReach.get());
        }
        if (ENCHANTMENTS.get(ID_VITALITY) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableVitality.get());
            enc.setTreasureEnchantment(treasureVitality.get());
            enc.setMaxLevel(levelVitality.get());
            VitalityEnchantment.health = healthLevelVitality.get();
        }
        // WEAPONS
        if (ENCHANTMENTS.get(ID_DAMAGE_ENDER) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableDamageEnder.get());
            enc.setTreasureEnchantment(treasureDamageEnder.get());
            enc.setMaxLevel(levelDamageEnder.get());
        }
        if (ENCHANTMENTS.get(ID_DAMAGE_ILLAGER) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableDamageIllager.get());
            enc.setTreasureEnchantment(treasureDamageIllager.get());
            enc.setMaxLevel(levelDamageIllager.get());
        }
        if (ENCHANTMENTS.get(ID_DAMAGE_VILLAGER) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableDamageVillager.get());
            enc.setTreasureEnchantment(treasureDamageVillager.get());
            enc.setMaxLevel(levelDamageVillager.get());
            DamageVillagerEnchantment.enableEmeraldDrops = dropsDamageVillager.get();
        }
        if (ENCHANTMENTS.get(ID_CAVALIER) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableCavalier.get());
            enc.setTreasureEnchantment(treasureCavalier.get());
            enc.setMaxLevel(levelCavalier.get());
        }
        if (ENCHANTMENTS.get(ID_FROST_ASPECT) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableFrostAspect.get());
            enc.setTreasureEnchantment(treasureFrostAspect.get());
            enc.setMaxLevel(levelFrostAspect.get());
        }
        if (ENCHANTMENTS.get(ID_INSTIGATING) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableInstigating.get());
            enc.setTreasureEnchantment(treasureInstigating.get());
        }
        if (ENCHANTMENTS.get(ID_LEECH) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableLeech.get());
            enc.setTreasureEnchantment(treasureLeech.get());
            enc.setMaxLevel(levelLeech.get());
        }
        if (ENCHANTMENTS.get(ID_MAGIC_EDGE) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableMagicEdge.get());
            enc.setTreasureEnchantment(treasureMagicEdge.get());
            enc.setMaxLevel(levelMagicEdge.get());
        }
        if (ENCHANTMENTS.get(ID_VORPAL) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableVorpal.get());
            enc.setTreasureEnchantment(treasureVorpal.get());
            enc.setMaxLevel(levelVorpal.get());
            VorpalEnchantment.critBase = critBaseVorpal.get();
            VorpalEnchantment.critLevel = critLevelVorpal.get();
            VorpalEnchantment.critDamage = critDamageVorpal.get();
            VorpalEnchantment.headBase = headBaseVorpal.get();
            VorpalEnchantment.headLevel = headLevelVorpal.get();
        }
        // TOOLS
        if (ENCHANTMENTS.get(ID_EXCAVATING) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableExcavating.get());
            enc.setTreasureEnchantment(treasureExcavating.get());
            //            ((EnchantmentCoFH) EXCAVATING).setMaxLevel(levelExcavating.get());
        }
        // BOWS
        if (ENCHANTMENTS.get(ID_HUNTER) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableHunter.get());
            enc.setTreasureEnchantment(treasureHunter.get());
            enc.setMaxLevel(levelHunter.get());
            HunterEnchantment.chance = chanceHunter.get();
        }
        if (ENCHANTMENTS.get(ID_QUICK_DRAW) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableQuickDraw.get());
            enc.setTreasureEnchantment(treasureQuickDraw.get());
            enc.setMaxLevel(levelQuickDraw.get());
        }
        if (ENCHANTMENTS.get(ID_TRUESHOT) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableTrueshot.get());
            enc.setTreasureEnchantment(treasureTrueshot.get());
            enc.setMaxLevel(levelTrueshot.get());
        }
        if (ENCHANTMENTS.get(ID_VOLLEY) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableVolley.get());
            enc.setTreasureEnchantment(treasureVolley.get());
        }
        // FISHING RODS
        if (ENCHANTMENTS.get(ID_ANGLER) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableAngler.get());
            enc.setTreasureEnchantment(treasureAngler.get());
            enc.setMaxLevel(levelAngler.get());
            AnglerEnchantment.chance = chanceAngler.get();
        }
        if (ENCHANTMENTS.get(ID_PILFERING) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enablePilfering.get());
            enc.setTreasureEnchantment(treasurePilfering.get());
            PilferingEnchantment.allowPlayerStealing = playerStealPilfering.get();
        }
        // HOES
        if (ENCHANTMENTS.get(ID_FURROWING) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableFurrowing.get());
            enc.setTreasureEnchantment(treasureFurrowing.get());
            enc.setMaxLevel(levelFurrowing.get());
        }
        if (ENCHANTMENTS.get(ID_TILLING) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableTilling.get());
            enc.setTreasureEnchantment(treasureTilling.get());
            enc.setMaxLevel(levelTilling.get());
        }
        if (ENCHANTMENTS.get(ID_WEEDING) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableWeeding.get());
            enc.setTreasureEnchantment(treasureWeeding.get());
        }
        // SHIELDS
        if (ENCHANTMENTS.get(ID_BULWARK) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableBulwark.get());
            enc.setTreasureEnchantment(treasureBulwark.get());
        }
        if (ENCHANTMENTS.get(ID_PHALANX) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enablePhalanx.get());
            enc.setTreasureEnchantment(treasurePhalanx.get());
            enc.setMaxLevel(levelPhalanx.get());
        }
        // MISC
        if (ENCHANTMENTS.get(ID_SOULBOUND) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableSoulbound.get());
            enc.setTreasureEnchantment(treasureSoulbound.get());
            enc.setMaxLevel(levelSoulbound.get());
            SoulboundEnchantment.permanent = permanentSoulbound.get();
        }
        // CURSES
        if (ENCHANTMENTS.get(ID_CURSE_FOOL) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableCurseFool.get());
        }
        if (ENCHANTMENTS.get(ID_CURSE_MERCY) instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableCurseMercy.get());
        }
    }

    // region CONFIG VARIABLES

    // ARMOR
    private Supplier<Boolean> enableProtectionMagic;
    private Supplier<Boolean> treasureProtectionMagic;
    private Supplier<Integer> levelProtectionMagic;

    private Supplier<Boolean> enableDisplacement;
    private Supplier<Boolean> treasureDisplacement;
    private Supplier<Integer> levelDisplacement;
    private Supplier<Integer> chanceDisplacement;
    private Supplier<Boolean> allowMobsDisplacement;

    private Supplier<Boolean> enableFireRebuke;
    private Supplier<Boolean> treasureFireRebuke;
    private Supplier<Integer> levelFireRebuke;
    private Supplier<Integer> chanceFireRebuke;
    private Supplier<Boolean> allowMobsFireRebuke;

    private Supplier<Boolean> enableFrostRebuke;
    private Supplier<Boolean> treasureFrostRebuke;
    private Supplier<Integer> levelFrostRebuke;
    private Supplier<Integer> chanceFrostRebuke;
    private Supplier<Boolean> allowMobsFrostRebuke;

    // HELMET
    private Supplier<Boolean> enableAirAffinity;
    private Supplier<Boolean> treasureAirAffinity;

    private Supplier<Boolean> enableXpBoost;
    private Supplier<Boolean> treasureXpBoost;
    private Supplier<Integer> levelXpBoost;
    private Supplier<Integer> amountXpBoost;

    private Supplier<Boolean> enableGourmand;
    private Supplier<Boolean> treasureGourmand;
    private Supplier<Integer> levelGourmand;

    // CHESTPLATE
    private Supplier<Boolean> enableReach;
    private Supplier<Boolean> treasureReach;
    private Supplier<Integer> levelReach;

    private Supplier<Boolean> enableVitality;
    private Supplier<Boolean> treasureVitality;
    private Supplier<Integer> levelVitality;
    private Supplier<Integer> healthLevelVitality;

    // WEAPONS
    private Supplier<Boolean> enableDamageEnder;
    private Supplier<Boolean> treasureDamageEnder;
    private Supplier<Integer> levelDamageEnder;

    private Supplier<Boolean> enableDamageIllager;
    private Supplier<Boolean> treasureDamageIllager;
    private Supplier<Integer> levelDamageIllager;

    private Supplier<Boolean> enableDamageVillager;
    private Supplier<Boolean> treasureDamageVillager;
    private Supplier<Integer> levelDamageVillager;
    private Supplier<Boolean> dropsDamageVillager;

    private Supplier<Boolean> enableCavalier;
    private Supplier<Boolean> treasureCavalier;
    private Supplier<Integer> levelCavalier;

    private Supplier<Boolean> enableFrostAspect;
    private Supplier<Boolean> treasureFrostAspect;
    private Supplier<Integer> levelFrostAspect;

    private Supplier<Boolean> enableInstigating;
    private Supplier<Boolean> treasureInstigating;

    private Supplier<Boolean> enableLeech;
    private Supplier<Boolean> treasureLeech;
    private Supplier<Integer> levelLeech;

    private Supplier<Boolean> enableMagicEdge;
    private Supplier<Boolean> treasureMagicEdge;
    private Supplier<Integer> levelMagicEdge;

    private Supplier<Boolean> enableVorpal;
    private Supplier<Boolean> treasureVorpal;
    private Supplier<Integer> levelVorpal;
    private Supplier<Integer> critBaseVorpal;
    private Supplier<Integer> critLevelVorpal;
    private Supplier<Integer> critDamageVorpal;
    private Supplier<Integer> headBaseVorpal;
    private Supplier<Integer> headLevelVorpal;

    // TOOLS
    private Supplier<Boolean> enableExcavating;
    private Supplier<Boolean> treasureExcavating;

    // BOWS
    private Supplier<Boolean> enableHunter;
    private Supplier<Boolean> treasureHunter;
    private Supplier<Integer> levelHunter;
    private Supplier<Integer> chanceHunter;

    private Supplier<Boolean> enableQuickDraw;
    private Supplier<Boolean> treasureQuickDraw;
    private Supplier<Integer> levelQuickDraw;

    private Supplier<Boolean> enableTrueshot;
    private Supplier<Boolean> treasureTrueshot;
    private Supplier<Integer> levelTrueshot;

    private Supplier<Boolean> enableVolley;
    private Supplier<Boolean> treasureVolley;

    // FISHING RODS
    private Supplier<Boolean> enableAngler;
    private Supplier<Boolean> treasureAngler;
    private Supplier<Integer> levelAngler;
    private Supplier<Integer> chanceAngler;

    private Supplier<Boolean> enablePilfering;
    private Supplier<Boolean> treasurePilfering;
    private Supplier<Boolean> playerStealPilfering;

    // HOES
    private Supplier<Boolean> enableFurrowing;
    private Supplier<Boolean> treasureFurrowing;
    private Supplier<Integer> levelFurrowing;

    private Supplier<Boolean> enableTilling;
    private Supplier<Boolean> treasureTilling;
    private Supplier<Integer> levelTilling;

    private Supplier<Boolean> enableWeeding;
    private Supplier<Boolean> treasureWeeding;

    // SHIELDS
    private Supplier<Boolean> enableBulwark;
    private Supplier<Boolean> treasureBulwark;

    private Supplier<Boolean> enablePhalanx;
    private Supplier<Boolean> treasurePhalanx;
    private Supplier<Integer> levelPhalanx;

    // MISC
    private Supplier<Boolean> enableSoulbound;
    private Supplier<Boolean> treasureSoulbound;
    private Supplier<Integer> levelSoulbound;
    private Supplier<Boolean> permanentSoulbound;

    // CURSES
    private Supplier<Boolean> enableCurseFool;

    private Supplier<Boolean> enableCurseMercy;
    // endregion
}
