package use_case.buy;

import entity.CommonSimulatedHoldingFactory;
import entity.SimulatedHolding;
import entity.SimulatedHoldingFactory;
import org.junit.jupiter.api.Test;
import use_case.portfolio.PortfolioDataAccessInterface;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BuyInteractorTest {

    @Test
    void testBuyInputData() {
        // Test the BuyInputData class
        BuyInputData inputData = new BuyInputData("AAPL", 150.0, 10.0);
        assertEquals("AAPL", inputData.getSymbol());
        assertEquals(150.0, inputData.getPrice());
        assertEquals(10.0, inputData.getQuantity());
    }

    @Test
    void testBuyOutputData() {
        // Test the BuyOutputData class
        SimulatedHoldingFactory holdingFactory = new CommonSimulatedHoldingFactory();
        SimulatedHolding simulatedHolding = holdingFactory.create("AAPL", 150.0, 10.0);
        BuyOutputData outputData = new BuyOutputData(simulatedHolding);
        assertEquals(simulatedHolding, outputData.getSimulatedHolding());
    }

    @Test
    void testExecute() {
        // Prepare input data
        BuyInputData inputData = new BuyInputData("AAPL", 150.0, 10.0);

        // Use in-memory portfolio data access object
        InMemoryPortfolioDataAccess portfolioDataAccessObject = new InMemoryPortfolioDataAccess();

        // Create a test presenter
        TestBuyOutputBoundary buyPresenter = new TestBuyOutputBoundary();

        // Create Interactor instance
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenter, portfolioDataAccessObject);

        // Execute test
        buyInteractor.execute(inputData);

        // Verify that the SimulatedHolding was added to the portfolio
        assertEquals(1, portfolioDataAccessObject.getPortfolioList().size());
        SimulatedHolding holding = portfolioDataAccessObject.getPortfolioList().get(0);
        assertEquals("AAPL", holding.getSymbol());
        assertEquals(150.0, holding.getPurchasePrice());
        assertEquals(10.0, holding.getPurchaseAmount());

        // Verify that the presenter was called correctly
        assertTrue(buyPresenter.successViewCalled);
        assertEquals(holding, buyPresenter.outputData.getSimulatedHolding());
    }

    @Test
    void testSwitchToHomeView() {
        // Use in-memory portfolio data access object
        InMemoryPortfolioDataAccess portfolioDataAccessObject = new InMemoryPortfolioDataAccess();

        // Create a test presenter
        TestBuyOutputBoundary buyPresenter = new TestBuyOutputBoundary();

        // Create Interactor instance
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenter, portfolioDataAccessObject);

        // Execute test
        buyInteractor.switchToHomeView();

        // Verify that switchToHomeView was called
        assertTrue(buyPresenter.switchToHomeViewCalled);
    }

    @Test
    void testSwitchToStockView() {
        // Use in-memory portfolio data access object
        InMemoryPortfolioDataAccess portfolioDataAccessObject = new InMemoryPortfolioDataAccess();

        // Create a test presenter
        TestBuyOutputBoundary buyPresenter = new TestBuyOutputBoundary();

        // Create Interactor instance
        BuyInteractor buyInteractor = new BuyInteractor(buyPresenter, portfolioDataAccessObject);

        // Execute test
        buyInteractor.switchToStockView();

        // Verify that switchToStockView was called
        assertTrue(buyPresenter.switchToStockViewCalled);
    }

    // In-memory portfolio data access object implementation
    static class InMemoryPortfolioDataAccess implements PortfolioDataAccessInterface {
        private final ArrayList<SimulatedHolding> portfolioList = new ArrayList<>();

        @Override
        public ArrayList<SimulatedHolding> getPortfolioList() {
            return portfolioList;
        }

        @Override
        public void savePortfolioList() {
            // Not needed for the test
        }

        @Override
        public void addToPortfolioList(SimulatedHolding simulatedHolding) {
            portfolioList.add(simulatedHolding);
        }

        @Override
        public void removeFromPortfolioList(SimulatedHolding simulatedHolding) {
            portfolioList.remove(simulatedHolding);
        }

        @Override
        public void createPortfolioList() {
            // Not needed for the test
        }
    }

    // Test presenter class
    static class TestBuyOutputBoundary implements BuyOutputBoundary {
        boolean successViewCalled = false;
        boolean switchToHomeViewCalled = false;
        boolean switchToStockViewCalled = false;
        BuyOutputData outputData;

        @Override
        public void prepareSuccessView(BuyOutputData outputData) {
            this.successViewCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void switchToHomeView() {
            this.switchToHomeViewCalled = true;
        }

        @Override
        public void switchToStockView() {
            this.switchToStockViewCalled = true;
        }
    }
}
