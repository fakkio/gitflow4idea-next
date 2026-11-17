# Build and release

Hand the user a zip only after the version has been bumped. `buildPlugin` enforces this: it fails when `src/` changed after the commit that last set `version`.

## Steps

1. Bump `version` in `build.gradle.kts` by semver: fix = patch, feature = minor, breaking = major. Commit it (history has `release X.Y.Z` commits to follow).
2. Build against the installed PhpStorm with its bundled JDK:

   ```
   JAVA_HOME=C:/Users/Fabio/AppData/Local/Programs/PhpStorm/jbr ./gradlew buildPlugin -PplatformLocalPath="C:/Users/Fabio/AppData/Local/Programs/PhpStorm"
   ```

3. Report the full path of the zip in `build/distributions/`.

Done when the zip's filename carries the bumped version and `BUILD SUCCESSFUL` was seen.

## Why this exact command

The default `./gradlew buildPlugin` fails on this machine: the default Java is 8 (Gradle needs 17+), and the IntelliJ IDEA 2026.3 it downloads no longer bundles `com.intellij.tasks` (only `com.intellij.platform.tasks`; PhpStorm has both). The README documents `-PplatformLocalPath` in general; the values above are the ones that work here.

## CI

`.github/workflows/build.yml` runs `buildPlugin verifyPlugin` on push and pull requests, against IDEA 2025.2.4 (`-PplatformVersion`, still has the tasks plugin) and verifies on IC-2024.2.6. The version check is skipped there (`CI` is set). Locally, `-PskipVersionCheck` skips it.
