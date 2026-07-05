create table Product (
	id serial unique not null,
   	name char(50) not null,
    price integer check(price > -1)                 
);

insert into Product (name, price) values ('Milk', 100), ('Apple', 150), 
	('Juice', 200), ('Meat', 500);

select * from Product;