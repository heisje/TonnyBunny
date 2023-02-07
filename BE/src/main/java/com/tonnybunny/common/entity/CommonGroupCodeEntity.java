package com.tonnybunny.common.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "common_group_code_table")
public class CommonGroupCodeEntity {

	@Id
	private String groupCode;

	private String groupCodeName;

	@OneToMany(mappedBy = "groupCode")
	private List<CommonCodeEntity> codeList;

}
