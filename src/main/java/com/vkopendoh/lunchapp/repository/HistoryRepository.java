package com.vkopendoh.lunchapp.repository;

import com.vkopendoh.lunchapp.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface HistoryRepository extends JpaRepository<History, Integer> {

    public List<History> findAllByRestaurantId(int id);
}
