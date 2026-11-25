# Changelog

All notable changes to Git Flow Integration Next are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Fixed
- Name the base branch in the notification shown after finishing a bugfix, instead of "null" (#20).

## [0.9.7] - 2026-11-25

### Fixed
- Stop the IDE from freezing when the Gitflow status bar widget refreshes: the git config is now read in the background, and once instead of twice (#17).

## [0.9.6] - 2026-11-24

### Changed
- Build the status bar popup with the public `JBPopupFactory` API instead of an internal class scheduled for removal (#10).
- Answer the `git flow init` prompts through the public process API instead of `GitTextHandler` internals scheduled for removal, and stop using the deprecated `ProcessAdapter` (#11, #9).

## [0.9.5] - 2026-11-17

### Changed
- Include the Apache 2.0 license in the plugin.

## [0.9.4] - 2026-11-17

### Fixed
- Stop logging the "No display name specified" error when opening Settings (#1).

## [0.9.3] - 2026-11-16

### Changed
- Correct the plugin description.

## [0.9.2] - 2026-11-16

### Added
- Give the plugin its own icon.

### Changed
- Expand the plugin description.

## [0.9.1] - 2026-11-16

### Changed
- Support all IDE builds from 2024.2 to 2026.3, instead of 2026.3 only.

## [0.9.0] - 2026-11-16

### Added
- Support the 2026.3 IDE build.
- Include the git-flow-next and gitflow-cjs support that was only in the unreleased 0.8.1 betas.

### Changed
- Fork Git Flow Integration Plus and rename it to Git Flow Integration Next.
- Replace the internal and removed platform APIs the plugin was using.

[Unreleased]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.7...HEAD
[0.9.7]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.6...v0.9.7
[0.9.6]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.5...v0.9.6
[0.9.5]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.4...v0.9.5
[0.9.4]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.3...v0.9.4
[0.9.3]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.2...v0.9.3
[0.9.2]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.1...v0.9.2
[0.9.1]: https://github.com/fakkio/gitflow4idea-next/compare/v0.9.0...v0.9.1
[0.9.0]: https://github.com/fakkio/gitflow4idea-next/releases/tag/v0.9.0
