package cofh.ensorcellation.common.config;

import cofh.core.common.config.IBaseConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static cofh.core.init.CoreEnchantments.Types.SWORD_OR_AXE;
import static net.minecraft.world.item.enchantment.EnchantmentCategory.WEAPON;
import static net.minecraft.world.item.enchantment.Enchantments.*;

public class OverrideEnchantmentConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {

        String treasure = "This sets whether or not the Enchantment is considered a 'treasure' enchantment.";
        String level = "This option adjusts the maximum allowable level for the Enchantment.";

        builder.push("Overrides");

        builder.push("Fire Aspect");
        enableFireAspect = builder
                .comment("If TRUE, the Fire Aspect Enchantment will now work on Axes.")
                .define("Enable", true);
        builder.pop();

        builder.push("Frost Walker");
        enableFrostWalker = builder
                .comment("If TRUE, the Frost Walker Enchantment will now work on Horse Armor.")
                .define("Enable", true);
        enableFreezeLava = builder
                .comment("If TRUE, the Frost Walker Enchantment will also freeze Lava into Glossed Magma.")
                .define("Freeze Lava", true);
        builder.pop();

        builder.push("Knockback");
        enableKnockback = builder
                .comment("If TRUE, the Knockback Enchantment will now work on Axes.")
                .define("Enable", true);
        builder.pop();

        builder.push("Looting");
        enableLooting = builder
                .comment("If TRUE, the Looting Enchantment will now work on Axes.")
                .define("Enable", true);
        builder.pop();

        builder.push("Thorns");
        enableThorns = builder
                .comment("If TRUE, the Thorns Enchantment will now work on Shields and Horse Armor.")
                .define("Enable", true);
        builder.pop();

        builder.pop();
    }

    @Override
    public void refresh() {

        // Fire Aspect
        FIRE_ASPECT.category = enableFireAspect.get() ? SWORD_OR_AXE : WEAPON;

        // Knockback
        KNOCKBACK.category = enableKnockback.get() ? SWORD_OR_AXE : WEAPON;

        // Looting
        MOB_LOOTING.category = enableLooting.get() ? SWORD_OR_AXE : WEAPON;

        // Frost Walker
        improvedFrostWalker = enableFrostWalker.get();
        lavaFrostWalker = enableFreezeLava.get();

        // Thorns
        improvedThorns = enableThorns.get();
    }

    // region VARIABLES
    public static boolean improvedFrostWalker = true;
    public static boolean lavaFrostWalker = true;
    public static boolean improvedThorns = true;
    public static boolean enableMendingOverride = false;
    // endregion

    // region CONFIG VARIABLES
    private Supplier<Boolean> enableFireAspect;
    private Supplier<Boolean> enableKnockback;
    private Supplier<Boolean> enableLooting;
    private Supplier<Boolean> enableThorns;

    private Supplier<Boolean> enableFrostWalker;
    private Supplier<Boolean> enableFreezeLava;
    // endregion
}
