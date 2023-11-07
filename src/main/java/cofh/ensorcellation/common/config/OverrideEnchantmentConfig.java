package cofh.ensorcellation.common.config;

import cofh.core.common.config.IBaseConfig;
import cofh.ensorcellation.common.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.ensorcellation.common.enchantment.override.MendingEnchantmentAlt;
import cofh.ensorcellation.common.enchantment.override.ThornsEnchantmentImp;
import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;
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
        //        enableFireAspect = builder
        //                .comment("If TRUE, the Fire Aspect Enchantment is replaced with a more configurable version which works on more items, such as Axes.")
        //                .define("Enable", true);
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
        //        enableKnockback = builder
        //                .comment("If TRUE, the Knockback Enchantment is replaced with a more configurable version which works on more items, such as Axes.")
        //                .define("Enable", true);
        levelKnockback = builder
                .comment(level)
                .defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        builder.pop();

        builder.push("Looting");
        //        enableLooting = builder
        //                .comment("If TRUE, the Looting Enchantment is replaced with a more configurable version which works on more items, such as Axes.")
        //                .define("Enable", true);
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
        if (ALL_DAMAGE_PROTECTION instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableProtection.get());
            enc.setMaxLevel(levelProtection.get());
        }
        if (BLAST_PROTECTION instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableProtectionBlast.get());
            enc.setMaxLevel(levelProtectionBlast.get());
        }
        if (FALL_PROTECTION instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableProtectionFall.get());
            enc.setMaxLevel(levelProtectionFall.get());
        }
        if (FIRE_PROTECTION instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableProtectionFire.get());
            enc.setMaxLevel(levelProtectionFire.get());
        }
        if (PROJECTILE_PROTECTION instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableProtectionProjectile.get());
            enc.setMaxLevel(levelProtectionProjectile.get());
        }
        if (FIRE_ASPECT instanceof EnchantmentCoFH enc) {
            enc.setMaxLevel(levelFireAspect.get());
        }
        if (FROST_WALKER instanceof FrostWalkerEnchantmentImp enc) {
            enc.setEnable(enableFrostWalker.get());
            enc.setTreasureEnchantment(treasureFrostWalker.get());
            enc.setMaxLevel(levelFrostWalker.get());
            enc.setFreezeLava(enableFreezeLava.get());
        }
        if (KNOCKBACK instanceof EnchantmentCoFH enc) {
            enc.setMaxLevel(levelKnockback.get());
        }
        if (MOB_LOOTING instanceof EnchantmentCoFH enc) {
            enc.setMaxLevel(levelLooting.get());
        }
        if (THORNS instanceof EnchantmentCoFH enc) {
            enc.setEnable(enableThorns.get());
            enc.setMaxLevel(levelThorns.get());
            ThornsEnchantmentImp.chance = chanceThorns.get();
        }
        if (MENDING instanceof EnchantmentCoFH enc) {
            enableMendingOverride = alternateMending.get();
            enc.setEnable(alternateMending.get());
            enc.setTreasureEnchantment(treasureMending.get());
            MendingEnchantmentAlt.anvilDamage = damageMending.get() / 100F;
        }
    }

    // region VARIABLES
    public static boolean enableMendingOverride = false;
    // endregion

    // region CONFIG VARIABLES
    private Supplier<Boolean> enableProtection;
    private Supplier<Integer> levelProtection;

    private Supplier<Boolean> enableProtectionBlast;
    private Supplier<Integer> levelProtectionBlast;

    private Supplier<Boolean> enableProtectionFall;
    private Supplier<Integer> levelProtectionFall;

    private Supplier<Boolean> enableProtectionFire;
    private Supplier<Integer> levelProtectionFire;

    private Supplier<Boolean> enableProtectionProjectile;
    private Supplier<Integer> levelProtectionProjectile;

    private Supplier<Integer> levelFireAspect;

    private Supplier<Boolean> enableFrostWalker;
    private Supplier<Boolean> treasureFrostWalker;
    private Supplier<Integer> levelFrostWalker;
    private Supplier<Boolean> enableFreezeLava;

    private Supplier<Integer> levelKnockback;

    private Supplier<Integer> levelLooting;

    private Supplier<Boolean> enableThorns;
    private Supplier<Integer> levelThorns;
    private Supplier<Integer> chanceThorns;

    private Supplier<Boolean> alternateMending;
    private Supplier<Boolean> treasureMending;
    private Supplier<Integer> damageMending;
    // endregion
}
