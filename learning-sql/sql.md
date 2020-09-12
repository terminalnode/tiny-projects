# SQL Syntax
Commands in SQL have the form `SELECT some value(s) FROM some table(s) WHERE some condition(s)` (though there are other actions than select). Keywords are not case sensitive and we could write something like `sElEcT value(s) fRoM db(s) wHeRe cond(s)` if we really wanted to, often capital letters are used for keywords to make it easier to read. Sometimes lowercase is used to make it easier to type.

All white space is ignored (though necessary to separate parts of the statement), and statements end with a semicolon. Thus the first statement above could be written as:
```sql
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

# Aliases
We can use aliases in our query to give tables and columns different names. When looking through the db manually this can be used to give columns more human-readable titles and other pointless fluff. However it can also be used for things like defining temporary columns. For example we could do something like this to essentially get a column with employees full name from a database that only stores first and last name separately:
```sql
SELECT
    employee_id,
    concat(first_name, ' ', last_name) fullname
FROM
    employees e
```
Or if we want the full names and id numbers for the first 10 actors in the Sakila database:
`SELECT actor_id 'ID', concat(first_name, ' ', last_name) 'Full Name' FROM actor ORDER BY last_name ASC LIMIT 10;`

Optionally when using aliases we may use the keyword `AS`, such as in `actor_id AS 'ID'`.

These aliases are often used to simplify queries so we don't have to do as much typing. For example if we need to reference `departments` several times in a query it's easy to just give it the alias `d` (`departments d` or `departments AS d`).

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

## WHERE
Filters rows based on certain conditions. There really isn't much to say about this except that it looks like this:
`SELECT first_name, last_name FROM employees WHERE salary > 20e3;`

Really not much else to say about it.
