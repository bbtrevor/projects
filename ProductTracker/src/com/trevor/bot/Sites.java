package com.trevor.bot;

/**
 * A list of supported sites with their associated bot objects
 * @author Trevor Bagbey
 * @version 2021.05.14
 */
public enum Sites {
    
    Amazon(new AmazonBot()),
    BestBuy(new BestBuyBot()),
    Walmart(new WalmartBot());
    
    private Bot bot;
    
    /**
     * Creates a new Site
     * @param bot The bot for the associated site
     */
    private Sites(Bot bot) {
        this.bot = bot;
    }
    
    /**
     * Gets the bot for a given site
     * @return the bot for a given site
     */
    public Bot getBot() {
        return bot;
    }
    

}
