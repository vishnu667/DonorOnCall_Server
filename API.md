
## Server API


### Login and Authentication API

#### Registration Api

url: /register

```
#Request:
{
    "userName":"demo2",
    "password":"demo",
    "email":"demo2@demo.com"
}

#Response:
{
  "status": "ok",
  "message": "Registration successful"
}
```

#### Login Api

url: /login

```
#Request:
{
    "userName":"demo2",
    "password":"demo"
}

#Response:
{
  "status": "ok",
  "token": "72475341908b9e960dabe658616077f8a79a2c648e59e87b74b19b3d3495f802",
  "message": "Login successful"
}
```

#### Get User Object Api

url: /api/user/get

```
#Request:
{
    "token":"04b95ce063b740a487f8bb491335fa61a89ab63d463031bf72d69938df3d4732"
}

#Response:
{
  "status": "ok",
  "user": {
    "email": "demo2@demo.com",
    "userName": "demo2"
  }
}
```