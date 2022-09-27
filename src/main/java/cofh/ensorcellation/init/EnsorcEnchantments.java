package cofh.ensorcellation.init;

import cofh.ensorcellation.enchantment.*;
import cofh.ensorcellation.enchantment.override.*;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraftforge.registries.RegistryObject;

import static cofh.core.util.references.EnsorcIDs.*;
import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;
import static cofh.ensorcellation.Ensorcellation.OVERRIDES;
import static cofh.ensorcellation.enchantment.override.ProtectionEnchantmentImp.Type.*;
import static cofh.lib.util.Constants.ARMOR_SLOTS;
import static net.minecraft.world.item.enchantment.Enchantment.Rarity.*;

public class EnsorcEnchantments {

    private EnsorcEnchantments() {

    }

    public static void register() {

        OVERRIDES.register(ID_PROTECTION, () -> new ProtectionEnchantmentImp(COMMON, ALL, ARMOR_SLOTS));
        OVERRIDES.register(ID_PROTECTION_BLAST, () -> new ProtectionEnchantmentImp(RARE, EXPLOSION, ARMOR_SLOTS));
        OVERRIDES.register(ID_PROTECTION_FALL, () -> new ProtectionEnchantmentImp(UNCOMMON, FALL, ARMOR_SLOTS));
        OVERRIDES.register(ID_PROTECTION_FIRE, () -> new ProtectionEnchantmentImp(UNCOMMON, FIRE, ARMOR_SLOTS));
        OVERRIDES.register(ID_PROTECTION_PROJECTILE, () -> new ProtectionEnchantmentImp(UNCOMMON, PROJECTILE, ARMOR_SLOTS));

        OVERRIDES.register(ID_FIRE_ASPECT, FireAspectEnchantmentImp::new);
        OVERRIDES.register(ID_FROST_WALKER, FrostWalkerEnchantmentImp::new);
        OVERRIDES.register(ID_KNOCKBACK, KnockbackEnchantmentImp::new);
        OVERRIDES.register(ID_LOOTING, LootingEnchantmentImp::new);
        OVERRIDES.register(ID_THORNS, ThornsEnchantmentImp::new);

        OVERRIDES.register(ID_MENDING, MendingEnchantmentAlt::new);

    }

    public static final RegistryObject<EnchantmentCoFH> PROTECTION_MAGIC = ENCHANTMENTS.register(ID_PROTECTION_MAGIC, () -> new ProtectionEnchantmentImp(UNCOMMON, MAGIC, ARMOR_SLOTS));
    public static final RegistryObject<EnchantmentCoFH> DISPLACEMENT = ENCHANTMENTS.register(ID_DISPLACEMENT, DisplacementEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> FIRE_REBUKE = ENCHANTMENTS.register(ID_FIRE_REBUKE, FireRebukeEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> FROST_REBUKE = ENCHANTMENTS.register(ID_FROST_REBUKE, FrostRebukeEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> AIR_AFFINITY = ENCHANTMENTS.register(ID_AIR_AFFINITY, AirAffinityEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> XP_BOOST = ENCHANTMENTS.register(ID_XP_BOOST, XpBoostEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> GOURMAND = ENCHANTMENTS.register(ID_GOURMAND, GourmandEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> REACH = ENCHANTMENTS.register(ID_REACH, ReachEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> VITALITY = ENCHANTMENTS.register(ID_VITALITY, VitalityEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> DAMAGE_ENDER = ENCHANTMENTS.register(ID_DAMAGE_ENDER, DamageEnderEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> DAMAGE_ILLAGER = ENCHANTMENTS.register(ID_DAMAGE_ILLAGER, DamageIllagerEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> DAMAGE_VILLAGER = ENCHANTMENTS.register(ID_DAMAGE_VILLAGER, DamageVillagerEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> CAVALIER = ENCHANTMENTS.register(ID_CAVALIER, CavalierEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> FROST_ASPECT = ENCHANTMENTS.register(ID_FROST_ASPECT, FrostAspectEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> INSTIGATING = ENCHANTMENTS.register(ID_INSTIGATING, InstigatingEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> LEECH = ENCHANTMENTS.register(ID_LEECH, LeechEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> MAGIC_EDGE = ENCHANTMENTS.register(ID_MAGIC_EDGE, MagicEdgeEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> VORPAL = ENCHANTMENTS.register(ID_VORPAL, VorpalEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> EXCAVATING = ENCHANTMENTS.register(ID_EXCAVATING, ExcavatingEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> HUNTER = ENCHANTMENTS.register(ID_HUNTER, HunterEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> QUICK_DRAW = ENCHANTMENTS.register(ID_QUICK_DRAW, QuickdrawEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> TRUESHOT = ENCHANTMENTS.register(ID_TRUESHOT, TrueshotEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> VOLLEY = ENCHANTMENTS.register(ID_VOLLEY, VolleyEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> ANGLER = ENCHANTMENTS.register(ID_ANGLER, AnglerEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> PILFERING = ENCHANTMENTS.register(ID_PILFERING, PilferingEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> BULWARK = ENCHANTMENTS.register(ID_BULWARK, BulwarkEnchantment::new);
    public static final RegistryObject<EnchantmentCoFH> PHALANX = ENCHANTMENTS.register(ID_PHALANX, PhalanxEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> SOULBOUND = ENCHANTMENTS.register(ID_SOULBOUND, SoulboundEnchantment::new);

    public static final RegistryObject<EnchantmentCoFH> CURSE_FOOL = ENCHANTMENTS.register(ID_CURSE_FOOL, CurseFoolEnchant::new);
    public static final RegistryObject<EnchantmentCoFH> CURSE_MERCY = ENCHANTMENTS.register(ID_CURSE_MERCY, CurseMercyEnchantment::new);

}
