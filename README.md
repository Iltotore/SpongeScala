# SpongeScala
SpongeScala is a Scala-specific layer for the Sponge API.
It includes syntactic sugar and Scala-specific features like pattern matching on some Sponge structures.

# Features
Here is a list of some SpongeScala's features:
- [Misc Features](https://github.com/Iltotore/wiki/Misc)
- [Task DSL](https://github.com/Iltotore/wiki/Task-API)
- [Text API](https://github.com/Iltotore/wiki/Text-API)

# Installing
## Importing the library
You can install this library like other scala libs from a Maven repo.

<details>
<summary>Using Gradle</summary>

```gradle
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.iltotore:sponge-scala_2.13:version'
}
```
</details>

<details>
<summary>Using SBT</summary>

```sbt
libraryDependencies += "io.github.iltotore" %% "sponge-scala" % "version"
```
</details>

## Using the 