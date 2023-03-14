package exercises.chapter20;

import annotations.database.Constraints;
import annotations.database.DBTable;
import annotations.database.SQLInteger;
import annotations.database.SQLString;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 1
 * Implements more SQL types in the database example.
 * {Args: exercises.chapter20.Member}
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLBoolean {
    String name() default "";
    Constraints constraints() default @Constraints;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLVarChar {
    int value();
    String name() default "";
    Constraints constraints() default @Constraints;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLDateTime {
    String name() default "";
    Constraints constraints() default @Constraints;
}

@DBTable(name = "MEMBER")
class Member {
    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;
    @SQLInteger
    Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String handle;

    @SQLBoolean
    Boolean active;

    @SQLDateTime
    String birthday;

    @SQLVarChar(70)
    String address;

    static int memberCount;

    public String getHandle() {
        return handle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean isActive() {
        return active;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return handle;
    }

    public Integer getAge() {
        return age;
    }
}

public class E01_TableCreator2 {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            // If the name is empty, use the Class name:
            if (tableName.length() < 1)
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1)
                    continue; // Not a db table column
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" +
                            getConstraints(sInt.constraints()));
                }
                if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified
                    if (sString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR(" +
                            sString.value() + ")" +
                            getConstraints(sString.constraints()));
                }
                if (anns[0] instanceof SQLBoolean) {
                    SQLBoolean sBool = (SQLBoolean) anns[0];
                    if (sBool.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sBool.name();
                    columnDefs.add(columnName + " BOOLEAN" +
                            getConstraints(sBool.constraints()));
                }
                if (anns[0] instanceof SQLDateTime) {
                    SQLDateTime sDateTime = (SQLDateTime) anns[0];
                    if (sDateTime.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sDateTime.name();
                    columnDefs.add(columnName + " DATETIME" +
                            getConstraints(sDateTime.constraints()));
                }
                if (anns[0] instanceof SQLVarChar) {
                    SQLVarChar sVarChar = (SQLVarChar) anns[0];
                    if (sVarChar.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sVarChar.name();
                    columnDefs.add(columnName + " VARCHAR(" +
                            sVarChar.value() + ")" +
                            getConstraints(sVarChar.constraints()));
                }
                StringBuilder createCommand = new StringBuilder(
                        "CREATE TABLE " + tableName + "(");
                for (String columnDef : columnDefs)
                    createCommand.append("\n    " + columnDef + ",");
                // Remove trailing comma
                String tableCreate = createCommand.substring(
                        0, createCommand.length() - 1) + ");";
                System.out.println("Table Creation SQL for " +
                        className + " is :\n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con) {
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
/* Output:
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30));
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50));
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT);
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT,
    HANDLE VARCHAR(30) PRIMARY KEY);
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT,
    HANDLE VARCHAR(30) PRIMARY KEY,
    ACTIVE BOOLEAN);
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT,
    HANDLE VARCHAR(30) PRIMARY KEY,
    ACTIVE BOOLEAN,
    BIRTHDAY DATETIME);
Table Creation SQL for exercises.chapter20.Member is :
CREATE TABLE MEMBER(
    FIRSTNAME VARCHAR(30),
    LASTNAME VARCHAR(50),
    AGE INT,
    HANDLE VARCHAR(30) PRIMARY KEY,
    ACTIVE BOOLEAN,
    BIRTHDAY DATETIME,
    ADDRESS VARCHAR(70));
 */