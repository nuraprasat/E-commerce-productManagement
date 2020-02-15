# E-commerce-productManagement
product management for e-commerce application

## Getting Started
To run a project, First clone it
```
git clone https://github.com/nuraprasat/E-commerce-productManagement.git
```

### Installing
After cloning just give the below command to run the service inside the project
```
java -jar target/E-commerce-productManagement-0.0.1-SNAPSHOT.jar
```

## Running


###Post Man - 

To generate the oauth token, Hit the below URL with appid and secret, Also set the grant_type as client_credentials in the header
```
POST : http://localhost:8080/oauth/token
Authorization : username and password
Body : 'x-www-form-urlencoded'
grant_type:client_credentials
```
![](/images_readme/oauth_token.png)
![](/images_readme/oauth_token2.png)

To get all the product, Hit the below URL with the token in the header as Authorization - Bearer token
```
GET : http://localhost:8080/product/getAllProduct
Headers : Authorization - Bearer token
```
![](/images_readme/getMethod.png)

To get the product by name, Hit the below URL with the token in the header as Authorization - Bearer token
```
GET : http://localhost:8080/product/getByProductName/{product name}
Headers : Authorization - Bearer token
pathVariable : product name
```
![](/images_readme/getByProductName.png)

To create a new product,
```
POST : http://localhost:8080/product/create
Header : Authorization - Bearer token
Request body : 'application/Json'
	{
        "productName": "round neck t-shirt",
        "url": "http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html",
        "image": "http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg",
        "price": 299.0,
        "msrp": 0.0,
        "productAvailability": true,
        "productType": "For Men",
        "description": "Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.",
        "merchant": {
            "merchantName": "puma",
            "description": "sells puma products"
        },
        "category": {
            "categoryName": "tshirt",
            "parentCategory": {
                "categoryName": "clothes",
                "parentCategory": null
            }
        }
    }
```
![](/images_readme/postMethod.png)
![](/images_readme/postMethod1.png)

To update a product by id,
```
PUT : http://localhost:8080/product/updateProduct/3
Header : Authorization - Bearer token
Request body : 'application/Json'
{
    "productName": "Jean Shirt",
    "url": "http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html",
    "image": "http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg",
    "price": 299.0,
    "msrp": 0.0,
    "productAvailability": true,
    "productType": "For Men",
    "description": "Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.",
    "merchant": {
        "merchantName": "wrogn",
        "description": "sells wrogn products"
    },
    "category": {
        "categoryName": "shirt",
        "parentCategory": {
            "categoryName": "clothes",
            "parentCategory": null
        }
    }
}
```
![](/images_readme/updateByProductId.png)

To update a product by Name,
```
PUT : http://localhost:8080/product/updateByProductName/Jean Shirt
Header : Authorization - Bearer token
Request body : 'application/Json'
{
    "productName": "Jean Shirt",
    "url": "http://www.lifestylelabels.com/steven-by-steve-madden-pryme-pump.html",
    "image": "http://www.lifestylelabels.com/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/t/steven-by-steve-madden-pryme-pump.jpg",
    "price": 299.0,
    "msrp": 0.0,
    "productAvailability": true,
    "productType": "For Men",
    "description": "Nothing will turn his head faster than you wearing the sexy Pryme pump from Steven by Steve Madden. This daring pump has a pretty patent leather upper with light shirring, a double stitch detail surrounding the collar, and a vampy almond shaped toe.",
    "merchant": {
        "merchantName": "wrogn",
        "description": "sells wrogn products"
    },
    "category": {
        "categoryName": "shirt",
        "parentCategory": {
            "categoryName": "clothes",
            "parentCategory": null
        }
    }
}
```
![](/images_readme/updateByProductName.png)
![](/images_readme/updateByProductName1.png)


To delete a product by id,
```
DELETE : http://localhost:8080/product/delete/2
Header : Authorization - Bearer token
```
![](/images_readme/deleteById.png)

To delete a product by name,
```
DELETE : http://localhost:8080/product/deleteByProductName/Jean Shirt
Header : Authorization - Bearer token
```
![](/images_readme/deleteByProductName.png)