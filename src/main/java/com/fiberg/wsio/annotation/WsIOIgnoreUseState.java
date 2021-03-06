package com.fiberg.wsio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Annotation used to ignore the annotation {@link WsIOUseState}.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface WsIOIgnoreUseState {

	/** Indicates the elements to be skipped */
	SkipType skip() default SkipType.ALL;

}
