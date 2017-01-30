package se.pesd.jdbc.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The procedure annotation can be used to decorate methods that should be callable.
 * The method must be public but should be able to be static?
 * @author Admin
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Procedure {

	public String catalog();
	public String schema();
	public String procedureName();
}
