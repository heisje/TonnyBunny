package com.tonnybunny.domain.bunny.entity;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("0030003")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BunnyHistoryEntity extends HistoryEntity {

	private String title;

	private Integer totalPrice;

}
