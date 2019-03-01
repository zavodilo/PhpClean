import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()
    maven {
        url = uri("http://dl.bintray.com/jetbrains/intellij-plugin-service")
    }
}
buildscript {
    repositories {
        dependencies {
            classpath(kotlin("gradle-plugin", version = "1.3.21"))
        }
    }
}
plugins {
    id("org.jetbrains.intellij") version "0.3.5"
    id("org.jetbrains.kotlin.jvm") version "1.3.21"
    idea
}
apply {
    plugin("java")
    plugin("kotlin")
    plugin("org.jetbrains.intellij")
}

if (file("local.properties").exists()) {
    apply(from = "local.properties")
}
println("Version: ${version}")
//tasks {
//    patchPluginXml {
//        changeNotes(project.property("changeNotes").toString().replace("\n", "<br>\n"))
//    }
//}
intellij {
    version = prop("ideaVersion")
    sandboxDirectory = project.rootDir.canonicalPath + "/build/idea-sandbox"
    downloadSources = false
    updateSinceUntilBuild = false
    pluginName = name
    setPlugins(
            "com.jetbrains.php:${prop("phpPluginVersion")}",
            "CSS",
            "java-i18n",
            "PsiViewer:3.28.93",
            "properties"
    )
}
//jar.archiveName = "${name}.jar"
//
//task patchRepositoryXml (type: Copy) {
//    from "src/ci/PhpClean-nightly.xml"
//    into "${buildDir}/libs"
//    expand([
//        buildDate : System. currentTimeMillis (),
//    fileSize  : "18000",
//    version   : project.property("version").toString(),
//    pluginName: project.property("pluginName"),
//    fileName  : jar.archiveName,
//    group     : project.property("group")
//    ])
//    doFirst {
//        ant.defaultexcludes remove : "${buildDir}/libs/*.xml"
//    }
//}
//task deployNightly (type: Exec) {
//    def ci_deploy_uri = project . hasProperty ("ci_deploy_uri") ? project.property("ci_deploy_uri").toString() : System.getenv("DEPLOY_URI")
//    commandLinex "curl", "-s", "-F", "file[]=@build/libs/PhpClean.jar", "-F", "file[]=@build/libs/PhpClean-nightly.xml", "${ci_deploy_uri}"
//}

dependencies {
    implementation(kotlin("stdlib"))
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

fun prop(name: String): String {
    return extra.properties[name] as? String
            ?: error("Property `$name` is not defined in gradle.properties")
}