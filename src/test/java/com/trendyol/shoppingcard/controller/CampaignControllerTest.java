package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.exception.BaseException;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.service.CampaignService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

public class CampaignControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CampaignService campaignService;

    private CampaignController campaignController;

    @Before
    public void setUp() {
        initMocks(this);
        campaignController = new CampaignController(campaignService);
    }

    @Test
    public void save() {
        Campaign campaign = new Campaign();
        campaign.setTitle("title");

        doReturn(campaign).when(campaignService).save(any());

        ApiResponse<Campaign> response = campaignController.save(campaign);

        assertEquals(campaign.getTitle(), response.getData().getTitle());
    }

    @Test
    public void save_shouldBeException() {
        Campaign campaign = new Campaign();
        campaign.setTitle("title");

        doThrow(BaseException.class).when(campaignService).save(any());

        expectedException.expect(BaseException.class);

        campaignController.save(campaign);
    }

    @Test
    public void list() {
        Campaign campaign = new Campaign();
        campaign.setTitle("title");

        List<Campaign> list = new ArrayList<>();
        list.add(campaign);
        doReturn(list).when(campaignService).listByCategory(campaign.getId());

        ApiResponse<List<Campaign>> response = campaignController.listByCategory(campaign.getId());

        assertEquals(campaign.getTitle(), response.getData().get(0).getTitle());
    }

    @Test
    public void list_shouldBeException() {
        Campaign campaign = new Campaign();
        campaign.setTitle("title");
        doThrow(BaseException.class).when(campaignService).listByCategory(campaign.getId());

        expectedException.expect(BaseException.class);

        campaignController.listByCategory(campaign.getId());
    }

    @Test
    public void get() {
        Campaign campaign = new Campaign();
        campaign.setTitle("title");
        campaign.setId("1");
        doReturn(campaign).when(campaignService).get(anyString());

        ApiResponse<Campaign> response = campaignController.get(campaign.getId());

        assertEquals(campaign.getId(), response.getData().getId());
    }

    @Test
    public void get_shouldBeException() {
        doThrow(BaseException.class).when(campaignService).get(anyString());

        expectedException.expect(BaseException.class);

        campaignController.get("1");
    }
}
