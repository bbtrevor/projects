package com.trevor.bot;

/**
 * A product object that can be tracked by a given bot.
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public class Product {
    
    private String url;
    private double targetLow;
    private double targetHigh;
    
    /**
     * Creates a new Product.
     * @param url The URL for this product
     * @param targetLow The target low price for this product
     * @param targetHigh The target high price for this product
     */
    public Product(String url, double targetLow, double targetHigh) {
        this.url = url;
        this.targetLow = targetLow;
        this.targetHigh = targetHigh;
    }
    
    /**
     * Gets the URL for this product
     * @return the URL for this product
     */
    public String getURL() {
        return url;
    }
    
    /**
     * Gets the target low price for this product
     * @return the target low price for this product
     */
    public double getTargetLow() {
        return targetLow;
    }
    
    /**
     * Gets the target high price for this product
     * @return the target high price for this product
     */
    public double getTargetHigh() {
        return targetHigh;
    }
    
}
