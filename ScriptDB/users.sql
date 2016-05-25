CREATE TABLE users (
  id SERIAL  NOT NULL ,
  name VARCHAR    ,
  email VARCHAR   NOT NULL ,
  pass VARCHAR    ,
  phone VARCHAR      ,
PRIMARY KEY(id));