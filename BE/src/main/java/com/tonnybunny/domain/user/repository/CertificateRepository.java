package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {

}
