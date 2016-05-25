# Service for Login Using API REST
==========

===============
### List Users.

GET
/users

===============

============
### Create User

POST
/users
### REQUEST
```
{
"name": "Jakaboy",
"email": "Jakaboy@example.com",
"pass": "12345678",
"phone": "+57 321556655"
}
```

### RESPONSE
```
{
  "status": 200,
  "message": "user created successfully"
}
```
============

============
### Update User

PUT
/users
### REQUEST
```
{
"id": 1,
"name": "Jakaboy Update",
"email": "Jakaboy@example.com",
"pass": "12345678",
"phone": "+57 321556655"
}
```
### RESPONSE
```
{
  "status": 200,
  "message": "User updated successfully"
}
```
============

=================
### Delete User

DELETE
/users/{id}
EXAMPLE: /users/1

RESPONSE
```
{
  "status": 200,
  "message": "User deleted successfully"
}
```
=================

============
### Login User

PUT
/users/authenticate 
### REQUEST
```
{
"email": "Jakaboy@example.com",
"pass": "12345678"
}
```
### RESPONSE
```
{
  "name": "JAKABOY",
  "phone": "+57 321556655"
}
```
============

#Jakaboy 2016
Anthony Ruiz
