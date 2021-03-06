// Generated by delombok at Sun Feb 26 12:31:38 KST 2017
package scouter.bytebuddy.matcher;

import scouter.bytebuddy.description.type.TypeDefinition;
import scouter.bytebuddy.description.type.TypeDescription;

import java.util.HashSet;
import java.util.Set;

/**
 * An element matcher that matches a super type.
 *
 * @param <T> The type of the matched entity.
 */
public class HasSuperTypeMatcher<T extends TypeDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    /**
     * The matcher to apply to any super type of the matched type.
     */
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    /**
     * Creates a new matcher for a super type.
     *
     * @param matcher The matcher to apply to any super type of the matched type.
     */
    public HasSuperTypeMatcher(ElementMatcher<? super TypeDescription.Generic> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(T target) {
        Set<TypeDescription> checkedInterfaces = new HashSet<TypeDescription>();
        for (TypeDefinition typeDefinition : target) {
            if (matcher.matches(typeDefinition.asGenericType()) || hasInterface(typeDefinition, checkedInterfaces)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Matches a type's interfaces against the provided matcher.
     *
     * @param typeDefinition    The type for which to check all implemented interfaces.
     * @param checkedInterfaces The interfaces that have already been checked.
     * @return {@code true} if any interface matches the supplied matcher.
     */
    private boolean hasInterface(TypeDefinition typeDefinition, Set<TypeDescription> checkedInterfaces) {
        for (TypeDefinition interfaceType : typeDefinition.getInterfaces()) {
            if (checkedInterfaces.add(interfaceType.asErasure()) && (matcher.matches(interfaceType.asGenericType()) || hasInterface(interfaceType, checkedInterfaces))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "hasSuperType(" + matcher + ")";
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof HasSuperTypeMatcher)) return false;
        final HasSuperTypeMatcher<?> other = (HasSuperTypeMatcher<?>) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$matcher = this.matcher;
        final java.lang.Object other$matcher = other.matcher;
        if (this$matcher == null ? other$matcher != null : !this$matcher.equals(other$matcher)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof HasSuperTypeMatcher;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $matcher = this.matcher;
        result = result * PRIME + ($matcher == null ? 43 : $matcher.hashCode());
        return result;
    }
}
