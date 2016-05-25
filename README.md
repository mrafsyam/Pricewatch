# PriceWatch

<p align="center">
  <img src="https://raw.githubusercontent.com/mrafsyam/Pricewatch/master/app/images/logo.png?raw=true" width="200"/>
</p>

#### Description  
Get the best price for all your groceries need

#### Screenshot (App)
<p align="center">
  <img src="https://github.com/mrafsyam/Pricewatch/blob/master/app/images/wireframe1.png?raw=true" width="250"/>
  <img src="https://github.com/mrafsyam/Pricewatch/blob/master/app/images/wireframe2.png?raw=true" width="250"/>
</p>


#### TO DO (First working version)
* Module 1 : Login screen + Sign up (optional Sign Up via FB)
  - Requires online database - table MST_USER ("email", "password", "FB_id") etc
  
* Module 2 : Location 
  - Prompt user for "home" locatione using GPS and list down suggestion) and save to Shared Preference
  - Google Play services location APIs are preferred over the Android framework location APIs (android.location)

* Module 3 : Grocery Listing & Selection
  - List grocery items' category - category will expand into individual item list 
  - Each item has include image, name, "tick" button
  - A go button will proceed to Module 4
  - Requires local database - SQLlite on Android. Can be patched upon update.

* Module 4 : Result aka Price Comparison
  - Prompt user with list of nearby Supermarket as collumns and list of "previously selected" item as rows 
  - Each collumn displays price for every "previously selected" Supermarket. 
  - Best price is highlighted for each item (i.e. Supermarket A may have best price for item A and thus highlighted. Supermarket B may have best price for item B and thus highlighted)
  - Supermarket with less total cost is displayed - done by totalling all item cost. 
  - Ability to copy the list into clipboard and Shared via Social Media
  - Requires online database - table MST_ITEM (structure is to be decide)


#### TO DO (After first working version is completed) 
* 
 

