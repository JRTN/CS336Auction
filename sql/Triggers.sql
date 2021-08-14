USE auction_db;

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
