// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package io.swagger.v3.oas.annotations.tags;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Tags.class)
@Inherited
public @interface Tag {
   String name();

   String description() default "";

   ExternalDocumentation externalDocs() default @ExternalDocumentation;

   Extension[] extensions() default {};
}
