pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CiteIt"
include(":app")

include(":base:uikit")

include(":common:navigation")
include(":common:navigation:api")

include(":data:common-data")
include(":data:quote-data")

include(":domain:quote-domain")

include(":feature:main-feature")
include(":feature:start-feature")
 