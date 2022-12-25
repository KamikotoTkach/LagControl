package tkachgeek.lagcontrol.сommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

public interface CommandInterface {
  default String getPermission() {
    return "*";
  }
  default String getHelp() {
    return "There is no help";
  }
  default HashMap<Integer, List<String>> getCompleter() {
    return new HashMap<>();
  }
  boolean execute(CommandSender sender, Command command, String commandLabel, String[] args);
  default boolean executeConsole(CommandSender sender, Command command, String commandLabel, String[] args) {
    return execute(sender, command, commandLabel, args);
  }
}
