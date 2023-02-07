package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import com.tonnybunny.domain.user.entity.HelperInfoImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HelperInfoImageRepository extends JpaRepository<HelperInfoImageEntity, Long> {

	Optional<HelperInfoImageEntity> deleteAllByHelperInfo(HelperInfoEntity helperInfo);

}
