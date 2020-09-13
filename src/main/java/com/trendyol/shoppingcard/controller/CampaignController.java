package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/save")
    public ApiResponse<Campaign> save(@RequestBody Campaign campaign) {
        return ApiResponse.response(campaignService.save(campaign));
    }

    @GetMapping("/")
    public ApiResponse<Campaign> get(String campaignId) {
        return ApiResponse.response(campaignService.get(campaignId));
    }

    @GetMapping("/listByCategory")
    public ApiResponse<List<Campaign>> listByCategory(String campaignId) {
        return ApiResponse.response(campaignService.listByCategory(campaignId));
    }
}
