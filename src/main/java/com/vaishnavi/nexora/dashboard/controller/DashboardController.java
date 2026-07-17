package com.vaishnavi.nexora.dashboard.controller;


import com.vaishnavi.nexora.dashboard.dto.DashboardResponse;
import com.vaishnavi.nexora.dashboard.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/dashboard")
public class DashboardController {



    @Autowired
    private DashboardService dashboardService;







    // ================= DASHBOARD SUMMARY =================


    @GetMapping("/summary")
    public DashboardResponse getDashboardSummary(

            Authentication authentication

    ){


        return dashboardService.getDashboardSummary(
                authentication.getName()
        );

    }


}