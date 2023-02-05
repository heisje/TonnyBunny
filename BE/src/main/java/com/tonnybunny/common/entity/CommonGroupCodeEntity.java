package com.tonnybunny.common.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonGroupCodeEntity {

	@Id
	private String groupCode;

	private String groupCodeName;

	@OneToMany(mappedBy = "groupCode")
	private List<CommonCodeEntity> codeList;

}
