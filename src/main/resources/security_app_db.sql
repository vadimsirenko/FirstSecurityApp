drop table if exists Person;

create table Person(
                       id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       username varchar(100) NOT NULL,
                       year_of_birth int,
                       password varchar
);

truncate table Person;

insert into Person(username, year_of_birth, password)
VALUES ('vadim', 1973, '$2a$10$Y1D4pH6lbvuS35DXnUSGPuYmrTjtZxXPifRr97nMtU2BQ.bwnMo0G'),
       ('elena', 1976, 'P@ssw0rd'),
       ('kirill', 1974, 'P@ssw0rd'),
       ('sergey', 1982, 'P@ssw0rd');

select * from Person;