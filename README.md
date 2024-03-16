# TCG Mapper

![CI](https://github.com/great-illuminary/tcg-mapper/actions/workflows/build.yml/badge.svg)
![License](https://img.shields.io/github/license/great-illuminary/tcg-mapper)
![Last Release](https://img.shields.io/github/v/release/great-illuminary/tcg-mapper)

[
![Discord](https://img.shields.io/badge/Discord-Lorcana_Manager-blue)
](https://discord.gg/cd4hRF2PXm)

![badge](https://img.shields.io/badge/json-kotlin-green)
![badge](https://img.shields.io/badge/android-blue)
![badge](https://img.shields.io/badge/ios-white)
![badge](https://img.shields.io/badge/js-yellow)
![badge](https://img.shields.io/badge/jvm-red)
![badge](https://img.shields.io/badge/linux-blue)
![badge](https://img.shields.io/badge/windows-blueviolet)
![badge](https://img.shields.io/badge/mac-orange)

Provide helpers to easily map Trading Card Game into Kotlin Multiplatform libraries / applications

# Installation

```gradle
implementation("eu.codlab:tcg-mapper:$version")
```

This will work on the following platforms :
- Mobile (Android/iOS)
- Web (js)
- Native (MacOS/Linux/Windows)
- JVM (MacOS/Linux/Windows)

## Usage

**Configure the GithubConfiguration to access the raw files remotely**
```
  GithubConfiguration.let {
    it.githubGroup = "your-group"
    it.githubRepo = "your-repo"
  }
```

**Loader & AbstractLoader**

Gives access to decoding from either remotely or locally

```kotlin
val loader: Loader<SWU> = Loader(
    myMokoLocalFile,
    "myMokoLocalFile",
    SWU.serializer(),
    "great-illuminary",
    "some-repo"
)

// ...

suspend fun load(): SWU {
    val loaded: SWU = loader.loadFromGithub("my-feature-branch")
    println("loaded $loaded")
    
    return loaded
}

```

**Http & Serializer provider**

- Provider.yaml
- Provider.json

**TranslationHolder**

Simple class definition to hold multilanguage T object