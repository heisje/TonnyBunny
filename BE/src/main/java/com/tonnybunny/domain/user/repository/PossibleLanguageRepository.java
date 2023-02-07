package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.HelperInfoEntity;
import com.tonnybunny.domain.user.entity.PossibleLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PossibleLanguageRepository extends JpaRepository<PossibleLanguageEntity, Long> {

	Optional<PossibleLanguageEntity> deleteAllByHelperInfo(HelperInfoEntity helperInfo);

}
