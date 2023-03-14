package exercises.chapter20;

import annotations.database.Constraints;
import annotations.database.DBTable;
import annotations.database.SQLInteger;
import annotations.database.SQLString;
import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.util.*;

import java.util.*;

import static com.sun.mirror.util.DeclarationVisitors.*;

/**
 * Exercise 3
 * Add support for more SQL types to TableCreationProcessorFactory.java
 * {Exec: apt -factory
 * exercises.chapter20.E03_TableCreationProcessorFactory2
 * exercises/chapter20/Member.java -s database}
 */
public class E03_TableCreationProcessorFactory2 implements AnnotationProcessorFactory {
    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    @Override
    public Collection<String> supportedOptions() {
        return Arrays.asList(
                "annotations.database.DBTable",
                "annotations.database.Constraints",
                "annotations.database.SQLString",
                "annotations.database.SQLInteger",
                "annotations.database.SQLBoolean",
                "annotations.database.SQLDateTime",
                "annotations.database.SQLVarChar");
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.emptySet();
    }

    private class TableCreationProcessor implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        private String sql = "";
        TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        @Override
        public void process() {
            for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }

        private class TableCreationVisitor extends SimpleDeclarationVisitor {
            @Override
            public void visitClassDeclaration(ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if (dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length() < 1)
                            ? d.getSimpleName().toUpperCase()
                            : dbTable.name();
                    sql += " (";
                }
            }

            @Override
            public void visitFieldDeclaration(FieldDeclaration d) {
                String columnName = "";
                if (d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(SQLInteger.class);
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sInt.name();
                    sql += "\n    " + columnName + " INT" +
                            getConstraints(sInt.constraints()) + ",";
                }
                if (d.getAnnotation(SQLString.class) != null) {
                    SQLString sString = d.getAnnotation(SQLString.class);
                    // Use field name if name not specified
                    if (sString.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sString.name();
                    sql += "\n    " + columnName + " VARCHAR(" +
                            sString.value() + ")" +
                            getConstraints(sString.constraints()) + ",";
                }
                if (d.getAnnotation(SQLBoolean.class) != null) {
                    SQLBoolean sBool = d.getAnnotation(SQLBoolean.class);
                    if (sBool.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sBool.name();
                    sql += "\n    " + columnName + " BOOLEAN" +
                            getConstraints(sBool.constraints()) + ",";
                }
                if (d.getAnnotation(SQLDateTime.class) != null) {
                    SQLDateTime sDateTime = d.getAnnotation(SQLDateTime.class);
                    if (sDateTime.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sDateTime.name();
                    sql += "\n    " + columnName + " DATETIME" +
                            getConstraints(sDateTime.constraints()) + ",";
                }
                if (d.getAnnotation(SQLVarChar.class) != null) {
                    SQLVarChar sVarChar = d.getAnnotation(SQLVarChar.class);
                    if (sVarChar.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sVarChar.name();
                    sql += "\n    " + columnName + " VARCHAR(" +
                            sVarChar.value() + ")" +
                            getConstraints(sVarChar.constraints()) + ",";
                }
            }

            private String getConstraints(Constraints con) {
                String constraints = "";
                if (!con.allowNull())
                    constraints += " NOT NULL";
                if (con.primaryKey())
                    constraints += " PRIMARY KEY";
                if (con.unique())
                    constraints += " UNIQUE";
                return constraints;
            }
        }
    }
}