--> findProductById
select * from product where id = ?;
--> insertNewProduct
insert into product (id, name, price) values ( (select max(id) from product)+1, ?, ?);
--> updateProductWithId
update product set name = ?, price = ? where id = ?;

