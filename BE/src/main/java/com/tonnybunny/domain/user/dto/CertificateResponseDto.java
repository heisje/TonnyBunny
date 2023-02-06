package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.CertificateEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * langCode     : 언어공통코드
 * certName     : 자격증 이름
 * score        : 자격증 점수
 */
@Data
@Builder
public class CertificateResponseDto {

	private Long seq;
	private String langCode;
	private String certName;
	private String content;


	public static CertificateResponseDto fromEntity(CertificateEntity certificate) {
		return CertificateResponseDto.builder()
		                             .langCode(certificate.getLangCode())
		                             .certName(certificate.getCertName())
		                             .content(certificate.getContent())
		                             .build();

	}


	public static List<CertificateResponseDto> fromEntityList(
		List<CertificateEntity> certificateList) {
		List<CertificateResponseDto> result = new ArrayList<>();
		for (CertificateEntity certificate : certificateList) {
			CertificateResponseDto certificateResponseDto = fromEntity(certificate);
			result.add(certificateResponseDto);
		}
		return result;
	}

}
