package com.tonnybunny.domain.bunny.repository;


import com.tonnybunny.domain.bunny.entity.BunnyImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BunnyImageRepository extends JpaRepository<BunnyImageEntity, Long> {

	BunnyImageEntity save(BunnyImageEntity bunnyNotiImage);

}
