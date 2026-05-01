# Z-Music GitHub Actions Setup

## GitHub Secrets yang wajib diisi

Pergi ke repo GitHub kamu → **Settings → Secrets and variables → Actions → New repository secret**

### Wajib untuk Build & Sign APK:

| Secret Name        | Isi dengan                                                          |
|--------------------|---------------------------------------------------------------------|
| `KEYSTORE`         | File keystore kamu di-encode base64 (lihat cara di bawah)          |
| `KEY_ALIAS`        | Alias key saat buat keystore                                        |
| `KEYSTORE_PASSWORD`| Password keystore                                                   |
| `KEY_PASSWORD`     | Password key                                                        |
| `RELEASE_TOKEN`    | GitHub Personal Access Token (scope: `repo`)                        |

### Opsional:

| Secret Name        | Isi dengan                          |
|--------------------|-------------------------------------|
| `GRADLE_CACHE_KEY` | String random bebas untuk enkripsi cache |
| `DEBUG_KEYSTORE`   | Keystore debug di-encode base64     |
| `LASTFM_API_KEY`   | API Key dari last.fm (opsional)     |
| `LASTFM_SECRET`    | Secret dari last.fm (opsional)      |

---

## Cara encode Keystore ke Base64

### Di Windows (PowerShell):
```powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("C:\path\to\your.keystore")) | Set-Clipboard
```

### Di Mac/Linux:
```bash
base64 -w 0 your-keystore.jks | pbcopy   # Mac
base64 -w 0 your-keystore.jks            # Linux, copy outputnya
```

Paste hasilnya ke secret `KEYSTORE`.

---

## Cara buat Keystore baru (kalau belum punya)

Di Android Studio:
**Build → Generate Signed Bundle/APK → APK → Create new keystore**

Atau via terminal:
```bash
keytool -genkeypair -v \
  -keystore z-music.keystore \
  -alias zmusic \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000
```

---

## Cara buat Personal Access Token (RELEASE_TOKEN)

1. GitHub → Settings (profil) → Developer settings
2. Personal access tokens → Tokens (classic) → Generate new token
3. Centang scope: `repo`
4. Copy token → paste ke secret `RELEASE_TOKEN`

---

## Workflow yang tersedia

| File                     | Fungsi                                              | Trigger                        |
|--------------------------|-----------------------------------------------------|--------------------------------|
| `build.yml`              | Build Release + Debug APK setiap push               | Push ke branch manapun         |
| `release.yml`            | Auto-release ke GitHub Releases saat versi berubah  | Push ke `main` + versi berubah |
| `build_pr.yml`           | Build APK saat ada Pull Request                     | Pull Request                   |
| `build_quick.yml`        | Build cepat tanpa lint (untuk testing)              | Manual / Push                  |
