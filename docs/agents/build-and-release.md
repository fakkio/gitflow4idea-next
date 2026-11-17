# Build and release

Hand the user a zip only after the version has been bumped. `buildPlugin` enforces this: it fails when `src/` changed after the commit that last set `version`.

## Steps

1. Bump `version` in `build.gradle.kts` by semver: fix = patch, feature = minor, breaking = major. Promote `CHANGELOG.md`'s `[Unreleased]` section to the new version and mirror it into `changeNotes` (`docs/agents/changelog.md`). Commit as `🔖: Release v<version>` (`docs/agents/commits.md`).
2. Build with the JDK bundled in PhpStorm (the default Java here is 8, Gradle needs 17+):

   ```
   JAVA_HOME=C:/Users/Fabio/AppData/Local/Programs/PhpStorm/jbr ./gradlew buildPlugin
   ```

3. Report the full path of the zip in `build/distributions/`.

Done when the zip's filename carries the bumped version and `BUILD SUCCESSFUL` was seen.

## Releasing

1. `git flow release start <version>`, then bump the version, promote the changelog and commit as `🔖: Release v<version>` (step 1 above), and check that `buildPlugin` passes.
2. Finish with `git flow release finish -m "🔖: Release v<version>" <version>`. To get gitmoji merge messages, run it with `GIT_MERGE_AUTOEDIT=yes` and `GIT_EDITOR` set to a `sed -i` script that rewrites the first line (`Merge branch 'release/X'` becomes `🔀: Merge release/X into main`; `Merge tag 'vX' into develop` becomes `🔀: Merge tag vX into develop`).
3. Push `main`, `develop` and the tag: `git push origin main develop v<version>`.
4. The tag triggers `.github/workflows/release.yml`: it checks that the tag matches `version`, builds the zip and creates a **draft** GitHub Release with the zip attached and the notes from `CHANGELOG.md` (`.github/scripts/release-notes.sh`). Check the draft, then publish it with `gh release edit v<version> --draft=false`. Drafts show up only for people with write access, not in `gh release list`.

Not automated: publishing to the JetBrains Marketplace (needs `PUBLISH_TOKEN`).

## The tasks plugin

The plugin depends on `com.intellij.tasks`. PhpStorm and the older IDEs bundle it. IntelliJ IDEA 2026.3 bundles only `com.intellij.platform.tasks` (the API classes), so `build.gradle.kts` takes `com.intellij.tasks` from the Marketplace for the default platform. With `-PplatformLocalPath=<IDE dir>` the local IDE is used as it is.

## CI

`.github/workflows/build.yml` runs `buildPlugin verifyPlugin` on push and pull requests, against the default platform, and verifies on IC-2024.2.6 and PS-2026.3. The version check is skipped there (`CI` is set); locally, `-PskipVersionCheck` skips it.

Each IDE the verifier downloads takes several gigabytes: check free disk before adding one. Verifying on IU-2026.3 reports `com.intellij.tasks: Unavailable`.
