drop table financialinfo;
drop table userinfo;
drop table budgetinfo;

create table userinfo
(
  Username varchar(20),
  password varchar(20),
  tel varchar(15),
  
  primary key (Username),
  check(length(password)>6)
);
create table budgetinfo
(
   userid varchar(20) primary key not null,
   Ex_budget int,
   Edu_budget int,
   Live_budget int,
   shop_budget int,
   travel_budget int,
   check(Ex_budget>0),
   check(Edu_budget>0),
   check(Live_budget>0),
   check(shop_budget>0),
   check(travel_budget>0)
);

create table financialinfo
(
  id int auto_increment primary key not null,
  Time datetime,
  type varchar(50),
  divide int,
  num int,
  about varchar(50),
  userid varchar(20),
  
  check(divide=0 or divide=1),
  check(num>0)
);


