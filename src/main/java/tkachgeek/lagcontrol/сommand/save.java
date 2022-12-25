package tkachgeek.lagcontrol.сommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tkachgeek.lagcontrol.config.Cfg;

import java.util.HashMap;
import java.util.List;

public class save implements CommandInterface {
  @Override
  public String getPermission() {
    return "*";
  }

  @Override
  public String getHelp() {
    return "saves the configs";
  }

  @Override
  public HashMap<Integer, List<String>> getCompleter() {
    return new HashMap<>();
  }

  @Override
  public boolean execute(CommandSender sender, Command command, String commandLabel, String[] args) {
    Cfg.save();
    return true;
  }
}
