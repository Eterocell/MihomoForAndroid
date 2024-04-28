package com.github.kr328.golang;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;

public interface GolangExtension {
    NamedDomainObjectContainer<GolangSourceSet> getSourceSets();

    default void sourceSets(Action<NamedDomainObjectContainer<GolangSourceSet>> action) {
        action.execute(getSourceSets());
    }
}
