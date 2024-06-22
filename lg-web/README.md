# 로우지지

## My App 아키텍처
이 문서는 My App의 아키텍처에 대한 설명입니다.

### 구조
My App은 다음과 같은 구조로 구성됩니다.

```text
com.example.myapp
├── AppStartupRunner
├── config
│   ├── security
│   ├── S3
│   └── WebMvcConfigurer
│       ├── properties
│       └── crypto
├── modules
│   ├── newservice1
│   │   ├── controller
│   │   ├── model
│   │   ├── service
│   │   │   ├── NewService.java
│   │   │   └── NewServiceImpl.java
│   │   └── repository
│   └── newservice2
│       ├── controller
│       ├── model
│       ├── service
│       │   ├── NewService.java
│       │   └── NewServiceImpl.java
│       └── repository
├── common
│   ├── enums
│   ├── converters
│   ├── exceptions
│   ├── redis
│   ├── filters
│   └── aop
├── repositories
├── entities
└── dao
```