package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CampaignServiceIT extends BaseIntegration {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void save() {
        Campaign campaign = CommonModel.createCampaign();
        Category savedCategory = categoryService.save(campaign.getCategory());
        campaign.setCategory(savedCategory);

        Campaign savedCampaign = campaignService.save(campaign);
        assertNotNull(savedCampaign.getId());
        assertNotNull(savedCampaign.getTitle());
    }

    @Test
    public void get() {
        Campaign campaign = CommonModel.createCampaign();
        Category savedCategory = categoryService.save(campaign.getCategory());
        campaign.setCategory(savedCategory);
        Campaign savedCampaign = campaignService.save(campaign);

        Campaign result = campaignService.get(savedCampaign.getId());

        assertEquals(savedCampaign.getId(), result.getId());
        assertEquals(savedCampaign.getTitle(), result.getTitle());
    }
}
