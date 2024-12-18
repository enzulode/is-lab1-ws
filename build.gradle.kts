import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("is-lab1.java-conventions")
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springBootDependencyManagement)
}

group = "com.enzulode"
version = "1.0.0"

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

repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging.events("PASSED", "SKIPPED", "FAILED")
}

tasks.named("bootBuildImage", BootBuildImage::class) {
    imageName = "docker.io/library/${rootProject.name}:v${project.version}"
}