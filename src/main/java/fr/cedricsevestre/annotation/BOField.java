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
		IMAGE, FILE, URL, 
		TOBJECT, NTOBJECT, OBJECT, 
		COLLECTION
	}
	public enum SortType {
		NULL, ASC, DESC
	}

	ValueType type();

	ValueType ofType() default ValueType.NULL;

	boolean inList() default true;
	
	SortType sortBy() default SortType.NULL;
	int sortPriority() default 100;
	
	boolean defaultField() default false;
	
	
}
