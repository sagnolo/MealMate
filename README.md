# MealMate 🍽️  
식사를 기록하고, 돌아보고, 습관을 설계합니다.

MealMate는 식단을 기록하고 날짜별로 회고하며, 건강한 식습관을 유지할 수 있도록 도와주는 Android 앱입니다. 최신 Jetpack Compose UI와 Clean Architecture, 모듈화 구조를 기반으로 개발되었습니다.

---

## ✅ 프로젝트 목표

- 기능 중심 멀티모듈 구조 설계
- MVVM + Clean Architecture 적용
- 테스트 가능한 구조 (단위 테스트 기반)
- Hilt를 활용한 의존성 주입
- Firebase와의 연동을 고려한 확장성 확보
- GitHub Actions 기반 CI/CD (예정)

---

## 🧭 주요 기능

| 기능 | 설명 |
|------|------|
| 식단 입력 | 식사 제목, 칼로리, 시간대, 날짜 등을 입력하고 저장 |
| 날짜별 식단 조회 | 캘린더 화면에서 특정 날짜를 선택해 해당 식단 확인 |
| 설정 | 다크모드 토글, 알림 설정 등을 DataStore에 저장 |
| 확장성 | Firebase, GPT API 연동, 이미지 저장 등의 확장 고려 중 |

---

## 📦 모듈 구조

```
MealMate/
├── app/                  # 앱 진입점 (MainActivity, NavHost 등)
├── core/
│   ├── model/            # 공통 모델 정의 (Meal, Nutrient 등)
│   ├── domain/           # 비즈니스 로직 계층 (UseCase, Repository 인터페이스)
│   ├── data/             # Room, Retrofit 등의 구현체 (RepositoryImpl 등)
│   └── ui/               # 공통 UI 컴포넌트 및 테마
├── feature/
│   ├── input/            # 식단 입력 화면
│   ├── calendar/         # 날짜별 식단 조회 화면
│   └── setting/          # 설정 화면 (다크모드 등)
├── test-support/         # 테스트용 FakeRepository, 테스트 헬퍼 등
```

---

## 🛠 기술 스택 및 도구

| 항목 | 내용 |
|------|------|
| 언어 | Kotlin |
| UI | Jetpack Compose, Material3 |
| 아키텍처 | MVVM, Clean Architecture, Multi Module |
| DI | Hilt |
| DB | Room, DataStore (설정 저장) |
| 비동기 | Kotlin Coroutines, Flow |
| 네트워크 | Retrofit2, OkHttp3 (예정) |
| 테스트 | JUnit5, MockK, Turbine (예정) |
| 빌드 시스템 | Gradle Kotlin DSL |
| CI/CD | GitHub Actions, Firebase App Distribution (예정) |

---

## 🧱 계층 아키텍처 흐름

```
[ View (Compose) ]
       ↓
[ ViewModel (StateFlow) ]
       ↓
[ UseCase ]
       ↓
[ Repository Interface (core:domain) ]
       ↓
[ RepositoryImpl (Room/Retrofit - core:data) ]
       ↓
[ Local DB (Room), API (Retrofit) ]
```

---

## ✅ 개발 진행 현황

- [x] 프로젝트 모듈 구조 및 Gradle 설정 완료
- [x] Meal 모델 및 Repository 구조 설계
- [x] 식단 입력 기능 구현 (ViewModel + UI)
- [x] 캘린더 기반 조회 기능 연결
- [x] DataStore 설정 저장 기능 구현
- [ ] 테스트 코드 작성 (UseCase, ViewModel)
- [ ] Firebase 연동 (Auth, Firestore, Storage)
- [ ] GitHub Actions CI/CD 연동

---

## 📄 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다.