plugins {
    id("java-library")
    id("com.github.weave-mc.weave-gradle") version "649dba7468"
}

group = "me.ballmc"
version = "1.0.0"

minecraft.version("1.8.9")

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:70bd82faa6")

    compileOnly("org.spongepowered:mixin:0.8.5")

    implementation("com.github.JnCrMx:discord-game-sdk4j:java-impl-SNAPSHOT")

    implementation(files("./libs/pde-4.1.1.jar"))
}

tasks.withType<Jar> {
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.compileJava {
    options.release.set(11)
}
