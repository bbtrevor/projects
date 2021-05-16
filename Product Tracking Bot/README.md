This project aims to allow a user to track out-of-stock products and get alerted when a given product is in stock. 
This project was inspired by the current shortage of PC components, including GPUs and some CPUs, and supports three 
large distributers of these products including Amazon, Walmart, and BestBuy.
Upon execution, the user may enter several products into the GUI by specifying the product URL and the price range they're willing 
to pay for it, along with the associated website and credentials for that website. The user can then start tracking the products 
they entered, and a chrome driver window will be created for each product. The chrome driver will sign into the user's account and 
navigate to the product URL, refreshing the page until the product is in stock, in which it will sound an in stock alert and attempt 
to add it to cart.
