# Git Flow Integration Next for IntelliJ

> Maintained fork of [Git Flow Integration Plus](https://github.com/RubinCarter/gitflow4idea-plus) by Rubin Carter (itself a fork of [gitflow4idea](https://github.com/OpherV/gitflow4idea) by Opher Vishnia), kept compatible with current JetBrains IDE builds (verified from 2024.2 to 2026.3). All credit for the original work goes to them.


An intelliJ plugin providing a UI layer for git-flow, which in itself is a collection of Git extensions to provide high-level repository operations for Vincent [Driessen's branching model](http://nvie.com/git-model).

![screenshot](https://github.com/fakkio/gitflow4idea-next/blob/develop/docs/img/gitflow.png)

## Companion agent skill (experimental)

Using an AI coding agent? Check out **[gitflow-cli-plus](https://github.com/RubinCarter/gitflow-cli-plus)** — an agent skill companion to this plugin, derived from a functional analysis of its semantics. It ships a `SKILL.md` that teaches agents the same git-flow operations (init / feature / release / hotfix / bugfix), backed by a small Go CLI binary, follows the AVH edition semantics and config layout, and recognizes repos initialized by git-flow-next through a read-only compatibility layer.

**Note: this skill project is currently experimental** — expect rough edges, and please report anything you find in its [issue tracker](https://github.com/RubinCarter/gitflow-cli-plus/issues).

## Building

Requires a JDK 25 (the JetBrains Runtime bundled with any 2026.3 IDE works):

```
./gradlew buildPlugin                                  # downloads IntelliJ IDEA 2026.3
./gradlew buildPlugin -PplatformLocalPath=<IDE dir>    # or reuse an installed IDE
./gradlew verifyPlugin -PverifyIdes=IC-2024.2.6,IU-2026.1.5.1   # check other IDE versions
```

The plugin is compiled against 2026.3 but emitted as Java 21 bytecode so that it also loads in older IDEs (since-build 242). When touching platform APIs, re-run `verifyPlugin` against the oldest supported version.

The zip is written to `build/distributions`.

## Getting started

For the best introduction to get started with `git flow`, please read Jeff Kreeftmeijer's blog post:

[http://jeffkreeftmeijer.com/2010/why-arent-you-using-git-flow/](http://jeffkreeftmeijer.com/2010/why-arent-you-using-git-flow/)

Or have a look at this [cheat sheet](http://danielkummer.github.io/git-flow-cheatsheet/) by Daniel Kummer:

Huge shoutout [to Kirill Likhodedov](https://github.com/klikh), who wrote much of the original git4idea plugin, without which this plugin could not exist

## Online Installation

Download the latest zip from the [releases page](https://github.com/fakkio/gitflow4idea-next/releases) and install it from disk (see below). Uninstall "Git Flow Integration Plus" first: the two plugins cannot be installed side by side.

**The plugin requires that you have gitflow installed, either the [AVH edition](https://github.com/petervanderdoes/gitflow), [git-flow-next](https://github.com/gittower/git-flow-next) or gitflow-cjs. This is because the [Vanilla Git Flow](https://github.com/nvie/gitflow) hasn't been maintained in years.** See this page [for details](https://github.com/fakkio/gitflow4idea-next/blob/develop/GITFLOW_VERSION.md)

## Offline Installation
download path: https://github.com/fakkio/gitflow4idea-next/releases

Installation document:https://www.jetbrains.com/help/idea/managing-plugins.html#install_plugin_from_disk

**The plugin requires that you have gitflow installed, either the [AVH edition](https://github.com/petervanderdoes/gitflow), [git-flow-next](https://github.com/gittower/git-flow-next) or gitflow-cjs. This is because the [Vanilla Git Flow](https://github.com/nvie/gitflow) hasn't been maintained in years.** See this page [for details](https://github.com/fakkio/gitflow4idea-next/blob/develop/GITFLOW_VERSION.md)

## Caveats

While the plugin is operational and contains all basic functions (init/feature/release/hotfix), it may contains bugs. With your help I'll be able to find and zap them all.

## Helping out

This project is under active development.
If you encounter any bug or an issue, I encourage you to add the them to the [Issues list](https://github.com/fakkio/gitflow4idea-next/issues) on Github.
Feedback and suggestions are also very welcome.

## License

This plugin is under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).
Copyright 2013-2020, Opher Vishnia.

## Who and why

This plugin was created by [Opher Vishnia](http://www.opherv.com), after I couldn't find any similar implementation.
I saw this [suggestion page](http://youtrack.jetbrains.com/issue/IDEA-65491) on the JetBrains site has more than 220 likes and 80 comments, and decided to take up the gauntlet :)

This plugin is forked from original https://github.com/OpherV/gitflow4idea .



