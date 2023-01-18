package com.tonnybunny.common;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonService {

	private final CommonRepository commonRepository;


	public List<CommonCode> getCommonCode(CommonGroupCode commonGroupCode) {
		return commonRepository.findCommonCode(commonGroupCode);
	}

}
