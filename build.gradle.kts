buildscript {
    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
    }

    dependencies {
    }
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
}

dependencies {
}

version = "1.0-SNAPSHOT"

plugins {
    idea
    application
}

application {
    mainClassName = "com.xhstormr.reversecolor.ReverseColor"
}

tasks {
    val beforeJar by creating {
        buildDir
                .resolve("tmp").apply { mkdirs() }
                .resolve("1.txt").apply { createNewFile() }
                .bufferedWriter().use { configurations.compile.forEach { s -> it.write("$s\n") } }
    }

    withType<Jar> {
        dependsOn(beforeJar)
        version = ""
        manifest.attributes["Main-Class"] = "com.xhstormr.reversecolor.ReverseColor"
        from(buildDir.resolve("tmp/1.txt").bufferedReader().readLines().map { zipTree(it) })
    }

    withType<Wrapper> {
        gradleVersion = "4.6"
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.isIncremental = true
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
}
