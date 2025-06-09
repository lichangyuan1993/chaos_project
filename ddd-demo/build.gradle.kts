plugins {
    java
    id("org.springframework.boot") version "3.4.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // springboot
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")


    // mybatis-springboot
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")


    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // mapstruct
    implementation ("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.6.3")
    testAnnotationProcessor ("org.mapstruct:mapstruct-processor:1.6.3")

//    compileOnly("com.github.pagehelper:pagehelper:6.1.0")
    compileOnly("com.github.pagehelper:pagehelper-spring-boot-starter:2.0.0")





    // junit
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // dameng
    // https://mvnrepository.com/artifact/com.dameng/DmJdbcDriver18
    implementation("com.dameng:DmJdbcDriver18:8.1.3.140")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
