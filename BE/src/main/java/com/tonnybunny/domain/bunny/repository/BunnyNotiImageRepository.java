package com.tonnybunny.domain.bunny.repository;


import com.tonnybunny.domain.bunny.entity.BunnyNotiImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BunnyNotiImageRepository extends JpaRepository<BunnyNotiImageEntity, Long> {

	BunnyNotiImageEntity save(BunnyNotiImageEntity bunnyNotiImage);

}
