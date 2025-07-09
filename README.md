# shorten-url-service
단축 URL 서비스 레포지토리


### 요구사항
1. [bitly](https://bitly.com/) 같은 단축 URL 서비스를 만들어야 합니다.
2. 단축된 URL의 키(Key)는 8글자로 생성되어야 합니다. '단축된 URL의 키'는 'https://bit.ly/3onGWgK' 에서 경로(Path)에 해당하는 '3onGWgK'를 의미합니다. bitly에서는 7글자의 키를 사용합니다.
3. 키 생성 알고리즘은 자유롭게 구현하시면 됩니다.
4. 단축된 URL로 사용자가 요청하면 원래의 URL로 리다이렉트(Redirect)되어야 합니다.
5. 원래의 URL로 다시 단축 URL을 생성해도 항상 새로운 단축 URL이 생성되어야 합니다. 이때 기존에 생성되었던 단축 URL도 여전히 동작해야 합니다.
6. 단축된 URL -> 원본 URL로 리다이렉트 될 때마다 카운트가 증가되어야하고, 해당 정보를 확인할 수 있는 API가 있어야합니다.
7. 데이터베이스 없이 컬렉션을 활용하여 데이터를 저장해야합니다.
8. 기능이 정상 동작하는 것을 확인할 수 있는 적절한 테스트 코드가 있어야 합니다.
9. (선택) 해당 서비스를 사용할 수 있는 UI 페이지를 구현해주세요.

### 필요 API
1. 단축 URL 생성 API
2. 단축 URL 리다이렉트 API
3. 단축 URL 정보 조회 API

<details>
<summary>️ Spring Initializer 프로젝트 설정과 이유</summary>

- **Spring Boot 3.x 기반 선택**  
- Spring Boot 2는 곧 End of Life이 예정되어 있어, 장기적으로 유지보수가 불리하다.
- 스프링 생태계도 Spring Boot 3 중심으로 이동 중이므로, 학습 및 적용에 유리.
- **Java 17 사용**  
  Java 17은 Long Term Support 버전으로, 안정성과 장기적인 유지보수가 보장된다.
- Spring Boot 3.x는 Java 17 이상을 요구하므로 호환성 측면에서 필수이기도함.

- **Gradle 사용**  
- Gradle은 빌드 속도가 빠르고, 의존성 관리가 유연하며, 설정이 간결
- 최근 Spring 진영과 다양한 오픈소스 프로젝트에서도 **Gradle 사용 비율이 증가**하고 있어서 추세에 부합함
</details>

<details>
<summary>URL 경로의 네이밍 </summary>

대표적으로 카멜케이스 , Spinal 케이스, 스네이크 케이스 등이 있는데
- 카멜케이스 - 일관성 있지만, 알파벳 외엔 띄어쓰기가 어렵다
- spinal케이스 - 일반적으로 가장 권장되는 방식이다. 나는 학습을 위해 이 케이스를 선택!
- 스네이크케이스 - URL에서 제일 권장되지않는 형식이다. 가끔 언더바를 띄어쓰기가아닌 하나의 단어로 판단하는 경우가 있음.
</details>



<details>
<summary>URL 만들기</summary>

단축 URL 서비스는 다음의 3가지 기능으로 구성된다. 각 기능에 맞는 RESTful API 경로는 다음과 같이 설계하였다.



### 1. 단축 URL 생성 API

- **Method**: POST  
- **Endpoint**: `/api/short-urls`  
- **설명**: 원본 URL을 입력받아 단축 URL을 생성하는 API이다.

#### 설계 고민
처음엔 다음과 같이 간단히 GET 요청에 원본 URL을 쿼리파라미터로 넘기는 방식도 고려했다.

```

GET /api/short-urls?originalUrl=[https://www.example.com/page](https://www.example.com/page)

````

하지만 이 방식은 몇 가지 문제점이 있다.
- **URL 인코딩 이슈**: 긴 원본 URL이 포함될 경우, query string의 길이 제한에 걸릴 수 있다.
- **URL 노출**: 브라우저 캐시나 로그 등에서 민감한 URL 정보가 쉽게 노출될 수 있다.
- **REST 원칙 위배**: 새로운 리소스를 생성하는 작업은 `POST`가 적절하다. `GET`은 부작용 없는 조회에 사용해야 한다.

이러한 이유로, 원본 URL은 본문(body)에 담고, `POST` 방식으로 처리하는 방식을 선택했다.

**요청 예시**
```json
{
  "originalUrl": "https://www.example.com/page"
}
````

**응답 예시**

```json
{
  "shortUrl": "http://localhost:8080/s/AbCdEfGh"
}
```

---

### 2. 단축 URL 리다이렉트 API

* **Method**: GET
* **Endpoint**: `/s/{shortKey}`
* **설명**: 단축된 URL로 요청이 들어오면, 해당 키에 해당하는 원본 URL로 리다이렉트 시키는 API이다.
* **예시 요청**: `/s/AbCdEfGh`
  (서버는 원본 URL로 `302 Redirect` 응답)

#### 설계 고민

실제 서비스에서 사용자들이 이 경로를 직접 브라우저에 입력하거나 공유하게 될 가능성이 높다. 따라서 API 경로는 짧고 단순하게 유지하고자 `/s/`로 명시했다. `short-urls/{key}` 같은 방식도 고려했지만, 사용자에게 보여질 최종 URL이므로 가독성과 간결함이 더 중요하다고 판단했다.

---

### 3. 단축 URL 정보 조회 API

* **Method**: GET
* **Endpoint**: `/api/short-urls/{shortKey}`
* **설명**: 단축 URL에 대한 상세 정보(원본 URL, 리다이렉트 횟수 등)를 조회하는 API이다.

**응답 예시**

```json
{
  "originalUrl": "https://www.example.com/page",
  "redirectCount": 12,
  "createdAt": "2025-07-09T13:00:00"
}
```

#### 설계 고민

해당 API는 서비스 내부 관리자 혹은 사용자 계정이 연동된 경우, 통계 데이터를 확인하기 위한 용도다. RESTful하게 리소스 단위로 관리하고자 `/api/short-urls/{shortKey}` 형태로 구성했다. 데이터 조회는 부작용이 없으므로 `GET`이 적절하다.

</details>