# MobilizCarTrackingApplication

## User & Vehicle Management Microservices

Bu projede kullanıcı bilgilerinin ve araç bilgilerinin saklandığı ve yönetildiği iki mikroservis bulunmaktadır.

## Başlangıç

### Ön Şartlar:
- PostgreSQL veritabanının kurulu olması.
- Projede kullanılan bağımlılıkların ve kütüphanelerin indirilmiş olması.

### Kurulum:

1. PostgreSQL veritabanında `UserMicroService` ve `VehicleMicroService` adlarında iki veritabanı oluşturun.
2. Veritabanı kullanıcı adınızı `postgres` olarak, şifrenizi `root` olarak ayarlayın. Eğer farklı bir kullanıcı adı veya şifre kullanmak isterseniz, ayarları `spring.datasource.username` ve `spring.datasource.password` içerisinde güncelleyin.

## Konfigürasyon

JWT için gerekli olan `secretkey`, `issuer` ve `audience` değerlerini ortam değişkenlerinden (`${MOBILIZ_SECRETKEY}`, `${MOBILIZ_ISSUER}`, `${MOBILIZ_AUDIENCE}`) alır. Bu değerleri kendi ortamınızda ayarlamalısınız.

### Uygulama Ayarları:

#### Port Ayarı:
- Vehicle Microservice için varsayılan port `8082`'dir.
- User Microservice için varsayılan port `8081`'dir.

#### Veritabanı Ayarları:

**Vehicle Microservice için:**

```yaml
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: root
    url: jdbc:postgresql://localhost:5432/VehicleMicroService



**User Microservice için:**

```yaml
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: root
    url: jdbc:postgresql://localhost:5432/UserMicroService
