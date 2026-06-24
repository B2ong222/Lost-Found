package com.dgsw.lostfound.repository;

import com.dgsw.lostfound.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}