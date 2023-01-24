package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.CertificateEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CertificateResponseDto {

	public static CertificateResponseDto fromEntity(CertificateEntity certificate) {
		return new CertificateResponseDto();
	}


	public static List<CertificateResponseDto> fromEntityList(List<CertificateEntity> certificateList) {
		List<CertificateResponseDto> result = new ArrayList<>();
		for (CertificateEntity certificate : certificateList) {
			CertificateResponseDto certificateResponseDto = fromEntity(certificate);
			result.add(certificateResponseDto);
		}
		return result;
	}

}
