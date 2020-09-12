# Comparison operators and logical operators
This section got a little long-winded, so I split it into another document for ease of access.

Here are the conditional operators:
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

Here are the logical operators:
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
