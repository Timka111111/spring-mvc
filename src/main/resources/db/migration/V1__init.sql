create table if not exists students (id bigserial primary key, name varchar(255), score int, secret_key varchar(512));

insert into students (name, score, secret_key)
values
('Bob', 100, 'dgdg44tgrg45g'),
('Jack', 80, 'dgdg44asdasdtgrg45g'),
('John', 90, 'asafaefae'),
('Ben', 10, 'asafaefae'),
('Alex', 95, 'asafaefae'),
('John', 80, '6drg'),
('Jenny', 75, 'asafaeregfae'),
('Margaret', 100, 'asaf34aefae');