package com.krazzzzymonkey.catalyst.module.modules.player;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;

public class AntiAFK extends Modules {

    public IntegerValue seconds;
    public BooleanValue shouldJump;
    public BooleanValue shouldRotate;
    public BooleanValue shouldMove;
    public BooleanValue autoReply;
    public DoubleValue delay;

    public AntiAFK() {
        super("AntiAFK", ModuleCategory.COMBAT, "Prevents you from getting kicked from a server for being AFK");
    }
}
