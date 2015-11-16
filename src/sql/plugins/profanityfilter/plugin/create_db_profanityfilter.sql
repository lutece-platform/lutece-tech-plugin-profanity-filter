
--
-- Structure for table profanityfilter_word
--

DROP TABLE IF EXISTS profanityfilter_word;
CREATE TABLE profanityfilter_word (
id_word int(6) NOT NULL,
value varchar(50) NOT NULL default '',
PRIMARY KEY (id_word)
);
