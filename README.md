# MobilizCarTrackingApplication

## User & Vehicle Management Microservices

Bu projede kullanıcı bilgilerinin ve araç bilgilerinin saklandığı ve yönetildiği iki mikroservis bulunmaktadır.
Projeyi otomatik başlatmak için aşağıdaki kodu çalıştırabilirsiniz docker compose olarak çalışıp. Otomatik olarak bağımlılık
ve gereksinimleri getirecektir.
```bash
docker-compose up -d --build
```
## Başlangıç

### Ön Şartlar:
- Docker kurulumu yapılmalı
- PostgreSQL veritabanının kurulu olması
- Postman kurulumu.

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

## Uygulama Ayarları:

#### Port Ayarı:
- Vehicle Microservice için varsayılan port `8082`'dir.
- User Microservice için varsayılan port `8081`'dir.

**JWT Ayarları:**
Kullanıcı doğrulama ve yetkilendirme için JWT ayarlarıdır. Bu değerler ortam değişkenlerinden alınır.

```yaml
jwt:
  secretkey: ${MOBILIZ_SECRETKEY}
  issuer: ${MOBILIZ_ISSUER}
  audience: ${MOBILIZ_AUDIENCE}
```
### Postman ile Test Etme
Bu projeyi test etmek için Postman kullanmanızı öneriyoruz. Postman, API test etme işlemlerinizi basit, hızlı ve güvenilir bir şekilde gerçekleştirmenizi sağlar.

#### Adımlar:
1. Postman Kurulumu: Eğer bilgisayarınızda Postman yüklü değilse, resmi web sitesi üzerinden indirebilir ve kurulumunu gerçekleştirebilirsiniz.

2. API Endpoints'ini İmport Etme: Postman'da, Import butonuna tıklayarak projenizin API endpoint'lerini içeren bir JSON veya Postman koleksiyonunu import edebilirsiniz. Bu sayede tüm endpoint'leri tek seferde Postman'a eklemiş olursunuz.

3. İstek Gönderme: Endpoint'lerinizi Postman üzerinden test etmek için, ilgili HTTP metodu (GET, POST, PUT, DELETE vb.) seçilerek API'nizin URL'ini yazabilir ve Send butonuna tıklayarak isteği gönderebilirsiniz.

4. Sonuçları İnceleme: İstek sonucu dönen cevabı Postman'ın alt kısmında bulunan bölümden inceleyebilirsiniz.

Not: Projede kullanılan JWT veya benzeri bir kimlik doğrulama yöntemi bulunmaktadır, bu bilgileri `Swagger Ui` dan alıp `Authorization` kısmına `Bearer` token olarak eklemeyi unutmayın.
