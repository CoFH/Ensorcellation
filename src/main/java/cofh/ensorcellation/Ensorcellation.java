package cofh.ensorcellation;

import cofh.core.config.ConfigManager;
import cofh.ensorcellation.config.BaseEnchantmentConfig;
import cofh.ensorcellation.config.OverrideEnchantmentConfig;
import cofh.ensorcellation.init.EnsorcEnchantments;
import cofh.lib.util.DeferredRegisterCoFH;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;
import static cofh.lib.util.constants.ModIds.ID_MINECRAFT;

@Mod (ID_ENSORCELLATION)
public class Ensorcellation {

    public static final Logger LOG = LogManager.getLogger(ID_ENSORCELLATION);
    public static final ConfigManager CONFIG_MANAGER = new ConfigManager();

    public static final DeferredRegisterCoFH<Enchantment> ENCHANTMENTS = DeferredRegisterCoFH.create(ForgeRegistries.ENCHANTMENTS, ID_ENSORCELLATION);
    public static final DeferredRegisterCoFH<Enchantment> OVERRIDES = DeferredRegisterCoFH.create(ForgeRegistries.ENCHANTMENTS, ID_MINECRAFT);

    public Ensorcellation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ENCHANTMENTS.register(modEventBus);

        CONFIG_MANAGER.register(modEventBus)
                .addServerConfig(new BaseEnchantmentConfig())
                .addServerConfig(new OverrideEnchantmentConfig());
        CONFIG_MANAGER.setupServer();

        EnsorcEnchantments.register();

        //        CoreItems.registerHorseArmorOverrides();
        //        CoreItems.registerShieldOverride();
    }

}
