package io.github.derexxd.sift_backport.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SculkGrassBlock extends BushBlock {
    public static final MapCodec<SculkGrassBlock> CODEC = simpleCodec(SculkGrassBlock::new);

    public SculkGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<SculkGrassBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModBlocks.SCULK_GRASS_BLOCK) || state.is(ModBlocks.LIGHT_SCULK_GRASS_BLOCK) || super.mayPlaceOn(state, level, pos);
    }
}
