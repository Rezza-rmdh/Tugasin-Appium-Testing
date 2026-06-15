# TugasinApp-Automation 🤖

Project automation testing untuk aplikasi [Tugasin](https://github.com/Rezza-rmdh/Tugasin.git) menggunakan framework Appium dengan bahasa pemrograman Java. Testing dilakukan pada aplikasi Android berbasis Jetpack Compose.

---

## Teknologi

- **Framework** : Appium 8.6.0
- **Bahasa** : Java 11
- **Build Tool** : Maven 3.9.16
- **Test Runner** : TestNG 7.9.0
- **Design Pattern** : Page Object Model (POM)

---

## Prasyarat

Pastikan semua tools berikut sudah terinstall sebelum menjalankan test:

| Tools | Versi | Keterangan |
|---|---|---|
| Java JDK | 11 | Disarankan JDK 11 LTS |
| Maven | 3.9.x | Build tool |
| Node.js | 18+ | Diperlukan oleh Appium |
| Appium | 2.x | Automation framework |
| Appium Driver | UiAutomator2 | Driver untuk Android |
| Android SDK | - | Untuk emulator/device |
| Emulator/Device | Android 7+ (API 24+) | Target test |

---

## Instalasi

### 1. Install Node.js
Download di [nodejs.org](https://nodejs.org) lalu verifikasi:
```bash
node -v
npm -v
```

### 2. Install Appium
```bash
npm install -g appium
appium -v
```

### 3. Install UiAutomator2 Driver
```bash
appium driver install uiautomator2
```

### 4. Verifikasi Appium Doctor
```bash
npm install -g appium-doctor
appium-doctor --android
```

### 5. Clone Repository
```bash
git clone https://github.com/Rezza-rmdh/Tugasin-Appium-Testing.git
cd Tugasin-Appium-Testing
```

### 6. Install Dependencies Maven
```bash
mvn clean install -DskipTests
```

---

## Konfigurasi

### Sesuaikan Path APK di `BaseTest.java`
```java
// Opsi 1 — path relatif (jika TugasinApp dan TugasinApp-Automation satu folder)
options.setApp(System.getProperty("user.dir") + "/../TugasinApp/..../app-debug.apk");

// Opsi 2 — path absolut
options.setApp("C:/Users/User/Documents/TugasinApp/..../app-debug.apk");
```

### Pastikan Emulator Sudah Menyala
```bash
# Cek device yang terhubung
adb devices
```
Harus muncul minimal 1 device/emulator dengan status `device`.

---

## Struktur Project

```
TugasinApp-Automation/
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java          ← setup & teardown Appium driver
│       │   ├── pages/
│       │   │   ├── HomePage.java          ← locator & method Home screen
│       │   │   ├── AddTaskPage.java       ← locator & method Add Task screen
│       │   │   └── EditTaskPage.java      ← locator & method Edit Task screen
│       │   └── tests/
│       │       ├── AddTaskTest.java       ← positive & negative test
│       │       ├── EditTaskTest.java      ← positive test
│       │       ├── DeleteTaskTest.java    ← positive test
│       │       ├── FilterTaskTest.java    ← functional test
│       │       └── TaskStatusTest.java    ← state change test
│       └── resources/
│           └── testng.xml                 ← konfigurasi urutan test suite
├── pom.xml                                ← Maven + dependencies
├── .gitignore
└── README.md
```

---

## Test Cases

### AddTaskTest — 3 Test Case
| No | Nama Test | Jenis | Deskripsi |
|---|---|---|---|
| 1 | `testAddTaskSuccess` | Positive | Tambah task lengkap dengan judul, deskripsi, kategori, dan deadline |
| 2 | `testAddTaskWithoutTitle` | Negative | Simpan task tanpa judul → error judul muncul |
| 3 | `testPriorityAutoCalculated` | Functional | Pilih kategori UAS + deadline besok → priority otomatis HIGH |

### EditTaskTest — 3 Test Case
| No | Nama Test | Jenis | Deskripsi |
|---|---|---|---|
| 1 | `testEditTaskSuccess` | Positive | Edit judul, kategori, dan deadline → perubahan tersimpan |
| 2 | `testEditTaskClearTitle` | Negative | Hapus judul saat edit → error judul muncul |
| 3 | `testEditTaskPriorityRecalculated` | Functional | Ubah kategori ke UAS + deadline besok → priority berubah ke HIGH |

### DeleteTaskTest — 2 Test Case
| No | Nama Test | Jenis | Deskripsi |
|---|---|---|---|
| 1 | `testDeleteTaskConfirm` | Positive | Tap hapus → konfirmasi di dialog → task terhapus |
| 2 | `testDeleteTaskCancel` | Positive | Tap hapus → batal di dialog → task tetap ada |

### FilterTaskTest — 5 Test Case
| No | Nama Test | Jenis | Deskripsi |
|---|---|---|---|
| 1 | `testFilterByPriorityHigh` | Functional | Filter HIGH → hanya task HIGH tampil |
| 2 | `testFilterByPriorityLow` | Functional | Filter LOW → hanya task LOW tampil |
| 3 | `testFilterByStatusActive` | Functional | Filter Active → semua task active tampil |
| 4 | `testFilterByStatusCompleted` | Functional | Filter Completed → empty state (belum ada yang selesai) |
| 5 | `testSortByPriority` | Functional | Sort by priority → urutan HIGH, MEDIUM, LOW |

### TaskStatusTest — 3 Test Case
| No | Nama Test | Jenis | Deskripsi |
|---|---|---|---|
| 1 | `testMarkTaskAsCompleted` | State Change | Centang checkbox → task muncul di filter completed |
| 2 | `testMarkTaskAsActive` | State Change | Centang dua kali → task kembali muncul di filter active |
| 3 | `testCompletedTaskAppearInFilter` | State Change | Mark task 1 selesai → filter completed → task 1 ada, task 2 tidak |

**Total: 16 Test Case**

---

## Cara Menjalankan Test

### 1. Jalankan Appium Server
Buka terminal baru dan jalankan:
```bash
appium
```
Pastikan muncul:
```
Appium REST http interface listener started on 0.0.0.0:4723
```

### 2. Jalankan Emulator atau Hubungkan Device
```bash
# Cek device
adb devices
```

### 3. Jalankan Semua Test
Buka terminal baru di folder project, lalu:
```bash
mvn test
```

### 4. Jalankan Test Class Tertentu
```bash
mvn test -Dtest=AddTaskTest
mvn test -Dtest=EditTaskTest
mvn test -Dtest=DeleteTaskTest
mvn test -Dtest=FilterTaskTest
mvn test -Dtest=TaskStatusTest
```

### 5. Jalankan Test Method Tertentu
```bash
mvn test -Dtest=AddTaskTest#testAddTaskSuccess
```

---

## Cara Kerja Test

Setiap test method berjalan **independen** dengan alur:

```
@BeforeMethod (dari BaseTest)
    → Install & launch APK (fresh state)
    → Setup data awal via @BeforeMethod (dari masing-masing test class)
         → AddTaskTest    : tidak perlu data awal
         → EditTaskTest   : tambah 1 task
         → DeleteTaskTest : tambah 1 task
         → FilterTaskTest : tambah 3 task (LOW, MEDIUM, HIGH)
         → TaskStatusTest : tambah 2 task
@Test
    → Jalankan skenario test
    → Verifikasi hasil dengan Assert
@AfterMethod (dari BaseTest)
    → Quit driver
    → App di-reset untuk test berikutnya
```

---

## Aplikasi yang Diuji

🔗 Repo Aplikasi: [Tugasin](https://github.com/Rezza-rmdh/Tugasin.git)

