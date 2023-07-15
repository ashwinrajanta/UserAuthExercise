DB used MySQL

![img.png](img.png)

```
CREATE TABLE user_details (
id INT PRIMARY KEY,
username VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(15) NOT NULL,
lastlogin TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);```


