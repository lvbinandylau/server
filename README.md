# spring-oauth2-demo

spring boot oauth2的使用例子，基于jwt


## sso 使用方式
1. 启动sso-auth-server
2. 启动sso-auth-client
3. 访问 [http://localhost:8085/user](http://localhost:8085/user)  注意：sso再域名相同时浏览器会使用同一个cookie导致页面一直再login页重定向，所以需要把认证服务器和sso客户端设置为不同域名下启动。

## resource server 使用方式
1. 启动sso-auth-server
2. 启动sso-auth-resource
3. 浏览器访问 [http://localhost:8080/oauth/authorize?client_id=testclient&response_type=code&scope=test&redirect_uri=http://127.0.0.1:8086](http://localhost:8080/oauth/authorize?client_id=testclient&response_type=code&scope=test&redirect_uri=http://127.0.0.1:8086) 输入验证信息后会返回对应的code (例如：http://127.0.0.1:8086/?code=j6BgIT )
4. 使用post请求获取token,把上一步获取的code(j6BgIT)放入请求参数 [http://localhost:8080/oauth/token?code=j6BgIT&grant_type=authorization_code&client_id=testclient&redirect_uri=http://127.0.0.1:8086](http://localhost:8080/oauth/token?code=j6BgIT&grant_type=authorization_code&client_id=testclient&redirect_uri=http://127.0.0.1:8086)
5. 使用获取的token访问资源服务器，例如 http://localhost:8086/user?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjIzNTg4ODksInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV90ZXN0Il0sImp0aSI6IjMxNmJjNWIyLTBlYzctNDI4OC04NTc0LWM3OTY2MTllZGE0MCIsImNsaWVudF9pZCI6InRlc3RjbGllbnQiLCJzY29wZSI6WyJ0ZXN0Il19.6Rplh9LQVJ1eZRXFcGnO9GnHkmDgsIje_nOViQRgQlE

## 生成jwt需要的证书
1.生产jks证书
```commandline
keytool -genkeypair -alias ssotest -keyalg RSA -keypass ssopass -keystore sso.jks -storepass ssostorepass
``` 
2.导出public key, 只取public这段
```commandline
keytool -list -rfc --keystore mytest.jks | openssl x509 -inform pem -pubkey  
```