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

