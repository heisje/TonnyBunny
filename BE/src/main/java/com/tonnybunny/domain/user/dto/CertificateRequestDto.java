package com.tonnybunny.domain.user.dto;


import com.tonnybunny.domain.user.entity.CertificateEntity;
import lombok.Data;


/**
 * langCode     : 언어공통코드
 * certName     : 자격증 이름
 * score        : 자격증 점수
 */
@Data
public class CertificateRequestDto {

    private String langCode;
    private String certName;
    private String score;


    public CertificateEntity toEntity() {
        return (CertificateEntity) new Object();
    }

}
