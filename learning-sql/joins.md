# JOIN statements
There seem to be a lot of these and they seem slightly more complex than most other stuff so far, so another file it is!

## INNER JOIN
Queries data from more than one table at a time. Or more accurately, it smashes two tables together based on matching fields.

For example, this querry joins the tables `employees` and `departments` on basis on the content of column `department_id`. In effect printing both of departments columns on every row in the `employees` table where it matches on `department_id`.

```sql
SELECT *
FROM employees e
INNER JOIN departments d ON d.department_id = e.department_id;
```

If we want to select fields we can use dot notation to say which table the field is in. This is only required when there is a naming conflict, however it might be good practice to always do it just in case. Maybe new columns get added and then you'll have a naming conflict on your hand or something. That would suck.
