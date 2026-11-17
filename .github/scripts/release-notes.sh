#!/usr/bin/env bash
# Prints the GitHub Release notes for a version: its CHANGELOG.md section, how to install,
# the zip's SHA-256 and the compare link. Usage: release-notes.sh <version> <zip>
set -euo pipefail

version="$1"
zip="$2"
changelog="$(dirname "$0")/../../CHANGELOG.md"

section=$(awk -v v="$version" '
  index($0, "## [" v "]") == 1 { found = 1; next }
  /^## \[/ { found = 0 }
  found
' "$changelog" | sed -e '/./,$!d')

if [ -z "$section" ]; then
  echo "No CHANGELOG.md section for $version" >&2
  exit 1
fi

link=$(sed -n "s|^\[$version\]: ||p" "$changelog")

printf '%s\n\n' "$section"
printf '**Install:** Settings | Plugins | ⚙ | Install Plugin from Disk..., then pick the attached zip.\n\n'
printf 'SHA-256 of the zip: `%s`\n' "$(sha256sum "$zip" | cut -d' ' -f1)"
if [ -n "$link" ]; then
  printf '\n**Full changelog:** %s\n' "$link"
fi
