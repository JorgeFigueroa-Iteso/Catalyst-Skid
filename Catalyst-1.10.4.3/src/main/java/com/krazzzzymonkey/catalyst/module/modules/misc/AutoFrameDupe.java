package com.krazzzzymonkey.catalyst.module.modules.misc;

import com.krazzzzymonkey.catalyst.events.ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import dev.tigr.simpleevents.listener.EventHandler;
import dev.tigr.simpleevents.listener.EventListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.util.EnumHand;

public class AutoFrameDupe extends Modules {

    public BooleanValue shulkeronly;
    public IntegerValue range;
    public IntegerValue turns;
    public IntegerValue ticks;

    public AutoFrameDupe(){
        super("AutoFrameDupe", ModuleCategory.MISC, "Does the Frame Dupe automatically");
        shulkeronly = new BooleanValue("Only Shulker",false,"Dupe only shulkers");
        range = new IntegerValue("Range",5,0,6,"Dupe only shulkers");
        turns = new IntegerValue("Turns",1,0,3,"Turn's per tick");
        ticks = new IntegerValue("Ticks",10,1,20,"Ammount of tick per server tick");

        this.addValue(shulkeronly, range, turns, ticks);
    }

    private int timeout_ticks = 0;

    @EventHandler
    public final EventListener<ClientTickEvent> onClientTick = new EventListener<>(e -> {
        if (mc.player != null && mc.world != null){

            if (shulkeronly.getValue()){
                int shulker_slot = getShulkerSlot();
                if (shulker_slot != -1) {
                    mc.player.inventory.currentItem = shulker_slot;
                } else {
                    this.toggle();
                    ChatUtils.message("[AutoFrameDupe] Shulker missing, disabling!");
                    return;
                }
            }

            for (Entity frame : mc.world.loadedEntityList) {
                if (frame instanceof EntityItemFrame) {
                    if (mc.player.getDistance(frame) <= range.getValue()+1) {
                        if (timeout_ticks >= ticks.getValue()+1) {
                            if (((EntityItemFrame) frame).getDisplayedItem().getItem() == Items.AIR && !mc.player.getHeldItemMainhand().isEmpty) {
                                mc.playerController.interactWithEntity(mc.player, frame, EnumHand.MAIN_HAND);
                            }
                            if (((EntityItemFrame) frame).getDisplayedItem().getItem() != Items.AIR) {
                                for (int i = 0; i < turns.getValue()+1; i++) {
                                    mc.playerController.interactWithEntity(mc.player, frame, EnumHand.MAIN_HAND);
                                }
                                mc.playerController.attackEntity(mc.player, frame);
                                timeout_ticks = 0;
                            }
                        }
                        ++timeout_ticks;
                    }
                }
            }

        }
    });


    private int getShulkerSlot() {
        int shulker_slot = -1;
        for (int i = 0; i < 9; i++) {
            Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if (item instanceof ItemShulkerBox) shulker_slot = i;
        }
        return shulker_slot;
    }
}
