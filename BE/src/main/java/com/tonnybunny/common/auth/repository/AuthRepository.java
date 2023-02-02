package com.tonnybunny.common.auth.repository;


import com.tonnybunny.common.auth.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

	Optional<AuthEntity> findByUserSeq(Long userSeq);

}
