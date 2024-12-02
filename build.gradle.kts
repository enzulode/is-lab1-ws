plugins {
    id("is-lab1.java-conventions")
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springBootDependencyManagement)
}

group = "com.enzulode"
version = "1.0.0-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${libs.versions.springCloudVersion.get()}"))

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.springframework.cloud:spring-cloud-kubernetes-fabric8-discovery")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging.events("PASSED", "SKIPPED", "FAILED")
}