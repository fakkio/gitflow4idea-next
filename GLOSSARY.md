# Gitflow

An IntelliJ plugin that drives the git-flow (AVH) branching model on a Git repository: it sets the model up on a repository and runs the feature, release, hotfix, bugfix and support workflows on top of it.

## Language

### Setting up a repository

**Production branch**:
The long-lived branch that holds released code; releases and hotfixes are finished onto it.
_Avoid_: master branch, main branch, trunk

**Development branch**:
The long-lived branch where the next release is integrated; features start from it and are finished onto it.
_Avoid_: develop branch, next-release branch, integration branch

**Base branches**:
The production branch and the development branch together, chosen when a repository is set up for gitflow.
_Avoid_: main branches, permanent branches

**Existing branch**:
A branch that is either local or present only on the origin remote, which git-flow then creates locally from it.
_Avoid_: available branch

**Branch prefix**:
The start of a branch name that marks it as a feature, bugfix, release, hotfix or support branch, such as `feature/`.
_Avoid_: branch namespace, branch folder

**Version tag prefix**:
The start of the name of every tag a finished release or hotfix creates, such as `v`; it may be empty.
_Avoid_: tag prefix

**Default configuration**:
Setting up a repository with the base branches and prefixes the plugin suggests, shown to the user but not editable.
_Avoid_: default mode, git-flow defaults

**Custom configuration**:
Setting up a repository with base branches and prefixes the user chooses.
_Avoid_: non-default configuration
