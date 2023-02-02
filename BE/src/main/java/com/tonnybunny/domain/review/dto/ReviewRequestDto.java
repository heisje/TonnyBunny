package com.tonnybunny.domain.review.dto;


import com.tonnybunny.domain.review.entity.ReviewEntity;
import lombok.Data;


@Data
public class ReviewRequestDto {

    Long seq;
    

    public ReviewEntity toEntity() {
        return (ReviewEntity) new Object();
    }

}
