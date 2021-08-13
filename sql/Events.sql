USE auction_db;

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
		a.closed = 1
;