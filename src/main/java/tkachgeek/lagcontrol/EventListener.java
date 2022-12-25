package tkachgeek.lagcontrol;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class EventListener implements Listener {

  @EventHandler
  void onRedstoneMove(BlockRedstoneEvent event) {
    ChunkDataWorker.addRedstoneTick(event.getBlock().getChunk());
  }

  @EventHandler
  void onPistonMove(BlockPistonExtendEvent event) {
    ChunkDataWorker.addPistonMove(event.getBlock().getChunk());
  }
  @EventHandler
  void onPistonMoveUp(BlockPistonRetractEvent event) {
    ChunkDataWorker.addPistonMove(event.getBlock().getChunk());
  }

  @EventHandler
  void onFallingBlock(BlockPhysicsEvent event) {
    ChunkDataWorker.addFallingBlock(event.getBlock().getChunk());
  }

  @EventHandler
  void onHopperTick(InventoryMoveItemEvent event) {
    ChunkDataWorker.addItemMove(event.getSource().getLocation().getChunk());
  }

  @EventHandler
  void onEntitySpawn(EntitySpawnEvent event) {
    ChunkDataWorker.addSpawnedEntity(event.getLocation().getChunk());
  }

  @EventHandler
  void onDropperTick(BlockDispenseEvent event) {
    ChunkDataWorker.addDispencerTick(event.getBlock().getChunk());
  }

  @EventHandler
  void onBlockChange(BlockFromToEvent event) {
    ChunkDataWorker.addBlockChange(event.getBlock().getChunk());
  }
}
