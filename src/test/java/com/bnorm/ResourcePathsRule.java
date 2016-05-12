package com.bnorm;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ResourcePathsRule implements TestRule {

    private Map<String, Path> paths;

    public Path path(String name) {
        return paths.get(name);
    }

    public String string(String name) throws IOException {
        return new Scanner(path(name)).useDelimiter("\\Z").next();
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                paths = new LinkedHashMap<>();

                ResourcePaths resourcePaths = description.getAnnotation(ResourcePaths.class);
                if (resourcePaths != null) {
                    for (ResourcePath resourcePath : resourcePaths.value()) {
                        String path = resourcePath.value();
                        Class<?> testClass = description.getTestClass();
                        URL resource = testClass.getResource(path);

                        assert resource != null : "Failed to load resource: " + path + " from " + testClass;
                        paths.put(path, Paths.get(resource.toURI()));
                    }
                }

                base.evaluate();
            }
        };
    }
}
