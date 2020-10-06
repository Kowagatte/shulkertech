package ca.damocles.shulkertech.blocks.blockentity;

import ca.damocles.shulkertech.blocks.GenericShulkerBoxBlock;
import ca.damocles.shulkertech.utilities.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;

import java.util.List;

public class GenericShulkerBoxBlockEntity  extends LootableContainerBlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, Tickable {
    private DefaultedList<ItemStack> inventory;
    private int viewerCount;
    private GenericShulkerBoxBlockEntity.AnimationStage animationStage;
    private float animationProgress;
    private float prevAnimationProgress;

    public GenericShulkerBoxBlockEntity(){
        super(null);
    }

    public GenericShulkerBoxBlockEntity(BlockEntityType<?> entityType, int size) {
        super(entityType);
        inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
        this.animationStage = GenericShulkerBoxBlockEntity.AnimationStage.CLOSED;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        inventory = list;
    }

    @Override
    public void tick() {
        this.updateAnimation();
        if (this.animationStage == GenericShulkerBoxBlockEntity.AnimationStage.OPENING || this.animationStage == GenericShulkerBoxBlockEntity.AnimationStage.CLOSING) {
            this.pushEntities();
        }

    }

    public GenericShulkerBoxBlockEntity.AnimationStage getAnimationStage() {
        return this.animationStage;
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1) {
            this.viewerCount = data;
            if (data == 0) {
                this.animationStage = GenericShulkerBoxBlockEntity.AnimationStage.CLOSING;
                this.updateNeighborStates();
            }

            if (data == 1) {
                this.animationStage = GenericShulkerBoxBlockEntity.AnimationStage.OPENING;
                this.updateNeighborStates();
            }

            return true;
        } else {
            return super.onSyncedBlockEvent(type, data);
        }
    }

    protected void updateAnimation() {
        this.prevAnimationProgress = this.animationProgress;
        switch(this.animationStage) {
            case CLOSED:
                this.animationProgress = 0.0F;
                break;
            case OPENING:
                this.animationProgress += 0.1F;
                if (this.animationProgress >= 1.0F) {
                    this.pushEntities();
                    this.animationStage = GenericShulkerBoxBlockEntity.AnimationStage.OPENED;
                    this.animationProgress = 1.0F;
                    this.updateNeighborStates();
                }
                break;
            case CLOSING:
                this.animationProgress -= 0.1F;
                if (this.animationProgress <= 0.0F) {
                    this.animationStage = GenericShulkerBoxBlockEntity.AnimationStage.CLOSED;
                    this.animationProgress = 0.0F;
                    this.updateNeighborStates();
                }
                break;
            case OPENED:
                this.animationProgress = 1.0F;
        }

    }

    @Deprecated
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return null;
    }

    @Deprecated
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.shulkerBox");
    }

    private void updateNeighborStates() {
        this.getCachedState().updateNeighbors(this.getWorld(), this.getPos(), 3);
    }

    public void deserializeInventory(CompoundTag tag) {
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(tag) && tag.contains("Items", 9)) {
            Inventories.fromTag(tag, this.inventory);
        }

    }

    public CompoundTag serializeInventory(CompoundTag tag) {
        if (!this.serializeLootTable(tag)) {
            Inventories.toTag(tag, this.inventory, false);
        }

        return tag;
    }

    @Override
    public void onOpen(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.viewerCount < 0) {
                this.viewerCount = 0;
            }

            ++this.viewerCount;
            this.world.addSyncedBlockEvent(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);
            if (this.viewerCount == 1) {
                this.world.playSound((PlayerEntity)null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    public float getAnimationProgress(float f) {
        return MathHelper.lerp(f, this.prevAnimationProgress, this.animationProgress);
    }

    private void pushEntities() {
        BlockState blockState = this.world.getBlockState(this.getPos());
        if (blockState.getBlock() instanceof GenericShulkerBoxBlock) {
            Direction direction = (Direction)blockState.get(GenericShulkerBoxBlock.FACING);
            Box box = this.getCollisionBox(direction).offset(this.pos);
            List<Entity> list = this.world.getOtherEntities((Entity)null, box);
            if (!list.isEmpty()) {
                for(int i = 0; i < list.size(); ++i) {
                    Entity entity = (Entity)list.get(i);
                    if (entity.getPistonBehavior() != PistonBehavior.IGNORE) {
                        double d = 0.0D;
                        double e = 0.0D;
                        double f = 0.0D;
                        Box box2 = entity.getBoundingBox();
                        switch(direction.getAxis()) {
                            case X:
                                if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
                                    d = box.maxX - box2.minX;
                                } else {
                                    d = box2.maxX - box.minX;
                                }

                                d += 0.01D;
                                break;
                            case Y:
                                if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
                                    e = box.maxY - box2.minY;
                                } else {
                                    e = box2.maxY - box.minY;
                                }

                                e += 0.01D;
                                break;
                            case Z:
                                if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
                                    f = box.maxZ - box2.minZ;
                                } else {
                                    f = box2.maxZ - box.minZ;
                                }

                                f += 0.01D;
                        }

                        entity.move(MovementType.SHULKER_BOX, new Vec3d(d * (double)direction.getOffsetX(), e * (double)direction.getOffsetY(), f * (double)direction.getOffsetZ()));
                    }
                }

            }
        }
    }

    public Box getBoundingBox(BlockState state) {
        return this.getBoundingBox((Direction)state.get(GenericShulkerBoxBlock.FACING));
    }

    public Box getBoundingBox(Direction openDirection) {
        float f = this.getAnimationProgress(1.0F);
        return VoxelShapes.fullCube().getBoundingBox().stretch((double)(0.5F * f * (float)openDirection.getOffsetX()), (double)(0.5F * f * (float)openDirection.getOffsetY()), (double)(0.5F * f * (float)openDirection.getOffsetZ()));
    }

    private Box getCollisionBox(Direction facing) {
        Direction direction = facing.getOpposite();
        return this.getBoundingBox(facing).shrink((double)direction.getOffsetX(), (double)direction.getOffsetY(), (double)direction.getOffsetZ());
    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.viewerCount;
            this.world.addSyncedBlockEvent(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);
            if (this.viewerCount <= 0) {
                this.world.playSound((PlayerEntity)null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,inventory);
        return super.toTag(tag);
    }


    public enum AnimationStage {
        CLOSED,
        OPENING,
        OPENED,
        CLOSING;

        AnimationStage() {
        }
    }
}
