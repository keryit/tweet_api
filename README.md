# tweet_api

## API

### User
- ***Register user*** @Post(/api/auth/register)
- ***Login*** @Post(/api/auth/login)
- ***Logout*** @Get(api/auth/logout)
- ***Subscribe/unsubscribe use***r @Post(/api/users/subscribe/{targetUserId})

### Post
- ***Create post*** @Post(/api/posts)
- ***Update post*** @Put(/api/posts/{postId}) 
  - verify that user has permission to modify
  - user can update just own post
- ***Delete post*** @Delete(/api/posts/{postId})
    - user can delete just own post
- ***Like post*** @Post(/{postId}/like/{userId})
  - if already liked will be unlike
- ***Add comment*** @Post(/{postId}/comments)
- ***Get feeds*** @Get(/api/posts/feed)
- ***Get all user's posts*** @Get(/api/posts)

## How to run
1. Docker should be installed
2. Execute docker-compose to run MongoDB container
```
   docker-compose up -d 
```
3. Run application from Intellij IDEA 
4. Try to send request using Postmam
- Register
  - POST: localhost:8080/api/auth/register
    ```
    {
    "email":"admin@in.ua",
    "password":"1234"
     } 
    ```