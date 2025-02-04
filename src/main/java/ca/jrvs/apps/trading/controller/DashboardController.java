package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.View.PortfolioView;
import ca.jrvs.apps.trading.model.View.TraderAccountView;
import ca.jrvs.apps.trading.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private DashboardService dashboardService;
    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService=dashboardService;
    }

    @GetMapping(path = "/portfolio/traderId/{traderId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PortfolioView> getPortfolio(@PathVariable Integer traderId) {
        try {
            return dashboardService.getProfileViewByTraderId(traderId);
        }catch (Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }
    @GetMapping(path = "profile/traderId/{traderId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TraderAccountView getProfile(@PathVariable Integer traderId) {
        try {
            return dashboardService.getTraderAccount(traderId);
        }catch (Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }


}
