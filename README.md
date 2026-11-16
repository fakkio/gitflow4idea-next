# Git Flow Integration Next

A JetBrains IDE plugin that puts [git-flow](https://nvie.com/posts/a-successful-git-branching-model/) one click away: start, publish, track and finish features, releases, hotfixes and bugfixes without leaving the IDE.

It is a maintained fork of [Git Flow Integration Plus](https://github.com/RubinCarter/gitflow4idea-plus), which stopped loading in recent IDE versions.

![The Gitflow popup in the status bar](docs/img/gitflow.png)

## Compatibility

| | Supported |
|---|---|
| IDEs | Any IntelliJ-based IDE with the bundled Git plugin (IntelliJ IDEA, PhpStorm, WebStorm, Rider, CLion, Android Studio, …) |
| IDE versions | 2024.2 to 2026.3, each checked with the JetBrains Plugin Verifier |
| git-flow | [git-flow-next](https://github.com/gittower/git-flow-next), [git-flow AVH Edition](https://github.com/petervanderdoes/gitflow-avh), gitflow-cjs |

The original `nvie/gitflow` (version `0.4.1`) is not supported.

## Requirements

git-flow must be installed and available as `git flow`. Check with:

```
git flow version
```

- **git-flow-next** is the actively maintained implementation and the recommended one. Install it with `winget install GitTower.GitFlowNext`, `scoop install git-flow-next` or `brew install git-flow-next`.
- **git-flow AVH Edition** ships with Git for Windows, but its repository is archived and no longer receives fixes.

See [GITFLOW_VERSION.md](GITFLOW_VERSION.md) if the plugin reports an unsupported or missing git-flow.

## Installation

1. Uninstall *Git Flow Integration Plus* (or the original *Git Flow Integration*) if you have it. The plugins register the same actions and cannot be installed side by side.
2. Download `gitflow4idea-next-<version>.zip` from the [releases page](https://github.com/fakkio/gitflow4idea-next/releases). Do not unpack it.
3. In the IDE open **Settings > Plugins > ⚙ > Install Plugin from Disk…** and pick the zip.
4. Restart the IDE.

Your keymap and the plugin's options carry over from the previous plugin.

## Usage

Open a project that is a Git repository. A **Gitflow** widget appears in the status bar; it reads **No Gitflow** until the repository is initialised.

- **Status bar widget**: click it for the list of actions that apply to the current branch.
- **Menu**: the same actions live under **Git > Gitflow**.
- **Shortcut**: assign a key to *Gitflow Operations Popup…* in **Settings > Keymap**.

| Branch type | Actions |
|---|---|
| Repository | Initialize, Re-initialize |
| Feature | Start, Finish, Publish, Track |
| Release | Start, Finish, Publish, Track |
| Hotfix | Start, Finish, Publish |
| Bugfix | Start, Finish, Publish, Track |

If a finish stops on a merge conflict, the plugin opens the IDE's merge tool and retries the finish once the conflicts are resolved.

With the bundled *Task Management* plugin, the **Open Task** and **Close Task** dialogs gain Gitflow options to start a feature or hotfix for the task and to finish it on close.

### Options

**Settings > Other Settings > Gitflow** holds the per-project defaults for each branch type, for example:

- fetch from origin before starting or finishing
- keep the local or remote branch after finishing
- always create a merge commit (`--no-ff`)
- push on finish
- skip the tag on release or hotfix, or use a custom tag message (`%name%` expands to the branch name)

## Building from source

You need JDK 25; the JetBrains Runtime bundled with any 2026.3 IDE works (point `JAVA_HOME` at its `jbr` folder).

```
./gradlew buildPlugin                                  # downloads IntelliJ IDEA 2026.3
./gradlew buildPlugin -PplatformLocalPath=<IDE dir>    # or reuse an installed IDE
./gradlew runIde                                       # start a sandbox IDE with the plugin
./gradlew verifyPlugin -PverifyIdes=IC-2024.2.6,IU-2026.1.5.1
```

The zip is written to `build/distributions`.

The plugin is compiled against 2026.3 but emitted as Java 21 bytecode, so that it also loads in older IDEs (`since-build` 242). After touching platform APIs, re-run `verifyPlugin` against the oldest supported version. Each IDE the verifier downloads takes several gigabytes of disk.

## Reporting problems

Open an issue on this repository's [issue tracker](https://github.com/fakkio/gitflow4idea-next/issues), with the IDE version, the output of `git flow version` and the steps to reproduce. Please do not report problems with this fork to the upstream projects.

## Credits

- [Opher Vishnia](https://github.com/OpherV) wrote the original [gitflow4idea](https://github.com/OpherV/gitflow4idea).
- [Rubin Carter](https://github.com/RubinCarter) maintained [Git Flow Integration Plus](https://github.com/RubinCarter/gitflow4idea-plus) and added the git-flow-next and gitflow-cjs support.
- [Kirill Likhodedov](https://github.com/klikh) wrote much of the git4idea plugin this one builds on.

## License

[Apache License 2.0](LICENSE.txt). Copyright 2013-2020 Opher Vishnia and contributors.
