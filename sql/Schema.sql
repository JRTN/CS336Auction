CREATE DATABASE IF NOT EXISTS auction_db;

USE auction_db;

CREATE TABLE IF NOT EXISTS users (
	username VARCHAR(50) NOT NULL,
    userpass VARCHAR(50) NOT NULL,
    useremail VARCHAR(50) NOT NULL,
    irlname VARCHAR(50) NOT NULL,
    userrole ENUM('ADMIN', 'REP', 'USER'),
    UNIQUE(username),
    PRIMARY KEY(username)
);

INSERT INTO users
	(username, userpass, useremail, irlname, userrole)
VALUES
	('administrator', 'password', 'admin@auction.rut', 'John Russell', 'ADMIN'),
    ('representative', 'password', 'rep@auction.rut', 'John Russell', 'REP')
;

CREATE TABLE IF NOT EXISTS book (
	isbn CHAR(13) NOT NULL,
    title VARCHAR(50) NOT NULL,
    subcategory ENUM('FICTION', 'NONFICTION', 'MAGAZINE') NOT NULL,
    pages INT NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    publication_date DATE NOT NULL,
    PRIMARY KEY(isbn)
);

CREATE TABLE IF NOT EXISTS auction (
	auction_id INT NOT NULL AUTO_INCREMENT,
    item_isbn CHAR(13) NOT NULL,
    username_created VARCHAR(50) NOT NULL,
    username_won VARCHAR(50),
    start_price DOUBLE NOT NULL,
    reserve_price DOUBLE NOT NULL,
    current_price DOUBLE NOT NULL,
    open_date DATETIME NOT NULL,
    close_date DATETIME NOT NULL,
    closed TINYINT(1) NOT NULL,
    PRIMARY KEY(auction_id),
    FOREIGN KEY(username_created) REFERENCES users(username),
    FOREIGN KEY(username_won) REFERENCES users(username),
    FOREIGN KEY(item_isbn) REFERENCES book(isbn)
);

-- use question_id and username_posted as PK so that qid is unique to usernames, not table as a whole
CREATE TABLE IF NOT EXISTS question (
	question_id INT NOT NULL AUTO_INCREMENT,
    username_posted VARCHAR(50) NOT NULL,
    title VARCHAR(50) NOT NULL,
    body VARCHAR(255) NOT NULL,
    posted_date DATETIME NOT NULL,
    PRIMARY KEY(question_id, username_posted),
    FOREIGN KEY(username_posted) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS reply (
	reply_id INT NOT NULL AUTO_INCREMENT,
	question_id INT NOT NULL,
    asker_username VARCHAR(50) NOT NULL,
    responder_username VARCHAR(50) NOT NULL,
    posted_date DATETIME NOT NULL,
    content VARCHAR(255) NOT NULL,
    PRIMARY KEY(reply_id, question_id, asker_username, responder_username),
    FOREIGN KEY(asker_username) REFERENCES users(username),
    FOREIGN KEY(responder_username) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS bid (
	bid_id INT NOT NULL AUTO_INCREMENT,
    auction_id INT NOT NULL,
    username_bidder VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL,
    placed_date DATETIME NOT NULL,
    PRIMARY KEY(bid_id, auction_id),
    FOREIGN KEY(auction_id) REFERENCES auction(auction_id),
    FOREIGN KEY(username_bidder) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS autobid (
	autobid_id INT NOT NULL AUTO_INCREMENT,
    auction_id INT NOT NULL,
    username_bidder VARCHAR(50) NOT NULL,
    current_bid DOUBLE NOT NULL,
    upper_limit DOUBLE NOT NULL,
    increment DOUBLE NOT NULL,
    PRIMARY KEY(autobid_id), 
    FOREIGN KEY(auction_id) REFERENCES auction(auction_id),
    FOREIGN KEY(username_bidder) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS bid_alert (
	alert_id INT NOT NULL AUTO_INCREMENT,
    auction_id INT NOT NULL,
    bid_id INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    created_date DATETIME NOT NULL,
    triggered TINYINT(1) NOT NULL,
    PRIMARY KEY(alert_id, auction_id, bid_id),
    FOREIGN KEY(auction_id) REFERENCES auction(auction_id),
    FOREIGN KEY(bid_id) REFERENCES bid(bid_id),
    FOREIGN KEY(username) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS stock_alert (
	alert_id INT NOT NULL AUTO_INCREMENT,
    item_isbn CHAR(13) NOT NULL,
    username VARCHAR(50) NOT NULL,
    created_date DATETIME NOT NULL,
    triggered TINYINT(1) NOT NULL,
    PRIMARY KEY(alert_id, item_isbn, created_date),
    FOREIGN KEY(item_isbn) REFERENCES book(isbn),
    FOREIGN KEY(username) REFERENCES users(username)
);
