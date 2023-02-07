package com.tonnybunny.domain.jtonny.entity;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;


@Entity
@DiscriminatorValue("j_tonny")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jtonny_history_table")
public class JTonnyHistoryEntity extends HistoryEntity {

	private LocalTime totalTime;
	private Integer unitPrice;
	private String recordVideoPath;
	private String tonnySituCode = "0040001";       // 디폴트 "일상" 코드 넣기

}
