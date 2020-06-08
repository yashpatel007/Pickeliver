# Pickeliver
Premium Pickup and Delivery Service


# Abstract
This is a Spring + Thymeleaf project, this project aims at building a scalable solution for a business looking 
to start a pickup, delivery service.

-- There are more advance features that's yet to be impelented
  * -> Push Notification for drivers nearby.
  * -> Real time GPS Tracking Report.
  * -> Pricing based on order type and Weight
  * -> Add an Admin Panel
  
  **Note** : You may expect to see these features in future releases

# Compiling The Project
You will require Java installed on your machine in order to compile the project, you will also require google developer key
in order to use the google map services.
  
  * Open the project in IDE and then compile it.
  
# Features

  1. Home Page
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/home.png)
  2. Login 
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/login.png)
  3. Register
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/register.png)
  4. Map Service
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/getquote.png)
  5. My Account
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/myaccount.png)
  6. My Jobs
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/myjobs.png)
  7. Others
   ![](https://github.com/yashpatel007/Pickeliver/blob/master/demo%20images/other.png)

## Replacing Google API KEY
open the file [farecalculator.html](https://github.com/yashpatel007/Pickeliver/blob/master/src/main/resources/templates/farecalculator.html) and add the api key at the code in place of your-api-key
```js
<script src="https://maps.googleapis.com/maps/api/js?key=your-api-key&libraries=places">
</script>
```

