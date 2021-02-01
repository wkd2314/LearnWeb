package ObserverPattern;

public class GrabStocks {
    public static void main(String[] args) {

        StockGrabber stockGrabber = new StockGrabber();

        StockObserver observer1 = new StockObserver(stockGrabber);

        stockGrabber.setAPPLPrice(1923.00); // notifies to observer1
        stockGrabber.setIBMPrice(3492.13);
        stockGrabber.setGOOGPrice(34.523);

        StockObserver observer2 = new StockObserver(stockGrabber);

        stockGrabber.setIBMPrice(1923.00); // notifies to observer1,2
        stockGrabber.setIBMPrice(3492.13);
        stockGrabber.setGOOGPrice(34.523);

        stockGrabber.unregister(observer1);

        stockGrabber.setIBMPrice(1923.00);
        stockGrabber.setIBMPrice(3492.13);
        stockGrabber.setGOOGPrice(34.523);

        // for multiThread Environment
        Runnable getIBM = new GetTheStock(stockGrabber, 2, "IBM", 1923.00);
        Runnable getAPPL = new GetTheStock(stockGrabber, 2, "APPL", 3492.13);
        Runnable getGOOG = new GetTheStock(stockGrabber, 2, "GOOG", 34.523);

        new Thread(getIBM).start();
        new Thread(getAPPL).start();
        new Thread(getGOOG).start();

    }
}
