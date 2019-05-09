drop schema bookstore;

create schema if not exists bookstore;

use bookstore;

create table if not exists publisher
(
  publisher_name varchar(45) primary key not null
);

create table if not exists publisher_addresses
(
  publisher_name varchar(45) not null,
  address        varchar(45) not null,

  primary key (publisher_name, address),
  constraint publisher_address_fk
    foreign key (publisher_name) references publisher (publisher_name) on update cascade on delete cascade
);

create table if not exists publisher_phones
(
  publisher_name varchar(45) not null,
  phone          varchar(20) not null,

  primary key (publisher_name, phone),
  constraint publisher_phone_fk
    foreign key (publisher_name) references publisher (publisher_name) on update cascade on delete cascade
);

create table if not exists category
(
  category_name varchar(30) primary key not null
);

create table if not exists book
(
  ISBN              varchar(15) primary key not null,
  title             varchar(45)             not null,
  price             int default 0,
  publication_date  date,
  quantity          int default 0,
  threshold         int default 0,
  required_quantity int default 0,
  category_name     varchar(30),
  publisher_name    varchar(45),

  constraint category_fk foreign key (category_name) references category (category_name),
  constraint publisher_fk foreign key (publisher_name) references publisher (publisher_name),
  constraint check_quantity check (quantity >= 0),
  constraint check_required_quantity check (required_quantity >= threshold),

  index isbn_index (ISBN),
  index title_index (title),
  index category_index (category_name)
);

create table if not exists book_authors
(
  ISBN        varchar(15) not null,
  author_name varchar(45) not null,

  primary key (ISBN, author_name),
  constraint book_author_fk
    foreign key (ISBN) references book (ISBN) on update cascade on delete cascade
);

create table if not exists library_orders
(
  order_id     integer primary key auto_increment not null,
  ISBN         varchar(15),
  quantity     int default 0,
  ordered_date date,
  confirmed    bit default 0,

  constraint book_order_fk
    foreign key (ISBN) references book (ISBN) on update cascade on delete cascade,

  index isbn_index (ISBN)
);

drop table user;
drop table user_orders;

create table if not exists user
(
  user_name  varchar(30) not null,
  email      varchar(45) not null unique,
  password   varchar(45) not null,
  phone      varchar(20),
  first_name varchar(20),
  last_name  varchar(20),
  address    varchar(45),
  is_manger  bit default 0,

  primary key (user_name)
);

create table if not exists user_orders
(
  order_id       int         not null auto_increment,
  ISBN           varchar(15) not null,
  user_name      varchar(30) not null,
  quantity       int default 0,
  check_out_date date,

  primary key (order_id, ISBN, user_name),

  constraint ordered_book_fk
    foreign key (ISBN) references book (ISBN) on update cascade on delete cascade,
  constraint user_fk
    foreign key (user_name) references user (user_name) on update cascade on delete cascade,

  index isbn_index (ISBN),
  index user_index (user_name)
);

set foreign_key_checks = 0;

set foreign_key_checks = 1;

set sql_safe_updates = 0;

set global event_scheduler = on;

delimiter $$

create trigger place_library_order
  after update
  on book
  for each row
begin
  if (NEW.quantity < NEW.threshold and OLD.quantity >= OLD.threshold)
  then
    insert into library_orders (ISBN, quantity, ordered_date, confirmed)
    values (NEW.ISBN, NEW.required_quantity, curdate(), 0);
  end if;
end $$

create trigger confirm_library_order
  after update
  on library_orders
  for each row
begin
  if (NEW.confirmed)
  then
    update book as b
    set b.quantity = b.quantity + b.required_quantity
    where b.ISBN = NEW.ISBN;
  end if;
end $$

create trigger confirm_user_order
  before insert
  on user_orders
  for each row
begin
  update book
  set book.quantity = book.quantity - NEW.quantity
  where book.ISBN = NEW.ISBN;
end $$

create event delete_old_orders
  on schedule every 1 day
  do
  delete
  from user_orders
  where check_out_date < date_sub(now(), interval 3 month);
$$

create procedure get_total_sales()
begin
  select sum(book.price * user_orders.quantity) as total_sales
  from user_orders
         natural join book
  where user_orders.check_out_date < date_sub(now(), interval 1 month);
end $$


delimiter ;

insert into category (category_name)
values ('Science');

insert into category (category_name)
values ('Art');

insert into category (category_name)
values ('Religion');

insert into category (category_name)
values ('History');

insert into category (category_name)
values ('Geography');

insert into user
values ('Khaled', 'k@k.com', '0000', '01201244416', 'Khaled', 'Abd', 'bla', 1);
