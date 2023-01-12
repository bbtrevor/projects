package com.trevor.bot;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
/**
 * A bot implementation for BestBuy. Creates and runs a new thread for each product for this 
 * site and tracks them by refreshing the page until the product is in stock.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public abstract class Bot {
    
    protected boolean isPlayingSound;
    protected boolean inCheckout;
    private Random random;
    
    /**
     * Abstract constructor for a Bot
     */
    public Bot() {
        isPlayingSound = false;
        inCheckout = false;
        random = new Random();
    }
    
    /**
     * Tracks all products added for a given site implementation.
     */
    public abstract void track();
    
    /**
     * Tracks a given product for a given site implementation.
     * @param product The product to track.
     */
    public abstract void track(Product product);
    
    /**
     * Stops tracking the products for a given site implementation.
     */
    public abstract void stopTracking();
    
    /**
     * Signs into the user's account with the given email and password.
     */
    public abstract void signIn(ChromeDriver driver) throws InterruptedException;
    
    /**
     * Sets the email and password for this site.
     */
    public abstract void setCredentials(String email, String password);
    
    /**
     * Determines whether this bot is active.
     * @return true if this bot is active, false otherwise.
     */
    public abstract boolean isActive();
    
    /**
     * Determines if this bot has no products.
     * @return true if this bot has no products, false otherwise.
     */
    public abstract boolean hasNoProducts();
    
    /**
     * Adds a given product for this bot to track.
     * @param product The product to add for this bot to track.
     * @return true if the product was added successfully, false otherwise.
     */
    public abstract boolean addProduct(Product product);
    
    /**
     * Sounds an in-stock alert to alert the user when a product is in stock for any 
     * product that is being tracked.
     * @param product The product that is in stock.
     */
    protected synchronized void inStockAlert() {
        File file = new File("tactical-nuke.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            System.out.println("clip playing");
            new Thread() {
                
                /**
                 * Starts the alert clip.
                 */
                public void run() {
                    clip.start();
                }
            }.start();
        }
        catch (Exception e) {
            
        }
    }
    
    /**
     * Sleeps the thread for a random duration of time within the bounds of rangeLow and rangeHigh
     * @param rangeLow The lower bound for the thread to sleep in milliseconds.
     * @param rangeHigh The higher bound for the thread to sleep in milliseconds
     */
    protected synchronized void sleep(int rangeLow, int rangeHigh) {
        try {
            Thread.sleep(Long.valueOf(random.nextInt(rangeHigh - rangeLow) + rangeLow));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a new ChromeDriver.
     * @return A new ChromeDriver.
     */
    protected synchronized ChromeDriver createDriver() {
        System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(createChromeOptions());
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        
        return driver;
    }
    
    /**
     * Creates a ChromeOptions object to use for a ChromeDriver.
     * @return a ChromeOptions object to use for a ChromeDriver.
     */
    private ChromeOptions createChromeOptions() {
        ChromeOptions co = new ChromeOptions();
        co.setAcceptInsecureCerts(true);
        co.addArguments("chrome.switches", "--disable-extensions");
        co.addArguments("disable-infobars");
        co.addArguments("ignore-certificate-errors");
        co.addArguments("exclude-switches");
        co.addArguments("use-automation-extension=false");
        co.addArguments("--disable-blink-features=AutomationControlled");
        
        return co;
    }
}
