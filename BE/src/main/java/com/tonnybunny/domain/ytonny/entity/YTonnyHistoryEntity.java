package com.tonnybunny.domain.ytonny.entity;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalTime;


@Entity
@DiscriminatorValue("y_tonny")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class YTonnyHistoryEntity extends HistoryEntity {

	private String title;
	private LocalTime totalTime;

	private Integer unitPrice;
	private String recordVideoPath;

	@Builder.Default
	private String tonnySituCode = "0040001";       // 디폴트 "일상" 코드 넣기

}