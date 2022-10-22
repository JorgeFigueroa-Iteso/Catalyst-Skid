package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.events.RenderGameOverlayEvent;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import dev.tigr.simpleevents.listener.EventHandler;
import dev.tigr.simpleevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Calendar;

//TODO CUSTOM STRING

public class Watermark extends Modules {
    private int color;
    private BooleanValue rainbow;
    private BooleanValue smiley;
    private ColorValue colorValue;
    private Number xOffset;
    private Number yOffset;
    private BooleanValue clientName;

    public Watermark() {
        super("Watermark", ModuleCategory.HUD, "Displays Watermark on hud", true);

        this.clientName = new BooleanValue("CustomName", false, "Shows your name instead");
        this.smiley = new BooleanValue("SmileyFace", false, "Adds a smiley face to the end :^)");
        this.colorValue = new ColorValue("Color", Color.CYAN, "The color of the greeter");
        this.rainbow = new BooleanValue("Rainbow", true, "Makes the greeter cycle through colors");
        this.xOffset = new Number("X Offset", 0.0);
        this.yOffset = new Number("Y Offset", 0.0);
        this.addValue(/*clientName, */smiley, colorValue, rainbow, xOffset, yOffset);
    }

    String time;
    int finalMouseX = 0, finalMouseY = 0;
    boolean isDragging = false;
    boolean isAlreadyDragging = false;

    @EventHandler
    private final EventListener<RenderGameOverlayEvent.Text> onRenderGameOverlay = new EventListener<>(e -> {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null)
            return;

        if (!rainbow.getValue()) {
            color = colorValue.getColor().getRGB();
        } else {
            color = ColorUtils.rainbow().getRGB();
        }
        int x = xOffset.getValue().intValue();
        int y = yOffset.getValue().intValue();
        String str = Minecraft.getMinecraft().player.getName() + " " + Main.VERSION;
        if (!clientName.getValue()) {
            str = Main.NAME + " " + Main.VERSION;
        }
        if (smiley.getValue()) {
            str = str.concat(" :^)");
        }

        GL11.glPushMatrix();


        if (ModuleManager.getModule("CustomFont").isToggled()) {
            Main.fontRenderer.drawStringWithShadow(str, x, y, color);
        } else {
            Main.fontRenderer.drawStringWithShadow(str, x, y, color);
        }

        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            if (ModuleManager.getModule("CustomFont").isToggled()) {
                RenderUtils.drawRect(x, y, x + Main.fontRenderer.getStringWidth(str), y + 14,
                    ColorUtils.color(0, 0, 0, 100));
            } else RenderUtils.drawRect(x, y, x + Wrapper.INSTANCE.fontRenderer().getStringWidth(str), y + 14,
                ColorUtils.color(0, 0, 0, 100));
            if (MouseUtils.isLeftClicked() && !(MouseUtils.isMouseOver(x, x + Main.fontRenderer.getStringWidth(str), y, y + 14))) {
                isAlreadyDragging = true;
            }

            if (!MouseUtils.isLeftClicked() && isAlreadyDragging) {
                isAlreadyDragging = false;
            }

            if (!isAlreadyDragging || isDragging) {
                if (MouseUtils.isMouseOver(x, x + Main.fontRenderer.getStringWidth(str), y, y + 14)) {
                    isDragging = true;
                }


                if (MouseUtils.isLeftClicked() && isDragging) {
                    finalMouseX = MouseUtils.getMouseX();
                    finalMouseY = MouseUtils.getMouseY();

                    xOffset.value = (double)finalMouseX - Main.fontRenderer.getStringWidth(str) / 2;
                    yOffset.value = (double)finalMouseY;
                    MouseUtils.isDragging = true;
                } else isDragging = false;

            }
        }
        GL11.glPopMatrix();
    });
}
