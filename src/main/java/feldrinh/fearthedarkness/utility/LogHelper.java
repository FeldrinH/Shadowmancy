package feldrinh.fearthedarkness.utility;

import org.apache.logging.log4j.Level;
import cpw.mods.fml.common.FMLLog;

public class LogHelper 
{
	public static void log(Level logLevel, Object object)
	{
		FMLLog.log("Fear The Darkness", logLevel,object.toString());
	}

}
