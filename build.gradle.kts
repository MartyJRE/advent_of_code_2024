plugins {
    kotlin("jvm") version "2.0.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.11"
    }

    compileJava {
        sourceCompatibility = "22"
        targetCompatibility = "22"
    }
}
