package com.trendyol.shoppingcard.repository;

import com.trendyol.shoppingcard.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String> {

    List<Campaign> findAllByCategory_Title(String categoryTitle);
}
