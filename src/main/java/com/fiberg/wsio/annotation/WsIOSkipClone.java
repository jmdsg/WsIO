package com.fiberg.wsio.annotation;

import java.lang.annotation.*;

/**
 * Annotation to skip clone of types {@link WsIOClone}.
 */
@Repeatable(WsIOSkipClones.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface WsIOSkipClone {

	/** Prefix name of the cloned class */
	String prefix();

	/** Suffix name of the cloned class */
	String suffix();

	/** Indicates the elements to be skipped */
	SkipType skip() default SkipType.ALL;

}
