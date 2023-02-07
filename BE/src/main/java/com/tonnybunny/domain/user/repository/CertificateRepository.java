package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.CertificateEntity;
import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {

	Optional<CertificateEntity> deleteAllByHelperInfo(HelperInfoEntity helperInfo);

}
