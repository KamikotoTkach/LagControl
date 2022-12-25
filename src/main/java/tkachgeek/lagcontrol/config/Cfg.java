package tkachgeek.lagcontrol.config;

import org.bukkit.plugin.java.JavaPlugin;

public class Cfg {

  public static MainCfg main;

  public static void load(JavaPlugin plugin) {
    main = new MainCfg("config", plugin);

  }

  public static void save() {
    main.save();
  }

}
