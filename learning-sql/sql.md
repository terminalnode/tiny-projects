# SQL Syntax
Commands in SQL have the form `SELECT some value(s) FROM some table(s) WHERE some condition(s)` (though there are other actions than select). Keywords are not case sensitive and we could write something like `sElEcT value(s) fRoM db(s) wHeRe cond(s)` if we really wanted to, often capital letters are used for keywords to make it easier to read. Sometimes lowercase is used to make it easier to type.

All white space is ignored (though necessary to separate parts of the statement), and statements end with a semicolon. Thus the first statement above could be written as:
```
SELECT
    some value(s)
FROM
    some table(s)
WHERE
    some condition(s)
```

Values are supposed not to be case-insensitive, but trying it out with the sample database below `select * from employees where first_name = 'John'` capitalization of `'jOhN'` makes no difference. It could be that this differs from database to database, this is when using MariaDB.

As demonstrated above, we can use `*` as a wild card as part of a query.

We have comments as well, comments are haskell style `--`, which is cool.

# Sample database
[Sample database via sqltutorial.org](http://www.sqltutorial.org/sql-sample-database/)

# Literals
SQL supports string, numberal and binary literals.

String literals are put inside single-quotes, e.g. `'one', 'two', 'three'`.

For numeric literals integers, decimals and scientific notations are supported, e.g. `10, -10, 10.0, 1E1`.

For binary, `x'0000'` where the zeroes are hexadecimal digits are used, e.g. `x'1010', x'f0f0'`.

# Statements
Bunch of statements (commands) with example uses.

## SELECT
Query data from a single table.

`SELECT column1, column2, column3 FROM table1`

## ORDER BY
Sort the result set based on a specified criteria in ascending or descending orders. When multiple criterias are specified entries will be sorted by the first primarily and the second secondarily.

It's possible to sort by alphabetical values, numerical values and dates.

`SELECT column1, column2 FROM table1 SORT BY column1 ASC, column2 DESC`

## DISTINCT
Remove duplicates from a selected set. Primary key ensures that there are no duplicate tables, however when picking your own columns etc you may get duplicate rows. For this to work the server needs to sort and scan through your query results, meaning performance is impaired.

`SELECT DISTINCT column1, column2 FROM table1`

## LIMIT and OFFSET
Constrain the number of rows returned by a `SELECT` statement. `OFFSET n` is an additional optional clause to skip the first n number of rows.

Remember to use `ORDER BY` in conjunction with `LIMIT` because otherwise the cut out results are essentially random (order added to database).

```sql
SELECT
    first_name, last_name
FROM
    employees
ORDER BY first_name
LIMIT 3 OFFSET 3;
```
This returns something like:
~~Adam Fripp~~ (offset)
~~Alexander Hurnold~~ (offset)
~~Alexander Khoo~~ (offset)
Britney Everett
Bruce Ernst
Charles Johnson
~~(everything else)~~ (limit)

`LIMIT` is supported by most SQL databases, but it is *not standard SQL*.

## FETCH
Very similar to `LIMIT`, except `LIMIT` is standard SQL. Syntax looks like this, where offset\_rows and fetch\_rows are integer numbers and words in curly brackets are synonymous.
```sql
OFFSET offset_rows {ROW | ROWS}
FETCH {FIRST | NEXT} fetch_rows {ROW | ROWS} ONLY
```
an actual example may look like this:
```sql
SELECT DISTINCT
    first_name
FROM
    employees
ORDER BY
    first_name ASC
OFFSET 1 ROW          -- (or OFFSET 1 ROWS ONLY)
FETCH NEXT 1 ROW ONLY -- (or FETCH FIRST 1 ROWS ONLY)
```

## WHERE, comparison operators and logical operators
Filters rows based on certain conditions. There really isn't much to say about this except that it looks like this:
`SELECT first_name, last_name FROM employees WHERE salary > 20e3;`

Here are the conditional operators we can use with it:
```
=   Equal to
<>  Not equal to
!=  Also not equal to
<   Less than
>   More than
<=  Less than or equal to
>=  More than or equal to
```
Note that SQL understands dates, so we can do stuff like `SELECT * FROM employees WHERE hire_date > '1997-01-01'`. Pretty cool. And a little strange.

Here are the logical operators we can use with it:
```
EXISTS(subquery)    True if subquery contains any rows.
a > ALL(subquery)   True if a is greater than all of the values returned by subquery.
a > ANY(subquery)   True if a is greater than any of the values returned by subquery.
a > SOME(subquery)  Alias of ANY.
a AND b             True if a and b evaluate to true.
a BETWEEN b AND c   True if a is between b and c in value.
a IN (b,c,d)        True if a is equal to an element of a list (b, c or d).
a LIKE 'pattern'    True if a matches pattern (see below).
NOT a               Reverse result of a boolean value a.
a OR b              True if a is true or b is true.
a IS NULL           True if a is NULL (note that a = NULL is always false!)
a IS NOT NULL       True if a is not NULL (duh).
```
The pattern for `LIKE` is kind of like regex except we use `_` for any single character (instead of `.`) and `%` for zero or more characters (sort of like `*`)... and we seem to have nothing else. It only matches full strings, so in regex terms `^` and `$` is applied to the ends of the pattern.

The truth tables are about what you'd expect but may behave strangely with NULL. Here are truth tables for NULL OPERATOR TOP\_ROW:
```
    TRUE    FALSE   NULL
AND NULL    FALSE   NULL
OR  TRUE    NULL    NULL
```

Some databases may use short-circuit evaluation, MariaDB is not one of them. In such databases `WHERE 1 = 1 AND 1 / 0` would evaluate to true, while in MariaDB it evaluates as false with warnings.
