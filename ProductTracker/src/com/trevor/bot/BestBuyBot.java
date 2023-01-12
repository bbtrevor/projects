package com.trevor.bot;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * A bot implementation for BestBuy. Creates and runs a new thread for each product for this 
 * site and tracks them by refreshing the page until the product is in stock.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public class BestBuyBot extends Bot {
    
    private ThreadGroup threadGroup;
    private ArrayList<ChromeDriver> drivers;
    private boolean isActive;
    private ArrayList<Product> products;
    private String email;
    private String password;
    
    /**
     * Creates a new BestBuy bot.
     */
    public BestBuyBot() {
        products = new ArrayList<Product>();
        drivers = new ArrayList<ChromeDriver>();
        threadGroup = new ThreadGroup("BestBuy");
        isActive = false;
    }
    
    /**
     * Tracks all products added for this site.
     */
    @Override
    public void track() {
        
        for (Product product: products) {
            track(product);
        }
        isActive = true;
    }
    
    /**
     * Tracks a given product for this site.
     * @param product The product to track.
     */
    @Override
    public synchronized void track(Product product) {
        Thread thread = new Thread(threadGroup, product.getURL()) {
            
            /**
             * Creates a ChromeDriver and uses it to track the given product until it is in stock.
             */
            public void run() {
                try {
                    ChromeDriver driver = createDriver();
                    drivers.add(driver);
                    signIn(driver);
                    Thread.sleep(5000);
                    driver.navigate().to(product.getURL());
                    boolean outOfStock = true;
                    
                    do {
                        try {
                            WebElement buyButton = driver.findElementByClassName("fulfillment-add-to-cart-button");
                            if (buyButton.getText().equals("Add to Cart")) {
                                double price = Double.valueOf(driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div[2]/div/div/div[1]/div/div/div/div/div[2]/div[1]/div/div/span[1]")).getText().
                                    replace("$", "").replace(",", ""));
                                if (price > product.getTargetHigh() || price < product.getTargetLow()) {
                                    continue;
                                }
                                outOfStock = false;
                                //sleep(50, 100);
                                //buyButton.click();
                                if (!inCheckout) {
                                    
                                    //driver.manage().window().maximize();
                                    inCheckout = true;
                                }
                                inStockAlert();
                            }
                            else {
                                sleep(500, 1000);
                                driver.navigate().refresh();
                            }
                        }
                        catch (Exception e) {
                            break;
                        }
                    }
                    while (outOfStock);
                } catch (InterruptedException e) {
                    
                }
                catch (Exception e) {
                    
                }
            }
        };
        thread.start();
    }
    
    /**
     * Stops tracking the products for this site.
     */
    @Override
    public void stopTracking() {
        for (ChromeDriver driver : drivers) {
            driver.quit();
        }
        threadGroup.interrupt();
    }
    
    /**
     * Signs into the user's account with the given email and password.
     */
    @Override
    public void signIn(ChromeDriver driver) {
        driver.get("https://www.bestbuy.com/identity/global/signin");
        driver.findElement(By.xpath("//*[@id=\"fld-e\"]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"fld-p1\"]")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[1]/div/section/main/div[2]/div[1]/div/div/div/div/form/div[4]/button")).click();
    }
    
    /**
     * Sets the email and password for this site.
     */
    @Override
    public void setCredentials(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Invalid email or password input");
        }
        this.email = email;
        this.password = password;
    }
    
    /**
     * Determines whether this bot is active.
     * @return true if this bot is active, false otherwise.
     */
    @Override
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * Determines if this bot has no products.
     * @return true if this bot has no products, false otherwise.
     */
    @Override
    public boolean hasNoProducts() {
        return products.isEmpty();
    }
    
    /**
     * Adds a given product for this bot to track.
     * @param product The product to add for this bot to track.
     * @return true if the product was added successfully, false otherwise.
     */
    @Override
    public boolean addProduct(Product product) {
        if (product == null) {
            return false;
        }
        products.add(product);
        return true;
    }

}
