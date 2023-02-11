package com.tonnybunny.domain.jtonny.entity;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;


@Entity
@DiscriminatorValue("0030001")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jtonny_history_table")
public class JTonnyHistoryEntity extends HistoryEntity {

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime totalTime;
	private Integer unitPrice;
	private String recordVideoPath;
	private String tonnySituCode = "0040001";       // 디폴트 "일상" 코드 넣기


	public void completeLive(LocalTime time, String recordVideoPath) {
		this.totalTime = time;
		this.recordVideoPath = recordVideoPath;
		super.completeLive();
	}

}
