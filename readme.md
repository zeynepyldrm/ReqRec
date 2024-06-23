## Request Record

Implementation that encrypts and saves the message received as a request with AES.

## Using for Mac
```sh
 colima start
 docker-compose up
 mvn spring-boot:run 
```
## Testing and Example Curl


```sh
http://127.0.0.1:8080/

curl --location 'http://127.0.0.1:8080/' \
--header 'Content-Type: application/json' \
--data '{
    "id":"3",
    "message":"testPass22"
}'

http://127.0.0.1:8080/messages

curl --location 'http://127.0.0.1:8080/messages' \
--data ''

```