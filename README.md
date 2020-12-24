# spring-playground

Learning and experimenting with Spring <https://github.com/spring-projects/spring-framework>.

---

## Standalone sub-projects

This repository illustrates different concepts, patterns and examples via standalone sub-projects. Each sub-project is
completely independent of the others and do not depend on the root project. This _standalone sub-project constraint_
forces the sub-projects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The sub-projects include:

### `caching/`

A basic implementation of caching in Spring. In particular, it illustrates the [Spring Boot production ready caching metrics](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics-cache) 
that are automatically enabled for the caches.

See [caching/README.md](caching/README.md).
