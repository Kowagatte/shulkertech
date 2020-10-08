package ca.damocles.shulkertech.client.screens;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DiamondShulkerBoxBlockScreen extends HandledScreen<ScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("shulkertech", "textures/gui/generic_45.png");

    public DiamondShulkerBoxBlockScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        super.backgroundWidth = 176;
        super.backgroundHeight = 202;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        //titleX = (this.backgroundWidth - textRenderer.getWidth(title)) / 2;
        super.playerInventoryTitleY += 36;
    }
}