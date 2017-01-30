package se.pesd.jdbc.annotations;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table{
	public String catalog();
	public String schema();
	public String tableName();
	public String tableType() default "TABLE";
	public String remarks() default "";
	public String typeCatalog() default "";
	public String typeSchema() default "";
	public String typeName() default "";
	public String selfReferencingColName() default "";
	public String refGeneration() default "";
}
/*
	public final String TABLE_CAT;// String => table catalog (may be null)
	public final String TABLE_SCHEM;// String => table schema (may be null)
	public final String TABLE_NAME;// String => table name
	public final String TABLE_TYPE;// String => table type. Typical types are
									// "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL
									// TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
									// "SYNONYM".
	public final String REMARKS;// String => explanatory comment on the table
	public final String TYPE_CAT;// String => the types catalog (may be null)
	public final String TYPE_SCHEM;// String => the types schema (may be null)
	public final String TYPE_NAME;// String => type name (may be null)
	public final String SELF_REFERENCING_COL_NAME;// String => name of the
													// designated "identifier"
													// column of a typed table
													// (may be null)
	public final String REF_GENERATION;// String => specifies how values in
										// SELF_REFERENCING_COL_NAME are
										// created. Values are "SYSTEM", "USER",
										// "DERIVED". (may be null)
 */
