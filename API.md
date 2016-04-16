
## Server API


### Login and Authentication API

#### Registration Api

url: /register

```
#Request:
{
  "userName": "vishnu2abc",
  "email": "vishnu@ABC.com",
  "name": "vishnu",
  "bloodGroup": "0+ve",
  "dob": "24-03-1985",
  "password": "qwe",
  "confirmPassword": "qwe",
  "phoneNo": "12123"
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

#### Register Blood Request Api

url: /api/bloodRequest/register

```
# Register

{
  "token": "8acbbd80e7ce457e8cd5b816fa01302b0fe60c4f276acf349aa39021bf4e98a2",
  "data": {
    "contactNumber": "13245645",
    "hospitalName": "hospital name",
    "hospitalAddress": " hospital address1, Hospital address 2",
    "patientName": "Patient Name",
    "bloodGroup": "0+ve",
    "purpose": "reason for blood Request will be displayed to the donors",
    "comment": "",
    "requiredUnits": 3,
    "lat": 0.0,
    "lon": 0.0,
    "requiredWithin": 1462022163000
  }
}

# Response

{
  "status": "ok",
  "bloodRequest": {
    "bloodGroup": "0+ve",
    "patientName": "Patient Name",
    "requiredWithin": 1462022163000,
    "purpose": "reason for blood Request will be displayed to the donors",
    "status": 0,
    "comment": "",
    "createdUserId": 1,
    "contactNumber": "13245645",
    "lon": 0.0,
    "requestId": 3,
    "hospitalAddress": " hospital address1, Hospital address 2",
    "requiredUnits": 3,
    "hospitalName": "hospital name",
    "fulfilledUnits": 0,
    "lat": 0.0,
    "promisedUnits": 0
  },
  "messages": ["Blood Request Created Successfully with Id 3"]
}

```

#### Get Blood Request Object Api

url: /api/bloodRequest/get

```
# Request 
{
"token": "0e74847772ad2abe9836c66fc2688a826288440a12a5d5d8bf91edb3eded2abe",
  "requestId":1
}

# Response
{
  "status": "ok",
  "bloodRequest": {
    "bloodGroup": "0+ve",
    "patientName": "Patient Name",
    "requiredWithin": 1462022163000,
    "purpose": "reason for blood Request will be displayed to the donors",
    "status": 0,
    "comment": "",
    "createdUserId": 1,
    "contactNumber": "13245645",
    "lon": 0.0,
    "requestId": 1,
    "hospitalAddress": " hospital address1, Hospital address 2",
    "requiredUnits": 3,
    "hospitalName": "hospital name",
    "fulfilledUnits": 0,
    "lat": 0.0,
    "promisedUnits": 0
  }
}
```


#### Register DonationRecord Api

url: /api/donationRecord/register

```
# Register

{
  "token": "8acbbd80e7ce457e8cd5b816fa01302b0fe60c4f276acf349aa39021bf4e98a2",
  "data": {
    "requestId": 120,
    "status": 2,
    
  }
}

# Response

{
  "status": "ok",
  "bloodRequest": {
    "donationId": 2,
    "userId": 1,
    "requestId": 120,
    "status": 2
  },
  "messages": [
    "Donation Record Created Successfully with Id 2"
  ]
}

```

#### Get DonationRecord Object Api

url: /api/donationRecord/get

```
# Request 
{
"token": "0e74847772ad2abe9836c66fc2688a826288440a12a5d5d8bf91edb3eded2abe",
  "donationId":1
}

# Response
{
  "status": "ok",
  "donationRecord": {
    "donationId": 1,
    "userId": 1,
    "requestId": 120,
    "status": 2
  }
}
```