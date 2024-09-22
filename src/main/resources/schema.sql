create schema netology;

create table netology.persons (
    id serial primary key,
    name VARCHAR(50) NOT NULL,
    surname varchar(50) not null,
    age integer not null,
    phone_number varchar(20),
    city_of_living varchar(50)
);

insert into netology.persons (NAME, SURNAME, AGE, PHONE_NUMBER, CITY_OF_LIVING)
 values ('Иван', 'Иванов', 25, '+79001234560', 'Москва'),
 ('Петр', 'Петров', 30, '+79001234561', 'Екатеринбург'),
 ('Сергей', 'Сергеев', 28, '+79001234562', 'Челябинск'),
 ('Дмитрий', 'Дмитров', 27, '+79001234563', 'Москва');

select * from netology.PERSONS
where CITY_OF_LIVING = 'Москва';


select *
from netology.persons
where age > 27
order by age desc;

select * from netology.persons;


SELECT *
FROM netology.persons A
WHERE LOWER(A.city_of_living) = LOWER(:city_of_living);

drop table netology.persons;

