# MobilizCarTrackingApplication

## User & Vehicle Management Microservices

Bu projede kullanıcı bilgilerinin ve araç bilgilerinin saklandığı ve yönetildiği iki mikroservis bulunmaktadır.

## Başlangıç

### Ön Şartlar:
- PostgreSQL veritabanının kurulu olması

## Proje Yapılandırması ve Bağımlılıkları

### Eklentiler:

- Java
- Spring Boot (version 2.7.9)
- Spring Dependency Management (version 1.0.15.RELEASE)
- Application

### Genel Ayarlar:

- Grup: `com.mobiliz`
- Versiyon: `v.0.1`
- Java Sürümü: 17 (Hem kaynak hem de hedef için)
- Kodlama: `UTF-8`

### Bağımlılıklar:

- Spring Boot Web: `libs.springBootWeb`
- Lombok (Sadece derleme için): `libs.lombok`
- Mapstruct İşlemci: `libs.mapstructProcessor`
- Mapstruct: `libs.mapstruct`
- Swagger UI: `libs.swaggerui`
- Spring Boot Validation: `libs.springBootValidation`

Bağımlılıklar `${rootDir}/dependencies.gradle` dosyasından uygulanır.

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

spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: root
    url: jdbc:postgresql://localhost:5432/UserMicroService
