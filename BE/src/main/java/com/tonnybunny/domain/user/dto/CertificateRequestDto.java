package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.CertificateEntity;
import lombok.Data;


/**
 * langCode     : 언어공통코드
 * certName     : 자격증 이름
 * content        : 자격증 내용
 */
@Data
public class CertificateRequestDto {

	private String langCode;
	private String certName;
	private String content;


	public CertificateEntity toEntity() {
		return (CertificateEntity) new Object();
	}

}
