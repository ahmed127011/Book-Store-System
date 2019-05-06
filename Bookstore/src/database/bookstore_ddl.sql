create schema bookstore;

use bookstore;

create table publisher
(
  publisher_name varchar(45) primary key not null
);

create table publisher_addresses
(
  publisher_name varchar(45) primary key not null,
  address        varchar(45) primary key not null,
  constraint publisher_fk foreign key (publisher_name) references publisher (publisher_name),
);

create table publisher_phones
(
  publisher_name varchar(45) primary key not null,
  phone          varchar(20) primary key not null,
  constraint publisher_fk foreign key (publisher_name) references publisher (publisher_name),

);

create table category
(
  category_name varchar(30) primary key not null
);

create table book
(
  ISBN              integer primary key not null,
  title             varchar(45)         not null,
  price             integer default 0,
  publication_date  date,
  quantity          integer default 0,
  threshold         integer default 0,
  required_quantity integer default 0,
  category_name     varchar(30),
  publisher_name    varchar(45),
  constraint category_fk foreign key (category_name) references category (category_name),
  constraint publisher_fk foreign key (publisher_name) references publisher (publisher_name)
);

create table book_authors
(
  ISBN        integer primary key     not null,
  author_name varchar(45) primary key not null,
  constraint book_fk foreign key (ISBN) references book (ISBN)
);

create table library_orders
(
  order_id     integer primary key auto_increment not null,
  ISBN         integer,
  quantity     integer default 0,
  ordered_date date,
  confirmed    bit     default 0,
  constraint book_fk foreign key (ISBN) references book (ISBN)
);

create table user
(
  user_name  varchar(30) primary key not null,
  email      varchar(45) primary key not null,
  password   varchar(45)             not null,
  phone      varchar(20),
  first_name varchar(20),
  last_name  varchar(20),
  address    varchar(45),
  is_manger  bit default 0
);

create table user_orders
(
  ISBN           integer primary key     not null,
  user_name      varchar(30) primary key not null,
  email          varchar(30) primary key not null,
  quantity       integer default 0,
  check_out_date date,
  constraint book_fk foreign key (ISBN) references book (ISBN),
  constraint user_name_fk foreign key (user_name) references user (user_name),
  constraint user_email_fk foreign key (email) references user (email)
);
