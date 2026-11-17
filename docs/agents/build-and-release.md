# Build and release

Hand the user a zip only after the version has been bumped. `buildPlugin` enforces this: it fails when `src/` changed after the commit that last set `version`.

## Steps

1. Bump `version` in `build.gradle.kts` by semver: fix = patch, feature = minor, breaking = major. Add a `Changelog for X.Y.Z` entry to `changeNotes` and commit (history has `release X.Y.Z` commits to follow).
2. Build with the JDK bundled in PhpStorm (the default Java here is 8, Gradle needs 17+):

   ```
   JAVA_HOME=C:/Users/Fabio/AppData/Local/Programs/PhpStorm/jbr ./gradlew buildPlugin
   ```

3. Report the full path of the zip in `build/distributions/`.

Done when the zip's filename carries the bumped version and `BUILD SUCCESSFUL` was seen.

## The tasks plugin

The plugin depends on `com.intellij.tasks`. PhpStorm and the older IDEs bundle it. IntelliJ IDEA 2026.3 bundles only `com.intellij.platform.tasks` (the API classes), so `build.gradle.kts` takes `com.intellij.tasks` from the Marketplace for the default platform. With `-PplatformLocalPath=<IDE dir>` the local IDE is used as it is.

## CI

`.github/workflows/build.yml` runs `buildPlugin verifyPlugin` on push and pull requests, against the default platform, and verifies on IC-2024.2.6 and PS-2026.3. The version check is skipped there (`CI` is set); locally, `-PskipVersionCheck` skips it.

Each IDE the verifier downloads takes several gigabytes: check free disk before adding one. Verifying on IU-2026.3 reports `com.intellij.tasks: Unavailable`.
