package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.BlockEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BlockResponseDto {

	private Long seq;
	private Long blockedUserSeq;


	public static BlockResponseDto fromEntity(BlockEntity block) {

		return new BlockResponseDto();
	}


	public static List<BlockResponseDto> fromEntityList(List<BlockEntity> blockList) {
		List<BlockResponseDto> result = new ArrayList<>();

		for (BlockEntity block : blockList) {
			BlockResponseDto blockResponseDto = fromEntity(block);
			result.add(blockResponseDto);
		}

		return result;

	}

}
