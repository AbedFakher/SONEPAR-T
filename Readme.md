# Biskot
Biskot API is micro service responsible for managing the cart of an e-commerce website.

Before attempting to crunch the code, we strongly recommend you to carefully read each of the sections below:

## Exercise goal

* Implement the endpoints defined in the [biskot.yaml](contract/biskot.yaml) api model. To better understand the model, we suggest you open the `yaml` file in a fancy UI available [here](https://editor.swagger.io/).

To give you an idea of what is the expected result from each endpoint looks like, below some examples:

> #### action: POST /carts
> response http code: 200

> #### action: GET /carts/{cartId}
> response http code: 200
>
> response body:
>```json
>{
>  "id": 1,
>  "items": [
>    {
>      "product_id": 1,
>      "product_label": "Déodorant Spray 200ml Ice Dive ADIDAS",
>      "quantity": 2,
>      "unit_price": 2.00,
>      "line_price": 2.00
>    }
>  ],
>  "totalPrice": 4.00
>}
>>```

> #### action: PUT /carts/{cartId}/items
> request body:
> ```json
> {
>    "product_id": 1,
>    "quantity": 2
>  }
>```
> response http code: 200

## Cart business rules

* Added quantity of a product should not exceed the stock availability
* Total price of the cart should not exceed 100 euros
* A cart cannot contain more than 3 different products

## Additional details and considerations
* Carts could be persisted in memory using an embedded db or a collection of you choice inside [InMemoryCartRepository](src/main/java/com/biskot/infra/repository/InMemoryCartRepository.java)
* Biskot has a dependency with an external service allowing you to get products information. The interactions with this external service should be implemented in [ProductGateway](src/main/java/com/biskot/infra/gateway/ProductGateway.java)
* As long as you respect the exercise goal, you have complete freedom to apply as many coding practices or external libraries as you see fit.  

## Tips
* The Java contract classes as well as the api interface can be generated using the openapi-generator-maven-plugin configured in `pom.xml` file
* A mock server has already been configured in [ProductMockServer](src/main/java/com/biskot/infra/mock/ProductMockServer.java) from which products can be retrieved calling:
GET http://localhost:9001/products/{productId} (productId ranges from 1 to 4)
* The project is structured using [hexagonal architecture](https://blog.octo.com/architecture-hexagonale-trois-principes-et-un-exemple-dimplementation/), so please be aware to implement all layers (you will find `// TODO: to be implemented` comments when expect you to provide an implementation). 

## Submission
When you feel confident with your implementation, please push your work in a source code management of your choice and share it with us.

Happy Coding. Good luck!
