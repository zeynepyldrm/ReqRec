## Request Record

Implementation that encrypts and saves the message received as a request with AES algorithm.

## Using for Docker

```sh
 colima start
 docker-compose up -d --build
```
### If you want to change your secret key and start again

```sh
 colima start
 docker-compose down -v
 docker-compose up -d --build
```

## Testing and Example Curl

```sh
http://127.0.0.1:8080/

curl --location 'http://127.0.0.1:8080/' \
--header 'Content-Type: application/json' \
--data '{
    "id":"1",
    "message":"hello"
}'

http://127.0.0.1:8080/messages

curl --location 'http://127.0.0.1:8080/messages' \
--data 

```