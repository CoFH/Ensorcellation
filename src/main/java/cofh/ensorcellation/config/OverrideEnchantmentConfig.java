package cofh.ensorcellation.config;

import cofh.ensorcellation.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.ensorcellation.enchantment.override.MendingEnchantmentAlt;
import cofh.ensorcellation.enchantment.override.ThornsEnchantmentImp;
import cofh.lib.config.IBaseConfig;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;
import static net.minecraft.world.item.enchantment.Enchantments.*;

public class OverrideEnchantmentConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {

        String treasure = "This sets whether or not the Enchantment is considered a 'treasure' enchantment.";
        String level = "This option adjusts the maximum allowable level for the Enchantment.";

        builder.push("Overrides");

        builder.push("Protection");
        enableProtection = builder
                .comment("If TRUE, the Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.")
                .define("Enable", true);
        levelProtection = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Blast Protection");
        enableProtectionBlast = builder
                .comment("If TRUE, the Blast Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.")
                .define("Enable", true);
        levelProtectionBlast = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Feather Falling");
        enableProtectionFall = builder
                .comment("If TRUE, the Feather Falling Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.")
                .define("Enable", true);
        levelProtectionFall = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Fire Protection");
        enableProtectionFire = builder
                .comment("If TRUE, the Fire Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.")
                .define("Enable", true);
        levelProtectionFire = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Projectile Protection");
        enableProtectionProjectile = builder
                .comment("If TRUE, the Projectile Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.")
                .define("Enable", true);
        levelProtectionProjectile = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Fire Aspect");
        enableFireAspect = builder
                .comment("If TRUE, the Fire Aspect Enchantment is replaced with a more configurable version which works on more items, such as Axes.")
                .define("Enable", true);
        levelFireAspect = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Frost Walker");
        enableFrostWalker = builder
                .comment("If TRUE, the Frost Walker Enchantment is replaced with an improved and more configurable version which works on more items, such as Horse Armor.")
                .define("Enable", true);
        treasureFrostWalker = builder
                .comment(treasure)
                .define("Treasure", true);
        levelFrostWalker = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        enableFreezeLava = builder
                .comment("If TRUE, the Frost Walker Enchantment will also chill Lava into Glossed Magma.")
                .define("Freeze Lava", true);
        builder.pop();

        builder.push("Knockback");
        enableKnockback = builder
                .comment("If TRUE, the Knockback Enchantment is replaced with a more configurable version which works on more items, such as Axes.")
                .define("Enable", true);
        levelKnockback = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Looting");
        enableLooting = builder
                .comment("If TRUE, the Looting Enchantment is replaced with a more configurable version which works on more items, such as Axes.")
                .define("Enable", true);
        levelLooting = builder
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Thorns");
        enableThorns = builder
                .comment("If TRUE, the Thorns Enchantment is replaced with a more configurable version which works on more items, such as Shields and Horse Armor.")
                .define("Enable", true);
        levelThorns = builder
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        chanceThorns = builder
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 15, 1, 100);
        builder.pop();

        builder.push("Mending");
        alternateMending = builder
                .comment("If TRUE, the Mending Enchantment is replaced with a new Enchantment - Preservation. This enchantment allows you to repair items at an Anvil without paying an increasing XP cost for every time you repair it. Additionally, these repairs have a much lower chance of damaging the anvil.")
                .define("Alternate Mending", false);
        treasureMending = builder
                .comment(treasure)
                .define("Treasure", true);
        damageMending = builder
                .comment("Adjust this value to set the chance of an Anvil being damaged when used to repair an item with Preservation (in percentage). Only used if Alternate Mending (Preservation) is enabled.")
                .defineInRange("Anvil Damage Chance", 3, 0, 12);
        builder.pop();

        builder.pop();
    }

    @Override
    public void refresh() {

        // These should not cast incorrectly, but who knows in a multi-mod setup. ¯\_(ツ)_/¯
        if (ALL_DAMAGE_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) ALL_DAMAGE_PROTECTION).setEnable(enableProtection.get());
            ((EnchantmentCoFH) ALL_DAMAGE_PROTECTION).setMaxLevel(levelProtection.get());
        }
        if (BLAST_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) BLAST_PROTECTION).setEnable(enableProtectionBlast.get());
            ((EnchantmentCoFH) BLAST_PROTECTION).setMaxLevel(levelProtectionBlast.get());
        }
        if (FALL_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FALL_PROTECTION).setEnable(enableProtectionFall.get());
            ((EnchantmentCoFH) FALL_PROTECTION).setMaxLevel(levelProtectionFall.get());
        }
        if (FIRE_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_PROTECTION).setEnable(enableProtectionFire.get());
            ((EnchantmentCoFH) FIRE_PROTECTION).setMaxLevel(levelProtectionFire.get());
        }
        if (PROJECTILE_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PROJECTILE_PROTECTION).setEnable(enableProtectionProjectile.get());
            ((EnchantmentCoFH) PROJECTILE_PROTECTION).setMaxLevel(levelProtectionProjectile.get());
        }
        if (FIRE_ASPECT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_ASPECT).setEnable(enableFireAspect.get());
            ((EnchantmentCoFH) FIRE_ASPECT).setMaxLevel(levelFireAspect.get());
        }
        if (FROST_WALKER instanceof FrostWalkerEnchantmentImp) {
            ((EnchantmentCoFH) FROST_WALKER).setEnable(enableFrostWalker.get());
            ((EnchantmentCoFH) FROST_WALKER).setTreasureEnchantment(treasureFrostWalker.get());
            ((EnchantmentCoFH) FROST_WALKER).setMaxLevel(levelFrostWalker.get());
            ((FrostWalkerEnchantmentImp) FROST_WALKER).setFreezeLava(enableFreezeLava.get());
        }
        if (KNOCKBACK instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) KNOCKBACK).setEnable(enableKnockback.get());
            ((EnchantmentCoFH) KNOCKBACK).setMaxLevel(levelKnockback.get());
        }
        if (MOB_LOOTING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MOB_LOOTING).setEnable(enableLooting.get());
            ((EnchantmentCoFH) MOB_LOOTING).setMaxLevel(levelLooting.get());
        }
        if (THORNS instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) THORNS).setEnable(enableThorns.get());
            ((EnchantmentCoFH) THORNS).setMaxLevel(levelThorns.get());
            ThornsEnchantmentImp.chance = chanceThorns.get();
        }
        if (MENDING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MENDING).setEnable(alternateMending.get());
            ((EnchantmentCoFH) MENDING).setTreasureEnchantment(treasureMending.get());
            MendingEnchantmentAlt.anvilDamage = damageMending.get() / 100F;
        }
    }

    // region VARIABLES
    public static boolean enableMendingOverride = false;
    // endregion

    // region CONFIG VARIABLES
    private ForgeConfigSpec.BooleanValue enableProtection;
    private ForgeConfigSpec.IntValue levelProtection;

    private ForgeConfigSpec.BooleanValue enableProtectionBlast;
    private ForgeConfigSpec.IntValue levelProtectionBlast;

    private ForgeConfigSpec.BooleanValue enableProtectionFall;
    private ForgeConfigSpec.IntValue levelProtectionFall;

    private ForgeConfigSpec.BooleanValue enableProtectionFire;
    private ForgeConfigSpec.IntValue levelProtectionFire;

    private ForgeConfigSpec.BooleanValue enableProtectionProjectile;
    private ForgeConfigSpec.IntValue levelProtectionProjectile;

    private ForgeConfigSpec.BooleanValue enableFireAspect;
    private ForgeConfigSpec.IntValue levelFireAspect;

    private ForgeConfigSpec.BooleanValue enableFrostWalker;
    private ForgeConfigSpec.BooleanValue treasureFrostWalker;
    private ForgeConfigSpec.IntValue levelFrostWalker;
    private ForgeConfigSpec.BooleanValue enableFreezeLava;

    private ForgeConfigSpec.BooleanValue enableKnockback;
    private ForgeConfigSpec.IntValue levelKnockback;

    private ForgeConfigSpec.BooleanValue enableLooting;
    private ForgeConfigSpec.IntValue levelLooting;

    private ForgeConfigSpec.BooleanValue enableThorns;
    private ForgeConfigSpec.IntValue levelThorns;
    private ForgeConfigSpec.IntValue chanceThorns;

    private ForgeConfigSpec.BooleanValue alternateMending;
    private ForgeConfigSpec.BooleanValue treasureMending;
    private ForgeConfigSpec.IntValue damageMending;
    // endregion
}
