package io.github.derexxd.sift_backport.block;

import io.github.derexxd.sift_backport.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("sift");

    public static final DeferredBlock<Block> SCULK_GRASS_BLOCK = registerBlock("sculk_grass_block",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6F)
                    .sound(SoundType.SCULK));

    public static final DeferredBlock<Block> LIGHT_SCULK_GRASS_BLOCK = registerBlock("light_sculk_grass_block",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .strength(0.6F)
                    .sound(SoundType.SCULK));

    public static final DeferredBlock<Block> SCULK_GRASS = registerBlock("sculk_grass",
            SculkGrassBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .pushReaction(PushReaction.POPPED));

    public static final DeferredBlock<Block> TALL_SCULK_GRASS = registerDoublePlantBlock("tall_sculk_grass",
            TallSculkGrassBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.POPPED));

    private static DeferredBlock<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Identifier id = Identifier.parse("sift:" + name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        properties.setId(blockKey);
        DeferredBlock<Block> toReturn = BLOCKS.register(name, () -> factory.apply(properties));
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)));
        return toReturn;
    }

    private static DeferredBlock<Block> registerDoublePlantBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Identifier id = Identifier.parse("sift:" + name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        properties.setId(blockKey);
        DeferredBlock<Block> toReturn = BLOCKS.register(name, () -> factory.apply(properties));
        ModItems.ITEMS.register(name, () -> new DoubleHighBlockItem(toReturn.get(), new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)));
        return toReturn;
    }
}
