package com.tonnybunny.domain.review.dto;


import com.tonnybunny.domain.review.entity.ReviewEntity;
import lombok.Data;


@Data
public class ReviewRequestDto {

    public ReviewEntity toEntity() {
        return (ReviewEntity) new Object();
    }

}
