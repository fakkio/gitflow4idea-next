# Build and release

Hand the user a zip only after the version has been bumped.

## Steps

1. Bump `version` in `build.gradle.kts` by semver: fix = patch, feature = minor, breaking = major. Commit it (history has `release X.Y.Z` commits to follow).
2. Build against the installed PhpStorm with its bundled JDK:

   ```
   JAVA_HOME=C:/Users/Fabio/AppData/Local/Programs/PhpStorm/jbr ./gradlew buildPlugin -PplatformLocalPath="C:/Users/Fabio/AppData/Local/Programs/PhpStorm"
   ```

3. Report the full path of the zip in `build/distributions/`.

Done when the zip's filename carries the bumped version and `BUILD SUCCESSFUL` was seen.

## Why this exact command

The default `./gradlew buildPlugin` fails on this machine: the default Java is 8 (Gradle needs 17+), and the IntelliJ IDEA 2026.3 it downloads lacks the bundled plugin `com.intellij.tasks`. The README documents `-PplatformLocalPath` in general; the values above are the ones that work here.
