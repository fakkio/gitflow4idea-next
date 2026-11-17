# Changelog: Keep a Changelog + semver

`CHANGELOG.md` at the repo root follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), versioned per [Semantic Versioning](https://semver.org/spec/v2.0.0.html) (the version is picked as in `docs/agents/build-and-release.md`).

## Structure

```
# Changelog

## [Unreleased]

### Added
- Add a shortcut for finishing the current feature.

## [0.9.4] - 2026-11-17

### Fixed
- Stop logging the "No display name specified" error when opening Settings (#1).

[Unreleased]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.4...HEAD
[0.9.4]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.3...v0.9.4
```

Newest first. Once the releases are tagged (`v<version>`, the git-flow version-tag prefix), every version section, including `[Unreleased]`, gets a compare-link footer at the bottom of the file, diffed against the tag directly before it. The 0.9.x sections predate the tags and have no links.

## Categories

Standard Keep a Changelog headers, in this order when present: `Added`, `Changed`, `Deprecated`, `Removed`, `Fixed`, `Security`. Omit a category entirely when nothing in the version needs it.

## Write entries as you go

Every commit or merge that lands on `develop` (a feature or bugfix branch merging in, or a one-off commit going straight to it) adds its entry under `## [Unreleased]` in that same commit or merge. The release step then has nothing left to draft.

- One entry per user-visible change, not per raw commit.
- Write for the plugin's user, not the git log: describe the effect ("Stop logging the error when opening Settings"), not the mechanics ("add displayName to plugin.xml"). Rephrase the underlying commit(s); don't paste their messages.
- Skip commits with no user-visible effect (tooling, CI, tests, docs, style, internal refactors, comments): they earn no changelog line.
- Pick the category from what the change does to the user; the commit's gitmoji (`docs/agents/commits.md`) is a hint, not the rule: ✨→Added, 🐛/🚑️/🩹→Fixed, 🔒️/🔐→Security, 🗑️→Deprecated, 🔥/➖→Removed, anything else user-visible→Changed.
- 💥 breaking changes go in whichever category the change itself belongs to, with the line opening on **Breaking:**.
- Reference the issue as `(#N)` when there is one.

## At release time

Rename `## [Unreleased]` to `## [<version>] - <YYYY-MM-DD>` and open a fresh, empty `## [Unreleased]` above it.

The JetBrains Marketplace shows the `changeNotes` block in `build.gradle.kts`, not `CHANGELOG.md`: add the same entries there as a `<H2>Changelog for <version></H2>` list, at the top of the block.

Hotfix branches start from `main`, which carries no pending `[Unreleased]` entries: add the fix's entry there directly, then rename it the same way. Merging the hotfix into `develop` can conflict with `develop`'s own `[Unreleased]` section: resolve by keeping `[Unreleased]` on top and the hotfix's new version section directly below it.
