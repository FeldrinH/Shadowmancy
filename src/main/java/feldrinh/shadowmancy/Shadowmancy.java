package feldrinh.shadowmancy;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import feldrinh.shadowmancy.ServerProxy;
import feldrinh.shadowmancy.api.RefluxRecipeBasic;
import feldrinh.shadowmancy.api.RefluxRecipeHandler;
import feldrinh.shadowmancy.blocks.RefluxTable;
import feldrinh.shadowmancy.blocks.RefluxTableEntity;
import feldrinh.shadowmancy.items.LifePoolPendant;
import feldrinh.shadowmancy.items.ShadowlurkerArmor;
import feldrinh.shadowmancy.utility.LogHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid="shadowmancy", name="Shadowmancy", version="0.3.0", dependencies="required-after:Baubles")

public class Shadowmancy
{
    @Mod.Instance("shadowmancy")
    public static Shadowmancy instance;

    @SidedProxy(clientSide="feldrinh.shadowmancy.ClientProxy",serverSide="feldrinh.shadowmancy.ServerProxy")
    public static CommonProxy proxy;
    
    private static LifePoolPendant LifePoolPendant;
    
    public static final ArmorMaterial shadowCloth = EnumHelper.addArmorMaterial("shadowCloth", 5, new int[] {1,3,2,1}, 0);
    //public static final ArmorMaterial shadeMail = ;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//Register Items
    	GameRegistry.registerItem(LifePoolPendant = new LifePoolPendant(), "lifePoolPendant");
    	GameRegistry.registerItem(new ShadowlurkerArmor(0).setUnlocalizedName("shadowlurkerHead"), "shadowlurkerHead");
    	GameRegistry.registerItem(new ShadowlurkerArmor(1).setUnlocalizedName("shadowlurkerBody"), "shadowlurkerBody");
    	GameRegistry.registerItem(new ShadowlurkerArmor(2).setUnlocalizedName("shadowlurkerLegs"), "shadowlurkerLegs");
    	GameRegistry.registerItem(new ShadowlurkerArmor(3).setUnlocalizedName("shadowlurkerFeet"), "shadowlurkerFeet");
    	
    	//Register Blocks
    	GameRegistry.registerBlock(new RefluxTable(), "shadowRefluxTable");
    	GameRegistry.registerTileEntity(RefluxTableEntity.class, "shadowRefluxTableEntity");
    	
    	//Register Reflux Recipes
    	RefluxRecipeHandler.addRecipe(new RefluxRecipeBasic(Items.iron_ingot, new ItemStack(Items.blaze_rod), 0.01F, 0.0F));
    	
    	LogHelper.log(Level.INFO, "Shadowmancy preInit completed. Still waiting...");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	LogHelper.log(Level.INFO, "Shadowmancy init completed. We're not there yet...");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(new FtdTickHandler());
        LogHelper.log(Level.INFO, "Fear for the Darkness rises...");
        
        ShadowEventHandler poolEventHandler = new ShadowEventHandler(LifePoolPendant);
        FMLCommonHandler.instance().bus().register(poolEventHandler);
        MinecraftForge.EVENT_BUS.register(poolEventHandler);
        
        proxy.postInit();
        
        LogHelper.log(Level.INFO, "Shadowmancy postInit completed. Now we can begin.");
    }
}