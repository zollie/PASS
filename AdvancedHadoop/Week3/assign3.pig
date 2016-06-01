
prices = LOAD 'NYSE_daily_prices_N.csv' USING PigStorage(',') AS (exchange:chararray, stock_symbol:chararray, date:chararray, stock_price_open:float, stock_price_high:float, stock_price_low:float, stock_price_close:float, stock_volume:long, stock_price_adj_close:float);

--DESCRIBE prices;

dividends = LOAD 'NYSE_dividends_N.csv' USING PigStorage(',') AS (exchange:chararray, stock_symbol:chararray, date:chararray, dividends:float);

--DESCRIBE dividends;

prices_dividends = JOIN prices BY (stock_symbol, date), dividends BY (stock_symbol, date);

--DESCRIBE prices_dividends;
--DUMP prices_dividends;

--STORE prices_dividends INTO 'price_dividends';

price_change_dividends = FOREACH prices_dividends GENERATE prices::stock_symbol, prices::stock_price_open-prices::stock_price_close AS price_change, dividends::dividends AS dividends;

--DESCRIBE price_change_dividends;
--DUMP price_change_dividends;

filtered = FILTER price_change_dividends BY price_change >= 1.0 AND dividends >= .25;
--DESCRIBE filtered;
--DUMP filtered;

grouped = GROUP filtered by stock_symbol;
DESCRIBE grouped;
--DUMP grouped;

thecount = FOREACH grouped GENERATE group as stock_symbol, COUNT(filtered) as the_count; 

final = ORDER thecount BY the_count DESC;
DUMP final;

STORE final INTO 'assign3.out';