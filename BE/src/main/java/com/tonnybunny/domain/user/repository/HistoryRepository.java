package com.tonnybunny.domain.user.repository;


import com.tonnybunny.domain.user.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

	List<HistoryEntity> findAllByClientOrHelper(Long clientSeq, Long helperSeq);

	Optional<HistoryEntity> findBySeq(Long historySeq);

}
