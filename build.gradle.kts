import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask.FailureLevel

plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.20.1"
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

group = "com.github.fakkio"
version = "0.9.3"

// Platform to compile against. Pass -PplatformLocalPath=<IDE install dir> to reuse a
// locally installed IDE instead of downloading one.
val platformVersion = providers.gradleProperty("platformVersion").getOrElse("2026.3")
val platformLocalPath = providers.gradleProperty("platformLocalPath").orNull

// Bytecode level: must not exceed the Java runtime of the oldest supported IDE.
val javaRelease = providers.gradleProperty("javaRelease").getOrElse("21").toInt()

tasks.withType<JavaCompile>().configureEach {
    options.release = javaRelease
}

dependencies {
    testImplementation("junit:junit:4.13.2")

    intellijPlatform {
        if (platformLocalPath != null) {
            local(platformLocalPath)
        } else {
            intellijIdea(platformVersion)
        }
        bundledPlugins("Git4Idea", "com.intellij.tasks")
        testFramework(TestFrameworkType.Platform)
        pluginVerifier()
    }
}

intellijPlatform {
    pluginVerification {
        failureLevel = listOf(
            FailureLevel.COMPATIBILITY_PROBLEMS,
            FailureLevel.INTERNAL_API_USAGES,
            FailureLevel.MISSING_DEPENDENCIES,
            FailureLevel.INVALID_PLUGIN,
        )
        // -PverifyIdes=IU-2025.1.7.2,IU-2024.3.7.1   IDEs to download and verify against
        // -PverifyLocalIdes=<dir>;<dir>              installed IDEs to verify against
        ides {
            val remote = providers.gradleProperty("verifyIdes").orNull
            val locals = providers.gradleProperty("verifyLocalIdes").orNull ?: platformLocalPath
            remote?.split(',')?.filter { it.isNotBlank() }?.forEach { notation ->
                create {
                    type = IntelliJPlatformType.fromCode(notation.trim().substringBefore('-'))
                    version = notation.trim().substringAfter('-')
                }
            }
            locals?.split(';')?.filter { it.isNotBlank() }?.forEach { local(it.trim()) }
            if (remote == null && locals == null) {
                recommended()
            }
        }
    }

    // Marketplace upload: set PUBLISH_TOKEN in the environment.
    publishing {
        token = providers.environmentVariable("PUBLISH_TOKEN")
    }

    pluginConfiguration {
        id = "com.github.fakkio.gitflow4idea-next"
        version = project.version.toString()
        ideaVersion {
            sinceBuild = providers.gradleProperty("sinceBuild").getOrElse("242")
            untilBuild = provider { null }
        }
        description = """
            <p>Run <a href="https://nvie.com/posts/a-successful-git-branching-model/">git-flow</a> from the IDE: start, publish, track and finish features, releases, hotfixes and bugfixes from the status bar or the Git menu, without typing <code>git flow</code> commands.</p>

            <p><b>What you get</b></p>
            <ul>
              <li>A <b>Gitflow</b> status bar widget listing the actions that apply to the current branch</li>
              <li>The same actions under <b>Git | Gitflow</b>, each assignable to a shortcut</li>
              <li>Initialize or re-initialize git-flow in a repository, with your own branch names and prefixes</li>
              <li>Per-project defaults for every branch type: fetch before start, keep the branch after finish, <code>--no-ff</code>, push on finish, tag message and more</li>
              <li>Merge conflicts during a finish open the IDE merge tool, then the finish is retried</li>
              <li>Open Task and Close Task dialogs can start and finish a git-flow branch for the task</li>
            </ul>

            <p><b>Requirements</b></p>
            <p>git-flow must be installed and available as <code>git flow</code>. Supported implementations: <a href="https://github.com/gittower/git-flow-next">git-flow-next</a> (recommended, actively maintained), git-flow AVH Edition and gitflow-cjs. The original nvie/gitflow 0.4.1 is not supported.</p>

            <p><b>Why another Git Flow plugin</b></p>
            <p>This is a maintained continuation of <a href="https://github.com/RubinCarter/gitflow4idea-plus">Git Flow Integration Plus</a> by Rubin Carter, itself based on <a href="https://github.com/OpherV/gitflow4idea">gitflow4idea</a> by Opher Vishnia. The released version of Git Flow Integration Plus is flagged as incompatible by IDE versions from 2024.2 onwards; this one is checked with the JetBrains Plugin Verifier on every version from 2024.2 to 2026.3 and ships the git-flow-next support that was only available in unreleased betas. Uninstall the older plugin before installing this one: they register the same actions.</p>

            <p>Source code and issue tracker: <a href="https://github.com/fakkio/gitflow4idea-next">github.com/fakkio/gitflow4idea-next</a></p>
        """
        changeNotes = """
            <H2>Changelog for 0.9.3</H2>
            <ul>
              <li>Corrected the plugin description</li>
            </ul>

            <H2>Changelog for 0.9.2</H2>
            <ul>
              <li>New plugin icon</li>
              <li>Expanded plugin description</li>
            </ul>

            <H2>Changelog for 0.9.1</H2>
            <ul>
              <li>Compatible with all IDE builds from 2024.2 to 2026.3 (was 2026.3 only)</li>
            </ul>

            <H2>Changelog for 0.9.0</H2>
            <ul>
              <li>Forked from Git Flow Integration Plus and renamed to Git Flow Integration Next</li>
              <li>Support for 2026.3 build</li>
              <li>Includes the git-flow-next / gitflow-cjs support from the unreleased 0.8.1 betas</li>
              <li>Replaced internal and removed platform API usages</li>
              <li>Build migrated to IntelliJ Platform Gradle Plugin 2.x</li>
            </ul>

            <H2>Changelog for 0.8.1-beta.2</H2>
            <ul>
              <li>Support git-flow-next implementation #45</li>
              <li>Support gitflow-cjs implementation #34</li>
              <li>Fix "Could not determine git flow version" for Windows users (Git 2.51.1+) #44</li>
              <li>Add git-flow-next config compatibility layer (from PR #46)</li>
              <li>Smart branch detection for init dialog (prioritize main over master)</li>
              <li>Improve init prompt matching for different git-flow implementations</li>
              <li>Improve unsupported version error message with installation guidance</li>
            </ul>

            <H2>Changelog for 0.7.13</H2>
            <ul>
              <li>Fix "(class com.intellij.openapi.project.impl.ProjectImpl) has already been disposed" #29 </li>
            </ul>
            
            <H2>Changelog for 0.7.11</H2>
            <ul>
              <li>Fix "Access is allowed from event dispatch thread only" #17 #21 #19 </li>
            </ul>
            
            <H2>Changelog for 0.7.10</H2>
            <ul>
              <li>Fix "Error during startup" #4</li>
            </ul>
        
            <H2>Changelog for 0.7.9</H2>
            <ul>
              <li>Support for 2022.1 build</li>
            </ul>
        
            <H2>Changelog for 0.7.8</H2>
            <ul>
              <li>Support for 2021.3 build</li>
            </ul>
        
            <H2>Changelog for 0.7.7</H2>
            <ul>
              <li>Fix issue with "Unsupported gitflow version" message presented at startup #328 #329</li>
              <li>Support for 2021.2 build</li>
            </ul>
        
            <H2>Changelog for 0.7.6</H2>
            <ul>
              <li>Fix "Error using shortcuts" #322</li>
              <li>fix Finishing BugFix throws stacktrace #320</li>
              <li>Support for 2021.1 build</li>
            </ul>
        
            <H2>Changelog for 0.7.5</H2>
            <ul>
              <li>PluginException: Icon cannot be found in 'AllIcons.Vcs.CheckOut' #314 (@tumb1er)</li>
              <li>Support for 2020.3 build</li>
            </ul>
        
            <H2>Changelog for 0.7.4</H2>
            <ul>
              <li>Fix deprecations #298 (@fabmars)</li>
              <li>Support for 2020.2 build</li>
            </ul>
        
            <H2>Changelog for 0.7.3</H2>
            <ul>
              <li>Implemented sorting and filtering of track branch dialog #290 (@mmopitz)</li>
              <li>Fix Version 0.7.2 causes that Active Tool Windows only is showed in one project if you have several open #301 (@tumb1er)</li>
              <li>Fix Unsupported Git Flow version Fix #302 (@opherv)</li>
              <li>Fix init settings shown in UI are misleading (do not match default) #283 (@opherv)</li>
            </ul>
        
            <H2>Changelog for 0.7.2</H2>
            <ul>
              <li>Support for Idea build 200 #276 (@fabmars, @tumb1er )</li>
              <li>Fix Icon cannot be found in 'AllIcons.Vcs.' #286 (@fabmars)</li>
              <li>Fix finish release error (Mac OS) #273 (@opherv)</li>
              <li>Breaking 'Search Everywhere' dialog window for projects without git #265 (@opherv)</li>
            </ul>
        
            <H2>Changelog for 0.7.1</H2>
            <ul>
              <li>Support for Idea build 193 #259 (@opherv)</li>
              <li>Check that the user has AVH version of git flow installed, show dialog otherwise #253 (@opherv)</li>
              <li>Add safety which should help fix #249 - Init repo failed #259 (@opherv)</li>
              <li>Fix Memory leak of ProjectImpl and GitRepositoryImpl after projet is closed #255 (@opherv)</li>
              <li>Add icons to actions #232 (@opherv)</li>
            </ul>
        
            <H2>Changelog for 0.7.0</H2>
            <ul>
              <li>Fix NPE when clicking Gitflow menu #245 (@opherv)</li>
              <li>Fix "Init gitflow" doesn't update widget #247 (@opherv)</li>
              <li>Fix Wrong message when finishing a feature #144 (@opherv)</li>
              <li>Feature: Re-init gitflow (access from VCS>Git>Gitflow>Advanced menu) #50 (@bmwsedee/@opherv)</li>
              <li>"Feature": Don't show branch select combo on new Hotfix (@opherv)</li>
            </ul>
        
            <H2>Changelog for 0.6.9</H2>
            <ul>
              <li>Support for Idea build 192 #241 (@opherv)</li>
              <li>Feature: No Fast-forward option is not working #225 (@opherv)</li>
              <li>Hotfix: option -k to keep branch after performing finish #199 (@opherv)</li>
              <li>Exceptions #243 #235 #323 #223 (@bmwsedee)</li>
            </ul>
        
            <H2>Changelog for 0.6.8</H2>
            <ul>
              <li>Support for Idea build 191 #221 (@ottnorml)</li>
              <li>Fix performance issues in plugin #195 (@bmwsedee)</li>
            </ul>
        
            <p>Note - if you see 'no gitflow' in the status bar you will need to re-init using <code>git flow init -f</code></p>
        """
    }
}
