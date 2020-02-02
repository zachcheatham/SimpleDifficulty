package com.charles445.simpledifficulty.config;


import com.charles445.simpledifficulty.SimpleDifficulty;
import com.charles445.simpledifficulty.api.config.ClientConfig;
import com.charles445.simpledifficulty.api.config.ClientOptions;
import com.charles445.simpledifficulty.api.config.ServerConfig;
import com.charles445.simpledifficulty.api.config.ServerOptions;
import com.charles445.simpledifficulty.network.MessageConfigLAN;
import com.charles445.simpledifficulty.network.MessageUpdateConfig;
import com.charles445.simpledifficulty.network.PacketHandler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = SimpleDifficulty.MODID)
public class ModConfig 
{
	@Config.Comment("Client configuration")
	@Config.Name("Client")
	public static final SDCFGClientConfig client = new SDCFGClientConfig();
	
	@Config.Comment("Server configuration")
	@Config.Name("Server")
	public static final SDCFGServerConfig server = new SDCFGServerConfig();
	
	//TODO Lang
	
	public static class SDCFGServerConfig
	{
		@Config.Comment("Miscellaneous gameplay configurations")
		@Config.Name("Miscellaneous")
		public final SDCFGMiscellaneous miscellaneous = new SDCFGMiscellaneous();
		
		@Config.Comment("Temperature related configurations")
		@Config.Name("Temperature")
		public final SDCFGTemperature temperature = new SDCFGTemperature();
		
		///
		/// Server Options
		///
		@Config.Comment("Whether thirst is enabled.")
		@Config.Name("ThirstEnabled")
		public boolean thirstEnabled = true;
		
		@Config.Comment("Whether the player is allowed to drink from normal water blocks.")
		@Config.Name("ThirstDrinkBlocks")
		public boolean thirstDrinkBlocks = true;
		
		@Config.Comment("Whether the player is allowed to drink from the rain.")
		@Config.Name("ThirstDrinkRain")
		public boolean thirstDrinkRain = true;
		
		@Config.Comment("Whether the mod should be dangerous on Peaceful difficulty.")
		@Config.Name("PeacefulDanger")
		public boolean peacefulDanger = false;
		
		@Config.Comment("Whether temperature is enabled.")
		@Config.Name("TemperatureEnabled")
		public boolean temperatureEnabled = true;
		
		@Config.Comment("Whether temperature tile entities are enabled.")
		@Config.Name("TemperatureTileEntities")
		public boolean temperatureTEEnabled = true;
		
		@Config.Comment("Spams chat with debug messages, do not enable this unless you are testing!")
		@Config.Name("DebugMode")
		public boolean debug = false;
		
		public static class SDCFGMiscellaneous
		{
			//Not synchronized with clients
			
			@Config.Comment("Whether Ice Blocks drop Ice Chunks")
			@Config.Name("IceDropsChunks")
			public boolean iceDropsChunks = true;
			
			@Config.Comment("Whether Magma Blocks drop Magma Chunks")
			@Config.Name("MagmaDropsChunks")
			public boolean magmaDropsChunks = true;
		}
		
		public static class SDCFGTemperature
		{
			//Not synchronized with clients
			//TODO it probably should be though, because of thermometers and such
			
			@Config.Comment("Serene Seasons Configurations")
			@Config.Name("Serene Seasons")
			public final SDCFGSereneSeasons sereneseasons = new SDCFGSereneSeasons();
			
			@Config.Comment("Altitude Temperature Multiplier - How strongly altitude affects temperature")
			@Config.Name("AltitudeMultiplier")
			@Config.RangeInt
			public int altitudeMultiplier = 3;
			
			@Config.Comment("Biome Temperature Multiplier - The maximum temperature change in any biome")
			@Config.Name("BiomeMultiplier")
			@Config.RangeInt
			public int biomeMultiplier = 10;
			
			@Config.Comment("Underground Effect - Whether being deep underground reduces some surface temperature effects")
			@Config.Name("UndergroundEffect")
			public boolean undergroundEffect = true;
			
			@Config.Comment("Underground Effect Cutoff - Y Level where surface temperature effects do nothing")
			@Config.Name("UndergroundEffectCutoff")
			@Config.RangeInt(min=0, max=64)
			public int undergroundEffectCutoff = 30;
			
			@Config.Comment("Time Temperature Multiplier - How strongly time affects temperature")
			@Config.Name("TimeMultiplier")
			@Config.RangeInt
			public int timeMultiplier = 3;
			
			@Config.Comment("Time Temperature Day - Whether time changes temperature during the day")
			@Config.Name("TimeTemperatureDay")
			public boolean timeTemperatureDay = true;
			
			@Config.Comment("Time Temperature Night - Whether time changes temperature during the night")
			@Config.Name("TimeTemperatureNight")
			public boolean timeTemperatureNight = true;
			
			@Config.Comment("Time Biome Temperature Multiplier - How strongly different biomes effect day/night temperature")
			@Config.Name("TimeBiomeMultiplier")
			@Config.RangeDouble(min=1.0,max=1000000.0)
			public float timeBiomeMultiplier = 1.25f;

			@Config.Comment("Snow Temperature Value - Effect of snowfall on temperature")
			@Config.Name("SnowValue")
			@Config.RangeInt
			public int snowValue = -10;
			
			@Config.Comment("Sprinting Temperature Value - Effect of sprinting on temperature")
			@Config.Name("SprintingValue")
			@Config.RangeInt
			public int sprintingValue = 3;
			
			@Config.Comment("Wet Temperature Value - Effect of being wet on temperature")
			@Config.Name("WetValue")
			@Config.RangeInt
			public int wetValue = -7;
			
			@Config.Comment("Temperature Max Speed - Maximum time in ticks for a player temperature change")
			@Config.Name("TemperatureTickMax")
			@Config.RangeInt(min=20)
			public int temperatureTickMax = 400;
			
			@Config.Comment("Temperature Min Speed - Minimum time in ticks for a player temperature change")
			@Config.Name("TemperatureTickMin")
			@Config.RangeInt(min=20)
			public int temperatureTickMin = 20;
			
			@Config.Comment("Enchantment Temperature Change - Effect of temperature enchantments")
			@Config.Name("EnchantmentTemperature")
			@Config.RangeInt
			public int enchantmentTemperature = 1;
			
			public static class SDCFGSereneSeasons
			{
				//Serene Seasons
				//Not synchronized with clients
				//TODO it should be
				
				@Config.Comment("Season Early Winter - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonEarlyWinter")
				@Config.RangeInt
				public int seasonEarlyWinter = -7;
				
				@Config.Comment("Season Mid Winter - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonMidWinter")
				@Config.RangeInt
				public int seasonMidWinter = -14;
				
				@Config.Comment("Season Late Winter - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonLateWinter")
				@Config.RangeInt
				public int seasonLateWinter = -7;
				
				@Config.Comment("Season Early Spring - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonEarlySpring")
				@Config.RangeInt
				public int seasonEarlySpring = -3;
				
				@Config.Comment("Season Mid Spring - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonMidSpring")
				@Config.RangeInt
				public int seasonMidSpring = 0;
				
				@Config.Comment("Season Late Spring - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonLateSpring")
				@Config.RangeInt
				public int seasonLateSpring = 2;
				
				@Config.Comment("Season Early Summer - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonEarlySummer")
				@Config.RangeInt
				public int seasonEarlySummer = 3;
				
				@Config.Comment("Season Mid Summer - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonMidSummer")
				@Config.RangeInt
				public int seasonMidSummer = 5;
				
				@Config.Comment("Season Late Summer - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonLateSummer")
				@Config.RangeInt
				public int seasonLateSummer = 3;
				
				@Config.Comment("Season Early Autumn - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonEarlyAutumn")
				@Config.RangeInt
				public int seasonEarlyAutumn = 2;
				
				@Config.Comment("Season Mid Autumn - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonMidAutumn")
				@Config.RangeInt
				public int seasonMidAutumn = 0;
				
				@Config.Comment("Season Late Autumn - Temperature change during the Serene Seasons season")
				@Config.Name("SeasonLateAutumn")
				@Config.RangeInt
				public int seasonLateAutumn = -3;
			}
		}
	}
	///
	/// Client Options
	///
	
	public static class SDCFGClientConfig
	{
		@Config.Comment("Whether the alternate temperature display is enabled")
		@Config.Name("AlternateTemperature")
		public boolean alternateTemp = true;
		
		@Config.Comment("Whether to draw thirst saturation on the HUD")
		@Config.Name("DrawThirstSaturation")
		public boolean drawThirstSaturation = true;
		
		@Config.Comment("Whether thermometers display the correct temperature. Only disable this if you are trying to determine what's lagging.")
		@Config.Name("EnableThermometer")
		public boolean enableThermometer = true;
		
		@Config.Comment("Whether thermometers in your inventory will display on your HUD")
		@Config.Name("HUDThermometer")
		public boolean hudThermometer = true;
		
		@Config.Comment("Client config debug")
		@Config.Name("ClientConfigDebug")
		public boolean clientdebug = false;
	}
	
	///
	/// Event Handler
	///
	
	@Mod.EventBusSubscriber(modid = SimpleDifficulty.MODID)
	private static class EventHandler
	{
		//Client Side
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID().equals(SimpleDifficulty.MODID))
			{
				ConfigManager.sync(SimpleDifficulty.MODID, Config.Type.INSTANCE);
				sendLocalClientConfigToAPI();
				
				//Make sure there's a world running
				if(event.isWorldRunning())
				{
					//Make new message
					MessageConfigLAN message = new MessageConfigLAN();
					
					//Send to server
					PacketHandler.instance.sendToServer(message);
				}
				else
				{
					//Title Screen
					sendLocalServerConfigToAPI();
				}
			}
		}
	}
	
	//TODO proper hash mapping so adding new config is easier, but it's not very important
	//It didn't work the first time I tried, that spurred on the creation of DebugVerify
	
	public static void sendLocalClientConfigToAPI()
	{
		//TODO if it's client side, it doesn't need to be synchronized
		//So it should just be referenced directly instead of using this hash map stuff?...
		
		//Place in Client Config
		
		ClientConfig.instance.put(ClientOptions.DRAW_THIRST_SATURATION, client.drawThirstSaturation);
		ClientConfig.instance.put(ClientOptions.ENABLE_THERMOMETER, client.enableThermometer);
		ClientConfig.instance.put(ClientOptions.ALTERNATE_TEMP, client.alternateTemp);
		ClientConfig.instance.put(ClientOptions.HUD_THERMOMETER, client.hudThermometer);
		ClientConfig.instance.put(ClientOptions.DEBUG, client.clientdebug);
		
	}
	
	public static void sendLocalServerConfigToAPI()
	{
		//Place in Server Config
		
		ServerConfig.instance.put(ServerOptions.DEBUG, server.debug);
		ServerConfig.instance.put(ServerOptions.THIRST_ENABLED, server.thirstEnabled);
		ServerConfig.instance.put(ServerOptions.THIRST_DRINK_BLOCKS, server.thirstDrinkBlocks);
		ServerConfig.instance.put(ServerOptions.THIRST_DRINK_RAIN, server.thirstDrinkRain);
		ServerConfig.instance.put(ServerOptions.PEACEFUL_DANGER, server.peacefulDanger);
		ServerConfig.instance.put(ServerOptions.TEMPERATURE_ENABLED, server.temperatureEnabled);
		ServerConfig.instance.put(ServerOptions.TEMPERATURE_TE_ENABLED, server.temperatureTEEnabled);
	}
	
	private static MessageUpdateConfig getNewConfigMessage()
	{
		NBTTagCompound compound = new NBTTagCompound();
		
		compound.setString(ServerOptions.DEBUG.getName(), ""+server.debug);
		compound.setString(ServerOptions.THIRST_ENABLED.getName(), ""+server.thirstEnabled);
		compound.setString(ServerOptions.THIRST_DRINK_BLOCKS.getName(), ""+server.thirstDrinkBlocks);
		compound.setString(ServerOptions.THIRST_DRINK_RAIN.getName(), ""+server.thirstDrinkRain);
		compound.setString(ServerOptions.PEACEFUL_DANGER.getName(), ""+server.peacefulDanger);
		compound.setString(ServerOptions.TEMPERATURE_ENABLED.getName(), ""+server.temperatureEnabled);
		compound.setString(ServerOptions.TEMPERATURE_TE_ENABLED.getName(), ""+server.temperatureTEEnabled);
		
		return new MessageUpdateConfig(compound);
	}
	
	public static void sendServerConfigToPlayer(EntityPlayerMP player)
	{
		SimpleDifficulty.logger.info("Sending Configuration to player: "+player.getName());
		PacketHandler.instance.sendTo(getNewConfigMessage(), player);
	}
	
	public static void sendServerConfigToAllPlayers()
	{
		SimpleDifficulty.logger.info("Sending Configuration to all players");
		PacketHandler.instance.sendToAll(getNewConfigMessage());
	}
}
