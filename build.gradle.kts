import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "1.0-SNAPSHOT"

plugins {
    idea
    application
    kotlin("jvm") version "1.3.70"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

application {
    mainClassName = "com.xhstormr.reversecolor.ReverseColor"
}

repositories {
    maven("https://maven.aliyun.com/repository/jcenter")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    withType<Jar> {
        manifest.attributes["Main-Class"] = application.mainClassName
        from(configurations.runtimeClasspath.get().map { zipTree(it) })
    }

    withType<Wrapper> {
        gradleVersion = "6.2.2"
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<Test> {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "12"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
        }
    }

    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.isFork = true
        options.isIncremental = true
        sourceCompatibility = JavaVersion.VERSION_12.toString()
        targetCompatibility = JavaVersion.VERSION_12.toString()
    }
}
