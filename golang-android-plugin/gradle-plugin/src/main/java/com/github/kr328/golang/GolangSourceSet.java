package com.github.kr328.golang;

import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;

public abstract class GolangSourceSet {
    private final String name;

    public GolangSourceSet(String name) {
        this.name = name;
    }

    public abstract DirectoryProperty getSrcDir();

    public abstract ListProperty<String> getTags();

    public abstract Property<String> getFileName();

    public abstract Property<String> getPackageName();

    public String getName() {
        return name;
    }
}
