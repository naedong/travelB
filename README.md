# travelB
kotlin, jetpack compose, mvvm, clean architecture 

## JetPack Compose 로만 구성하였습니다.
> - MMA로 기존 프로젝트를 페이지를 모듈로서 나누었으며 각각 모듈로서 구현하였습니다.
> - Map - KaKaoMap을 사용하였습니다.
> - 연습용이기에 시간이 날 때 업데이트 시키도록 하겠습니다.

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

## 보완 사항
### - 다음번에는 Gson 이 아닌 moshi 로 제작할 것 입니다.
### - Clean arhcitect Local 저장 부분을 더욱 보완 할 것입니다.
### - 패키지 명이 달라 명칭을 통일화 시키도록 하겠습니다.



https://github.com/naedong/travelB/assets/107181511/5505bc92-1e54-4eec-9113-4511cfeb7fe4


