
CREATE TABLE category (
    category_id INTEGER NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(128) NOT NULL,
    parent_category_id INTEGER NULL,

    PRIMARY KEY (category_id)
);

CREATE TABLE merchant (
    merchant_id INTEGER NOT NULL AUTO_INCREMENT,
    merchant_name VARCHAR(128) NOT NULL,
    description VARCHAR(MAX) null,
    
    PRIMARY KEY (merchant_id)
);

CREATE TABLE product (
    product_id INTEGER NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(128),
    url VARCHAR(256) NOT NULL,
    image VARCHAR(MAX) NOT NULL,
    price double NOT NULL,
    msrp double NOT NULL,
    product_availability bit NOT NULL,
    description VARCHAR(MAX) NULL,
    product_type VARCHAR(128) NULL,
    
    merchant_id INTEGER ,
    foreign key (merchant_id) references merchant(merchant_id),
    
    category_id INTEGER ,
    foreign key (category_id) references category(category_id),
    
    PRIMARY KEY (product_id)
);