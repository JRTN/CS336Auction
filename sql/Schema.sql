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

CREATE TABLE IF NOT EXISTS top_bid (
	top_bid_id INT NOT NUll AUTO_INCREMENT,
    bid_id INT NOT NULL,
    auction_id INT NOT NULL,
    bid_amount DOUBLE NOT NULL,
    username_bidder VARCHAR(50) NOT NULL,
    PRIMARY KEY(top_bid_id),
    FOREIGN KEY(auction_id) REFERENCES auction(auction_id),
    FOREIGN KEY(username_bidder) REFERENCES users(username),
    FOREIGN KEY(bid_id) REFERENCES bid(bid_id)
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
    acknowledged TINYINT(1) NOT NULL,
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

CREATE EVENT IF NOT EXISTS
	close_event
ON SCHEDULE EVERY
	1 SECOND
DO
	UPDATE 
		auction a
	SET
		a.closed = 1
	WHERE
		close_date < NOW()
;

CREATE EVENT IF NOT EXISTS
	set_winner_event
ON SCHEDULE EVERY
	1 SECOND
DO
	UPDATE
		auction a
	SET
		a.username_won = 
        (
			SELECT
				b.username_bidder
			FROM
				bid b
			WHERE
				b.auction_id = a.auction_id
			ORDER BY 
				b.placed_date DESC
            LIMIT 1
        )
	WHERE
		a.closed = 1 AND
        a.current_price >= a.reserve_price
;

DELIMITER $$
CREATE TRIGGER
	set_currentPrice
AFTER INSERT ON
	bid
FOR EACH ROW
BEGIN
	UPDATE
		auction a
	SET
		a.current_price = NEW.amount
	WHERE
		a.auction_id = NEW.auction_id
	;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER
	set_alerts
AFTER INSERT ON
	bid
FOR EACH ROW
BEGIN
	UPDATE
		bid_alert ba
    SET
		ba.triggered = 1
	WHERE
		ba.auction_id = NEW.auction_id AND
        ba.triggered = 0 AND
        ba.acknowledged = 0 AND
        NEW.amount > (
			SELECT
				b.amount
			FROM
				bid b
			WHERE
				b.bid_id = ba.bid_id
        )
	;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER
	set_top_bid
AFTER INSERT ON
	bid
FOR EACH ROW
BEGIN
	IF EXISTS (SELECT * FROM top_bid WHERE auction_id = NEW.auction_id)
    THEN
		UPDATE 
			top_bid 
		SET 
			bid_id = NEW.bid_id,
            bid_amount = NEW.amount,
            username_bidder = NEW.username_bidder
		WHERE
			auction_id = NEW.auction_id
		;
	ELSE
		INSERT INTO top_bid
			(bid_id, auction_id, bid_amount, username_bidder)
		VALUES
			(NEW.bid_id, NEW.auction_id, NEW.amount, NEW.username_bidder)
		;
    END IF;
END
$$
DELIMITER ;

/*

DELIMITER $$
CREATE PROCEDURE update_autobid(new_auction_id INT, new_amount DOUBLE, new_username_bidder VARCHAR(50))
BEGIN
	DECLARE autobid_id INT DEFAULT 0;
	DECLARE auction_id INT DEFAULT 0;
    DECLARE username_bidder VARCHAR(50) DEFAULT '';
    DECLARE current_bid DOUBLE DEFAULT 0.0;
    DECLARE upper_limit DOUBLE DEFAULT 0.0;
    DECLARE increment DOUBLE DEFAULT 0.0;
    
	DECLARE cur_autobid CURSOR FOR SELECT ab.autobid_id, ab.auction_id, ab.username_bidder, ab.current_bid, ab.upper_limit, ab.increment FROM autobid ab WHERE ab.auction_id = new_auction_id;
    OPEN cur_autobid;
		insert_loop : LOOP
			FETCH
				cur_autobid
			INTO
				autobid_id,
				auction_id,
                username_bidder,
                current_bid,
                upper_limit,
                increment
			;
            IF new_amount > current_bid AND 
			   new_amount + increment <= upper_limit AND
               new_username_bidder <> username_bidder THEN
				INSERT INTO bid (auction_id, username_bidder, amount, placed_date) VALUES (auction_id, username_bidder, new_amount + increment, NOW());
				UPDATE autobid ab SET current_bid = new_amount + ab.increment WHERE ab.autobid_id = autobid_id;
            END IF
            ;
        END LOOP;
    CLOSE cur_autobid;
END
$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER
	do_autobid_oninsert
AFTER INSERT ON
	top_bid
FOR EACH ROW
BEGIN
	CALL update_autobid(NEW.auction_id, NEW.bid_amount, NEW.username_bidder);
END
$$
DELIMITER ;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER
	do_autobid_onupdate
AFTER UPDATE ON
	top_bid
FOR EACH ROW
BEGIN
	CALL update_autobid(NEW.auction_id, NEW.bid_amount, NEW.username_bidder);
END
$$
DELIMITER ;
END
$$
DELIMITER ;

*/

