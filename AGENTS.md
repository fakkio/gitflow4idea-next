## Agent skills

### Issue tracker

Issues live in GitHub Issues on `fakkio/gitflow4idea-next` (via the `gh` CLI), never on the upstream `RubinCarter/gitflow4idea-plus`. See `docs/agents/issue-tracker.md`.

### Triage labels

Default vocabulary: `needs-triage`, `needs-info`, `ready-for-agent`, `ready-for-human`, `wontfix`. See `docs/agents/triage-labels.md`.

### Domain docs

Single-context: one `GLOSSARY.md` and `docs/adr/` at the repo root, created lazily. See `docs/agents/domain.md`.

## Build and release

Building a version for the user to test: bump, then build. See `docs/agents/build-and-release.md`.

## Language

This is a public repository: write code, comments, commit messages, issues, pull requests and docs in English, whatever language the conversation is in.

## Commits (gitmoji)

Every commit leads with a gitmoji for its type (`<emoji>: <description>`), Conventional Commits structure otherwise. See `docs/agents/commits.md`.

## Changelog (Keep a Changelog)

Every commit or merge onto `develop` that a user can see adds its entry under `CHANGELOG.md`'s `[Unreleased]` section as it happens; a release renames that section to the version. See `docs/agents/changelog.md`.
