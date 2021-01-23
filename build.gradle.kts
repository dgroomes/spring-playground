/**
 * A convenience task to assemble all of the included projects. This is useful as a quick smoke test of the Gradle config
 * and a general environmental test.
 */
tasks.register("assembleAll") {
    dependsOn(gradle.includedBuild("caching").task(":assemble"))
    dependsOn(gradle.includedBuild("scheduling").task(":assemble"))
    dependsOn(gradle.includedBuild("config").task(":assemble"))
    dependsOn(gradle.includedBuild("bootless").task(":assemble"))
}
