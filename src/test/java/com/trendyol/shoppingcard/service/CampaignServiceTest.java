package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CampaignRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BaseRepository baseRepository;

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void save_shouldBeError_TitleIsNull() {
        Campaign campaign = CommonModel.createCampaign();
        campaign.setTitle(null);

        expectedException.expect(Exception.class);
        campaignService.save(campaign);
    }

    @Test
    public void save_shouldBeError_CategoryIsNull() {
        Campaign campaign = CommonModel.createCampaign();
        campaign.setCategory(null);

        expectedException.expect(Exception.class);
        campaignService.save(campaign);
    }

    @Test
    public void save_shouldBeError_DiscountIsNull() {
        Campaign campaign = CommonModel.createCampaign();
        Category category = CommonModel.createCategory();
        campaign.setDiscount(null);

        expectedException.expect(Exception.class);

        doReturn(category).when(categoryService).get(anyString());
        campaignService.save(campaign);
    }

    @Test
    public void save_shouldBeError_QuantityIsNull() {
        Campaign campaign = CommonModel.createCampaign();
        Category category = CommonModel.createCategory();
        campaign.setQuantity(null);

        expectedException.expect(Exception.class);

        doReturn(category).when(categoryService).get(anyString());
        campaignService.save(campaign);
    }

    @Test
    public void save_shouldBeSuccess() {
        Campaign campaign = CommonModel.createCampaign();
        Category category = CommonModel.createCategory();

        doReturn(category).when(categoryService).get(anyString());
        doReturn(campaign).when(baseRepository).saveOrUpdate(any());
        Campaign saved = campaignService.save(campaign);
        Assert.assertEquals(campaign.getTitle(), saved.getTitle());
    }

    @Test
    public void get_shouldBeError_CampaignIdIsNull() {
        expectedException.expect(Exception.class);
        campaignService.get(null);
    }

    @Test
    public void get_shouldBeError_CampaignNotFound() {
        expectedException.expect(Exception.class);

        doReturn(null).when(baseRepository).get(anyString(), any());
        campaignService.get("id");
    }

    @Test
    public void get_shouldBeSuccess() {
        Campaign campaign = CommonModel.createCampaign();
        doReturn(campaign).when(baseRepository).get(anyString(), any());
        Campaign saved = campaignService.get(campaign.getId());
        Assert.assertEquals(campaign.getTitle(), saved.getTitle());
    }


    @Test
    public void listByCategory_shouldBeSuccess() {
        Campaign campaign = CommonModel.createCampaign();
        Category category = CommonModel.createCategory();
        campaign.setCategory(category);
        List<Campaign> campaignList = new ArrayList<>();
        campaignList.add(campaign);

        doReturn(campaignList).when(campaignRepository).findAllByCategory_Title(category.getTitle());
        List<Campaign> list = campaignService.listByCategory(category.getTitle());
        Assert.assertEquals(campaign.getTitle(), list.get(0).getTitle());
    }

    @Test
    public void listByCategory_shouldBeError_CategoryTitlesAreNull() {
        expectedException.expect(Exception.class);

        campaignService.listByCategory(null);

    }
}