# Service for Login Using API REST



Users List
GET
/users

Create User
POST
/users
REQUEST
{
"name": "Jakaboy",
"email": "Jakaboy@example.com",
"pass": "12345678",
"phone": "+57 321556655"
}
RESPONSE
{
  "status": 200,
  "message": "user created successfully"
}

Update User
PUT
/users
REQUEST
{
"id": 1,
"name": "Jakaboy Update",
"email": "Jakaboy@example.com",
"pass": "12345678",
"phone": "+57 321556655"
}
RESPONSE
{
  "status": 200,
  "message": "User updated successfully"
}

Delete User
DELETE
/users/{id}
EXAMPLE: /users/1

RESPONSE
{
  "status": 200,
  "message": "User deleted successfully"
}

Jakaboy 2016