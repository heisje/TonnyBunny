package com.tonnybunny.domain.user.dto;

import com.tonnybunny.domain.user.entity.CertificateEntity;
import lombok.Data;

@Data
public class CertificateRequestDto {

    public CertificateEntity toEntity(){
        return (CertificateEntity) new Object();
    }
}
