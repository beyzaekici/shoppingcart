package com.trendyol.shoppingcard.service;


import com.trendyol.shoppingcard.exception.ExceptionFactory;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CampaignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CampaignService {

    private CampaignRepository campaignRepository;

    private BaseRepository baseRepository;

    private CategoryService categoryService;


    public CampaignService(CampaignRepository campaignRepository, CategoryService categoryService, BaseRepository baseRepository) {
        this.campaignRepository = campaignRepository;
        this.categoryService = categoryService;
        this.baseRepository = baseRepository;
    }

    @Transactional
    public Campaign save(Campaign campaign) {
        if (StringUtils.isEmpty(campaign.getTitle())) {
            ExceptionFactory.throwException("Title is required.", HttpStatus.BAD_REQUEST);
        }
        if (campaign.getCategory() == null) {
            ExceptionFactory.throwException("Category is required.", HttpStatus.BAD_REQUEST);
        }

        Category category = categoryService.get(campaign.getCategory().getId());
        campaign.setCategory(category);
        if (campaign.getDiscount() == null) {
            ExceptionFactory.throwException("Discount is required.", HttpStatus.BAD_REQUEST);
        }
        if (campaign.getQuantity() == null) {
            ExceptionFactory.throwException("Quantity is required.", HttpStatus.BAD_REQUEST);
        }

        return baseRepository.saveOrUpdate(campaign);
    }

    public Campaign get(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionFactory.throwException("CampaignId is required.", HttpStatus.BAD_REQUEST);
        }
        Campaign campaign = baseRepository.get(id, Campaign.class);
        if (campaign == null) {
            ExceptionFactory.throwException("Campaign not found.", HttpStatus.NOT_FOUND);
        }
        return campaign;
    }

    public List<Campaign> listByCategory(String categoryTitle) {
        if (categoryTitle == null || categoryTitle.isEmpty()) {
            ExceptionFactory.throwException("CategoryTitle is required.", HttpStatus.BAD_REQUEST);
        }
        return campaignRepository.findAllByCategory_Title(categoryTitle);
    }

}
