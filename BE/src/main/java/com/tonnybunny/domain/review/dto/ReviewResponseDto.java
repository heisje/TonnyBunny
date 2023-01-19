package com.tonnybunny.domain.review.dto;


import com.tonnybunny.domain.review.entity.ReviewEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ReviewResponseDto {

    public static ReviewResponseDto fromEntity(ReviewEntity reviewSeq) {
        return new ReviewResponseDto();
    }


    public static List<ReviewResponseDto> fromEntityList(List<ReviewEntity> reviewList) {
        List<ReviewResponseDto> result = new ArrayList<>();

        for (ReviewEntity review : reviewList) {
            ReviewResponseDto reviewResponseDto = fromEntity(review);
            result.add(reviewResponseDto);
        }

        return result;

    }

}
