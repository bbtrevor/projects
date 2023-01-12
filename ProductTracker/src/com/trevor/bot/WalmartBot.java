package com.trevor.bot;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * A bot implementation for Walmart. Creates and runs a new thread for each product for this 
 * site and tracks them by refreshing the page until the product is in stock.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public class WalmartBot extends Bot {
    
    private ThreadGroup threadGroup;
    private ArrayList<ChromeDriver> drivers;
    private boolean isActive;
    private ArrayList<Product> products;
    private String email;
    private String password;
    
    /**
     * Creates a new Walmart bot.
     */
    public WalmartBot() {
        threadGroup = new ThreadGroup("Walmart");
        drivers = new ArrayList<ChromeDriver>();
        products = new ArrayList<Product>();
        isActive = false;
    }
    
    /**
     * Tracks all products added for this site.
     */
    @Override
    public void track() {
        for (Product product : products) {
            track(product);
        }
        isActive = true;
    }
    
    /**
     * Tracks a given product for this site.
     * @param product The product to track.
     */
    @Override
    public void track(Product product) {
        Thread thread = new Thread(threadGroup, product.getURL()) {
            
            /**
             * Creates a ChromeDriver and uses it to track the given product until it is in stock.
             */
            public void run() {
                try {
                    ChromeDriver driver = createDriver();
                    drivers.add(driver);
                    signIn(driver);
                    sleep(4000, 4500);
                    driver.navigate().to(product.getURL());
                    boolean outOfStock = true;
                    do {
                        try {
                            WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"add-on-atc-container\"]/div[1]/section/div[1]/div[3]/button/span/span"));
                            if (addToCartButton.getText().contains("Add to cart")) {
                                double price = Double.valueOf(driver.findElement(By.xpath("//*[@id=\"price\"]/div/span[1]/span/span[2]")).getText().replace("$", "").
                                    replace(",", ""));
                                if (price > product.getTargetHigh() || price < product.getTargetLow()) {
                                    continue;
                                }
                                addToCartButton.click();
                                outOfStock = false;
                                inStockAlert();
                            } 
                            else { 
                                sleep(2150, 5250);
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
    public void signIn(ChromeDriver driver) throws InterruptedException {
        driver.get("https://www.walmart.com/account/login");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);

        Thread.sleep(101);
        try {
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        } catch (NoSuchElementException e) { driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form[1]/div[1]/div[2]/button")).click(); 
            Thread.sleep(101);
            driver.findElement(By.xpath("//*[@id=\"sign-in-password-no-otp\"]")).sendKeys(password);
            Thread.sleep(103);
            driver.findElement(By.xpath("//*[@id=\"sign-in-with-password-form\"]/div[4]/button")).click();
        }
        Thread.sleep(109);
        driver.findElement(By.xpath("//*[@id=\"sign-in-form\"]/button[1]")).click();
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
