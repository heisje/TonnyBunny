package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import com.tonnybunny.domain.user.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

	List<HistoryEntity> findByClientOrHelper(UserEntity client, UserEntity helper, Sort sort);

	List<HistoryEntity> findByClient(UserEntity client, Sort sort);

	List<HistoryEntity> findByHelper(UserEntity helper, Sort sort);

	List<HistoryEntity> findByTaskCode(String taskCode, Sort sort);

	List<HistoryEntity> findByStartLangCodeOrEndLangCode(String startLangCode, String endLangCode, Sort sort);

	Optional<HistoryEntity> findById(Long historySeq);

}
