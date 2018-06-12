SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS itemss (
 id int PRIMARY KEY auto_increment,
 type VARCHAR,
 priceInCents int,
 discountAsPercentage int,
 description VARCHAR
);