# travelB
kotlin, jetpack compose, mvvm, clean architecture 

## Figma 

[Figma Design](https://www.figma.com/file/1zlCdJ8h1E8DivXBJJB84v/prototype?type=design&node-id=1%3A4&mode=design&t=TcmNOIU9fpFOUMvh-1)

제가 생각한 초기 생각한 디자인입니다.

MVP는 구성하지 않았습니다. 

## JetPack Compose 로만 구성하였습니다.
> - MMA로 기존 프로젝트를 페이지를 모듈로서 나누었으며 각각 모듈로서 구현하였습니다.
> - Map - KaKaoMap을 사용하였습니다.
> - 100% Compose로 구성 중 이며 Groovy에서 KTS로 빌드 구성 하였습니다.
> - coroutiones 사용 

## 중요 라이브러리 버젼 정보
> - androidx.compose.material3:material3    = "1.1.1"
> - com.google.accompanist:accompanist-pager: = 0.30.1
> - org.jetbrains.kotlinx:kotlinx-coroutines-android: = "1.7.1"


# AppConfig
> java 17
> minSDK 26



## tree 
```
│  └─modules
│      ├─app
│      ├─common
│      ├─data
│      ├─domain
│      ├─home
│      ├─map
│      ├─plan
│      ├─region
│      └─subway
├─app
│  └─src
│      ├─androidTest
│      │  └─java
│      │      └─kr
│      │          └─tr
│      │              └─travelbproject
│      ├─main
│      │  ├─java
│      │  │  └─kr
│      │  │      └─tr
│      │  │          └─travelbproject
│      │  │              ├─di
│      │  │              ├─navigation
│      │  │              └─ui
│      │  │                  ├─main
│      │  │                  └─viewmodel
│      │  └─res
│      │      ├─drawable
│      │      ├─values
│      │      └─xml
│      └─test
│          └─java
│              └─kr
│                  └─tr
│                      └─travelbproject
├─buildSrc
│  └─src
│      └─main
│          └─java
├─common
│  ├─build
│  └─src
│      ├─androidTest
│      │  └─java
│      │      └─kr
│      │          └─tr
│      │              └─commom
│      ├─main
│      │  ├─java
│      │  │  └─kr
│      │  │      └─tr
│      │  │          └─commom
│      │  │              ├─animation
│      │  │              ├─expection
│      │  │              ├─font
│      │  │              ├─items
│      │  │              │  └─indicator
│      │  │              ├─model
│      │  │              ├─theme
│      │  │              └─utill
│      │  └─res
│      │      ├─drawable
│      │      ├─font
│      │      ├─mipmap-anydpi-v26
│      │      ├─mipmap-hdpi
│      │      ├─mipmap-mdpi
│      │      ├─mipmap-xhdpi
│      │      ├─mipmap-xxhdpi
│      │      ├─mipmap-xxxhdpi
│      │      ├─values
│      │      └─xml
│      └─test
│          └─java
│              └─kr
│                  └─tr
│                      └─commom
├─data
│  ├─build
│  └─src
│      └─main
│          ├─java
│          │  └─kr
│          │      └─tr
│          │          └─data
│          │              ├─api
│          │              ├─error
│          │              ├─mapper
│          │              ├─model
│          │              │  └─item
│          │              ├─repository
│          │              │  ├─datasource
│          │              │  ├─local
│          │              │  └─repositoryimpl
│          │              └─source
│          │                  └─remote
│          └─res
│              └─values
├─domain
│  ├─build
│  └─src
│      ├─main
│      └─java
│           └─kr
│               └─tr
│                  └─domain
│                        ├─datasource
│                        ├─model
│                        │  └─item
│                        ├─repository
│                        │  └─local
│                        ├─usecase
│                        │  └─gateway
│                        └─util
├─home
│  └─src
│      └─main
│          └─java
│              └─kr
│                  └─tr
│                      └─home
│                          ├─data
│                          ├─item
│                          ├─model
│                          │  └─factory
│                          ├─navigation
│                          ├─view
│                          │  ├─mainpage
│                          │  ├─schedule
│                          │  └─ticket
│                          └─widget
├─map
│  ├─build
│  └─src
│      ├─main
│         ├─cpp
│         ├─java
│         └─kr
│            └─com
│                └─map
│                   ├─data
│                    │  ├─entity
│                    │  └─model
│                    ├─item
│                    ├─mapeventlistener
│                    ├─navigation
│                    ├─presentation
│                    │  ├─main
│                    │  └─model
│                    └─util
├─plan
│  └─src
│      ├─main
│      │  └─java
│      │      └─kr
│      │          └─com
│      │              └─plan
│      │                  ├─navigation
│      │                  └─presentation
│      │                      └─view
│      │                          └─page
│      └─test
│          └─java
│              └─kr
│                  └─com
│                      └─plan
└─region
    ├─build
    └─src
        ├─androidTest
        │  └─java
        │      └─kr
        │          └─com
        │              └─subway
        ├─main
        │  └─java
        │      └─kr
        │          └─com
        │              └─region
        │                  ├─item
        │                  ├─navigation
        │                  ├─presentation
        │                  │  ├─model
        │                  │  ├─server
        │                  │  └─view
        │                  │      ├─region
        │                  │      └─subway
        │                  └─util
        └─test
            └─java
                └─kr
                    └─com
                        └─subway
```




https://github.com/naedong/travelB/assets/107181511/5505bc92-1e54-4eec-9113-4511cfeb7fe4

- Main Page
- 보완 사항 Data Shared 가 부족하고
- Local에 저장을 하지 않아 기본적으로 데이터가 사라지면 재 호출을 함 


https://github.com/naedong/travelB/assets/107181511/044ae7f2-ae9d-498f-8349-dbf5f7b15c81

- Plan Page 주기적으로 개발을 하여야 하며 UI만 잡혔습니다.  


https://github.com/naedong/travelB/assets/107181511/0d4dcbe9-340c-40cb-b221-df01fd84ade7

- 기본 UI만 완성


## 보완 사항
### - 다음번에는 Gson 이 아닌 moshi 로 제작할 것 입니다.
### - Clean arhcitect Local 저장 부분을 더욱 보완 할 것입니다.
### - 패키지 명이 달라 명칭을 통일화 시키도록 하겠습니다.
### - 다음 보완 사항에 UpsideDownCake 사용 예정 
### - proguard 를 대신 R 로 사용할 예정입니다. 
