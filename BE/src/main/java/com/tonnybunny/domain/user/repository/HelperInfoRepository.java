package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HelperInfoRepository extends JpaRepository<HelperInfoEntity, Long> {

	Optional<HelperInfoEntity> findByUser(UserEntity user);

}
