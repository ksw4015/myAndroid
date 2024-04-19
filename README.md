## Android 개발 트랜드 따라잡기
### 1. 클린 아키텍처
1) 프로젝트 모듈화
- App, Presentation, Domain(Optional), Data 계층으로 분리
- 개념 자체가 추상적이라 프로젝트 사이즈와 협업하는 사람들과 상의하에 구조를 설계하면 됨 (Ex. app + presentation)
- Android Framwork에 의존이 있는 계층은 Android Library로 모듈생성하고, Domain 처럼 순수 비즈니스 로직만 있는 모듈은 Java or Kotlin Library로 생성
- 각 모듈에서 다른 모듈을 삽입하고 싶으면 implementation(project(":presentation") <-- 처럼 입력하고 sync
2) buildSrc를 이용한 프로젝트 전체 의존성 관리
- 프로젝트 Root에 buildSrc 폴더 생성
- build.gradle.kts 파일 생성 아래 코드 삽입 후 sync 한번 실행
  ```
  plugins {
    `kotlin-dsl`
	}
	repositories {
	    mavenCentral()
	}
  ```
- 이후 src/java/main 위치에 디펜던시.kt 파일 생성 후 object로 각 의존성의 버전 등 관리
### 2. UI 상태 관리
1) LiveData 안쓰고 StateFlow 쓴다고 함
2) 예전처럼 데이터 타입 각각의 상태를 LiveData로 만들어서 일일히 구독(Observe)하는 방식이 아닌 연관된 것(Component or Widget 단위?)들끼리 UiState 등의 형태로 모델링
3) Enum대신 sealed class, sealed interface 등으로 상태를 만들고 각 상태에 맞게 UI 랜더링
