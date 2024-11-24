package interface_adapter.portfolio;

import use_case.portfolio.PortfolioInputBoundary;

public class PortfolioController {

    private final PortfolioInputBoundary portfolioInteractor;

    public PortfolioController(PortfolioInputBoundary portfolioInteractor) {
        this.portfolioInteractor = portfolioInteractor;
    }

    public void getPortfolioList() {
        portfolioInteractor.getPortfolioListData();
    }
}
