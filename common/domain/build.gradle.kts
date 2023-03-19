import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
//    kotlin("jvm") version "1.5.21"
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring") version "1.5.21"
    kotlin("plugin.jpa") version "1.5.21"
}

group = "kr.co.anna.common"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework:spring-webmvc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.querydsl:querydsl-jpa")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.6.2")
    kapt("com.querydsl:querydsl-apt:4.4.0:jpa")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
    archiveBaseName.set("fw-common-domain")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

val generatedQuerydslDir = "$buildDir/generated/source/kapt/main"
// 아래와 같이 지정해도 Q클래스 가 kaptKotlin/querydsl 아래에 생기지 않고 kapt/main 아래에 생성된다
//val generatedQuerydslDir = "$buildDir/generated/source/kaptKotlin/querydsl"

querydsl {
    library = "com.querydsl:querydsl-apt"
    jpa = true
    querydslSourcesDir = generatedQuerydslDir
}

sourceSets["main"].withConvention(KotlinSourceSet::class) {
    kotlin.srcDir(generatedQuerydslDir)
}

