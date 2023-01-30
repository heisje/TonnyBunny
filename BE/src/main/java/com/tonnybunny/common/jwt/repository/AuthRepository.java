package com.tonnybunny.common.jwt.repository;


import com.tonnybunny.common.jwt.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

	Optional<AuthEntity> findByUserSeq(Long userSeq);

}
