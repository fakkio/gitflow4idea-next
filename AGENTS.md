## Agent skills

### Issue tracker

Issues live in GitHub Issues on `fakkio/gitflow4idea-next` (via the `gh` CLI), never on the upstream `RubinCarter/gitflow4idea-plus`. See `docs/agents/issue-tracker.md`.

### Triage labels

Default vocabulary: `needs-triage`, `needs-info`, `ready-for-agent`, `ready-for-human`, `wontfix`. See `docs/agents/triage-labels.md`.

### Domain docs

Single-context: one `GLOSSARY.md` and `docs/adr/` at the repo root, created lazily. See `docs/agents/domain.md`.

## Building and versioning

Building a new version for the user to test: first bump `version` in `build.gradle.kts` following semver (fix = patch, feature = minor, breaking = major), commit it, then build. Don't hand over a zip with the version that is already published.

The default `./gradlew buildPlugin` fails on this machine (Java 8 by default, and IntelliJ IDEA 2026.3 lacks the bundled `com.intellij.tasks`). Build against the installed PhpStorm with a JDK 17+:

```
JAVA_HOME=C:/Users/Fabio/AppData/Local/Programs/PhpStorm/jbr ./gradlew buildPlugin -PplatformLocalPath="C:/Users/Fabio/AppData/Local/Programs/PhpStorm"
```

The zip lands in `build/distributions/`.
