`keytool -genkeypair -alias course.spring -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore course.spring.p12 -validity 3650`
`keytool -genkeypair -alias course.spring -keyalg RSA -keysize 2048 -keystore course.spring.jks -validity 3650`
`keytool -importkeystore -srckeystore course.spring.jks -destkeystore course.spring.p12 -deststoretype pkcs12`

## In application.properties:
```
# The format used for the keystore. It could be set to JKS in case it is a JKS file
server.ssl.key-store-type=JKS
# The path to the keystore containing the certificate
server.ssl.key-store=classpath:keystore/course.spring.p12
# The password used to generate the certificate
server.ssl.key-store-password=abc123
# The alias mapped to the certificate
server.ssl.key-alias=course.spring
```
