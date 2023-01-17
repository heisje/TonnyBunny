package com.tonnybunny.common;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CommonRepository {

	private final EntityManager em;


	public List<CommonCode> findCommonCode(CommonGroupCode commonGroupCode) {
		return em.createQuery("select c from CommonCode c where c.groupCode = :commonGroupCode",
				CommonCode.class)
			.getResultList();
	}

}
