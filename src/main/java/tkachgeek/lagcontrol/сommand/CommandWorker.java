package tkachgeek.lagcontrol.сommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandWorker implements CommandExecutor {

  HashMap<String, CommandInterface> commands = new HashMap<>();
  List<String> fancyCommands = new ArrayList<>();
  CommandInterface command;
  boolean hasSubCommands;
  public HashMap<String, Runnable> completer = new HashMap<>();

  public CommandWorker(CommandInterface c, boolean hasSubCommands) {
    command = c;
    this.hasSubCommands = hasSubCommands;
  }
  public void addCommand(String name, CommandInterface obj) {
    commands.put(name.toLowerCase(), obj);
    fancyCommands.add(name);
    completer.put(name, obj::getCompleter);
  }
  public List<String> getCommands() {
    return fancyCommands;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    for (int i = 0; i<(args.length>0?1:0); i++){
      args[i] = args[i].toLowerCase();
    }
    boolean needHelp = false;
    if (args.length >= 1) {
      if (args[0].equalsIgnoreCase("help")) needHelp = true;
    }
    if(args.length>0 && commands.containsKey(args[0])) {
      if(!sender.hasPermission(commands.get(args[0]).getPermission()) && !sender.isOp()) {
        sender.sendMessage("Нет полномочий");
        return true;
      }
      if(needHelp || !commands.get(args[0]).execute(sender, command, label, args)) {
        sender.sendMessage(commands.get(args[0]).getHelp());
        return true;
      }
    } else {
      if(!sender.hasPermission(this.command.getPermission()) && !sender.isOp()) {
        sender.sendMessage("Нет полномочий");
        return true;
      }
      if(needHelp || !this.command.execute(sender, command, label, args)) {
        sender.sendMessage(this.command.getHelp());
        return true;
      }
    }
    return true;
  }

}