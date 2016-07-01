package fr.cedricsevestre.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
public @interface BOField {
	public enum ValueType {
		NULL, 
		INTEGER, FLOAT, DOUBLE, 
		VARCHAR50, VARCHAR255, TEXT, HTML,
		DATETIME, DATE, TIME, 
		IMAGE, FILE, 
		TOBJECT, NTOBJECT, OBJECT, 
		COLLECTION
	}

	ValueType type();

	ValueType ofType() default ValueType.NULL;

	boolean inList() default true;
}
