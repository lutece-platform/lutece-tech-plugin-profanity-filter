
--
-- Structure for table profanityfilter_word
--

DROP TABLE IF EXISTS profanityfilter_word;
CREATE TABLE profanityfilter_word (
id_word int(6) NOT NULL,
value varchar(50) NOT NULL default '',
PRIMARY KEY (id_word)
);

DROP TABLE IF EXISTS profanityfilter_counter;
CREATE TABLE profanityfilter_counter (
id_counter int(6) NOT NULL,
resouece_type varchar(50) NOT NULL default '',
counter int (12) NULL,

PRIMARY KEY (id_counter)
);
