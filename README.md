# MobilizCarTrackingApplication
### User & Vehicle Management Microservices
Bu projede kullanıcı bilgilerinin ve araç bilgilerinin saklandığı ve yönetildiği iki mikroservis bulunmaktadır.

### Başlangıç
Ön Şartlar:
PostgreSQL veritabanının kurulu olması.
Projede kullanılan bağımlılıkların ve kütüphanelerin indirilmiş olması.
Kurulum:
PostgreSQL veritabanında UserMicroService ve VehicleMicroService adlarında iki veritabanı oluşturun.
Veritabanı kullanıcı adınızı postgres olarak, şifrenizi root olarak ayarlayın. Eğer farklı bir kullanıcı adı veya şifre kullanmak isterseniz, ayarları spring.datasource.username ve spring.datasource.password içerisinde güncelleyin.
Konfigürasyon
JWT için gerekli olan secretkey, issuer ve audience değerlerini ortam değişkenlerinden (${MOBILIZ_SECRETKEY}, ${MOBILIZ_ISSUER}, ${MOBILIZ_AUDIENCE}) alır. Bu değerleri kendi ortamınızda ayarlamalısınız.
Uygulama Ayarları:
Port Ayarı:

Vehicle Microservice için varsayılan port 8082'dir.
User Microservice için varsayılan port 8081'dir.
Veritabanı Ayarları:

Vehicle Microservice için:
Inline `code` has `back-ticks around` it.
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: root
    url: jdbc:postgresql://localhost:5432/VehicleMicroService
User Microservice için:
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: root
    url: jdbc:postgresql://localhost:5432/UserMicroService
Hibernate Ayarları: Her iki servis için de veritabanı şemasının otomatik olarak güncellenmesini sağlar.
spring:
  jpa:
    hibernate:
      ddl-auto: update
### JWT Ayarları: 
Kullanıcı doğrulama ve yetkilendirme için JWT ayarlarıdır. Bu değerler ortam değişkenlerinden alınır.
jwt:
  secretkey: ${MOBILIZ_SECRETKEY}
  issuer: ${MOBILIZ_ISSUER}
  audience: ${MOBILIZ_AUDIENCE}


