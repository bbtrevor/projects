package com.trevor.bot;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * A bot implementation for Amazon. Creates and runs a new thread for each product for this 
 * site and tracks them by refreshing the page until the product is in stock.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public class AmazonBot extends Bot {
    
    private ThreadGroup threadGroup;
    private ArrayList<ChromeDriver> drivers;
    private boolean isActive;
    private ArrayList<Product> products;
    private String email;
    private String password;
    
    /**
     * Creates a new Amazon bot.
     */
    public AmazonBot() {
        drivers = new ArrayList<ChromeDriver>();
        products = new ArrayList<Product>();
        threadGroup = new ThreadGroup("Amazon");
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
                    Thread.sleep(250);
                    driver.navigate().to(product.getURL());
                    boolean outOfStock = true;
                    do {
                        try {
                            double buyPrice = -1;
                            try {
                                buyPrice = Double.valueOf(driver.findElement(By.xpath("//*[@id=\"price_inside_buybox\"]")).getText().replace("$", "").replace(",", ""));
                            } catch (NoSuchElementException e) {
                                driver.navigate().to(product.getURL());
                                continue;
                            }
                            catch (Exception e) {
                                break;
                            }
                            if (buyPrice <= product.getTargetHigh() && buyPrice >= product.getTargetLow()) {
                                driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
                                Thread.sleep(1500);
                                try {
                                    driver.findElement(By.xpath("//*[@id=\"attachSiNoCoverage\"]/span/input")).click();
                                    Thread.sleep(250);
                                } catch (NoSuchElementException e) {}
                                try {
                                    driver.findElement(By.xpath("//*[@id=\"hlb-ptc-btn-native\"]")).click();
                                }
                                catch (NoSuchElementException e) {
                                    driver.navigate().to(product.getURL());
                                    continue;
                                }
                                try {
                                    WebElement placeOrder = driver.findElement(By.xpath("//*[@id=\"submitOrderButtonId\"]/span/input"));
                                    if (placeOrder != null) {
                                        if (!inCheckout) {
                                            driver.manage().window().maximize();
                                        }
                                        inStockAlert();
                                        
                                    }
                                }
                                catch (NoSuchElementException x) {
                                    driver.navigate().to(product.getURL());
                                    continue;
                                }
                                outOfStock = false;
                            }
                            else {
                                driver.navigate().refresh();
                                continue;
                            }
                        } 
                        catch (NoSuchElementException e) {
                            driver.navigate().refresh();
                        }
                        catch (Exception e) {
                            break;
                        }
                        
                    }
                    while (outOfStock);
                } catch (InterruptedException e) {
                    
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
        driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fhelp%2Fcustomer%2Fdisplay.html%3FnodeId%3DGX7AZREFL3D5VC2D%26ref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
        Thread.sleep(250);
        driver.findElement(By.xpath("//*[@id=\"ap_password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
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
